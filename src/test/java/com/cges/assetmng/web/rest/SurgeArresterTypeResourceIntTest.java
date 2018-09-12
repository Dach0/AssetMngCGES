package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.SurgeArresterType;
import com.cges.assetmng.repository.SurgeArresterTypeRepository;
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
 * Test class for the SurgeArresterTypeResource REST controller.
 *
 * @see SurgeArresterTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class SurgeArresterTypeResourceIntTest {

    private static final String DEFAULT_SA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SA_TYPE = "BBBBBBBBBB";

    @Autowired
    private SurgeArresterTypeRepository surgeArresterTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurgeArresterTypeMockMvc;

    private SurgeArresterType surgeArresterType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurgeArresterTypeResource surgeArresterTypeResource = new SurgeArresterTypeResource(surgeArresterTypeRepository);
        this.restSurgeArresterTypeMockMvc = MockMvcBuilders.standaloneSetup(surgeArresterTypeResource)
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
    public static SurgeArresterType createEntity(EntityManager em) {
        SurgeArresterType surgeArresterType = new SurgeArresterType()
            .saType(DEFAULT_SA_TYPE);
        return surgeArresterType;
    }

    @Before
    public void initTest() {
        surgeArresterType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurgeArresterType() throws Exception {
        int databaseSizeBeforeCreate = surgeArresterTypeRepository.findAll().size();

        // Create the SurgeArresterType
        restSurgeArresterTypeMockMvc.perform(post("/api/surge-arrester-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArresterType)))
            .andExpect(status().isCreated());

        // Validate the SurgeArresterType in the database
        List<SurgeArresterType> surgeArresterTypeList = surgeArresterTypeRepository.findAll();
        assertThat(surgeArresterTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SurgeArresterType testSurgeArresterType = surgeArresterTypeList.get(surgeArresterTypeList.size() - 1);
        assertThat(testSurgeArresterType.getSaType()).isEqualTo(DEFAULT_SA_TYPE);
    }

    @Test
    @Transactional
    public void createSurgeArresterTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surgeArresterTypeRepository.findAll().size();

        // Create the SurgeArresterType with an existing ID
        surgeArresterType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurgeArresterTypeMockMvc.perform(post("/api/surge-arrester-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArresterType)))
            .andExpect(status().isBadRequest());

        // Validate the SurgeArresterType in the database
        List<SurgeArresterType> surgeArresterTypeList = surgeArresterTypeRepository.findAll();
        assertThat(surgeArresterTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSurgeArresterTypes() throws Exception {
        // Initialize the database
        surgeArresterTypeRepository.saveAndFlush(surgeArresterType);

        // Get all the surgeArresterTypeList
        restSurgeArresterTypeMockMvc.perform(get("/api/surge-arrester-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surgeArresterType.getId().intValue())))
            .andExpect(jsonPath("$.[*].saType").value(hasItem(DEFAULT_SA_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getSurgeArresterType() throws Exception {
        // Initialize the database
        surgeArresterTypeRepository.saveAndFlush(surgeArresterType);

        // Get the surgeArresterType
        restSurgeArresterTypeMockMvc.perform(get("/api/surge-arrester-types/{id}", surgeArresterType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(surgeArresterType.getId().intValue()))
            .andExpect(jsonPath("$.saType").value(DEFAULT_SA_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSurgeArresterType() throws Exception {
        // Get the surgeArresterType
        restSurgeArresterTypeMockMvc.perform(get("/api/surge-arrester-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurgeArresterType() throws Exception {
        // Initialize the database
        surgeArresterTypeRepository.saveAndFlush(surgeArresterType);

        int databaseSizeBeforeUpdate = surgeArresterTypeRepository.findAll().size();

        // Update the surgeArresterType
        SurgeArresterType updatedSurgeArresterType = surgeArresterTypeRepository.findById(surgeArresterType.getId()).get();
        // Disconnect from session so that the updates on updatedSurgeArresterType are not directly saved in db
        em.detach(updatedSurgeArresterType);
        updatedSurgeArresterType
            .saType(UPDATED_SA_TYPE);

        restSurgeArresterTypeMockMvc.perform(put("/api/surge-arrester-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSurgeArresterType)))
            .andExpect(status().isOk());

        // Validate the SurgeArresterType in the database
        List<SurgeArresterType> surgeArresterTypeList = surgeArresterTypeRepository.findAll();
        assertThat(surgeArresterTypeList).hasSize(databaseSizeBeforeUpdate);
        SurgeArresterType testSurgeArresterType = surgeArresterTypeList.get(surgeArresterTypeList.size() - 1);
        assertThat(testSurgeArresterType.getSaType()).isEqualTo(UPDATED_SA_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSurgeArresterType() throws Exception {
        int databaseSizeBeforeUpdate = surgeArresterTypeRepository.findAll().size();

        // Create the SurgeArresterType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurgeArresterTypeMockMvc.perform(put("/api/surge-arrester-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surgeArresterType)))
            .andExpect(status().isBadRequest());

        // Validate the SurgeArresterType in the database
        List<SurgeArresterType> surgeArresterTypeList = surgeArresterTypeRepository.findAll();
        assertThat(surgeArresterTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurgeArresterType() throws Exception {
        // Initialize the database
        surgeArresterTypeRepository.saveAndFlush(surgeArresterType);

        int databaseSizeBeforeDelete = surgeArresterTypeRepository.findAll().size();

        // Get the surgeArresterType
        restSurgeArresterTypeMockMvc.perform(delete("/api/surge-arrester-types/{id}", surgeArresterType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SurgeArresterType> surgeArresterTypeList = surgeArresterTypeRepository.findAll();
        assertThat(surgeArresterTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurgeArresterType.class);
        SurgeArresterType surgeArresterType1 = new SurgeArresterType();
        surgeArresterType1.setId(1L);
        SurgeArresterType surgeArresterType2 = new SurgeArresterType();
        surgeArresterType2.setId(surgeArresterType1.getId());
        assertThat(surgeArresterType1).isEqualTo(surgeArresterType2);
        surgeArresterType2.setId(2L);
        assertThat(surgeArresterType1).isNotEqualTo(surgeArresterType2);
        surgeArresterType1.setId(null);
        assertThat(surgeArresterType1).isNotEqualTo(surgeArresterType2);
    }
}
