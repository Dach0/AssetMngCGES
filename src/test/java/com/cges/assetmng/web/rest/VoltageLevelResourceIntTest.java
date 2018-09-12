package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.VoltageLevel;
import com.cges.assetmng.repository.VoltageLevelRepository;
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
 * Test class for the VoltageLevelResource REST controller.
 *
 * @see VoltageLevelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class VoltageLevelResourceIntTest {

    private static final Integer DEFAULT_VOLTAGE_LEVEL = 1;
    private static final Integer UPDATED_VOLTAGE_LEVEL = 2;

    @Autowired
    private VoltageLevelRepository voltageLevelRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVoltageLevelMockMvc;

    private VoltageLevel voltageLevel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VoltageLevelResource voltageLevelResource = new VoltageLevelResource(voltageLevelRepository);
        this.restVoltageLevelMockMvc = MockMvcBuilders.standaloneSetup(voltageLevelResource)
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
    public static VoltageLevel createEntity(EntityManager em) {
        VoltageLevel voltageLevel = new VoltageLevel()
            .voltageLevel(DEFAULT_VOLTAGE_LEVEL);
        return voltageLevel;
    }

    @Before
    public void initTest() {
        voltageLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoltageLevel() throws Exception {
        int databaseSizeBeforeCreate = voltageLevelRepository.findAll().size();

        // Create the VoltageLevel
        restVoltageLevelMockMvc.perform(post("/api/voltage-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageLevel)))
            .andExpect(status().isCreated());

        // Validate the VoltageLevel in the database
        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeCreate + 1);
        VoltageLevel testVoltageLevel = voltageLevelList.get(voltageLevelList.size() - 1);
        assertThat(testVoltageLevel.getVoltageLevel()).isEqualTo(DEFAULT_VOLTAGE_LEVEL);
    }

    @Test
    @Transactional
    public void createVoltageLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voltageLevelRepository.findAll().size();

        // Create the VoltageLevel with an existing ID
        voltageLevel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoltageLevelMockMvc.perform(post("/api/voltage-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageLevel)))
            .andExpect(status().isBadRequest());

        // Validate the VoltageLevel in the database
        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVoltageLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = voltageLevelRepository.findAll().size();
        // set the field null
        voltageLevel.setVoltageLevel(null);

        // Create the VoltageLevel, which fails.

        restVoltageLevelMockMvc.perform(post("/api/voltage-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageLevel)))
            .andExpect(status().isBadRequest());

        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVoltageLevels() throws Exception {
        // Initialize the database
        voltageLevelRepository.saveAndFlush(voltageLevel);

        // Get all the voltageLevelList
        restVoltageLevelMockMvc.perform(get("/api/voltage-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voltageLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].voltageLevel").value(hasItem(DEFAULT_VOLTAGE_LEVEL)));
    }
    

    @Test
    @Transactional
    public void getVoltageLevel() throws Exception {
        // Initialize the database
        voltageLevelRepository.saveAndFlush(voltageLevel);

        // Get the voltageLevel
        restVoltageLevelMockMvc.perform(get("/api/voltage-levels/{id}", voltageLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(voltageLevel.getId().intValue()))
            .andExpect(jsonPath("$.voltageLevel").value(DEFAULT_VOLTAGE_LEVEL));
    }
    @Test
    @Transactional
    public void getNonExistingVoltageLevel() throws Exception {
        // Get the voltageLevel
        restVoltageLevelMockMvc.perform(get("/api/voltage-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoltageLevel() throws Exception {
        // Initialize the database
        voltageLevelRepository.saveAndFlush(voltageLevel);

        int databaseSizeBeforeUpdate = voltageLevelRepository.findAll().size();

        // Update the voltageLevel
        VoltageLevel updatedVoltageLevel = voltageLevelRepository.findById(voltageLevel.getId()).get();
        // Disconnect from session so that the updates on updatedVoltageLevel are not directly saved in db
        em.detach(updatedVoltageLevel);
        updatedVoltageLevel
            .voltageLevel(UPDATED_VOLTAGE_LEVEL);

        restVoltageLevelMockMvc.perform(put("/api/voltage-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoltageLevel)))
            .andExpect(status().isOk());

        // Validate the VoltageLevel in the database
        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeUpdate);
        VoltageLevel testVoltageLevel = voltageLevelList.get(voltageLevelList.size() - 1);
        assertThat(testVoltageLevel.getVoltageLevel()).isEqualTo(UPDATED_VOLTAGE_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingVoltageLevel() throws Exception {
        int databaseSizeBeforeUpdate = voltageLevelRepository.findAll().size();

        // Create the VoltageLevel

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVoltageLevelMockMvc.perform(put("/api/voltage-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voltageLevel)))
            .andExpect(status().isBadRequest());

        // Validate the VoltageLevel in the database
        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoltageLevel() throws Exception {
        // Initialize the database
        voltageLevelRepository.saveAndFlush(voltageLevel);

        int databaseSizeBeforeDelete = voltageLevelRepository.findAll().size();

        // Get the voltageLevel
        restVoltageLevelMockMvc.perform(delete("/api/voltage-levels/{id}", voltageLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VoltageLevel> voltageLevelList = voltageLevelRepository.findAll();
        assertThat(voltageLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoltageLevel.class);
        VoltageLevel voltageLevel1 = new VoltageLevel();
        voltageLevel1.setId(1L);
        VoltageLevel voltageLevel2 = new VoltageLevel();
        voltageLevel2.setId(voltageLevel1.getId());
        assertThat(voltageLevel1).isEqualTo(voltageLevel2);
        voltageLevel2.setId(2L);
        assertThat(voltageLevel1).isNotEqualTo(voltageLevel2);
        voltageLevel1.setId(null);
        assertThat(voltageLevel1).isNotEqualTo(voltageLevel2);
    }
}
