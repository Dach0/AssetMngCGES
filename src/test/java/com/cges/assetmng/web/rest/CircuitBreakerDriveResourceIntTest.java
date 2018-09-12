package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CircuitBreakerDrive;
import com.cges.assetmng.repository.CircuitBreakerDriveRepository;
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
 * Test class for the CircuitBreakerDriveResource REST controller.
 *
 * @see CircuitBreakerDriveResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CircuitBreakerDriveResourceIntTest {

    private static final String DEFAULT_CB_DRIVE = "AAAAAAAAAA";
    private static final String UPDATED_CB_DRIVE = "BBBBBBBBBB";

    @Autowired
    private CircuitBreakerDriveRepository circuitBreakerDriveRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCircuitBreakerDriveMockMvc;

    private CircuitBreakerDrive circuitBreakerDrive;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CircuitBreakerDriveResource circuitBreakerDriveResource = new CircuitBreakerDriveResource(circuitBreakerDriveRepository);
        this.restCircuitBreakerDriveMockMvc = MockMvcBuilders.standaloneSetup(circuitBreakerDriveResource)
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
    public static CircuitBreakerDrive createEntity(EntityManager em) {
        CircuitBreakerDrive circuitBreakerDrive = new CircuitBreakerDrive()
            .cbDrive(DEFAULT_CB_DRIVE);
        return circuitBreakerDrive;
    }

    @Before
    public void initTest() {
        circuitBreakerDrive = createEntity(em);
    }

    @Test
    @Transactional
    public void createCircuitBreakerDrive() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerDriveRepository.findAll().size();

        // Create the CircuitBreakerDrive
        restCircuitBreakerDriveMockMvc.perform(post("/api/circuit-breaker-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerDrive)))
            .andExpect(status().isCreated());

        // Validate the CircuitBreakerDrive in the database
        List<CircuitBreakerDrive> circuitBreakerDriveList = circuitBreakerDriveRepository.findAll();
        assertThat(circuitBreakerDriveList).hasSize(databaseSizeBeforeCreate + 1);
        CircuitBreakerDrive testCircuitBreakerDrive = circuitBreakerDriveList.get(circuitBreakerDriveList.size() - 1);
        assertThat(testCircuitBreakerDrive.getCbDrive()).isEqualTo(DEFAULT_CB_DRIVE);
    }

    @Test
    @Transactional
    public void createCircuitBreakerDriveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = circuitBreakerDriveRepository.findAll().size();

        // Create the CircuitBreakerDrive with an existing ID
        circuitBreakerDrive.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCircuitBreakerDriveMockMvc.perform(post("/api/circuit-breaker-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerDrive)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreakerDrive in the database
        List<CircuitBreakerDrive> circuitBreakerDriveList = circuitBreakerDriveRepository.findAll();
        assertThat(circuitBreakerDriveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCircuitBreakerDrives() throws Exception {
        // Initialize the database
        circuitBreakerDriveRepository.saveAndFlush(circuitBreakerDrive);

        // Get all the circuitBreakerDriveList
        restCircuitBreakerDriveMockMvc.perform(get("/api/circuit-breaker-drives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(circuitBreakerDrive.getId().intValue())))
            .andExpect(jsonPath("$.[*].cbDrive").value(hasItem(DEFAULT_CB_DRIVE.toString())));
    }
    

    @Test
    @Transactional
    public void getCircuitBreakerDrive() throws Exception {
        // Initialize the database
        circuitBreakerDriveRepository.saveAndFlush(circuitBreakerDrive);

        // Get the circuitBreakerDrive
        restCircuitBreakerDriveMockMvc.perform(get("/api/circuit-breaker-drives/{id}", circuitBreakerDrive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(circuitBreakerDrive.getId().intValue()))
            .andExpect(jsonPath("$.cbDrive").value(DEFAULT_CB_DRIVE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCircuitBreakerDrive() throws Exception {
        // Get the circuitBreakerDrive
        restCircuitBreakerDriveMockMvc.perform(get("/api/circuit-breaker-drives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCircuitBreakerDrive() throws Exception {
        // Initialize the database
        circuitBreakerDriveRepository.saveAndFlush(circuitBreakerDrive);

        int databaseSizeBeforeUpdate = circuitBreakerDriveRepository.findAll().size();

        // Update the circuitBreakerDrive
        CircuitBreakerDrive updatedCircuitBreakerDrive = circuitBreakerDriveRepository.findById(circuitBreakerDrive.getId()).get();
        // Disconnect from session so that the updates on updatedCircuitBreakerDrive are not directly saved in db
        em.detach(updatedCircuitBreakerDrive);
        updatedCircuitBreakerDrive
            .cbDrive(UPDATED_CB_DRIVE);

        restCircuitBreakerDriveMockMvc.perform(put("/api/circuit-breaker-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCircuitBreakerDrive)))
            .andExpect(status().isOk());

        // Validate the CircuitBreakerDrive in the database
        List<CircuitBreakerDrive> circuitBreakerDriveList = circuitBreakerDriveRepository.findAll();
        assertThat(circuitBreakerDriveList).hasSize(databaseSizeBeforeUpdate);
        CircuitBreakerDrive testCircuitBreakerDrive = circuitBreakerDriveList.get(circuitBreakerDriveList.size() - 1);
        assertThat(testCircuitBreakerDrive.getCbDrive()).isEqualTo(UPDATED_CB_DRIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingCircuitBreakerDrive() throws Exception {
        int databaseSizeBeforeUpdate = circuitBreakerDriveRepository.findAll().size();

        // Create the CircuitBreakerDrive

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCircuitBreakerDriveMockMvc.perform(put("/api/circuit-breaker-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circuitBreakerDrive)))
            .andExpect(status().isBadRequest());

        // Validate the CircuitBreakerDrive in the database
        List<CircuitBreakerDrive> circuitBreakerDriveList = circuitBreakerDriveRepository.findAll();
        assertThat(circuitBreakerDriveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCircuitBreakerDrive() throws Exception {
        // Initialize the database
        circuitBreakerDriveRepository.saveAndFlush(circuitBreakerDrive);

        int databaseSizeBeforeDelete = circuitBreakerDriveRepository.findAll().size();

        // Get the circuitBreakerDrive
        restCircuitBreakerDriveMockMvc.perform(delete("/api/circuit-breaker-drives/{id}", circuitBreakerDrive.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CircuitBreakerDrive> circuitBreakerDriveList = circuitBreakerDriveRepository.findAll();
        assertThat(circuitBreakerDriveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircuitBreakerDrive.class);
        CircuitBreakerDrive circuitBreakerDrive1 = new CircuitBreakerDrive();
        circuitBreakerDrive1.setId(1L);
        CircuitBreakerDrive circuitBreakerDrive2 = new CircuitBreakerDrive();
        circuitBreakerDrive2.setId(circuitBreakerDrive1.getId());
        assertThat(circuitBreakerDrive1).isEqualTo(circuitBreakerDrive2);
        circuitBreakerDrive2.setId(2L);
        assertThat(circuitBreakerDrive1).isNotEqualTo(circuitBreakerDrive2);
        circuitBreakerDrive1.setId(null);
        assertThat(circuitBreakerDrive1).isNotEqualTo(circuitBreakerDrive2);
    }
}
