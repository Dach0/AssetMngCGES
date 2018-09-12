package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.ThermalLimit;
import com.cges.assetmng.repository.ThermalLimitRepository;
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
 * Test class for the ThermalLimitResource REST controller.
 *
 * @see ThermalLimitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class ThermalLimitResourceIntTest {

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_I_MAX_SUMMER = 1;
    private static final Integer UPDATED_I_MAX_SUMMER = 2;

    private static final Integer DEFAULT_I_MAX_WINTER = 1;
    private static final Integer UPDATED_I_MAX_WINTER = 2;

    private static final Integer DEFAULT_P_MAX_SUMMER = 1;
    private static final Integer UPDATED_P_MAX_SUMMER = 2;

    private static final Integer DEFAULT_P_MAX_WINTER = 1;
    private static final Integer UPDATED_P_MAX_WINTER = 2;

    private static final Integer DEFAULT_THERMAL_LIMIT = 1;
    private static final Integer UPDATED_THERMAL_LIMIT = 2;

    @Autowired
    private ThermalLimitRepository thermalLimitRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restThermalLimitMockMvc;

    private ThermalLimit thermalLimit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThermalLimitResource thermalLimitResource = new ThermalLimitResource(thermalLimitRepository);
        this.restThermalLimitMockMvc = MockMvcBuilders.standaloneSetup(thermalLimitResource)
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
    public static ThermalLimit createEntity(EntityManager em) {
        ThermalLimit thermalLimit = new ThermalLimit()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .iMaxSummer(DEFAULT_I_MAX_SUMMER)
            .iMaxWinter(DEFAULT_I_MAX_WINTER)
            .pMaxSummer(DEFAULT_P_MAX_SUMMER)
            .pMaxWinter(DEFAULT_P_MAX_WINTER)
            .thermalLimit(DEFAULT_THERMAL_LIMIT);
        return thermalLimit;
    }

    @Before
    public void initTest() {
        thermalLimit = createEntity(em);
    }

    @Test
    @Transactional
    public void createThermalLimit() throws Exception {
        int databaseSizeBeforeCreate = thermalLimitRepository.findAll().size();

        // Create the ThermalLimit
        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isCreated());

        // Validate the ThermalLimit in the database
        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeCreate + 1);
        ThermalLimit testThermalLimit = thermalLimitList.get(thermalLimitList.size() - 1);
        assertThat(testThermalLimit.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testThermalLimit.getiMaxSummer()).isEqualTo(DEFAULT_I_MAX_SUMMER);
        assertThat(testThermalLimit.getiMaxWinter()).isEqualTo(DEFAULT_I_MAX_WINTER);
        assertThat(testThermalLimit.getpMaxSummer()).isEqualTo(DEFAULT_P_MAX_SUMMER);
        assertThat(testThermalLimit.getpMaxWinter()).isEqualTo(DEFAULT_P_MAX_WINTER);
        assertThat(testThermalLimit.getThermalLimit()).isEqualTo(DEFAULT_THERMAL_LIMIT);
    }

    @Test
    @Transactional
    public void createThermalLimitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thermalLimitRepository.findAll().size();

        // Create the ThermalLimit with an existing ID
        thermalLimit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        // Validate the ThermalLimit in the database
        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTemplateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = thermalLimitRepository.findAll().size();
        // set the field null
        thermalLimit.setTemplateName(null);

        // Create the ThermalLimit, which fails.

        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkiMaxSummerIsRequired() throws Exception {
        int databaseSizeBeforeTest = thermalLimitRepository.findAll().size();
        // set the field null
        thermalLimit.setiMaxSummer(null);

        // Create the ThermalLimit, which fails.

        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkiMaxWinterIsRequired() throws Exception {
        int databaseSizeBeforeTest = thermalLimitRepository.findAll().size();
        // set the field null
        thermalLimit.setiMaxWinter(null);

        // Create the ThermalLimit, which fails.

        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkpMaxSummerIsRequired() throws Exception {
        int databaseSizeBeforeTest = thermalLimitRepository.findAll().size();
        // set the field null
        thermalLimit.setpMaxSummer(null);

        // Create the ThermalLimit, which fails.

        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkpMaxWinterIsRequired() throws Exception {
        int databaseSizeBeforeTest = thermalLimitRepository.findAll().size();
        // set the field null
        thermalLimit.setpMaxWinter(null);

        // Create the ThermalLimit, which fails.

        restThermalLimitMockMvc.perform(post("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllThermalLimits() throws Exception {
        // Initialize the database
        thermalLimitRepository.saveAndFlush(thermalLimit);

        // Get all the thermalLimitList
        restThermalLimitMockMvc.perform(get("/api/thermal-limits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thermalLimit.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].iMaxSummer").value(hasItem(DEFAULT_I_MAX_SUMMER)))
            .andExpect(jsonPath("$.[*].iMaxWinter").value(hasItem(DEFAULT_I_MAX_WINTER)))
            .andExpect(jsonPath("$.[*].pMaxSummer").value(hasItem(DEFAULT_P_MAX_SUMMER)))
            .andExpect(jsonPath("$.[*].pMaxWinter").value(hasItem(DEFAULT_P_MAX_WINTER)))
            .andExpect(jsonPath("$.[*].thermalLimit").value(hasItem(DEFAULT_THERMAL_LIMIT)));
    }
    

    @Test
    @Transactional
    public void getThermalLimit() throws Exception {
        // Initialize the database
        thermalLimitRepository.saveAndFlush(thermalLimit);

        // Get the thermalLimit
        restThermalLimitMockMvc.perform(get("/api/thermal-limits/{id}", thermalLimit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thermalLimit.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME.toString()))
            .andExpect(jsonPath("$.iMaxSummer").value(DEFAULT_I_MAX_SUMMER))
            .andExpect(jsonPath("$.iMaxWinter").value(DEFAULT_I_MAX_WINTER))
            .andExpect(jsonPath("$.pMaxSummer").value(DEFAULT_P_MAX_SUMMER))
            .andExpect(jsonPath("$.pMaxWinter").value(DEFAULT_P_MAX_WINTER))
            .andExpect(jsonPath("$.thermalLimit").value(DEFAULT_THERMAL_LIMIT));
    }
    @Test
    @Transactional
    public void getNonExistingThermalLimit() throws Exception {
        // Get the thermalLimit
        restThermalLimitMockMvc.perform(get("/api/thermal-limits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThermalLimit() throws Exception {
        // Initialize the database
        thermalLimitRepository.saveAndFlush(thermalLimit);

        int databaseSizeBeforeUpdate = thermalLimitRepository.findAll().size();

        // Update the thermalLimit
        ThermalLimit updatedThermalLimit = thermalLimitRepository.findById(thermalLimit.getId()).get();
        // Disconnect from session so that the updates on updatedThermalLimit are not directly saved in db
        em.detach(updatedThermalLimit);
        updatedThermalLimit
            .templateName(UPDATED_TEMPLATE_NAME)
            .iMaxSummer(UPDATED_I_MAX_SUMMER)
            .iMaxWinter(UPDATED_I_MAX_WINTER)
            .pMaxSummer(UPDATED_P_MAX_SUMMER)
            .pMaxWinter(UPDATED_P_MAX_WINTER)
            .thermalLimit(UPDATED_THERMAL_LIMIT);

        restThermalLimitMockMvc.perform(put("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedThermalLimit)))
            .andExpect(status().isOk());

        // Validate the ThermalLimit in the database
        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeUpdate);
        ThermalLimit testThermalLimit = thermalLimitList.get(thermalLimitList.size() - 1);
        assertThat(testThermalLimit.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testThermalLimit.getiMaxSummer()).isEqualTo(UPDATED_I_MAX_SUMMER);
        assertThat(testThermalLimit.getiMaxWinter()).isEqualTo(UPDATED_I_MAX_WINTER);
        assertThat(testThermalLimit.getpMaxSummer()).isEqualTo(UPDATED_P_MAX_SUMMER);
        assertThat(testThermalLimit.getpMaxWinter()).isEqualTo(UPDATED_P_MAX_WINTER);
        assertThat(testThermalLimit.getThermalLimit()).isEqualTo(UPDATED_THERMAL_LIMIT);
    }

    @Test
    @Transactional
    public void updateNonExistingThermalLimit() throws Exception {
        int databaseSizeBeforeUpdate = thermalLimitRepository.findAll().size();

        // Create the ThermalLimit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restThermalLimitMockMvc.perform(put("/api/thermal-limits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thermalLimit)))
            .andExpect(status().isBadRequest());

        // Validate the ThermalLimit in the database
        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThermalLimit() throws Exception {
        // Initialize the database
        thermalLimitRepository.saveAndFlush(thermalLimit);

        int databaseSizeBeforeDelete = thermalLimitRepository.findAll().size();

        // Get the thermalLimit
        restThermalLimitMockMvc.perform(delete("/api/thermal-limits/{id}", thermalLimit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ThermalLimit> thermalLimitList = thermalLimitRepository.findAll();
        assertThat(thermalLimitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThermalLimit.class);
        ThermalLimit thermalLimit1 = new ThermalLimit();
        thermalLimit1.setId(1L);
        ThermalLimit thermalLimit2 = new ThermalLimit();
        thermalLimit2.setId(thermalLimit1.getId());
        assertThat(thermalLimit1).isEqualTo(thermalLimit2);
        thermalLimit2.setId(2L);
        assertThat(thermalLimit1).isNotEqualTo(thermalLimit2);
        thermalLimit1.setId(null);
        assertThat(thermalLimit1).isNotEqualTo(thermalLimit2);
    }
}
