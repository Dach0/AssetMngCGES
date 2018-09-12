package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CurrentMeasuringTransformer;
import com.cges.assetmng.repository.CurrentMeasuringTransformerRepository;
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
 * Test class for the CurrentMeasuringTransformerResource REST controller.
 *
 * @see CurrentMeasuringTransformerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CurrentMeasuringTransformerResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final String DEFAULT_TRANSMISSION_RATIO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSMISSION_RATIO = "BBBBBBBBBB";

    private static final Double DEFAULT_ACCURACY_CLASS = 1D;
    private static final Double UPDATED_ACCURACY_CLASS = 2D;

    private static final Integer DEFAULT_NOMINAL_POWER = 1;
    private static final Integer UPDATED_NOMINAL_POWER = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private CurrentMeasuringTransformerRepository currentMeasuringTransformerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCurrentMeasuringTransformerMockMvc;

    private CurrentMeasuringTransformer currentMeasuringTransformer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CurrentMeasuringTransformerResource currentMeasuringTransformerResource = new CurrentMeasuringTransformerResource(currentMeasuringTransformerRepository);
        this.restCurrentMeasuringTransformerMockMvc = MockMvcBuilders.standaloneSetup(currentMeasuringTransformerResource)
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
    public static CurrentMeasuringTransformer createEntity(EntityManager em) {
        CurrentMeasuringTransformer currentMeasuringTransformer = new CurrentMeasuringTransformer()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .transmissionRatio(DEFAULT_TRANSMISSION_RATIO)
            .accuracyClass(DEFAULT_ACCURACY_CLASS)
            .nominalPower(DEFAULT_NOMINAL_POWER)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return currentMeasuringTransformer;
    }

    @Before
    public void initTest() {
        currentMeasuringTransformer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurrentMeasuringTransformer() throws Exception {
        int databaseSizeBeforeCreate = currentMeasuringTransformerRepository.findAll().size();

        // Create the CurrentMeasuringTransformer
        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isCreated());

        // Validate the CurrentMeasuringTransformer in the database
        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeCreate + 1);
        CurrentMeasuringTransformer testCurrentMeasuringTransformer = currentMeasuringTransformerList.get(currentMeasuringTransformerList.size() - 1);
        assertThat(testCurrentMeasuringTransformer.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testCurrentMeasuringTransformer.getTransmissionRatio()).isEqualTo(DEFAULT_TRANSMISSION_RATIO);
        assertThat(testCurrentMeasuringTransformer.getAccuracyClass()).isEqualTo(DEFAULT_ACCURACY_CLASS);
        assertThat(testCurrentMeasuringTransformer.getNominalPower()).isEqualTo(DEFAULT_NOMINAL_POWER);
        assertThat(testCurrentMeasuringTransformer.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testCurrentMeasuringTransformer.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createCurrentMeasuringTransformerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = currentMeasuringTransformerRepository.findAll().size();

        // Create the CurrentMeasuringTransformer with an existing ID
        currentMeasuringTransformer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        // Validate the CurrentMeasuringTransformer in the database
        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentMeasuringTransformerRepository.findAll().size();
        // set the field null
        currentMeasuringTransformer.setProductionYear(null);

        // Create the CurrentMeasuringTransformer, which fails.

        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransmissionRatioIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentMeasuringTransformerRepository.findAll().size();
        // set the field null
        currentMeasuringTransformer.setTransmissionRatio(null);

        // Create the CurrentMeasuringTransformer, which fails.

        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccuracyClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentMeasuringTransformerRepository.findAll().size();
        // set the field null
        currentMeasuringTransformer.setAccuracyClass(null);

        // Create the CurrentMeasuringTransformer, which fails.

        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNominalPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentMeasuringTransformerRepository.findAll().size();
        // set the field null
        currentMeasuringTransformer.setNominalPower(null);

        // Create the CurrentMeasuringTransformer, which fails.

        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentMeasuringTransformerRepository.findAll().size();
        // set the field null
        currentMeasuringTransformer.setSerial(null);

        // Create the CurrentMeasuringTransformer, which fails.

        restCurrentMeasuringTransformerMockMvc.perform(post("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCurrentMeasuringTransformers() throws Exception {
        // Initialize the database
        currentMeasuringTransformerRepository.saveAndFlush(currentMeasuringTransformer);

        // Get all the currentMeasuringTransformerList
        restCurrentMeasuringTransformerMockMvc.perform(get("/api/current-measuring-transformers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(currentMeasuringTransformer.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].transmissionRatio").value(hasItem(DEFAULT_TRANSMISSION_RATIO.toString())))
            .andExpect(jsonPath("$.[*].accuracyClass").value(hasItem(DEFAULT_ACCURACY_CLASS.doubleValue())))
            .andExpect(jsonPath("$.[*].nominalPower").value(hasItem(DEFAULT_NOMINAL_POWER)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getCurrentMeasuringTransformer() throws Exception {
        // Initialize the database
        currentMeasuringTransformerRepository.saveAndFlush(currentMeasuringTransformer);

        // Get the currentMeasuringTransformer
        restCurrentMeasuringTransformerMockMvc.perform(get("/api/current-measuring-transformers/{id}", currentMeasuringTransformer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(currentMeasuringTransformer.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.transmissionRatio").value(DEFAULT_TRANSMISSION_RATIO.toString()))
            .andExpect(jsonPath("$.accuracyClass").value(DEFAULT_ACCURACY_CLASS.doubleValue()))
            .andExpect(jsonPath("$.nominalPower").value(DEFAULT_NOMINAL_POWER))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCurrentMeasuringTransformer() throws Exception {
        // Get the currentMeasuringTransformer
        restCurrentMeasuringTransformerMockMvc.perform(get("/api/current-measuring-transformers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrentMeasuringTransformer() throws Exception {
        // Initialize the database
        currentMeasuringTransformerRepository.saveAndFlush(currentMeasuringTransformer);

        int databaseSizeBeforeUpdate = currentMeasuringTransformerRepository.findAll().size();

        // Update the currentMeasuringTransformer
        CurrentMeasuringTransformer updatedCurrentMeasuringTransformer = currentMeasuringTransformerRepository.findById(currentMeasuringTransformer.getId()).get();
        // Disconnect from session so that the updates on updatedCurrentMeasuringTransformer are not directly saved in db
        em.detach(updatedCurrentMeasuringTransformer);
        updatedCurrentMeasuringTransformer
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .transmissionRatio(UPDATED_TRANSMISSION_RATIO)
            .accuracyClass(UPDATED_ACCURACY_CLASS)
            .nominalPower(UPDATED_NOMINAL_POWER)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restCurrentMeasuringTransformerMockMvc.perform(put("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCurrentMeasuringTransformer)))
            .andExpect(status().isOk());

        // Validate the CurrentMeasuringTransformer in the database
        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeUpdate);
        CurrentMeasuringTransformer testCurrentMeasuringTransformer = currentMeasuringTransformerList.get(currentMeasuringTransformerList.size() - 1);
        assertThat(testCurrentMeasuringTransformer.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testCurrentMeasuringTransformer.getTransmissionRatio()).isEqualTo(UPDATED_TRANSMISSION_RATIO);
        assertThat(testCurrentMeasuringTransformer.getAccuracyClass()).isEqualTo(UPDATED_ACCURACY_CLASS);
        assertThat(testCurrentMeasuringTransformer.getNominalPower()).isEqualTo(UPDATED_NOMINAL_POWER);
        assertThat(testCurrentMeasuringTransformer.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testCurrentMeasuringTransformer.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingCurrentMeasuringTransformer() throws Exception {
        int databaseSizeBeforeUpdate = currentMeasuringTransformerRepository.findAll().size();

        // Create the CurrentMeasuringTransformer

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCurrentMeasuringTransformerMockMvc.perform(put("/api/current-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        // Validate the CurrentMeasuringTransformer in the database
        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurrentMeasuringTransformer() throws Exception {
        // Initialize the database
        currentMeasuringTransformerRepository.saveAndFlush(currentMeasuringTransformer);

        int databaseSizeBeforeDelete = currentMeasuringTransformerRepository.findAll().size();

        // Get the currentMeasuringTransformer
        restCurrentMeasuringTransformerMockMvc.perform(delete("/api/current-measuring-transformers/{id}", currentMeasuringTransformer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CurrentMeasuringTransformer> currentMeasuringTransformerList = currentMeasuringTransformerRepository.findAll();
        assertThat(currentMeasuringTransformerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrentMeasuringTransformer.class);
        CurrentMeasuringTransformer currentMeasuringTransformer1 = new CurrentMeasuringTransformer();
        currentMeasuringTransformer1.setId(1L);
        CurrentMeasuringTransformer currentMeasuringTransformer2 = new CurrentMeasuringTransformer();
        currentMeasuringTransformer2.setId(currentMeasuringTransformer1.getId());
        assertThat(currentMeasuringTransformer1).isEqualTo(currentMeasuringTransformer2);
        currentMeasuringTransformer2.setId(2L);
        assertThat(currentMeasuringTransformer1).isNotEqualTo(currentMeasuringTransformer2);
        currentMeasuringTransformer1.setId(null);
        assertThat(currentMeasuringTransformer1).isNotEqualTo(currentMeasuringTransformer2);
    }
}
