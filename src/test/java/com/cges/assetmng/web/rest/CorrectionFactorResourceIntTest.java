package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CorrectionFactor;
import com.cges.assetmng.repository.CorrectionFactorRepository;
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
 * Test class for the CorrectionFactorResource REST controller.
 *
 * @see CorrectionFactorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CorrectionFactorResourceIntTest {

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_DEGREES_30 = 1D;
    private static final Double UPDATED_DEGREES_30 = 2D;

    private static final Double DEFAULT_DEGREES_20 = 1D;
    private static final Double UPDATED_DEGREES_20 = 2D;

    private static final Double DEFAULT_DEGREES_10 = 1D;
    private static final Double UPDATED_DEGREES_10 = 2D;

    private static final Double DEFAULT_DEGREES_0 = 1D;
    private static final Double UPDATED_DEGREES_0 = 2D;

    @Autowired
    private CorrectionFactorRepository correctionFactorRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCorrectionFactorMockMvc;

    private CorrectionFactor correctionFactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CorrectionFactorResource correctionFactorResource = new CorrectionFactorResource(correctionFactorRepository);
        this.restCorrectionFactorMockMvc = MockMvcBuilders.standaloneSetup(correctionFactorResource)
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
    public static CorrectionFactor createEntity(EntityManager em) {
        CorrectionFactor correctionFactor = new CorrectionFactor()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .degrees30(DEFAULT_DEGREES_30)
            .degrees20(DEFAULT_DEGREES_20)
            .degrees10(DEFAULT_DEGREES_10)
            .degrees0(DEFAULT_DEGREES_0);
        return correctionFactor;
    }

    @Before
    public void initTest() {
        correctionFactor = createEntity(em);
    }

    @Test
    @Transactional
    public void createCorrectionFactor() throws Exception {
        int databaseSizeBeforeCreate = correctionFactorRepository.findAll().size();

        // Create the CorrectionFactor
        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isCreated());

        // Validate the CorrectionFactor in the database
        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeCreate + 1);
        CorrectionFactor testCorrectionFactor = correctionFactorList.get(correctionFactorList.size() - 1);
        assertThat(testCorrectionFactor.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testCorrectionFactor.getDegrees30()).isEqualTo(DEFAULT_DEGREES_30);
        assertThat(testCorrectionFactor.getDegrees20()).isEqualTo(DEFAULT_DEGREES_20);
        assertThat(testCorrectionFactor.getDegrees10()).isEqualTo(DEFAULT_DEGREES_10);
        assertThat(testCorrectionFactor.getDegrees0()).isEqualTo(DEFAULT_DEGREES_0);
    }

    @Test
    @Transactional
    public void createCorrectionFactorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = correctionFactorRepository.findAll().size();

        // Create the CorrectionFactor with an existing ID
        correctionFactor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        // Validate the CorrectionFactor in the database
        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDegrees30IsRequired() throws Exception {
        int databaseSizeBeforeTest = correctionFactorRepository.findAll().size();
        // set the field null
        correctionFactor.setDegrees30(null);

        // Create the CorrectionFactor, which fails.

        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDegrees20IsRequired() throws Exception {
        int databaseSizeBeforeTest = correctionFactorRepository.findAll().size();
        // set the field null
        correctionFactor.setDegrees20(null);

        // Create the CorrectionFactor, which fails.

        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDegrees10IsRequired() throws Exception {
        int databaseSizeBeforeTest = correctionFactorRepository.findAll().size();
        // set the field null
        correctionFactor.setDegrees10(null);

        // Create the CorrectionFactor, which fails.

        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDegrees0IsRequired() throws Exception {
        int databaseSizeBeforeTest = correctionFactorRepository.findAll().size();
        // set the field null
        correctionFactor.setDegrees0(null);

        // Create the CorrectionFactor, which fails.

        restCorrectionFactorMockMvc.perform(post("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCorrectionFactors() throws Exception {
        // Initialize the database
        correctionFactorRepository.saveAndFlush(correctionFactor);

        // Get all the correctionFactorList
        restCorrectionFactorMockMvc.perform(get("/api/correction-factors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(correctionFactor.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].degrees30").value(hasItem(DEFAULT_DEGREES_30.doubleValue())))
            .andExpect(jsonPath("$.[*].degrees20").value(hasItem(DEFAULT_DEGREES_20.doubleValue())))
            .andExpect(jsonPath("$.[*].degrees10").value(hasItem(DEFAULT_DEGREES_10.doubleValue())))
            .andExpect(jsonPath("$.[*].degrees0").value(hasItem(DEFAULT_DEGREES_0.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getCorrectionFactor() throws Exception {
        // Initialize the database
        correctionFactorRepository.saveAndFlush(correctionFactor);

        // Get the correctionFactor
        restCorrectionFactorMockMvc.perform(get("/api/correction-factors/{id}", correctionFactor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(correctionFactor.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME.toString()))
            .andExpect(jsonPath("$.degrees30").value(DEFAULT_DEGREES_30.doubleValue()))
            .andExpect(jsonPath("$.degrees20").value(DEFAULT_DEGREES_20.doubleValue()))
            .andExpect(jsonPath("$.degrees10").value(DEFAULT_DEGREES_10.doubleValue()))
            .andExpect(jsonPath("$.degrees0").value(DEFAULT_DEGREES_0.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCorrectionFactor() throws Exception {
        // Get the correctionFactor
        restCorrectionFactorMockMvc.perform(get("/api/correction-factors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCorrectionFactor() throws Exception {
        // Initialize the database
        correctionFactorRepository.saveAndFlush(correctionFactor);

        int databaseSizeBeforeUpdate = correctionFactorRepository.findAll().size();

        // Update the correctionFactor
        CorrectionFactor updatedCorrectionFactor = correctionFactorRepository.findById(correctionFactor.getId()).get();
        // Disconnect from session so that the updates on updatedCorrectionFactor are not directly saved in db
        em.detach(updatedCorrectionFactor);
        updatedCorrectionFactor
            .templateName(UPDATED_TEMPLATE_NAME)
            .degrees30(UPDATED_DEGREES_30)
            .degrees20(UPDATED_DEGREES_20)
            .degrees10(UPDATED_DEGREES_10)
            .degrees0(UPDATED_DEGREES_0);

        restCorrectionFactorMockMvc.perform(put("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCorrectionFactor)))
            .andExpect(status().isOk());

        // Validate the CorrectionFactor in the database
        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeUpdate);
        CorrectionFactor testCorrectionFactor = correctionFactorList.get(correctionFactorList.size() - 1);
        assertThat(testCorrectionFactor.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testCorrectionFactor.getDegrees30()).isEqualTo(UPDATED_DEGREES_30);
        assertThat(testCorrectionFactor.getDegrees20()).isEqualTo(UPDATED_DEGREES_20);
        assertThat(testCorrectionFactor.getDegrees10()).isEqualTo(UPDATED_DEGREES_10);
        assertThat(testCorrectionFactor.getDegrees0()).isEqualTo(UPDATED_DEGREES_0);
    }

    @Test
    @Transactional
    public void updateNonExistingCorrectionFactor() throws Exception {
        int databaseSizeBeforeUpdate = correctionFactorRepository.findAll().size();

        // Create the CorrectionFactor

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCorrectionFactorMockMvc.perform(put("/api/correction-factors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(correctionFactor)))
            .andExpect(status().isBadRequest());

        // Validate the CorrectionFactor in the database
        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCorrectionFactor() throws Exception {
        // Initialize the database
        correctionFactorRepository.saveAndFlush(correctionFactor);

        int databaseSizeBeforeDelete = correctionFactorRepository.findAll().size();

        // Get the correctionFactor
        restCorrectionFactorMockMvc.perform(delete("/api/correction-factors/{id}", correctionFactor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CorrectionFactor> correctionFactorList = correctionFactorRepository.findAll();
        assertThat(correctionFactorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CorrectionFactor.class);
        CorrectionFactor correctionFactor1 = new CorrectionFactor();
        correctionFactor1.setId(1L);
        CorrectionFactor correctionFactor2 = new CorrectionFactor();
        correctionFactor2.setId(correctionFactor1.getId());
        assertThat(correctionFactor1).isEqualTo(correctionFactor2);
        correctionFactor2.setId(2L);
        assertThat(correctionFactor1).isNotEqualTo(correctionFactor2);
        correctionFactor1.setId(null);
        assertThat(correctionFactor1).isNotEqualTo(correctionFactor2);
    }
}
