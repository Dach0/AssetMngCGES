package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.VoltageMeasuringTransformer;
import com.cges.assetmng.repository.VoltageMeasuringTransformerRepository;
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
 * Test class for the VoltageMeasuringTransformerResource REST controller.
 *
 * @see VoltageMeasuringTransformerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class VoltageMeasuringTransformerResourceIntTest {

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
    private VoltageMeasuringTransformerRepository voltageMeasuringTransformerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVoltageMeasuringTransformerMockMvc;

    private VoltageMeasuringTransformer voltageMeasuringTransformer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VoltageMeasuringTransformerResource voltageMeasuringTransformerResource = new VoltageMeasuringTransformerResource(voltageMeasuringTransformerRepository);
        this.restVoltageMeasuringTransformerMockMvc = MockMvcBuilders.standaloneSetup(voltageMeasuringTransformerResource)
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
    public static VoltageMeasuringTransformer createEntity(EntityManager em) {
        VoltageMeasuringTransformer voltageMeasuringTransformer = new VoltageMeasuringTransformer()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .transmissionRatio(DEFAULT_TRANSMISSION_RATIO)
            .accuracyClass(DEFAULT_ACCURACY_CLASS)
            .nominalPower(DEFAULT_NOMINAL_POWER)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return voltageMeasuringTransformer;
    }

    @Before
    public void initTest() {
        voltageMeasuringTransformer = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoltageMeasuringTransformer() throws Exception {
        int databaseSizeBeforeCreate = voltageMeasuringTransformerRepository.findAll().size();

        // Create the VoltageMeasuringTransformer
        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isCreated());

        // Validate the VoltageMeasuringTransformer in the database
        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeCreate + 1);
        VoltageMeasuringTransformer testVoltageMeasuringTransformer = voltageMeasuringTransformerList.get(voltageMeasuringTransformerList.size() - 1);
        assertThat(testVoltageMeasuringTransformer.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testVoltageMeasuringTransformer.getTransmissionRatio()).isEqualTo(DEFAULT_TRANSMISSION_RATIO);
        assertThat(testVoltageMeasuringTransformer.getAccuracyClass()).isEqualTo(DEFAULT_ACCURACY_CLASS);
        assertThat(testVoltageMeasuringTransformer.getNominalPower()).isEqualTo(DEFAULT_NOMINAL_POWER);
        assertThat(testVoltageMeasuringTransformer.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testVoltageMeasuringTransformer.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createVoltageMeasuringTransformerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voltageMeasuringTransformerRepository.findAll().size();

        // Create the VoltageMeasuringTransformer with an existing ID
        voltageMeasuringTransformer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        // Validate the VoltageMeasuringTransformer in the database
        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageMeasuringTransformerRepository.findAll().size();
        // set the field null
        voltageMeasuringTransformer.setProductionYear(null);

        // Create the VoltageMeasuringTransformer, which fails.

        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransmissionRatioIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageMeasuringTransformerRepository.findAll().size();
        // set the field null
        voltageMeasuringTransformer.setTransmissionRatio(null);

        // Create the VoltageMeasuringTransformer, which fails.

        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccuracyClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageMeasuringTransformerRepository.findAll().size();
        // set the field null
        voltageMeasuringTransformer.setAccuracyClass(null);

        // Create the VoltageMeasuringTransformer, which fails.

        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNominalPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageMeasuringTransformerRepository.findAll().size();
        // set the field null
        voltageMeasuringTransformer.setNominalPower(null);

        // Create the VoltageMeasuringTransformer, which fails.

        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageMeasuringTransformerRepository.findAll().size();
        // set the field null
        voltageMeasuringTransformer.setSerial(null);

        // Create the VoltageMeasuringTransformer, which fails.

        restVoltageMeasuringTransformerMockMvc.perform(post("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVoltageMeasuringTransformers() throws Exception {
        // Initialize the database
        voltageMeasuringTransformerRepository.saveAndFlush(voltageMeasuringTransformer);

        // Get all the voltageMeasuringTransformerList
        restVoltageMeasuringTransformerMockMvc.perform(get("/api/voltage-measuring-transformers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voltageMeasuringTransformer.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].transmissionRatio").value(hasItem(DEFAULT_TRANSMISSION_RATIO.toString())))
            .andExpect(jsonPath("$.[*].accuracyClass").value(hasItem(DEFAULT_ACCURACY_CLASS.doubleValue())))
            .andExpect(jsonPath("$.[*].nominalPower").value(hasItem(DEFAULT_NOMINAL_POWER)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getVoltageMeasuringTransformer() throws Exception {
        // Initialize the database
        voltageMeasuringTransformerRepository.saveAndFlush(voltageMeasuringTransformer);

        // Get the voltageMeasuringTransformer
        restVoltageMeasuringTransformerMockMvc.perform(get("/api/voltage-measuring-transformers/{id}", voltageMeasuringTransformer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(voltageMeasuringTransformer.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.transmissionRatio").value(DEFAULT_TRANSMISSION_RATIO.toString()))
            .andExpect(jsonPath("$.accuracyClass").value(DEFAULT_ACCURACY_CLASS.doubleValue()))
            .andExpect(jsonPath("$.nominalPower").value(DEFAULT_NOMINAL_POWER))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVoltageMeasuringTransformer() throws Exception {
        // Get the voltageMeasuringTransformer
        restVoltageMeasuringTransformerMockMvc.perform(get("/api/voltage-measuring-transformers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoltageMeasuringTransformer() throws Exception {
        // Initialize the database
        voltageMeasuringTransformerRepository.saveAndFlush(voltageMeasuringTransformer);

        int databaseSizeBeforeUpdate = voltageMeasuringTransformerRepository.findAll().size();

        // Update the voltageMeasuringTransformer
        VoltageMeasuringTransformer updatedVoltageMeasuringTransformer = voltageMeasuringTransformerRepository.findById(voltageMeasuringTransformer.getId()).get();
        // Disconnect from session so that the updates on updatedVoltageMeasuringTransformer are not directly saved in db
        em.detach(updatedVoltageMeasuringTransformer);
        updatedVoltageMeasuringTransformer
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .transmissionRatio(UPDATED_TRANSMISSION_RATIO)
            .accuracyClass(UPDATED_ACCURACY_CLASS)
            .nominalPower(UPDATED_NOMINAL_POWER)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restVoltageMeasuringTransformerMockMvc.perform(put("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoltageMeasuringTransformer)))
            .andExpect(status().isOk());

        // Validate the VoltageMeasuringTransformer in the database
        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeUpdate);
        VoltageMeasuringTransformer testVoltageMeasuringTransformer = voltageMeasuringTransformerList.get(voltageMeasuringTransformerList.size() - 1);
        assertThat(testVoltageMeasuringTransformer.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testVoltageMeasuringTransformer.getTransmissionRatio()).isEqualTo(UPDATED_TRANSMISSION_RATIO);
        assertThat(testVoltageMeasuringTransformer.getAccuracyClass()).isEqualTo(UPDATED_ACCURACY_CLASS);
        assertThat(testVoltageMeasuringTransformer.getNominalPower()).isEqualTo(UPDATED_NOMINAL_POWER);
        assertThat(testVoltageMeasuringTransformer.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testVoltageMeasuringTransformer.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingVoltageMeasuringTransformer() throws Exception {
        int databaseSizeBeforeUpdate = voltageMeasuringTransformerRepository.findAll().size();

        // Create the VoltageMeasuringTransformer

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVoltageMeasuringTransformerMockMvc.perform(put("/api/voltage-measuring-transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageMeasuringTransformer)))
            .andExpect(status().isBadRequest());

        // Validate the VoltageMeasuringTransformer in the database
        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoltageMeasuringTransformer() throws Exception {
        // Initialize the database
        voltageMeasuringTransformerRepository.saveAndFlush(voltageMeasuringTransformer);

        int databaseSizeBeforeDelete = voltageMeasuringTransformerRepository.findAll().size();

        // Get the voltageMeasuringTransformer
        restVoltageMeasuringTransformerMockMvc.perform(delete("/api/voltage-measuring-transformers/{id}", voltageMeasuringTransformer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VoltageMeasuringTransformer> voltageMeasuringTransformerList = voltageMeasuringTransformerRepository.findAll();
        assertThat(voltageMeasuringTransformerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoltageMeasuringTransformer.class);
        VoltageMeasuringTransformer voltageMeasuringTransformer1 = new VoltageMeasuringTransformer();
        voltageMeasuringTransformer1.setId(1L);
        VoltageMeasuringTransformer voltageMeasuringTransformer2 = new VoltageMeasuringTransformer();
        voltageMeasuringTransformer2.setId(voltageMeasuringTransformer1.getId());
        assertThat(voltageMeasuringTransformer1).isEqualTo(voltageMeasuringTransformer2);
        voltageMeasuringTransformer2.setId(2L);
        assertThat(voltageMeasuringTransformer1).isNotEqualTo(voltageMeasuringTransformer2);
        voltageMeasuringTransformer1.setId(null);
        assertThat(voltageMeasuringTransformer1).isNotEqualTo(voltageMeasuringTransformer2);
    }
}
