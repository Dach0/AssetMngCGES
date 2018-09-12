package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.BoardMember;
import com.cges.assetmng.repository.BoardMemberRepository;
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
 * Test class for the BoardMemberResource REST controller.
 *
 * @see BoardMemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class BoardMemberResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private BoardMemberRepository boardMemberRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBoardMemberMockMvc;

    private BoardMember boardMember;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BoardMemberResource boardMemberResource = new BoardMemberResource(boardMemberRepository);
        this.restBoardMemberMockMvc = MockMvcBuilders.standaloneSetup(boardMemberResource)
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
    public static BoardMember createEntity(EntityManager em) {
        BoardMember boardMember = new BoardMember()
            .name(DEFAULT_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .picture(DEFAULT_PICTURE);
        return boardMember;
    }

    @Before
    public void initTest() {
        boardMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoardMember() throws Exception {
        int databaseSizeBeforeCreate = boardMemberRepository.findAll().size();

        // Create the BoardMember
        restBoardMemberMockMvc.perform(post("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardMember)))
            .andExpect(status().isCreated());

        // Validate the BoardMember in the database
        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeCreate + 1);
        BoardMember testBoardMember = boardMemberList.get(boardMemberList.size() - 1);
        assertThat(testBoardMember.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBoardMember.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testBoardMember.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testBoardMember.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testBoardMember.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createBoardMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boardMemberRepository.findAll().size();

        // Create the BoardMember with an existing ID
        boardMember.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoardMemberMockMvc.perform(post("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardMember)))
            .andExpect(status().isBadRequest());

        // Validate the BoardMember in the database
        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = boardMemberRepository.findAll().size();
        // set the field null
        boardMember.setName(null);

        // Create the BoardMember, which fails.

        restBoardMemberMockMvc.perform(post("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardMember)))
            .andExpect(status().isBadRequest());

        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = boardMemberRepository.findAll().size();
        // set the field null
        boardMember.setLastName(null);

        // Create the BoardMember, which fails.

        restBoardMemberMockMvc.perform(post("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardMember)))
            .andExpect(status().isBadRequest());

        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBoardMembers() throws Exception {
        // Initialize the database
        boardMemberRepository.saveAndFlush(boardMember);

        // Get all the boardMemberList
        restBoardMemberMockMvc.perform(get("/api/board-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boardMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getBoardMember() throws Exception {
        // Initialize the database
        boardMemberRepository.saveAndFlush(boardMember);

        // Get the boardMember
        restBoardMemberMockMvc.perform(get("/api/board-members/{id}", boardMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boardMember.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBoardMember() throws Exception {
        // Get the boardMember
        restBoardMemberMockMvc.perform(get("/api/board-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoardMember() throws Exception {
        // Initialize the database
        boardMemberRepository.saveAndFlush(boardMember);

        int databaseSizeBeforeUpdate = boardMemberRepository.findAll().size();

        // Update the boardMember
        BoardMember updatedBoardMember = boardMemberRepository.findById(boardMember.getId()).get();
        // Disconnect from session so that the updates on updatedBoardMember are not directly saved in db
        em.detach(updatedBoardMember);
        updatedBoardMember
            .name(UPDATED_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .picture(UPDATED_PICTURE);

        restBoardMemberMockMvc.perform(put("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBoardMember)))
            .andExpect(status().isOk());

        // Validate the BoardMember in the database
        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeUpdate);
        BoardMember testBoardMember = boardMemberList.get(boardMemberList.size() - 1);
        assertThat(testBoardMember.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBoardMember.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testBoardMember.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testBoardMember.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testBoardMember.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingBoardMember() throws Exception {
        int databaseSizeBeforeUpdate = boardMemberRepository.findAll().size();

        // Create the BoardMember

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBoardMemberMockMvc.perform(put("/api/board-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boardMember)))
            .andExpect(status().isBadRequest());

        // Validate the BoardMember in the database
        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBoardMember() throws Exception {
        // Initialize the database
        boardMemberRepository.saveAndFlush(boardMember);

        int databaseSizeBeforeDelete = boardMemberRepository.findAll().size();

        // Get the boardMember
        restBoardMemberMockMvc.perform(delete("/api/board-members/{id}", boardMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BoardMember> boardMemberList = boardMemberRepository.findAll();
        assertThat(boardMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoardMember.class);
        BoardMember boardMember1 = new BoardMember();
        boardMember1.setId(1L);
        BoardMember boardMember2 = new BoardMember();
        boardMember2.setId(boardMember1.getId());
        assertThat(boardMember1).isEqualTo(boardMember2);
        boardMember2.setId(2L);
        assertThat(boardMember1).isNotEqualTo(boardMember2);
        boardMember1.setId(null);
        assertThat(boardMember1).isNotEqualTo(boardMember2);
    }
}
