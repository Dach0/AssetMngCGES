package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.SurgeArrester;
import com.cges.assetmng.repository.SurgeArresterRepository;
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
 * Test class for the SurgeArresterResource REST controller.
 *
 * @see SurgeArresterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class SurgeArresterResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final String DEFAULT_UCUR = "AAAAAAAAAA";
    private static final String UPDATED_UCUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_DRAINAGE_CURRENT = 1;
    private static final Integer UPDATED_DRAINAGE_CURRENT = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private SurgeArresterRepository surgeArresterRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurgeArresterMockMvc;

    private SurgeArrester surgeArrester;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurgeArresterResource surgeArresterResource = new SurgeArresterResource(surgeArresterRepository);
        this.restSurgeArresterMockMvc = MockMvcBuilders.standaloneSetup(surgeArresterResource)
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
    public static SurgeArrester createEntity(EntityManager em) {
        SurgeArrester surgeArrester = new SurgeArrester()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .ucur(DEFAULT_UCUR)
            .drainageCurrent(DEFAULT_DRAINAGE_CURRENT)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return surgeArrester;
    }

    @Before
    public void initTest() {
        surgeArrester = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurgeArrester() throws Exception {
        int databaseSizeBeforeCreate = surgeArresterRepository.findAll().size();

        // Create the SurgeArrester
        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isCreated());

        // Validate the SurgeArrester in the database
        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeCreate + 1);
        SurgeArrester testSurgeArrester = surgeArresterList.get(surgeArresterList.size() - 1);
        assertThat(testSurgeArrester.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testSurgeArrester.getUcur()).isEqualTo(DEFAULT_UCUR);
        assertThat(testSurgeArrester.getDrainageCurrent()).isEqualTo(DEFAULT_DRAINAGE_CURRENT);
        assertThat(testSurgeArrester.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testSurgeArrester.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createSurgeArresterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surgeArresterRepository.findAll().size();

        // Create the SurgeArrester with an existing ID
        surgeArrester.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        // Validate the SurgeArrester in the database
        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = surgeArresterRepository.findAll().size();
        // set the field null
        surgeArrester.setProductionYear(null);

        // Create the SurgeArrester, which fails.

        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcurIsRequired() throws Exception {
        int databaseSizeBeforeTest = surgeArresterRepository.findAll().size();
        // set the field null
        surgeArrester.setUcur(null);

        // Create the SurgeArrester, which fails.

        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrainageCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = surgeArresterRepository.findAll().size();
        // set the field null
        surgeArrester.setDrainageCurrent(null);

        // Create the SurgeArrester, which fails.

        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = surgeArresterRepository.findAll().size();
        // set the field null
        surgeArrester.setSerial(null);

        // Create the SurgeArrester, which fails.

        restSurgeArresterMockMvc.perform(post("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSurgeArresters() throws Exception {
        // Initialize the database
        surgeArresterRepository.saveAndFlush(surgeArrester);

        // Get all the surgeArresterList
        restSurgeArresterMockMvc.perform(get("/api/surge-arresters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surgeArrester.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].ucur").value(hasItem(DEFAULT_UCUR.toString())))
            .andExpect(jsonPath("$.[*].drainageCurrent").value(hasItem(DEFAULT_DRAINAGE_CURRENT)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getSurgeArrester() throws Exception {
        // Initialize the database
        surgeArresterRepository.saveAndFlush(surgeArrester);

        // Get the surgeArrester
        restSurgeArresterMockMvc.perform(get("/api/surge-arresters/{id}", surgeArrester.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(surgeArrester.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.ucur").value(DEFAULT_UCUR.toString()))
            .andExpect(jsonPath("$.drainageCurrent").value(DEFAULT_DRAINAGE_CURRENT))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSurgeArrester() throws Exception {
        // Get the surgeArrester
        restSurgeArresterMockMvc.perform(get("/api/surge-arresters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurgeArrester() throws Exception {
        // Initialize the database
        surgeArresterRepository.saveAndFlush(surgeArrester);

        int databaseSizeBeforeUpdate = surgeArresterRepository.findAll().size();

        // Update the surgeArrester
        SurgeArrester updatedSurgeArrester = surgeArresterRepository.findById(surgeArrester.getId()).get();
        // Disconnect from session so that the updates on updatedSurgeArrester are not directly saved in db
        em.detach(updatedSurgeArrester);
        updatedSurgeArrester
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .ucur(UPDATED_UCUR)
            .drainageCurrent(UPDATED_DRAINAGE_CURRENT)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restSurgeArresterMockMvc.perform(put("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSurgeArrester)))
            .andExpect(status().isOk());

        // Validate the SurgeArrester in the database
        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeUpdate);
        SurgeArrester testSurgeArrester = surgeArresterList.get(surgeArresterList.size() - 1);
        assertThat(testSurgeArrester.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testSurgeArrester.getUcur()).isEqualTo(UPDATED_UCUR);
        assertThat(testSurgeArrester.getDrainageCurrent()).isEqualTo(UPDATED_DRAINAGE_CURRENT);
        assertThat(testSurgeArrester.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testSurgeArrester.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingSurgeArrester() throws Exception {
        int databaseSizeBeforeUpdate = surgeArresterRepository.findAll().size();

        // Create the SurgeArrester

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurgeArresterMockMvc.perform(put("/api/surge-arresters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArrester)))
            .andExpect(status().isBadRequest());

        // Validate the SurgeArrester in the database
        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurgeArrester() throws Exception {
        // Initialize the database
        surgeArresterRepository.saveAndFlush(surgeArrester);

        int databaseSizeBeforeDelete = surgeArresterRepository.findAll().size();

        // Get the surgeArrester
        restSurgeArresterMockMvc.perform(delete("/api/surge-arresters/{id}", surgeArrester.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SurgeArrester> surgeArresterList = surgeArresterRepository.findAll();
        assertThat(surgeArresterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurgeArrester.class);
        SurgeArrester surgeArrester1 = new SurgeArrester();
        surgeArrester1.setId(1L);
        SurgeArrester surgeArrester2 = new SurgeArrester();
        surgeArrester2.setId(surgeArrester1.getId());
        assertThat(surgeArrester1).isEqualTo(surgeArrester2);
        surgeArrester2.setId(2L);
        assertThat(surgeArrester1).isNotEqualTo(surgeArrester2);
        surgeArrester1.setId(null);
        assertThat(surgeArrester1).isNotEqualTo(surgeArrester2);
    }
}
