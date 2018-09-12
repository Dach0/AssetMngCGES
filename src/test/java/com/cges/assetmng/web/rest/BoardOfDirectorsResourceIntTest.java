package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.BoardOfDirectors;
import com.cges.assetmng.repository.BoardOfDirectorsRepository;
import com.cges.assetmng.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.cges.assetmng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BoardOfDirectorsResource REST controller.
 *
 * @see BoardOfDirectorsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class BoardOfDirectorsResourceIntTest {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BoardOfDirectorsRepository boardOfDirectorsRepository;
    @Mock
    private BoardOfDirectorsRepository boardOfDirectorsRepositoryMock;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBoardOfDirectorsMockMvc;

    private BoardOfDirectors boardOfDirectors;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BoardOfDirectorsResource boardOfDirectorsResource = new BoardOfDirectorsResource(boardOfDirectorsRepository);
        this.restBoardOfDirectorsMockMvc = MockMvcBuilders.standaloneSetup(boardOfDirectorsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoardOfDirectors createEntity(EntityManager em) {
        BoardOfDirectors boardOfDirectors = new BoardOfDirectors()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return boardOfDirectors;
    }

    @Before
    public void initTest() {
        boardOfDirectors = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoardOfDirectors() throws Exception {
        int databaseSizeBeforeCreate = boardOfDirectorsRepository.findAll().size();

        // Create the BoardOfDirectors
        restBoardOfDirectorsMockMvc.perform(post("/api/board-of-directors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardOfDirectors)))
            .andExpect(status().isCreated());

        // Validate the BoardOfDirectors in the database
        List<BoardOfDirectors> boardOfDirectorsList = boardOfDirectorsRepository.findAll();
        assertThat(boardOfDirectorsList).hasSize(databaseSizeBeforeCreate + 1);
        BoardOfDirectors testBoardOfDirectors = boardOfDirectorsList.get(boardOfDirectorsList.size() - 1);
        assertThat(testBoardOfDirectors.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBoardOfDirectors.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createBoardOfDirectorsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boardOfDirectorsRepository.findAll().size();

        // Create the BoardOfDirectors with an existing ID
        boardOfDirectors.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoardOfDirectorsMockMvc.perform(post("/api/board-of-directors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardOfDirectors)))
            .andExpect(status().isBadRequest());

        // Validate the BoardOfDirectors in the database
        List<BoardOfDirectors> boardOfDirectorsList = boardOfDirectorsRepository.findAll();
        assertThat(boardOfDirectorsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBoardOfDirectors() throws Exception {
        // Initialize the database
        boardOfDirectorsRepository.saveAndFlush(boardOfDirectors);

        // Get all the boardOfDirectorsList
        restBoardOfDirectorsMockMvc.perform(get("/api/board-of-directors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boardOfDirectors.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    public void getAllBoardOfDirectorsWithEagerRelationshipsIsEnabled() throws Exception {
        BoardOfDirectorsResource boardOfDirectorsResource = new BoardOfDirectorsResource(boardOfDirectorsRepositoryMock);
        when(boardOfDirectorsRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restBoardOfDirectorsMockMvc = MockMvcBuilders.standaloneSetup(boardOfDirectorsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBoardOfDirectorsMockMvc.perform(get("/api/board-of-directors?eagerload=true"))
        .andExpect(status().isOk());

        verify(boardOfDirectorsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllBoardOfDirectorsWithEagerRelationshipsIsNotEnabled() throws Exception {
        BoardOfDirectorsResource boardOfDirectorsResource = new BoardOfDirectorsResource(boardOfDirectorsRepositoryMock);
            when(boardOfDirectorsRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restBoardOfDirectorsMockMvc = MockMvcBuilders.standaloneSetup(boardOfDirectorsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBoardOfDirectorsMockMvc.perform(get("/api/board-of-directors?eagerload=true"))
        .andExpect(status().isOk());

            verify(boardOfDirectorsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getBoardOfDirectors() throws Exception {
        // Initialize the database
        boardOfDirectorsRepository.saveAndFlush(boardOfDirectors);

        // Get the boardOfDirectors
        restBoardOfDirectorsMockMvc.perform(get("/api/board-of-directors/{id}", boardOfDirectors.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boardOfDirectors.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBoardOfDirectors() throws Exception {
        // Get the boardOfDirectors
        restBoardOfDirectorsMockMvc.perform(get("/api/board-of-directors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoardOfDirectors() throws Exception {
        // Initialize the database
        boardOfDirectorsRepository.saveAndFlush(boardOfDirectors);

        int databaseSizeBeforeUpdate = boardOfDirectorsRepository.findAll().size();

        // Update the boardOfDirectors
        BoardOfDirectors updatedBoardOfDirectors = boardOfDirectorsRepository.findById(boardOfDirectors.getId()).get();
        // Disconnect from session so that the updates on updatedBoardOfDirectors are not directly saved in db
        em.detach(updatedBoardOfDirectors);
        updatedBoardOfDirectors
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);

        restBoardOfDirectorsMockMvc.perform(put("/api/board-of-directors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBoardOfDirectors)))
            .andExpect(status().isOk());

        // Validate the BoardOfDirectors in the database
        List<BoardOfDirectors> boardOfDirectorsList = boardOfDirectorsRepository.findAll();
        assertThat(boardOfDirectorsList).hasSize(databaseSizeBeforeUpdate);
        BoardOfDirectors testBoardOfDirectors = boardOfDirectorsList.get(boardOfDirectorsList.size() - 1);
        assertThat(testBoardOfDirectors.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBoardOfDirectors.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBoardOfDirectors() throws Exception {
        int databaseSizeBeforeUpdate = boardOfDirectorsRepository.findAll().size();

        // Create the BoardOfDirectors

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBoardOfDirectorsMockMvc.perform(put("/api/board-of-directors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardOfDirectors)))
            .andExpect(status().isBadRequest());

        // Validate the BoardOfDirectors in the database
        List<BoardOfDirectors> boardOfDirectorsList = boardOfDirectorsRepository.findAll();
        assertThat(boardOfDirectorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBoardOfDirectors() throws Exception {
        // Initialize the database
        boardOfDirectorsRepository.saveAndFlush(boardOfDirectors);

        int databaseSizeBeforeDelete = boardOfDirectorsRepository.findAll().size();

        // Get the boardOfDirectors
        restBoardOfDirectorsMockMvc.perform(delete("/api/board-of-directors/{id}", boardOfDirectors.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BoardOfDirectors> boardOfDirectorsList = boardOfDirectorsRepository.findAll();
        assertThat(boardOfDirectorsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoardOfDirectors.class);
        BoardOfDirectors boardOfDirectors1 = new BoardOfDirectors();
        boardOfDirectors1.setId(1L);
        BoardOfDirectors boardOfDirectors2 = new BoardOfDirectors();
        boardOfDirectors2.setId(boardOfDirectors1.getId());
        assertThat(boardOfDirectors1).isEqualTo(boardOfDirectors2);
        boardOfDirectors2.setId(2L);
        assertThat(boardOfDirectors1).isNotEqualTo(boardOfDirectors2);
        boardOfDirectors1.setId(null);
        assertThat(boardOfDirectors1).isNotEqualTo(boardOfDirectors2);
    }
}
