package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Power;
import com.cges.assetmng.repository.PowerRepository;
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
 * Test class for the PowerResource REST controller.
 *
 * @see PowerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class PowerResourceIntTest {

    private static final String DEFAULT_POWER = "AAAAAAAAAA";
    private static final String UPDATED_POWER = "BBBBBBBBBB";

    @Autowired
    private PowerRepository powerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPowerMockMvc;

    private Power power;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PowerResource powerResource = new PowerResource(powerRepository);
        this.restPowerMockMvc = MockMvcBuilders.standaloneSetup(powerResource)
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
    public static Power createEntity(EntityManager em) {
        Power power = new Power()
            .power(DEFAULT_POWER);
        return power;
    }

    @Before
    public void initTest() {
        power = createEntity(em);
    }

    @Test
    @Transactional
    public void createPower() throws Exception {
        int databaseSizeBeforeCreate = powerRepository.findAll().size();

        // Create the Power
        restPowerMockMvc.perform(post("/api/powers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(power)))
            .andExpect(status().isCreated());

        // Validate the Power in the database
        List<Power> powerList = powerRepository.findAll();
        assertThat(powerList).hasSize(databaseSizeBeforeCreate + 1);
        Power testPower = powerList.get(powerList.size() - 1);
        assertThat(testPower.getPower()).isEqualTo(DEFAULT_POWER);
    }

    @Test
    @Transactional
    public void createPowerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = powerRepository.findAll().size();

        // Create the Power with an existing ID
        power.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPowerMockMvc.perform(post("/api/powers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(power)))
            .andExpect(status().isBadRequest());

        // Validate the Power in the database
        List<Power> powerList = powerRepository.findAll();
        assertThat(powerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPowers() throws Exception {
        // Initialize the database
        powerRepository.saveAndFlush(power);

        // Get all the powerList
        restPowerMockMvc.perform(get("/api/powers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(power.getId().intValue())))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER.toString())));
    }
    

    @Test
    @Transactional
    public void getPower() throws Exception {
        // Initialize the database
        powerRepository.saveAndFlush(power);

        // Get the power
        restPowerMockMvc.perform(get("/api/powers/{id}", power.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(power.getId().intValue()))
            .andExpect(jsonPath("$.power").value(DEFAULT_POWER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPower() throws Exception {
        // Get the power
        restPowerMockMvc.perform(get("/api/powers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePower() throws Exception {
        // Initialize the database
        powerRepository.saveAndFlush(power);

        int databaseSizeBeforeUpdate = powerRepository.findAll().size();

        // Update the power
        Power updatedPower = powerRepository.findById(power.getId()).get();
        // Disconnect from session so that the updates on updatedPower are not directly saved in db
        em.detach(updatedPower);
        updatedPower
            .power(UPDATED_POWER);

        restPowerMockMvc.perform(put("/api/powers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPower)))
            .andExpect(status().isOk());

        // Validate the Power in the database
        List<Power> powerList = powerRepository.findAll();
        assertThat(powerList).hasSize(databaseSizeBeforeUpdate);
        Power testPower = powerList.get(powerList.size() - 1);
        assertThat(testPower.getPower()).isEqualTo(UPDATED_POWER);
    }

    @Test
    @Transactional
    public void updateNonExistingPower() throws Exception {
        int databaseSizeBeforeUpdate = powerRepository.findAll().size();

        // Create the Power

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPowerMockMvc.perform(put("/api/powers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(power)))
            .andExpect(status().isBadRequest());

        // Validate the Power in the database
        List<Power> powerList = powerRepository.findAll();
        assertThat(powerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePower() throws Exception {
        // Initialize the database
        powerRepository.saveAndFlush(power);

        int databaseSizeBeforeDelete = powerRepository.findAll().size();

        // Get the power
        restPowerMockMvc.perform(delete("/api/powers/{id}", power.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Power> powerList = powerRepository.findAll();
        assertThat(powerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Power.class);
        Power power1 = new Power();
        power1.setId(1L);
        Power power2 = new Power();
        power2.setId(power1.getId());
        assertThat(power1).isEqualTo(power2);
        power2.setId(2L);
        assertThat(power1).isNotEqualTo(power2);
        power1.setId(null);
        assertThat(power1).isNotEqualTo(power2);
    }
}
