package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.GroundingSticks;
import com.cges.assetmng.repository.GroundingSticksRepository;
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
 * Test class for the GroundingSticksResource REST controller.
 *
 * @see GroundingSticksResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class GroundingSticksResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final Integer DEFAULT_CURRENT = 1;
    private static final Integer UPDATED_CURRENT = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private GroundingSticksRepository groundingSticksRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGroundingSticksMockMvc;

    private GroundingSticks groundingSticks;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroundingSticksResource groundingSticksResource = new GroundingSticksResource(groundingSticksRepository);
        this.restGroundingSticksMockMvc = MockMvcBuilders.standaloneSetup(groundingSticksResource)
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
    public static GroundingSticks createEntity(EntityManager em) {
        GroundingSticks groundingSticks = new GroundingSticks()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .current(DEFAULT_CURRENT)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return groundingSticks;
    }

    @Before
    public void initTest() {
        groundingSticks = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroundingSticks() throws Exception {
        int databaseSizeBeforeCreate = groundingSticksRepository.findAll().size();

        // Create the GroundingSticks
        restGroundingSticksMockMvc.perform(post("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isCreated());

        // Validate the GroundingSticks in the database
        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeCreate + 1);
        GroundingSticks testGroundingSticks = groundingSticksList.get(groundingSticksList.size() - 1);
        assertThat(testGroundingSticks.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testGroundingSticks.getCurrent()).isEqualTo(DEFAULT_CURRENT);
        assertThat(testGroundingSticks.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testGroundingSticks.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createGroundingSticksWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groundingSticksRepository.findAll().size();

        // Create the GroundingSticks with an existing ID
        groundingSticks.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroundingSticksMockMvc.perform(post("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isBadRequest());

        // Validate the GroundingSticks in the database
        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = groundingSticksRepository.findAll().size();
        // set the field null
        groundingSticks.setProductionYear(null);

        // Create the GroundingSticks, which fails.

        restGroundingSticksMockMvc.perform(post("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isBadRequest());

        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = groundingSticksRepository.findAll().size();
        // set the field null
        groundingSticks.setCurrent(null);

        // Create the GroundingSticks, which fails.

        restGroundingSticksMockMvc.perform(post("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isBadRequest());

        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = groundingSticksRepository.findAll().size();
        // set the field null
        groundingSticks.setSerial(null);

        // Create the GroundingSticks, which fails.

        restGroundingSticksMockMvc.perform(post("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isBadRequest());

        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGroundingSticks() throws Exception {
        // Initialize the database
        groundingSticksRepository.saveAndFlush(groundingSticks);

        // Get all the groundingSticksList
        restGroundingSticksMockMvc.perform(get("/api/grounding-sticks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groundingSticks.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getGroundingSticks() throws Exception {
        // Initialize the database
        groundingSticksRepository.saveAndFlush(groundingSticks);

        // Get the groundingSticks
        restGroundingSticksMockMvc.perform(get("/api/grounding-sticks/{id}", groundingSticks.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groundingSticks.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.current").value(DEFAULT_CURRENT))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGroundingSticks() throws Exception {
        // Get the groundingSticks
        restGroundingSticksMockMvc.perform(get("/api/grounding-sticks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroundingSticks() throws Exception {
        // Initialize the database
        groundingSticksRepository.saveAndFlush(groundingSticks);

        int databaseSizeBeforeUpdate = groundingSticksRepository.findAll().size();

        // Update the groundingSticks
        GroundingSticks updatedGroundingSticks = groundingSticksRepository.findById(groundingSticks.getId()).get();
        // Disconnect from session so that the updates on updatedGroundingSticks are not directly saved in db
        em.detach(updatedGroundingSticks);
        updatedGroundingSticks
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .current(UPDATED_CURRENT)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restGroundingSticksMockMvc.perform(put("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroundingSticks)))
            .andExpect(status().isOk());

        // Validate the GroundingSticks in the database
        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeUpdate);
        GroundingSticks testGroundingSticks = groundingSticksList.get(groundingSticksList.size() - 1);
        assertThat(testGroundingSticks.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testGroundingSticks.getCurrent()).isEqualTo(UPDATED_CURRENT);
        assertThat(testGroundingSticks.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testGroundingSticks.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroundingSticks() throws Exception {
        int databaseSizeBeforeUpdate = groundingSticksRepository.findAll().size();

        // Create the GroundingSticks

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGroundingSticksMockMvc.perform(put("/api/grounding-sticks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundingSticks)))
            .andExpect(status().isBadRequest());

        // Validate the GroundingSticks in the database
        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroundingSticks() throws Exception {
        // Initialize the database
        groundingSticksRepository.saveAndFlush(groundingSticks);

        int databaseSizeBeforeDelete = groundingSticksRepository.findAll().size();

        // Get the groundingSticks
        restGroundingSticksMockMvc.perform(delete("/api/grounding-sticks/{id}", groundingSticks.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroundingSticks> groundingSticksList = groundingSticksRepository.findAll();
        assertThat(groundingSticksList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroundingSticks.class);
        GroundingSticks groundingSticks1 = new GroundingSticks();
        groundingSticks1.setId(1L);
        GroundingSticks groundingSticks2 = new GroundingSticks();
        groundingSticks2.setId(groundingSticks1.getId());
        assertThat(groundingSticks1).isEqualTo(groundingSticks2);
        groundingSticks2.setId(2L);
        assertThat(groundingSticks1).isNotEqualTo(groundingSticks2);
        groundingSticks1.setId(null);
        assertThat(groundingSticks1).isNotEqualTo(groundingSticks2);
    }
}
