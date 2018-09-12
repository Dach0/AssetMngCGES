package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Transformer;
import com.cges.assetmng.domain.Coupling;
import com.cges.assetmng.domain.Power;
import com.cges.assetmng.domain.TransmissionRatio;
import com.cges.assetmng.domain.Manufacturer;
import com.cges.assetmng.domain.TransformerType;
import com.cges.assetmng.repository.TransformerRepository;
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
 * Test class for the TransformerResource REST controller.
 *
 * @see TransformerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TransformerResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final Integer DEFAULT_INSTALLATION_YEAR = 1;
    private static final Integer UPDATED_INSTALLATION_YEAR = 2;

    private static final Double DEFAULT_SHORT_CIRCUIT_VOLTAGE = 1D;
    private static final Double UPDATED_SHORT_CIRCUIT_VOLTAGE = 2D;

    private static final Boolean DEFAULT_IS_EARTH_GROUNDING = false;
    private static final Boolean UPDATED_IS_EARTH_GROUNDING = true;

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    @Autowired
    private TransformerRepository transformerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransformerMockMvc;

    private Transformer transformer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransformerResource transformerResource = new TransformerResource(transformerRepository);
        this.restTransformerMockMvc = MockMvcBuilders.standaloneSetup(transformerResource)
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
    public static Transformer createEntity(EntityManager em) {
        Transformer transformer = new Transformer()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .installationYear(DEFAULT_INSTALLATION_YEAR)
            .shortCircuitVoltage(DEFAULT_SHORT_CIRCUIT_VOLTAGE)
            .isEarthGrounding(DEFAULT_IS_EARTH_GROUNDING)
            .picture(DEFAULT_PICTURE)
            .serialNumber(DEFAULT_SERIAL_NUMBER);
        // Add required entity
        Coupling coupling = CouplingResourceIntTest.createEntity(em);
        em.persist(coupling);
        em.flush();
        transformer.setCoupling(coupling);
        // Add required entity
        Power power = PowerResourceIntTest.createEntity(em);
        em.persist(power);
        em.flush();
        transformer.setPower(power);
        // Add required entity
        TransmissionRatio transmissionRatio = TransmissionRatioResourceIntTest.createEntity(em);
        em.persist(transmissionRatio);
        em.flush();
        transformer.setTransRatio(transmissionRatio);
        // Add required entity
        Manufacturer manufacturer = ManufacturerResourceIntTest.createEntity(em);
        em.persist(manufacturer);
        em.flush();
        transformer.setManufacturer(manufacturer);
        // Add required entity
        TransformerType transformerType = TransformerTypeResourceIntTest.createEntity(em);
        em.persist(transformerType);
        em.flush();
        transformer.setType(transformerType);
        return transformer;
    }

    @Before
    public void initTest() {
        transformer = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransformer() throws Exception {
        int databaseSizeBeforeCreate = transformerRepository.findAll().size();

        // Create the Transformer
        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isCreated());

        // Validate the Transformer in the database
        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeCreate + 1);
        Transformer testTransformer = transformerList.get(transformerList.size() - 1);
        assertThat(testTransformer.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testTransformer.getInstallationYear()).isEqualTo(DEFAULT_INSTALLATION_YEAR);
        assertThat(testTransformer.getShortCircuitVoltage()).isEqualTo(DEFAULT_SHORT_CIRCUIT_VOLTAGE);
        assertThat(testTransformer.isIsEarthGrounding()).isEqualTo(DEFAULT_IS_EARTH_GROUNDING);
        assertThat(testTransformer.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testTransformer.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void createTransformerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transformerRepository.findAll().size();

        // Create the Transformer with an existing ID
        transformer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        // Validate the Transformer in the database
        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = transformerRepository.findAll().size();
        // set the field null
        transformer.setProductionYear(null);

        // Create the Transformer, which fails.

        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstallationYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = transformerRepository.findAll().size();
        // set the field null
        transformer.setInstallationYear(null);

        // Create the Transformer, which fails.

        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShortCircuitVoltageIsRequired() throws Exception {
        int databaseSizeBeforeTest = transformerRepository.findAll().size();
        // set the field null
        transformer.setShortCircuitVoltage(null);

        // Create the Transformer, which fails.

        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEarthGroundingIsRequired() throws Exception {
        int databaseSizeBeforeTest = transformerRepository.findAll().size();
        // set the field null
        transformer.setIsEarthGrounding(null);

        // Create the Transformer, which fails.

        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPictureIsRequired() throws Exception {
        int databaseSizeBeforeTest = transformerRepository.findAll().size();
        // set the field null
        transformer.setPicture(null);

        // Create the Transformer, which fails.

        restTransformerMockMvc.perform(post("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransformers() throws Exception {
        // Initialize the database
        transformerRepository.saveAndFlush(transformer);

        // Get all the transformerList
        restTransformerMockMvc.perform(get("/api/transformers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transformer.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].installationYear").value(hasItem(DEFAULT_INSTALLATION_YEAR)))
            .andExpect(jsonPath("$.[*].shortCircuitVoltage").value(hasItem(DEFAULT_SHORT_CIRCUIT_VOLTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].isEarthGrounding").value(hasItem(DEFAULT_IS_EARTH_GROUNDING.booleanValue())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())));
    }
    

    @Test
    @Transactional
    public void getTransformer() throws Exception {
        // Initialize the database
        transformerRepository.saveAndFlush(transformer);

        // Get the transformer
        restTransformerMockMvc.perform(get("/api/transformers/{id}", transformer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transformer.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.installationYear").value(DEFAULT_INSTALLATION_YEAR))
            .andExpect(jsonPath("$.shortCircuitVoltage").value(DEFAULT_SHORT_CIRCUIT_VOLTAGE.doubleValue()))
            .andExpect(jsonPath("$.isEarthGrounding").value(DEFAULT_IS_EARTH_GROUNDING.booleanValue()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransformer() throws Exception {
        // Get the transformer
        restTransformerMockMvc.perform(get("/api/transformers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransformer() throws Exception {
        // Initialize the database
        transformerRepository.saveAndFlush(transformer);

        int databaseSizeBeforeUpdate = transformerRepository.findAll().size();

        // Update the transformer
        Transformer updatedTransformer = transformerRepository.findById(transformer.getId()).get();
        // Disconnect from session so that the updates on updatedTransformer are not directly saved in db
        em.detach(updatedTransformer);
        updatedTransformer
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .installationYear(UPDATED_INSTALLATION_YEAR)
            .shortCircuitVoltage(UPDATED_SHORT_CIRCUIT_VOLTAGE)
            .isEarthGrounding(UPDATED_IS_EARTH_GROUNDING)
            .picture(UPDATED_PICTURE)
            .serialNumber(UPDATED_SERIAL_NUMBER);

        restTransformerMockMvc.perform(put("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransformer)))
            .andExpect(status().isOk());

        // Validate the Transformer in the database
        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeUpdate);
        Transformer testTransformer = transformerList.get(transformerList.size() - 1);
        assertThat(testTransformer.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testTransformer.getInstallationYear()).isEqualTo(UPDATED_INSTALLATION_YEAR);
        assertThat(testTransformer.getShortCircuitVoltage()).isEqualTo(UPDATED_SHORT_CIRCUIT_VOLTAGE);
        assertThat(testTransformer.isIsEarthGrounding()).isEqualTo(UPDATED_IS_EARTH_GROUNDING);
        assertThat(testTransformer.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testTransformer.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingTransformer() throws Exception {
        int databaseSizeBeforeUpdate = transformerRepository.findAll().size();

        // Create the Transformer

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransformerMockMvc.perform(put("/api/transformers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformer)))
            .andExpect(status().isBadRequest());

        // Validate the Transformer in the database
        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransformer() throws Exception {
        // Initialize the database
        transformerRepository.saveAndFlush(transformer);

        int databaseSizeBeforeDelete = transformerRepository.findAll().size();

        // Get the transformer
        restTransformerMockMvc.perform(delete("/api/transformers/{id}", transformer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transformer> transformerList = transformerRepository.findAll();
        assertThat(transformerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transformer.class);
        Transformer transformer1 = new Transformer();
        transformer1.setId(1L);
        Transformer transformer2 = new Transformer();
        transformer2.setId(transformer1.getId());
        assertThat(transformer1).isEqualTo(transformer2);
        transformer2.setId(2L);
        assertThat(transformer1).isNotEqualTo(transformer2);
        transformer1.setId(null);
        assertThat(transformer1).isNotEqualTo(transformer2);
    }
}
