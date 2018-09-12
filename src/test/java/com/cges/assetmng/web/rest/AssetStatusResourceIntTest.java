package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.AssetStatus;
import com.cges.assetmng.repository.AssetStatusRepository;
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
 * Test class for the AssetStatusResource REST controller.
 *
 * @see AssetStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class AssetStatusResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private AssetStatusRepository assetStatusRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetStatusMockMvc;

    private AssetStatus assetStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetStatusResource assetStatusResource = new AssetStatusResource(assetStatusRepository);
        this.restAssetStatusMockMvc = MockMvcBuilders.standaloneSetup(assetStatusResource)
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
    public static AssetStatus createEntity(EntityManager em) {
        AssetStatus assetStatus = new AssetStatus()
            .status(DEFAULT_STATUS);
        return assetStatus;
    }

    @Before
    public void initTest() {
        assetStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssetStatus() throws Exception {
        int databaseSizeBeforeCreate = assetStatusRepository.findAll().size();

        // Create the AssetStatus
        restAssetStatusMockMvc.perform(post("/api/asset-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetStatus)))
            .andExpect(status().isCreated());

        // Validate the AssetStatus in the database
        List<AssetStatus> assetStatusList = assetStatusRepository.findAll();
        assertThat(assetStatusList).hasSize(databaseSizeBeforeCreate + 1);
        AssetStatus testAssetStatus = assetStatusList.get(assetStatusList.size() - 1);
        assertThat(testAssetStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAssetStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetStatusRepository.findAll().size();

        // Create the AssetStatus with an existing ID
        assetStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetStatusMockMvc.perform(post("/api/asset-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetStatus)))
            .andExpect(status().isBadRequest());

        // Validate the AssetStatus in the database
        List<AssetStatus> assetStatusList = assetStatusRepository.findAll();
        assertThat(assetStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssetStatuses() throws Exception {
        // Initialize the database
        assetStatusRepository.saveAndFlush(assetStatus);

        // Get all the assetStatusList
        restAssetStatusMockMvc.perform(get("/api/asset-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assetStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    

    @Test
    @Transactional
    public void getAssetStatus() throws Exception {
        // Initialize the database
        assetStatusRepository.saveAndFlush(assetStatus);

        // Get the assetStatus
        restAssetStatusMockMvc.perform(get("/api/asset-statuses/{id}", assetStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assetStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAssetStatus() throws Exception {
        // Get the assetStatus
        restAssetStatusMockMvc.perform(get("/api/asset-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssetStatus() throws Exception {
        // Initialize the database
        assetStatusRepository.saveAndFlush(assetStatus);

        int databaseSizeBeforeUpdate = assetStatusRepository.findAll().size();

        // Update the assetStatus
        AssetStatus updatedAssetStatus = assetStatusRepository.findById(assetStatus.getId()).get();
        // Disconnect from session so that the updates on updatedAssetStatus are not directly saved in db
        em.detach(updatedAssetStatus);
        updatedAssetStatus
            .status(UPDATED_STATUS);

        restAssetStatusMockMvc.perform(put("/api/asset-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssetStatus)))
            .andExpect(status().isOk());

        // Validate the AssetStatus in the database
        List<AssetStatus> assetStatusList = assetStatusRepository.findAll();
        assertThat(assetStatusList).hasSize(databaseSizeBeforeUpdate);
        AssetStatus testAssetStatus = assetStatusList.get(assetStatusList.size() - 1);
        assertThat(testAssetStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAssetStatus() throws Exception {
        int databaseSizeBeforeUpdate = assetStatusRepository.findAll().size();

        // Create the AssetStatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetStatusMockMvc.perform(put("/api/asset-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetStatus)))
            .andExpect(status().isBadRequest());

        // Validate the AssetStatus in the database
        List<AssetStatus> assetStatusList = assetStatusRepository.findAll();
        assertThat(assetStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssetStatus() throws Exception {
        // Initialize the database
        assetStatusRepository.saveAndFlush(assetStatus);

        int databaseSizeBeforeDelete = assetStatusRepository.findAll().size();

        // Get the assetStatus
        restAssetStatusMockMvc.perform(delete("/api/asset-statuses/{id}", assetStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AssetStatus> assetStatusList = assetStatusRepository.findAll();
        assertThat(assetStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetStatus.class);
        AssetStatus assetStatus1 = new AssetStatus();
        assetStatus1.setId(1L);
        AssetStatus assetStatus2 = new AssetStatus();
        assetStatus2.setId(assetStatus1.getId());
        assertThat(assetStatus1).isEqualTo(assetStatus2);
        assetStatus2.setId(2L);
        assertThat(assetStatus1).isNotEqualTo(assetStatus2);
        assetStatus1.setId(null);
        assertThat(assetStatus1).isNotEqualTo(assetStatus2);
    }
}
