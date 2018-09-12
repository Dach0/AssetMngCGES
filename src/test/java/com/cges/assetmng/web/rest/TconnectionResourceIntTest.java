package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Tconnection;
import com.cges.assetmng.repository.TconnectionRepository;
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
 * Test class for the TconnectionResource REST controller.
 *
 * @see TconnectionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TconnectionResourceIntTest {

    private static final String DEFAULT_T_CONN_SUB_STATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_T_CONN_SUB_STATION_NAME = "BBBBBBBBBB";

    @Autowired
    private TconnectionRepository tconnectionRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTconnectionMockMvc;

    private Tconnection tconnection;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TconnectionResource tconnectionResource = new TconnectionResource(tconnectionRepository);
        this.restTconnectionMockMvc = MockMvcBuilders.standaloneSetup(tconnectionResource)
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
    public static Tconnection createEntity(EntityManager em) {
        Tconnection tconnection = new Tconnection()
            .tConnSubStationName(DEFAULT_T_CONN_SUB_STATION_NAME);
        return tconnection;
    }

    @Before
    public void initTest() {
        tconnection = createEntity(em);
    }

    @Test
    @Transactional
    public void createTconnection() throws Exception {
        int databaseSizeBeforeCreate = tconnectionRepository.findAll().size();

        // Create the Tconnection
        restTconnectionMockMvc.perform(post("/api/tconnections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tconnection)))
            .andExpect(status().isCreated());

        // Validate the Tconnection in the database
        List<Tconnection> tconnectionList = tconnectionRepository.findAll();
        assertThat(tconnectionList).hasSize(databaseSizeBeforeCreate + 1);
        Tconnection testTconnection = tconnectionList.get(tconnectionList.size() - 1);
        assertThat(testTconnection.gettConnSubStationName()).isEqualTo(DEFAULT_T_CONN_SUB_STATION_NAME);
    }

    @Test
    @Transactional
    public void createTconnectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tconnectionRepository.findAll().size();

        // Create the Tconnection with an existing ID
        tconnection.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTconnectionMockMvc.perform(post("/api/tconnections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tconnection)))
            .andExpect(status().isBadRequest());

        // Validate the Tconnection in the database
        List<Tconnection> tconnectionList = tconnectionRepository.findAll();
        assertThat(tconnectionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTconnections() throws Exception {
        // Initialize the database
        tconnectionRepository.saveAndFlush(tconnection);

        // Get all the tconnectionList
        restTconnectionMockMvc.perform(get("/api/tconnections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tconnection.getId().intValue())))
            .andExpect(jsonPath("$.[*].tConnSubStationName").value(hasItem(DEFAULT_T_CONN_SUB_STATION_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getTconnection() throws Exception {
        // Initialize the database
        tconnectionRepository.saveAndFlush(tconnection);

        // Get the tconnection
        restTconnectionMockMvc.perform(get("/api/tconnections/{id}", tconnection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tconnection.getId().intValue()))
            .andExpect(jsonPath("$.tConnSubStationName").value(DEFAULT_T_CONN_SUB_STATION_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTconnection() throws Exception {
        // Get the tconnection
        restTconnectionMockMvc.perform(get("/api/tconnections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTconnection() throws Exception {
        // Initialize the database
        tconnectionRepository.saveAndFlush(tconnection);

        int databaseSizeBeforeUpdate = tconnectionRepository.findAll().size();

        // Update the tconnection
        Tconnection updatedTconnection = tconnectionRepository.findById(tconnection.getId()).get();
        // Disconnect from session so that the updates on updatedTconnection are not directly saved in db
        em.detach(updatedTconnection);
        updatedTconnection
            .tConnSubStationName(UPDATED_T_CONN_SUB_STATION_NAME);

        restTconnectionMockMvc.perform(put("/api/tconnections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTconnection)))
            .andExpect(status().isOk());

        // Validate the Tconnection in the database
        List<Tconnection> tconnectionList = tconnectionRepository.findAll();
        assertThat(tconnectionList).hasSize(databaseSizeBeforeUpdate);
        Tconnection testTconnection = tconnectionList.get(tconnectionList.size() - 1);
        assertThat(testTconnection.gettConnSubStationName()).isEqualTo(UPDATED_T_CONN_SUB_STATION_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTconnection() throws Exception {
        int databaseSizeBeforeUpdate = tconnectionRepository.findAll().size();

        // Create the Tconnection

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTconnectionMockMvc.perform(put("/api/tconnections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tconnection)))
            .andExpect(status().isBadRequest());

        // Validate the Tconnection in the database
        List<Tconnection> tconnectionList = tconnectionRepository.findAll();
        assertThat(tconnectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTconnection() throws Exception {
        // Initialize the database
        tconnectionRepository.saveAndFlush(tconnection);

        int databaseSizeBeforeDelete = tconnectionRepository.findAll().size();

        // Get the tconnection
        restTconnectionMockMvc.perform(delete("/api/tconnections/{id}", tconnection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tconnection> tconnectionList = tconnectionRepository.findAll();
        assertThat(tconnectionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tconnection.class);
        Tconnection tconnection1 = new Tconnection();
        tconnection1.setId(1L);
        Tconnection tconnection2 = new Tconnection();
        tconnection2.setId(tconnection1.getId());
        assertThat(tconnection1).isEqualTo(tconnection2);
        tconnection2.setId(2L);
        assertThat(tconnection1).isNotEqualTo(tconnection2);
        tconnection1.setId(null);
        assertThat(tconnection1).isNotEqualTo(tconnection2);
    }
}
