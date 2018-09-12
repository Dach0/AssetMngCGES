package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.TitleInBoard;
import com.cges.assetmng.repository.TitleInBoardRepository;
import com.cges.assetmng.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.cges.assetmng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TitleInBoardResource REST controller.
 *
 * @see TitleInBoardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TitleInBoardResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private TitleInBoardRepository titleInBoardRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTitleInBoardMockMvc;

    private TitleInBoard titleInBoard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TitleInBoardResource titleInBoardResource = new TitleInBoardResource(titleInBoardRepository);
        this.restTitleInBoardMockMvc = MockMvcBuilders.standaloneSetup(titleInBoardResource)
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
    public static TitleInBoard createEntity(EntityManager em) {
        TitleInBoard titleInBoard = new TitleInBoard()
            .title(DEFAULT_TITLE);
        return titleInBoard;
    }

    @Before
    public void initTest() {
        titleInBoard = createEntity(em);
    }

    @Test
    @Transactional
    public void createTitleInBoard() throws Exception {
        int databaseSizeBeforeCreate = titleInBoardRepository.findAll().size();

        // Create the TitleInBoard
        restTitleInBoardMockMvc.perform(post("/api/title-in-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(titleInBoard)))
            .andExpect(status().isCreated());

        // Validate the TitleInBoard in the database
        List<TitleInBoard> titleInBoardList = titleInBoardRepository.findAll();
        assertThat(titleInBoardList).hasSize(databaseSizeBeforeCreate + 1);
        TitleInBoard testTitleInBoard = titleInBoardList.get(titleInBoardList.size() - 1);
        assertThat(testTitleInBoard.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createTitleInBoardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = titleInBoardRepository.findAll().size();

        // Create the TitleInBoard with an existing ID
        titleInBoard.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTitleInBoardMockMvc.perform(post("/api/title-in-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(titleInBoard)))
            .andExpect(status().isBadRequest());

        // Validate the TitleInBoard in the database
        List<TitleInBoard> titleInBoardList = titleInBoardRepository.findAll();
        assertThat(titleInBoardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTitleInBoards() throws Exception {
        // Initialize the database
        titleInBoardRepository.saveAndFlush(titleInBoard);

        // Get all the titleInBoardList
        restTitleInBoardMockMvc.perform(get("/api/title-in-boards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(titleInBoard.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    

    @Test
    @Transactional
    public void getTitleInBoard() throws Exception {
        // Initialize the database
        titleInBoardRepository.saveAndFlush(titleInBoard);

        // Get the titleInBoard
        restTitleInBoardMockMvc.perform(get("/api/title-in-boards/{id}", titleInBoard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(titleInBoard.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTitleInBoard() throws Exception {
        // Get the titleInBoard
        restTitleInBoardMockMvc.perform(get("/api/title-in-boards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTitleInBoard() throws Exception {
        // Initialize the database
        titleInBoardRepository.saveAndFlush(titleInBoard);

        int databaseSizeBeforeUpdate = titleInBoardRepository.findAll().size();

        // Update the titleInBoard
        TitleInBoard updatedTitleInBoard = titleInBoardRepository.findById(titleInBoard.getId()).get();
        // Disconnect from session so that the updates on updatedTitleInBoard are not directly saved in db
        em.detach(updatedTitleInBoard);
        updatedTitleInBoard
            .title(UPDATED_TITLE);

        restTitleInBoardMockMvc.perform(put("/api/title-in-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTitleInBoard)))
            .andExpect(status().isOk());

        // Validate the TitleInBoard in the database
        List<TitleInBoard> titleInBoardList = titleInBoardRepository.findAll();
        assertThat(titleInBoardList).hasSize(databaseSizeBeforeUpdate);
        TitleInBoard testTitleInBoard = titleInBoardList.get(titleInBoardList.size() - 1);
        assertThat(testTitleInBoard.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTitleInBoard() throws Exception {
        int databaseSizeBeforeUpdate = titleInBoardRepository.findAll().size();

        // Create the TitleInBoard

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTitleInBoardMockMvc.perform(put("/api/title-in-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(titleInBoard)))
            .andExpect(status().isBadRequest());

        // Validate the TitleInBoard in the database
        List<TitleInBoard> titleInBoardList = titleInBoardRepository.findAll();
        assertThat(titleInBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTitleInBoard() throws Exception {
        // Initialize the database
        titleInBoardRepository.saveAndFlush(titleInBoard);

        int databaseSizeBeforeDelete = titleInBoardRepository.findAll().size();

        // Get the titleInBoard
        restTitleInBoardMockMvc.perform(delete("/api/title-in-boards/{id}", titleInBoard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TitleInBoard> titleInBoardList = titleInBoardRepository.findAll();
        assertThat(titleInBoardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TitleInBoard.class);
        TitleInBoard titleInBoard1 = new TitleInBoard();
        titleInBoard1.setId(1L);
        TitleInBoard titleInBoard2 = new TitleInBoard();
        titleInBoard2.setId(titleInBoard1.getId());
        assertThat(titleInBoard1).isEqualTo(titleInBoard2);
        titleInBoard2.setId(2L);
        assertThat(titleInBoard1).isNotEqualTo(titleInBoard2);
        titleInBoard1.setId(null);
        assertThat(titleInBoard1).isNotEqualTo(titleInBoard2);
    }
}
