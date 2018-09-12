package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.DisconnectorLineExit;
import com.cges.assetmng.repository.DisconnectorLineExitRepository;
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
 * Test class for the DisconnectorLineExitResource REST controller.
 *
 * @see DisconnectorLineExitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class DisconnectorLineExitResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final Integer DEFAULT_CURRENT = 1;
    private static final Integer UPDATED_CURRENT = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private DisconnectorLineExitRepository disconnectorLineExitRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisconnectorLineExitMockMvc;

    private DisconnectorLineExit disconnectorLineExit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisconnectorLineExitResource disconnectorLineExitResource = new DisconnectorLineExitResource(disconnectorLineExitRepository);
        this.restDisconnectorLineExitMockMvc = MockMvcBuilders.standaloneSetup(disconnectorLineExitResource)
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
    public static DisconnectorLineExit createEntity(EntityManager em) {
        DisconnectorLineExit disconnectorLineExit = new DisconnectorLineExit()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .current(DEFAULT_CURRENT)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return disconnectorLineExit;
    }

    @Before
    public void initTest() {
        disconnectorLineExit = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisconnectorLineExit() throws Exception {
        int databaseSizeBeforeCreate = disconnectorLineExitRepository.findAll().size();

        // Create the DisconnectorLineExit
        restDisconnectorLineExitMockMvc.perform(post("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isCreated());

        // Validate the DisconnectorLineExit in the database
        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeCreate + 1);
        DisconnectorLineExit testDisconnectorLineExit = disconnectorLineExitList.get(disconnectorLineExitList.size() - 1);
        assertThat(testDisconnectorLineExit.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testDisconnectorLineExit.getCurrent()).isEqualTo(DEFAULT_CURRENT);
        assertThat(testDisconnectorLineExit.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testDisconnectorLineExit.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createDisconnectorLineExitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disconnectorLineExitRepository.findAll().size();

        // Create the DisconnectorLineExit with an existing ID
        disconnectorLineExit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisconnectorLineExitMockMvc.perform(post("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorLineExit in the database
        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorLineExitRepository.findAll().size();
        // set the field null
        disconnectorLineExit.setProductionYear(null);

        // Create the DisconnectorLineExit, which fails.

        restDisconnectorLineExitMockMvc.perform(post("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isBadRequest());

        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorLineExitRepository.findAll().size();
        // set the field null
        disconnectorLineExit.setCurrent(null);

        // Create the DisconnectorLineExit, which fails.

        restDisconnectorLineExitMockMvc.perform(post("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isBadRequest());

        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorLineExitRepository.findAll().size();
        // set the field null
        disconnectorLineExit.setSerial(null);

        // Create the DisconnectorLineExit, which fails.

        restDisconnectorLineExitMockMvc.perform(post("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isBadRequest());

        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDisconnectorLineExits() throws Exception {
        // Initialize the database
        disconnectorLineExitRepository.saveAndFlush(disconnectorLineExit);

        // Get all the disconnectorLineExitList
        restDisconnectorLineExitMockMvc.perform(get("/api/disconnector-line-exits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disconnectorLineExit.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getDisconnectorLineExit() throws Exception {
        // Initialize the database
        disconnectorLineExitRepository.saveAndFlush(disconnectorLineExit);

        // Get the disconnectorLineExit
        restDisconnectorLineExitMockMvc.perform(get("/api/disconnector-line-exits/{id}", disconnectorLineExit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disconnectorLineExit.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.current").value(DEFAULT_CURRENT))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDisconnectorLineExit() throws Exception {
        // Get the disconnectorLineExit
        restDisconnectorLineExitMockMvc.perform(get("/api/disconnector-line-exits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisconnectorLineExit() throws Exception {
        // Initialize the database
        disconnectorLineExitRepository.saveAndFlush(disconnectorLineExit);

        int databaseSizeBeforeUpdate = disconnectorLineExitRepository.findAll().size();

        // Update the disconnectorLineExit
        DisconnectorLineExit updatedDisconnectorLineExit = disconnectorLineExitRepository.findById(disconnectorLineExit.getId()).get();
        // Disconnect from session so that the updates on updatedDisconnectorLineExit are not directly saved in db
        em.detach(updatedDisconnectorLineExit);
        updatedDisconnectorLineExit
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .current(UPDATED_CURRENT)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restDisconnectorLineExitMockMvc.perform(put("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisconnectorLineExit)))
            .andExpect(status().isOk());

        // Validate the DisconnectorLineExit in the database
        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeUpdate);
        DisconnectorLineExit testDisconnectorLineExit = disconnectorLineExitList.get(disconnectorLineExitList.size() - 1);
        assertThat(testDisconnectorLineExit.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testDisconnectorLineExit.getCurrent()).isEqualTo(UPDATED_CURRENT);
        assertThat(testDisconnectorLineExit.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testDisconnectorLineExit.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisconnectorLineExit() throws Exception {
        int databaseSizeBeforeUpdate = disconnectorLineExitRepository.findAll().size();

        // Create the DisconnectorLineExit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisconnectorLineExitMockMvc.perform(put("/api/disconnector-line-exits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorLineExit)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorLineExit in the database
        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisconnectorLineExit() throws Exception {
        // Initialize the database
        disconnectorLineExitRepository.saveAndFlush(disconnectorLineExit);

        int databaseSizeBeforeDelete = disconnectorLineExitRepository.findAll().size();

        // Get the disconnectorLineExit
        restDisconnectorLineExitMockMvc.perform(delete("/api/disconnector-line-exits/{id}", disconnectorLineExit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisconnectorLineExit> disconnectorLineExitList = disconnectorLineExitRepository.findAll();
        assertThat(disconnectorLineExitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisconnectorLineExit.class);
        DisconnectorLineExit disconnectorLineExit1 = new DisconnectorLineExit();
        disconnectorLineExit1.setId(1L);
        DisconnectorLineExit disconnectorLineExit2 = new DisconnectorLineExit();
        disconnectorLineExit2.setId(disconnectorLineExit1.getId());
        assertThat(disconnectorLineExit1).isEqualTo(disconnectorLineExit2);
        disconnectorLineExit2.setId(2L);
        assertThat(disconnectorLineExit1).isNotEqualTo(disconnectorLineExit2);
        disconnectorLineExit1.setId(null);
        assertThat(disconnectorLineExit1).isNotEqualTo(disconnectorLineExit2);
    }
}
