package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CircuitBreakerType;
import com.cges.assetmng.repository.CircuitBreakerTypeRepository;
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
 * Test class for the CircuitBreakerTypeResource REST controller.
 *
 * @see CircuitBreakerTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CircuitBreakerTypeResourceIntTest {

    private static final String DEFAULT_CB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CB_TYPE = "BBBBBBBBBB";

    @Autowired
    private CircuitBreakerTypeRepository circuitBreakerTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCircuitBreakerTypeMockMvc;

    private CircuitBreakerType circuitBreakerType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CircuitBreakerTypeResource circuitBreakerTypeResource = new CircuitBreakerTypeResource(circuitBreakerTypeRepository);
        this.restCircuitBreakerTypeMockMvc = MockMvcBuilders.standaloneSetup(circuitBreakerTypeResource)
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
    public static CircuitBreakerType createEntity(EntityManager em) {
        CircuitBreakerType circuitBreakerType = new CircuitBreakerType()
            .cbType(DEFAULT_CB_TYPE);
        return circuitBreakerType;
    }

    @Before
    public void initTest() {
        circuitBreakerType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCircuitBreakerType() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerTypeRepository.findAll().size();

        // Create the CircuitBreakerType
        restCircuitBreakerTypeMockMvc.perform(post("/api/circuit-breaker-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerType)))
            .andExpect(status().isCreated());

        // Validate the CircuitBreakerType in the database
        List<CircuitBreakerType> circuitBreakerTypeList = circuitBreakerTypeRepository.findAll();
        assertThat(circuitBreakerTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CircuitBreakerType testCircuitBreakerType = circuitBreakerTypeList.get(circuitBreakerTypeList.size() - 1);
        assertThat(testCircuitBreakerType.getCbType()).isEqualTo(DEFAULT_CB_TYPE);
    }

    @Test
    @Transactional
    public void createCircuitBreakerTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerTypeRepository.findAll().size();

        // Create the CircuitBreakerType with an existing ID
        circuitBreakerType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCircuitBreakerTypeMockMvc.perform(post("/api/circuit-breaker-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerType)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreakerType in the database
        List<CircuitBreakerType> circuitBreakerTypeList = circuitBreakerTypeRepository.findAll();
        assertThat(circuitBreakerTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCircuitBreakerTypes() throws Exception {
        // Initialize the database
        circuitBreakerTypeRepository.saveAndFlush(circuitBreakerType);

        // Get all the circuitBreakerTypeList
        restCircuitBreakerTypeMockMvc.perform(get("/api/circuit-breaker-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(circuitBreakerType.getId().intValue())))
            .andExpect(jsonPath("$.[*].cbType").value(hasItem(DEFAULT_CB_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getCircuitBreakerType() throws Exception {
        // Initialize the database
        circuitBreakerTypeRepository.saveAndFlush(circuitBreakerType);

        // Get the circuitBreakerType
        restCircuitBreakerTypeMockMvc.perform(get("/api/circuit-breaker-types/{id}", circuitBreakerType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(circuitBreakerType.getId().intValue()))
            .andExpect(jsonPath("$.cbType").value(DEFAULT_CB_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCircuitBreakerType() throws Exception {
        // Get the circuitBreakerType
        restCircuitBreakerTypeMockMvc.perform(get("/api/circuit-breaker-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCircuitBreakerType() throws Exception {
        // Initialize the database
        circuitBreakerTypeRepository.saveAndFlush(circuitBreakerType);

        int databaseSizeBeforeUpdate = circuitBreakerTypeRepository.findAll().size();

        // Update the circuitBreakerType
        CircuitBreakerType updatedCircuitBreakerType = circuitBreakerTypeRepository.findById(circuitBreakerType.getId()).get();
        // Disconnect from session so that the updates on updatedCircuitBreakerType are not directly saved in db
        em.detach(updatedCircuitBreakerType);
        updatedCircuitBreakerType
            .cbType(UPDATED_CB_TYPE);

        restCircuitBreakerTypeMockMvc.perform(put("/api/circuit-breaker-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCircuitBreakerType)))
            .andExpect(status().isOk());

        // Validate the CircuitBreakerType in the database
        List<CircuitBreakerType> circuitBreakerTypeList = circuitBreakerTypeRepository.findAll();
        assertThat(circuitBreakerTypeList).hasSize(databaseSizeBeforeUpdate);
        CircuitBreakerType testCircuitBreakerType = circuitBreakerTypeList.get(circuitBreakerTypeList.size() - 1);
        assertThat(testCircuitBreakerType.getCbType()).isEqualTo(UPDATED_CB_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCircuitBreakerType() throws Exception {
        int databaseSizeBeforeUpdate = circuitBreakerTypeRepository.findAll().size();

        // Create the CircuitBreakerType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCircuitBreakerTypeMockMvc.perform(put("/api/circuit-breaker-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerType)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreakerType in the database
        List<CircuitBreakerType> circuitBreakerTypeList = circuitBreakerTypeRepository.findAll();
        assertThat(circuitBreakerTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCircuitBreakerType() throws Exception {
        // Initialize the database
        circuitBreakerTypeRepository.saveAndFlush(circuitBreakerType);

        int databaseSizeBeforeDelete = circuitBreakerTypeRepository.findAll().size();

        // Get the circuitBreakerType
        restCircuitBreakerTypeMockMvc.perform(delete("/api/circuit-breaker-types/{id}", circuitBreakerType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CircuitBreakerType> circuitBreakerTypeList = circuitBreakerTypeRepository.findAll();
        assertThat(circuitBreakerTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircuitBreakerType.class);
        CircuitBreakerType circuitBreakerType1 = new CircuitBreakerType();
        circuitBreakerType1.setId(1L);
        CircuitBreakerType circuitBreakerType2 = new CircuitBreakerType();
        circuitBreakerType2.setId(circuitBreakerType1.getId());
        assertThat(circuitBreakerType1).isEqualTo(circuitBreakerType2);
        circuitBreakerType2.setId(2L);
        assertThat(circuitBreakerType1).isNotEqualTo(circuitBreakerType2);
        circuitBreakerType1.setId(null);
        assertThat(circuitBreakerType1).isNotEqualTo(circuitBreakerType2);
    }
}
