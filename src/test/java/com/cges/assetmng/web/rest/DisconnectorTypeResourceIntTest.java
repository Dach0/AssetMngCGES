package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.DisconnectorType;
import com.cges.assetmng.repository.DisconnectorTypeRepository;
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
 * Test class for the DisconnectorTypeResource REST controller.
 *
 * @see DisconnectorTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class DisconnectorTypeResourceIntTest {

    private static final String DEFAULT_DISC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DISC_TYPE = "BBBBBBBBBB";

    @Autowired
    private DisconnectorTypeRepository disconnectorTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisconnectorTypeMockMvc;

    private DisconnectorType disconnectorType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisconnectorTypeResource disconnectorTypeResource = new DisconnectorTypeResource(disconnectorTypeRepository);
        this.restDisconnectorTypeMockMvc = MockMvcBuilders.standaloneSetup(disconnectorTypeResource)
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
    public static DisconnectorType createEntity(EntityManager em) {
        DisconnectorType disconnectorType = new DisconnectorType()
            .discType(DEFAULT_DISC_TYPE);
        return disconnectorType;
    }

    @Before
    public void initTest() {
        disconnectorType = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisconnectorType() throws Exception {
        int databaseSizeBeforeCreate = disconnectorTypeRepository.findAll().size();

        // Create the DisconnectorType
        restDisconnectorTypeMockMvc.perform(post("/api/disconnector-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorType)))
            .andExpect(status().isCreated());

        // Validate the DisconnectorType in the database
        List<DisconnectorType> disconnectorTypeList = disconnectorTypeRepository.findAll();
        assertThat(disconnectorTypeList).hasSize(databaseSizeBeforeCreate + 1);
        DisconnectorType testDisconnectorType = disconnectorTypeList.get(disconnectorTypeList.size() - 1);
        assertThat(testDisconnectorType.getDiscType()).isEqualTo(DEFAULT_DISC_TYPE);
    }

    @Test
    @Transactional
    public void createDisconnectorTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disconnectorTypeRepository.findAll().size();

        // Create the DisconnectorType with an existing ID
        disconnectorType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisconnectorTypeMockMvc.perform(post("/api/disconnector-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorType)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorType in the database
        List<DisconnectorType> disconnectorTypeList = disconnectorTypeRepository.findAll();
        assertThat(disconnectorTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDisconnectorTypes() throws Exception {
        // Initialize the database
        disconnectorTypeRepository.saveAndFlush(disconnectorType);

        // Get all the disconnectorTypeList
        restDisconnectorTypeMockMvc.perform(get("/api/disconnector-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disconnectorType.getId().intValue())))
            .andExpect(jsonPath("$.[*].discType").value(hasItem(DEFAULT_DISC_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getDisconnectorType() throws Exception {
        // Initialize the database
        disconnectorTypeRepository.saveAndFlush(disconnectorType);

        // Get the disconnectorType
        restDisconnectorTypeMockMvc.perform(get("/api/disconnector-types/{id}", disconnectorType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disconnectorType.getId().intValue()))
            .andExpect(jsonPath("$.discType").value(DEFAULT_DISC_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDisconnectorType() throws Exception {
        // Get the disconnectorType
        restDisconnectorTypeMockMvc.perform(get("/api/disconnector-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisconnectorType() throws Exception {
        // Initialize the database
        disconnectorTypeRepository.saveAndFlush(disconnectorType);

        int databaseSizeBeforeUpdate = disconnectorTypeRepository.findAll().size();

        // Update the disconnectorType
        DisconnectorType updatedDisconnectorType = disconnectorTypeRepository.findById(disconnectorType.getId()).get();
        // Disconnect from session so that the updates on updatedDisconnectorType are not directly saved in db
        em.detach(updatedDisconnectorType);
        updatedDisconnectorType
            .discType(UPDATED_DISC_TYPE);

        restDisconnectorTypeMockMvc.perform(put("/api/disconnector-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisconnectorType)))
            .andExpect(status().isOk());

        // Validate the DisconnectorType in the database
        List<DisconnectorType> disconnectorTypeList = disconnectorTypeRepository.findAll();
        assertThat(disconnectorTypeList).hasSize(databaseSizeBeforeUpdate);
        DisconnectorType testDisconnectorType = disconnectorTypeList.get(disconnectorTypeList.size() - 1);
        assertThat(testDisconnectorType.getDiscType()).isEqualTo(UPDATED_DISC_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisconnectorType() throws Exception {
        int databaseSizeBeforeUpdate = disconnectorTypeRepository.findAll().size();

        // Create the DisconnectorType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisconnectorTypeMockMvc.perform(put("/api/disconnector-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorType)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorType in the database
        List<DisconnectorType> disconnectorTypeList = disconnectorTypeRepository.findAll();
        assertThat(disconnectorTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisconnectorType() throws Exception {
        // Initialize the database
        disconnectorTypeRepository.saveAndFlush(disconnectorType);

        int databaseSizeBeforeDelete = disconnectorTypeRepository.findAll().size();

        // Get the disconnectorType
        restDisconnectorTypeMockMvc.perform(delete("/api/disconnector-types/{id}", disconnectorType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisconnectorType> disconnectorTypeList = disconnectorTypeRepository.findAll();
        assertThat(disconnectorTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisconnectorType.class);
        DisconnectorType disconnectorType1 = new DisconnectorType();
        disconnectorType1.setId(1L);
        DisconnectorType disconnectorType2 = new DisconnectorType();
        disconnectorType2.setId(disconnectorType1.getId());
        assertThat(disconnectorType1).isEqualTo(disconnectorType2);
        disconnectorType2.setId(2L);
        assertThat(disconnectorType1).isNotEqualTo(disconnectorType2);
        disconnectorType1.setId(null);
        assertThat(disconnectorType1).isNotEqualTo(disconnectorType2);
    }
}
