package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.ConductorCrossSect;
import com.cges.assetmng.repository.ConductorCrossSectRepository;
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
 * Test class for the ConductorCrossSectResource REST controller.
 *
 * @see ConductorCrossSectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class ConductorCrossSectResourceIntTest {

    private static final String DEFAULT_CONDUCTOR_CROSS_SECTION = "AAAAAAAAAA";
    private static final String UPDATED_CONDUCTOR_CROSS_SECTION = "BBBBBBBBBB";

    @Autowired
    private ConductorCrossSectRepository conductorCrossSectRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConductorCrossSectMockMvc;

    private ConductorCrossSect conductorCrossSect;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConductorCrossSectResource conductorCrossSectResource = new ConductorCrossSectResource(conductorCrossSectRepository);
        this.restConductorCrossSectMockMvc = MockMvcBuilders.standaloneSetup(conductorCrossSectResource)
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
    public static ConductorCrossSect createEntity(EntityManager em) {
        ConductorCrossSect conductorCrossSect = new ConductorCrossSect()
            .conductorCrossSection(DEFAULT_CONDUCTOR_CROSS_SECTION);
        return conductorCrossSect;
    }

    @Before
    public void initTest() {
        conductorCrossSect = createEntity(em);
    }

    @Test
    @Transactional
    public void createConductorCrossSect() throws Exception {
        int databaseSizeBeforeCreate = conductorCrossSectRepository.findAll().size();

        // Create the ConductorCrossSect
        restConductorCrossSectMockMvc.perform(post("/api/conductor-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conductorCrossSect)))
            .andExpect(status().isCreated());

        // Validate the ConductorCrossSect in the database
        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeCreate + 1);
        ConductorCrossSect testConductorCrossSect = conductorCrossSectList.get(conductorCrossSectList.size() - 1);
        assertThat(testConductorCrossSect.getConductorCrossSection()).isEqualTo(DEFAULT_CONDUCTOR_CROSS_SECTION);
    }

    @Test
    @Transactional
    public void createConductorCrossSectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conductorCrossSectRepository.findAll().size();

        // Create the ConductorCrossSect with an existing ID
        conductorCrossSect.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConductorCrossSectMockMvc.perform(post("/api/conductor-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conductorCrossSect)))
            .andExpect(status().isBadRequest());

        // Validate the ConductorCrossSect in the database
        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkConductorCrossSectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = conductorCrossSectRepository.findAll().size();
        // set the field null
        conductorCrossSect.setConductorCrossSection(null);

        // Create the ConductorCrossSect, which fails.

        restConductorCrossSectMockMvc.perform(post("/api/conductor-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conductorCrossSect)))
            .andExpect(status().isBadRequest());

        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConductorCrossSects() throws Exception {
        // Initialize the database
        conductorCrossSectRepository.saveAndFlush(conductorCrossSect);

        // Get all the conductorCrossSectList
        restConductorCrossSectMockMvc.perform(get("/api/conductor-cross-sects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conductorCrossSect.getId().intValue())))
            .andExpect(jsonPath("$.[*].conductorCrossSection").value(hasItem(DEFAULT_CONDUCTOR_CROSS_SECTION.toString())));
    }
    

    @Test
    @Transactional
    public void getConductorCrossSect() throws Exception {
        // Initialize the database
        conductorCrossSectRepository.saveAndFlush(conductorCrossSect);

        // Get the conductorCrossSect
        restConductorCrossSectMockMvc.perform(get("/api/conductor-cross-sects/{id}", conductorCrossSect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conductorCrossSect.getId().intValue()))
            .andExpect(jsonPath("$.conductorCrossSection").value(DEFAULT_CONDUCTOR_CROSS_SECTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingConductorCrossSect() throws Exception {
        // Get the conductorCrossSect
        restConductorCrossSectMockMvc.perform(get("/api/conductor-cross-sects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConductorCrossSect() throws Exception {
        // Initialize the database
        conductorCrossSectRepository.saveAndFlush(conductorCrossSect);

        int databaseSizeBeforeUpdate = conductorCrossSectRepository.findAll().size();

        // Update the conductorCrossSect
        ConductorCrossSect updatedConductorCrossSect = conductorCrossSectRepository.findById(conductorCrossSect.getId()).get();
        // Disconnect from session so that the updates on updatedConductorCrossSect are not directly saved in db
        em.detach(updatedConductorCrossSect);
        updatedConductorCrossSect
            .conductorCrossSection(UPDATED_CONDUCTOR_CROSS_SECTION);

        restConductorCrossSectMockMvc.perform(put("/api/conductor-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConductorCrossSect)))
            .andExpect(status().isOk());

        // Validate the ConductorCrossSect in the database
        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeUpdate);
        ConductorCrossSect testConductorCrossSect = conductorCrossSectList.get(conductorCrossSectList.size() - 1);
        assertThat(testConductorCrossSect.getConductorCrossSection()).isEqualTo(UPDATED_CONDUCTOR_CROSS_SECTION);
    }

    @Test
    @Transactional
    public void updateNonExistingConductorCrossSect() throws Exception {
        int databaseSizeBeforeUpdate = conductorCrossSectRepository.findAll().size();

        // Create the ConductorCrossSect

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConductorCrossSectMockMvc.perform(put("/api/conductor-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conductorCrossSect)))
            .andExpect(status().isBadRequest());

        // Validate the ConductorCrossSect in the database
        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConductorCrossSect() throws Exception {
        // Initialize the database
        conductorCrossSectRepository.saveAndFlush(conductorCrossSect);

        int databaseSizeBeforeDelete = conductorCrossSectRepository.findAll().size();

        // Get the conductorCrossSect
        restConductorCrossSectMockMvc.perform(delete("/api/conductor-cross-sects/{id}", conductorCrossSect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConductorCrossSect> conductorCrossSectList = conductorCrossSectRepository.findAll();
        assertThat(conductorCrossSectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConductorCrossSect.class);
        ConductorCrossSect conductorCrossSect1 = new ConductorCrossSect();
        conductorCrossSect1.setId(1L);
        ConductorCrossSect conductorCrossSect2 = new ConductorCrossSect();
        conductorCrossSect2.setId(conductorCrossSect1.getId());
        assertThat(conductorCrossSect1).isEqualTo(conductorCrossSect2);
        conductorCrossSect2.setId(2L);
        assertThat(conductorCrossSect1).isNotEqualTo(conductorCrossSect2);
        conductorCrossSect1.setId(null);
        assertThat(conductorCrossSect1).isNotEqualTo(conductorCrossSect2);
    }
}
