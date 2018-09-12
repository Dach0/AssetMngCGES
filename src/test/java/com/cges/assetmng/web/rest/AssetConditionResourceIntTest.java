package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.AssetCondition;
import com.cges.assetmng.repository.AssetConditionRepository;
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
 * Test class for the AssetConditionResource REST controller.
 *
 * @see AssetConditionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class AssetConditionResourceIntTest {

    private static final String DEFAULT_CONDITION_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION_DESC = "BBBBBBBBBB";

    @Autowired
    private AssetConditionRepository assetConditionRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetConditionMockMvc;

    private AssetCondition assetCondition;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetConditionResource assetConditionResource = new AssetConditionResource(assetConditionRepository);
        this.restAssetConditionMockMvc = MockMvcBuilders.standaloneSetup(assetConditionResource)
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
    public static AssetCondition createEntity(EntityManager em) {
        AssetCondition assetCondition = new AssetCondition()
            .conditionDesc(DEFAULT_CONDITION_DESC);
        return assetCondition;
    }

    @Before
    public void initTest() {
        assetCondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssetCondition() throws Exception {
        int databaseSizeBeforeCreate = assetConditionRepository.findAll().size();

        // Create the AssetCondition
        restAssetConditionMockMvc.perform(post("/api/asset-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetCondition)))
            .andExpect(status().isCreated());

        // Validate the AssetCondition in the database
        List<AssetCondition> assetConditionList = assetConditionRepository.findAll();
        assertThat(assetConditionList).hasSize(databaseSizeBeforeCreate + 1);
        AssetCondition testAssetCondition = assetConditionList.get(assetConditionList.size() - 1);
        assertThat(testAssetCondition.getConditionDesc()).isEqualTo(DEFAULT_CONDITION_DESC);
    }

    @Test
    @Transactional
    public void createAssetConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetConditionRepository.findAll().size();

        // Create the AssetCondition with an existing ID
        assetCondition.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetConditionMockMvc.perform(post("/api/asset-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetCondition)))
            .andExpect(status().isBadRequest());

        // Validate the AssetCondition in the database
        List<AssetCondition> assetConditionList = assetConditionRepository.findAll();
        assertThat(assetConditionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssetConditions() throws Exception {
        // Initialize the database
        assetConditionRepository.saveAndFlush(assetCondition);

        // Get all the assetConditionList
        restAssetConditionMockMvc.perform(get("/api/asset-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assetCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].conditionDesc").value(hasItem(DEFAULT_CONDITION_DESC.toString())));
    }
    

    @Test
    @Transactional
    public void getAssetCondition() throws Exception {
        // Initialize the database
        assetConditionRepository.saveAndFlush(assetCondition);

        // Get the assetCondition
        restAssetConditionMockMvc.perform(get("/api/asset-conditions/{id}", assetCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assetCondition.getId().intValue()))
            .andExpect(jsonPath("$.conditionDesc").value(DEFAULT_CONDITION_DESC.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAssetCondition() throws Exception {
        // Get the assetCondition
        restAssetConditionMockMvc.perform(get("/api/asset-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssetCondition() throws Exception {
        // Initialize the database
        assetConditionRepository.saveAndFlush(assetCondition);

        int databaseSizeBeforeUpdate = assetConditionRepository.findAll().size();

        // Update the assetCondition
        AssetCondition updatedAssetCondition = assetConditionRepository.findById(assetCondition.getId()).get();
        // Disconnect from session so that the updates on updatedAssetCondition are not directly saved in db
        em.detach(updatedAssetCondition);
        updatedAssetCondition
            .conditionDesc(UPDATED_CONDITION_DESC);

        restAssetConditionMockMvc.perform(put("/api/asset-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssetCondition)))
            .andExpect(status().isOk());

        // Validate the AssetCondition in the database
        List<AssetCondition> assetConditionList = assetConditionRepository.findAll();
        assertThat(assetConditionList).hasSize(databaseSizeBeforeUpdate);
        AssetCondition testAssetCondition = assetConditionList.get(assetConditionList.size() - 1);
        assertThat(testAssetCondition.getConditionDesc()).isEqualTo(UPDATED_CONDITION_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingAssetCondition() throws Exception {
        int databaseSizeBeforeUpdate = assetConditionRepository.findAll().size();

        // Create the AssetCondition

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetConditionMockMvc.perform(put("/api/asset-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetCondition)))
            .andExpect(status().isBadRequest());

        // Validate the AssetCondition in the database
        List<AssetCondition> assetConditionList = assetConditionRepository.findAll();
        assertThat(assetConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssetCondition() throws Exception {
        // Initialize the database
        assetConditionRepository.saveAndFlush(assetCondition);

        int databaseSizeBeforeDelete = assetConditionRepository.findAll().size();

        // Get the assetCondition
        restAssetConditionMockMvc.perform(delete("/api/asset-conditions/{id}", assetCondition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AssetCondition> assetConditionList = assetConditionRepository.findAll();
        assertThat(assetConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetCondition.class);
        AssetCondition assetCondition1 = new AssetCondition();
        assetCondition1.setId(1L);
        AssetCondition assetCondition2 = new AssetCondition();
        assetCondition2.setId(assetCondition1.getId());
        assertThat(assetCondition1).isEqualTo(assetCondition2);
        assetCondition2.setId(2L);
        assertThat(assetCondition1).isNotEqualTo(assetCondition2);
        assetCondition1.setId(null);
        assertThat(assetCondition1).isNotEqualTo(assetCondition2);
    }
}
