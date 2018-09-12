package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.PylonType;
import com.cges.assetmng.repository.PylonTypeRepository;
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
 * Test class for the PylonTypeResource REST controller.
 *
 * @see PylonTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class PylonTypeResourceIntTest {

    private static final String DEFAULT_PYLON_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PYLON_TYPE = "BBBBBBBBBB";

    @Autowired
    private PylonTypeRepository pylonTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPylonTypeMockMvc;

    private PylonType pylonType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PylonTypeResource pylonTypeResource = new PylonTypeResource(pylonTypeRepository);
        this.restPylonTypeMockMvc = MockMvcBuilders.standaloneSetup(pylonTypeResource)
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
    public static PylonType createEntity(EntityManager em) {
        PylonType pylonType = new PylonType()
            .pylonType(DEFAULT_PYLON_TYPE);
        return pylonType;
    }

    @Before
    public void initTest() {
        pylonType = createEntity(em);
    }

    @Test
    @Transactional
    public void createPylonType() throws Exception {
        int databaseSizeBeforeCreate = pylonTypeRepository.findAll().size();

        // Create the PylonType
        restPylonTypeMockMvc.perform(post("/api/pylon-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pylonType)))
            .andExpect(status().isCreated());

        // Validate the PylonType in the database
        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeCreate + 1);
        PylonType testPylonType = pylonTypeList.get(pylonTypeList.size() - 1);
        assertThat(testPylonType.getPylonType()).isEqualTo(DEFAULT_PYLON_TYPE);
    }

    @Test
    @Transactional
    public void createPylonTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pylonTypeRepository.findAll().size();

        // Create the PylonType with an existing ID
        pylonType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPylonTypeMockMvc.perform(post("/api/pylon-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pylonType)))
            .andExpect(status().isBadRequest());

        // Validate the PylonType in the database
        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPylonTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pylonTypeRepository.findAll().size();
        // set the field null
        pylonType.setPylonType(null);

        // Create the PylonType, which fails.

        restPylonTypeMockMvc.perform(post("/api/pylon-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pylonType)))
            .andExpect(status().isBadRequest());

        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPylonTypes() throws Exception {
        // Initialize the database
        pylonTypeRepository.saveAndFlush(pylonType);

        // Get all the pylonTypeList
        restPylonTypeMockMvc.perform(get("/api/pylon-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pylonType.getId().intValue())))
            .andExpect(jsonPath("$.[*].pylonType").value(hasItem(DEFAULT_PYLON_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getPylonType() throws Exception {
        // Initialize the database
        pylonTypeRepository.saveAndFlush(pylonType);

        // Get the pylonType
        restPylonTypeMockMvc.perform(get("/api/pylon-types/{id}", pylonType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pylonType.getId().intValue()))
            .andExpect(jsonPath("$.pylonType").value(DEFAULT_PYLON_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPylonType() throws Exception {
        // Get the pylonType
        restPylonTypeMockMvc.perform(get("/api/pylon-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePylonType() throws Exception {
        // Initialize the database
        pylonTypeRepository.saveAndFlush(pylonType);

        int databaseSizeBeforeUpdate = pylonTypeRepository.findAll().size();

        // Update the pylonType
        PylonType updatedPylonType = pylonTypeRepository.findById(pylonType.getId()).get();
        // Disconnect from session so that the updates on updatedPylonType are not directly saved in db
        em.detach(updatedPylonType);
        updatedPylonType
            .pylonType(UPDATED_PYLON_TYPE);

        restPylonTypeMockMvc.perform(put("/api/pylon-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPylonType)))
            .andExpect(status().isOk());

        // Validate the PylonType in the database
        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeUpdate);
        PylonType testPylonType = pylonTypeList.get(pylonTypeList.size() - 1);
        assertThat(testPylonType.getPylonType()).isEqualTo(UPDATED_PYLON_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingPylonType() throws Exception {
        int databaseSizeBeforeUpdate = pylonTypeRepository.findAll().size();

        // Create the PylonType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPylonTypeMockMvc.perform(put("/api/pylon-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pylonType)))
            .andExpect(status().isBadRequest());

        // Validate the PylonType in the database
        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePylonType() throws Exception {
        // Initialize the database
        pylonTypeRepository.saveAndFlush(pylonType);

        int databaseSizeBeforeDelete = pylonTypeRepository.findAll().size();

        // Get the pylonType
        restPylonTypeMockMvc.perform(delete("/api/pylon-types/{id}", pylonType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PylonType> pylonTypeList = pylonTypeRepository.findAll();
        assertThat(pylonTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PylonType.class);
        PylonType pylonType1 = new PylonType();
        pylonType1.setId(1L);
        PylonType pylonType2 = new PylonType();
        pylonType2.setId(pylonType1.getId());
        assertThat(pylonType1).isEqualTo(pylonType2);
        pylonType2.setId(2L);
        assertThat(pylonType1).isNotEqualTo(pylonType2);
        pylonType1.setId(null);
        assertThat(pylonType1).isNotEqualTo(pylonType2);
    }
}
