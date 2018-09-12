package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.DisconnectorBusBar;
import com.cges.assetmng.repository.DisconnectorBusBarRepository;
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
 * Test class for the DisconnectorBusBarResource REST controller.
 *
 * @see DisconnectorBusBarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class DisconnectorBusBarResourceIntTest {

    private static final Integer DEFAULT_PRODUCTION_YEAR = 1;
    private static final Integer UPDATED_PRODUCTION_YEAR = 2;

    private static final Integer DEFAULT_CURRENT = 1;
    private static final Integer UPDATED_CURRENT = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    @Autowired
    private DisconnectorBusBarRepository disconnectorBusBarRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisconnectorBusBarMockMvc;

    private DisconnectorBusBar disconnectorBusBar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisconnectorBusBarResource disconnectorBusBarResource = new DisconnectorBusBarResource(disconnectorBusBarRepository);
        this.restDisconnectorBusBarMockMvc = MockMvcBuilders.standaloneSetup(disconnectorBusBarResource)
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
    public static DisconnectorBusBar createEntity(EntityManager em) {
        DisconnectorBusBar disconnectorBusBar = new DisconnectorBusBar()
            .productionYear(DEFAULT_PRODUCTION_YEAR)
            .current(DEFAULT_CURRENT)
            .serial(DEFAULT_SERIAL)
            .picture(DEFAULT_PICTURE);
        return disconnectorBusBar;
    }

    @Before
    public void initTest() {
        disconnectorBusBar = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisconnectorBusBar() throws Exception {
        int databaseSizeBeforeCreate = disconnectorBusBarRepository.findAll().size();

        // Create the DisconnectorBusBar
        restDisconnectorBusBarMockMvc.perform(post("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isCreated());

        // Validate the DisconnectorBusBar in the database
        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeCreate + 1);
        DisconnectorBusBar testDisconnectorBusBar = disconnectorBusBarList.get(disconnectorBusBarList.size() - 1);
        assertThat(testDisconnectorBusBar.getProductionYear()).isEqualTo(DEFAULT_PRODUCTION_YEAR);
        assertThat(testDisconnectorBusBar.getCurrent()).isEqualTo(DEFAULT_CURRENT);
        assertThat(testDisconnectorBusBar.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testDisconnectorBusBar.getPicture()).isEqualTo(DEFAULT_PICTURE);
    }

    @Test
    @Transactional
    public void createDisconnectorBusBarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disconnectorBusBarRepository.findAll().size();

        // Create the DisconnectorBusBar with an existing ID
        disconnectorBusBar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisconnectorBusBarMockMvc.perform(post("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorBusBar in the database
        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductionYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorBusBarRepository.findAll().size();
        // set the field null
        disconnectorBusBar.setProductionYear(null);

        // Create the DisconnectorBusBar, which fails.

        restDisconnectorBusBarMockMvc.perform(post("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isBadRequest());

        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrentIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorBusBarRepository.findAll().size();
        // set the field null
        disconnectorBusBar.setCurrent(null);

        // Create the DisconnectorBusBar, which fails.

        restDisconnectorBusBarMockMvc.perform(post("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isBadRequest());

        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = disconnectorBusBarRepository.findAll().size();
        // set the field null
        disconnectorBusBar.setSerial(null);

        // Create the DisconnectorBusBar, which fails.

        restDisconnectorBusBarMockMvc.perform(post("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isBadRequest());

        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDisconnectorBusBars() throws Exception {
        // Initialize the database
        disconnectorBusBarRepository.saveAndFlush(disconnectorBusBar);

        // Get all the disconnectorBusBarList
        restDisconnectorBusBarMockMvc.perform(get("/api/disconnector-bus-bars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disconnectorBusBar.getId().intValue())))
            .andExpect(jsonPath("$.[*].productionYear").value(hasItem(DEFAULT_PRODUCTION_YEAR)))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())));
    }
    

    @Test
    @Transactional
    public void getDisconnectorBusBar() throws Exception {
        // Initialize the database
        disconnectorBusBarRepository.saveAndFlush(disconnectorBusBar);

        // Get the disconnectorBusBar
        restDisconnectorBusBarMockMvc.perform(get("/api/disconnector-bus-bars/{id}", disconnectorBusBar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disconnectorBusBar.getId().intValue()))
            .andExpect(jsonPath("$.productionYear").value(DEFAULT_PRODUCTION_YEAR))
            .andExpect(jsonPath("$.current").value(DEFAULT_CURRENT))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDisconnectorBusBar() throws Exception {
        // Get the disconnectorBusBar
        restDisconnectorBusBarMockMvc.perform(get("/api/disconnector-bus-bars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisconnectorBusBar() throws Exception {
        // Initialize the database
        disconnectorBusBarRepository.saveAndFlush(disconnectorBusBar);

        int databaseSizeBeforeUpdate = disconnectorBusBarRepository.findAll().size();

        // Update the disconnectorBusBar
        DisconnectorBusBar updatedDisconnectorBusBar = disconnectorBusBarRepository.findById(disconnectorBusBar.getId()).get();
        // Disconnect from session so that the updates on updatedDisconnectorBusBar are not directly saved in db
        em.detach(updatedDisconnectorBusBar);
        updatedDisconnectorBusBar
            .productionYear(UPDATED_PRODUCTION_YEAR)
            .current(UPDATED_CURRENT)
            .serial(UPDATED_SERIAL)
            .picture(UPDATED_PICTURE);

        restDisconnectorBusBarMockMvc.perform(put("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisconnectorBusBar)))
            .andExpect(status().isOk());

        // Validate the DisconnectorBusBar in the database
        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeUpdate);
        DisconnectorBusBar testDisconnectorBusBar = disconnectorBusBarList.get(disconnectorBusBarList.size() - 1);
        assertThat(testDisconnectorBusBar.getProductionYear()).isEqualTo(UPDATED_PRODUCTION_YEAR);
        assertThat(testDisconnectorBusBar.getCurrent()).isEqualTo(UPDATED_CURRENT);
        assertThat(testDisconnectorBusBar.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testDisconnectorBusBar.getPicture()).isEqualTo(UPDATED_PICTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisconnectorBusBar() throws Exception {
        int databaseSizeBeforeUpdate = disconnectorBusBarRepository.findAll().size();

        // Create the DisconnectorBusBar

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisconnectorBusBarMockMvc.perform(put("/api/disconnector-bus-bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorBusBar)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorBusBar in the database
        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisconnectorBusBar() throws Exception {
        // Initialize the database
        disconnectorBusBarRepository.saveAndFlush(disconnectorBusBar);

        int databaseSizeBeforeDelete = disconnectorBusBarRepository.findAll().size();

        // Get the disconnectorBusBar
        restDisconnectorBusBarMockMvc.perform(delete("/api/disconnector-bus-bars/{id}", disconnectorBusBar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisconnectorBusBar> disconnectorBusBarList = disconnectorBusBarRepository.findAll();
        assertThat(disconnectorBusBarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisconnectorBusBar.class);
        DisconnectorBusBar disconnectorBusBar1 = new DisconnectorBusBar();
        disconnectorBusBar1.setId(1L);
        DisconnectorBusBar disconnectorBusBar2 = new DisconnectorBusBar();
        disconnectorBusBar2.setId(disconnectorBusBar1.getId());
        assertThat(disconnectorBusBar1).isEqualTo(disconnectorBusBar2);
        disconnectorBusBar2.setId(2L);
        assertThat(disconnectorBusBar1).isNotEqualTo(disconnectorBusBar2);
        disconnectorBusBar1.setId(null);
        assertThat(disconnectorBusBar1).isNotEqualTo(disconnectorBusBar2);
    }
}
