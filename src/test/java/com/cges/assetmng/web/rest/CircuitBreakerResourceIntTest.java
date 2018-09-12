package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CircuitBreaker;
import com.cges.assetmng.repository.CircuitBreakerRepository;
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
 * Test class for the CircuitBreakerResource REST controller.
 *
 * @see CircuitBreakerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CircuitBreakerResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final Integer DEFAULT_CURRENT = 1;
    private static final Integer UPDATED_CURRENT = 2;

    private static final Integer DEFAULT_SHORT_CIRCUIT_CURRENT = 1;
    private static final Integer UPDATED_SHORT_CIRCUIT_CURRENT = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private CircuitBreakerRepository circuitBreakerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCircuitBreakerMockMvc;

    private CircuitBreaker circuitBreaker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CircuitBreakerResource circuitBreakerResource = new CircuitBreakerResource(circuitBreakerRepository);
        this.restCircuitBreakerMockMvc = MockMvcBuilders.standaloneSetup(circuitBreakerResource)
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
    public static CircuitBreaker createEntity(EntityManager em) {
        CircuitBreaker circuitBreaker = new CircuitBreaker()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .current(DEFAULT_CURRENT)
            .shortCircuitCurrent(DEFAULT_SHORT_CIRCUIT_CURRENT)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return circuitBreaker;
    }

    @Before
    public void initTest() {
        circuitBreaker = createEntity(em);
    }

    @Test
    @Transactional
    public void createCircuitBreaker() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerRepository.findAll().size();

        // Create the CircuitBreaker
        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isCreated());

        // Validate the CircuitBreaker in the database
        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeCreate + 1);
        CircuitBreaker testCircuitBreaker = circuitBreakerList.get(circuitBreakerList.size() - 1);
        assertThat(testCircuitBreaker.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testCircuitBreaker.getCurrent()).isEqualTo(DEFAULT_CURRENT);
        assertThat(testCircuitBreaker.getShortCircuitCurrent()).isEqualTo(DEFAULT_SHORT_CIRCUIT_CURRENT);
        assertThat(testCircuitBreaker.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testCircuitBreaker.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createCircuitBreakerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerRepository.findAll().size();

        // Create the CircuitBreaker with an existing ID
        circuitBreaker.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreaker in the database
        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = circuitBreakerRepository.findAll().size();
        // set the field null
        circuitBreaker.setProductionYear(null);

        // Create the CircuitBreaker, which fails.

        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = circuitBreakerRepository.findAll().size();
        // set the field null
        circuitBreaker.setCurrent(null);

        // Create the CircuitBreaker, which fails.

        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShortCircuitCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = circuitBreakerRepository.findAll().size();
        // set the field null
        circuitBreaker.setShortCircuitCurrent(null);

        // Create the CircuitBreaker, which fails.

        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = circuitBreakerRepository.findAll().size();
        // set the field null
        circuitBreaker.setSerial(null);

        // Create the CircuitBreaker, which fails.

        restCircuitBreakerMockMvc.perform(post("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCircuitBreakers() throws Exception {
        // Initialize the database
        circuitBreakerRepository.saveAndFlush(circuitBreaker);

        // Get all the circuitBreakerList
        restCircuitBreakerMockMvc.perform(get("/api/circuit-breakers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(circuitBreaker.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT)))
            .andExpect(jsonPath("$.[*].shortCircuitCurrent").value(hasItem(DEFAULT_SHORT_CIRCUIT_CURRENT)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getCircuitBreaker() throws Exception {
        // Initialize the database
        circuitBreakerRepository.saveAndFlush(circuitBreaker);

        // Get the circuitBreaker
        restCircuitBreakerMockMvc.perform(get("/api/circuit-breakers/{id}", circuitBreaker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(circuitBreaker.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.current").value(DEFAULT_CURRENT))
            .andExpect(jsonPath("$.shortCircuitCurrent").value(DEFAULT_SHORT_CIRCUIT_CURRENT))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCircuitBreaker() throws Exception {
        // Get the circuitBreaker
        restCircuitBreakerMockMvc.perform(get("/api/circuit-breakers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCircuitBreaker() throws Exception {
        // Initialize the database
        circuitBreakerRepository.saveAndFlush(circuitBreaker);

        int databaseSizeBeforeUpdate = circuitBreakerRepository.findAll().size();

        // Update the circuitBreaker
        CircuitBreaker updatedCircuitBreaker = circuitBreakerRepository.findById(circuitBreaker.getId()).get();
        // Disconnect from session so that the updates on updatedCircuitBreaker are not directly saved in db
        em.detach(updatedCircuitBreaker);
        updatedCircuitBreaker
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .current(UPDATED_CURRENT)
            .shortCircuitCurrent(UPDATED_SHORT_CIRCUIT_CURRENT)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restCircuitBreakerMockMvc.perform(put("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCircuitBreaker)))
            .andExpect(status().isOk());

        // Validate the CircuitBreaker in the database
        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeUpdate);
        CircuitBreaker testCircuitBreaker = circuitBreakerList.get(circuitBreakerList.size() - 1);
        assertThat(testCircuitBreaker.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testCircuitBreaker.getCurrent()).isEqualTo(UPDATED_CURRENT);
        assertThat(testCircuitBreaker.getShortCircuitCurrent()).isEqualTo(UPDATED_SHORT_CIRCUIT_CURRENT);
        assertThat(testCircuitBreaker.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testCircuitBreaker.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingCircuitBreaker() throws Exception {
        int databaseSizeBeforeUpdate = circuitBreakerRepository.findAll().size();

        // Create the CircuitBreaker

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCircuitBreakerMockMvc.perform(put("/api/circuit-breakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreaker)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreaker in the database
        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCircuitBreaker() throws Exception {
        // Initialize the database
        circuitBreakerRepository.saveAndFlush(circuitBreaker);

        int databaseSizeBeforeDelete = circuitBreakerRepository.findAll().size();

        // Get the circuitBreaker
        restCircuitBreakerMockMvc.perform(delete("/api/circuit-breakers/{id}", circuitBreaker.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CircuitBreaker> circuitBreakerList = circuitBreakerRepository.findAll();
        assertThat(circuitBreakerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircuitBreaker.class);
        CircuitBreaker circuitBreaker1 = new CircuitBreaker();
        circuitBreaker1.setId(1L);
        CircuitBreaker circuitBreaker2 = new CircuitBreaker();
        circuitBreaker2.setId(circuitBreaker1.getId());
        assertThat(circuitBreaker1).isEqualTo(circuitBreaker2);
        circuitBreaker2.setId(2L);
        assertThat(circuitBreaker1).isNotEqualTo(circuitBreaker2);
        circuitBreaker1.setId(null);
        assertThat(circuitBreaker1).isNotEqualTo(circuitBreaker2);
    }
}
