package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.EarthWireCrossSect;
import com.cges.assetmng.repository.EarthWireCrossSectRepository;
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
 * Test class for the EarthWireCrossSectResource REST controller.
 *
 * @see EarthWireCrossSectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class EarthWireCrossSectResourceIntTest {

    private static final String DEFAULT_EARTH_WIRE_CROSS_SECTION = "AAAAAAAAAA";
    private static final String UPDATED_EARTH_WIRE_CROSS_SECTION = "BBBBBBBBBB";

    @Autowired
    private EarthWireCrossSectRepository earthWireCrossSectRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEarthWireCrossSectMockMvc;

    private EarthWireCrossSect earthWireCrossSect;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EarthWireCrossSectResource earthWireCrossSectResource = new EarthWireCrossSectResource(earthWireCrossSectRepository);
        this.restEarthWireCrossSectMockMvc = MockMvcBuilders.standaloneSetup(earthWireCrossSectResource)
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
    public static EarthWireCrossSect createEntity(EntityManager em) {
        EarthWireCrossSect earthWireCrossSect = new EarthWireCrossSect()
            .earthWireCrossSection(DEFAULT_EARTH_WIRE_CROSS_SECTION);
        return earthWireCrossSect;
    }

    @Before
    public void initTest() {
        earthWireCrossSect = createEntity(em);
    }

    @Test
    @Transactional
    public void createEarthWireCrossSect() throws Exception {
        int databaseSizeBeforeCreate = earthWireCrossSectRepository.findAll().size();

        // Create the EarthWireCrossSect
        restEarthWireCrossSectMockMvc.perform(post("/api/earth-wire-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(earthWireCrossSect)))
            .andExpect(status().isCreated());

        // Validate the EarthWireCrossSect in the database
        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeCreate + 1);
        EarthWireCrossSect testEarthWireCrossSect = earthWireCrossSectList.get(earthWireCrossSectList.size() - 1);
        assertThat(testEarthWireCrossSect.getEarthWireCrossSection()).isEqualTo(DEFAULT_EARTH_WIRE_CROSS_SECTION);
    }

    @Test
    @Transactional
    public void createEarthWireCrossSectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = earthWireCrossSectRepository.findAll().size();

        // Create the EarthWireCrossSect with an existing ID
        earthWireCrossSect.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEarthWireCrossSectMockMvc.perform(post("/api/earth-wire-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(earthWireCrossSect)))
            .andExpect(status().isBadRequest());

        // Validate the EarthWireCrossSect in the database
        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEarthWireCrossSectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = earthWireCrossSectRepository.findAll().size();
        // set the field null
        earthWireCrossSect.setEarthWireCrossSection(null);

        // Create the EarthWireCrossSect, which fails.

        restEarthWireCrossSectMockMvc.perform(post("/api/earth-wire-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(earthWireCrossSect)))
            .andExpect(status().isBadRequest());

        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEarthWireCrossSects() throws Exception {
        // Initialize the database
        earthWireCrossSectRepository.saveAndFlush(earthWireCrossSect);

        // Get all the earthWireCrossSectList
        restEarthWireCrossSectMockMvc.perform(get("/api/earth-wire-cross-sects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(earthWireCrossSect.getId().intValue())))
            .andExpect(jsonPath("$.[*].earthWireCrossSection").value(hasItem(DEFAULT_EARTH_WIRE_CROSS_SECTION.toString())));
    }
    

    @Test
    @Transactional
    public void getEarthWireCrossSect() throws Exception {
        // Initialize the database
        earthWireCrossSectRepository.saveAndFlush(earthWireCrossSect);

        // Get the earthWireCrossSect
        restEarthWireCrossSectMockMvc.perform(get("/api/earth-wire-cross-sects/{id}", earthWireCrossSect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(earthWireCrossSect.getId().intValue()))
            .andExpect(jsonPath("$.earthWireCrossSection").value(DEFAULT_EARTH_WIRE_CROSS_SECTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEarthWireCrossSect() throws Exception {
        // Get the earthWireCrossSect
        restEarthWireCrossSectMockMvc.perform(get("/api/earth-wire-cross-sects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEarthWireCrossSect() throws Exception {
        // Initialize the database
        earthWireCrossSectRepository.saveAndFlush(earthWireCrossSect);

        int databaseSizeBeforeUpdate = earthWireCrossSectRepository.findAll().size();

        // Update the earthWireCrossSect
        EarthWireCrossSect updatedEarthWireCrossSect = earthWireCrossSectRepository.findById(earthWireCrossSect.getId()).get();
        // Disconnect from session so that the updates on updatedEarthWireCrossSect are not directly saved in db
        em.detach(updatedEarthWireCrossSect);
        updatedEarthWireCrossSect
            .earthWireCrossSection(UPDATED_EARTH_WIRE_CROSS_SECTION);

        restEarthWireCrossSectMockMvc.perform(put("/api/earth-wire-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEarthWireCrossSect)))
            .andExpect(status().isOk());

        // Validate the EarthWireCrossSect in the database
        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeUpdate);
        EarthWireCrossSect testEarthWireCrossSect = earthWireCrossSectList.get(earthWireCrossSectList.size() - 1);
        assertThat(testEarthWireCrossSect.getEarthWireCrossSection()).isEqualTo(UPDATED_EARTH_WIRE_CROSS_SECTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEarthWireCrossSect() throws Exception {
        int databaseSizeBeforeUpdate = earthWireCrossSectRepository.findAll().size();

        // Create the EarthWireCrossSect

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEarthWireCrossSectMockMvc.perform(put("/api/earth-wire-cross-sects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(earthWireCrossSect)))
            .andExpect(status().isBadRequest());

        // Validate the EarthWireCrossSect in the database
        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEarthWireCrossSect() throws Exception {
        // Initialize the database
        earthWireCrossSectRepository.saveAndFlush(earthWireCrossSect);

        int databaseSizeBeforeDelete = earthWireCrossSectRepository.findAll().size();

        // Get the earthWireCrossSect
        restEarthWireCrossSectMockMvc.perform(delete("/api/earth-wire-cross-sects/{id}", earthWireCrossSect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EarthWireCrossSect> earthWireCrossSectList = earthWireCrossSectRepository.findAll();
        assertThat(earthWireCrossSectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EarthWireCrossSect.class);
        EarthWireCrossSect earthWireCrossSect1 = new EarthWireCrossSect();
        earthWireCrossSect1.setId(1L);
        EarthWireCrossSect earthWireCrossSect2 = new EarthWireCrossSect();
        earthWireCrossSect2.setId(earthWireCrossSect1.getId());
        assertThat(earthWireCrossSect1).isEqualTo(earthWireCrossSect2);
        earthWireCrossSect2.setId(2L);
        assertThat(earthWireCrossSect1).isNotEqualTo(earthWireCrossSect2);
        earthWireCrossSect1.setId(null);
        assertThat(earthWireCrossSect1).isNotEqualTo(earthWireCrossSect2);
    }
}
