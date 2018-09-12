package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.GroundSticksType;
import com.cges.assetmng.repository.GroundSticksTypeRepository;
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
 * Test class for the GroundSticksTypeResource REST controller.
 *
 * @see GroundSticksTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class GroundSticksTypeResourceIntTest {

    private static final String DEFAULT_GS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GS_TYPE = "BBBBBBBBBB";

    @Autowired
    private GroundSticksTypeRepository groundSticksTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGroundSticksTypeMockMvc;

    private GroundSticksType groundSticksType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroundSticksTypeResource groundSticksTypeResource = new GroundSticksTypeResource(groundSticksTypeRepository);
        this.restGroundSticksTypeMockMvc = MockMvcBuilders.standaloneSetup(groundSticksTypeResource)
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
    public static GroundSticksType createEntity(EntityManager em) {
        GroundSticksType groundSticksType = new GroundSticksType()
            .gsType(DEFAULT_GS_TYPE);
        return groundSticksType;
    }

    @Before
    public void initTest() {
        groundSticksType = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroundSticksType() throws Exception {
        int databaseSizeBeforeCreate = groundSticksTypeRepository.findAll().size();

        // Create the GroundSticksType
        restGroundSticksTypeMockMvc.perform(post("/api/ground-sticks-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksType)))
            .andExpect(status().isCreated());

        // Validate the GroundSticksType in the database
        List<GroundSticksType> groundSticksTypeList = groundSticksTypeRepository.findAll();
        assertThat(groundSticksTypeList).hasSize(databaseSizeBeforeCreate + 1);
        GroundSticksType testGroundSticksType = groundSticksTypeList.get(groundSticksTypeList.size() - 1);
        assertThat(testGroundSticksType.getGsType()).isEqualTo(DEFAULT_GS_TYPE);
    }

    @Test
    @Transactional
    public void createGroundSticksTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groundSticksTypeRepository.findAll().size();

        // Create the GroundSticksType with an existing ID
        groundSticksType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroundSticksTypeMockMvc.perform(post("/api/ground-sticks-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksType)))
            .andExpect(status().isBadRequest());

        // Validate the GroundSticksType in the database
        List<GroundSticksType> groundSticksTypeList = groundSticksTypeRepository.findAll();
        assertThat(groundSticksTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGroundSticksTypes() throws Exception {
        // Initialize the database
        groundSticksTypeRepository.saveAndFlush(groundSticksType);

        // Get all the groundSticksTypeList
        restGroundSticksTypeMockMvc.perform(get("/api/ground-sticks-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groundSticksType.getId().intValue())))
            .andExpect(jsonPath("$.[*].gsType").value(hasItem(DEFAULT_GS_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getGroundSticksType() throws Exception {
        // Initialize the database
        groundSticksTypeRepository.saveAndFlush(groundSticksType);

        // Get the groundSticksType
        restGroundSticksTypeMockMvc.perform(get("/api/ground-sticks-types/{id}", groundSticksType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groundSticksType.getId().intValue()))
            .andExpect(jsonPath("$.gsType").value(DEFAULT_GS_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGroundSticksType() throws Exception {
        // Get the groundSticksType
        restGroundSticksTypeMockMvc.perform(get("/api/ground-sticks-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroundSticksType() throws Exception {
        // Initialize the database
        groundSticksTypeRepository.saveAndFlush(groundSticksType);

        int databaseSizeBeforeUpdate = groundSticksTypeRepository.findAll().size();

        // Update the groundSticksType
        GroundSticksType updatedGroundSticksType = groundSticksTypeRepository.findById(groundSticksType.getId()).get();
        // Disconnect from session so that the updates on updatedGroundSticksType are not directly saved in db
        em.detach(updatedGroundSticksType);
        updatedGroundSticksType
            .gsType(UPDATED_GS_TYPE);

        restGroundSticksTypeMockMvc.perform(put("/api/ground-sticks-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroundSticksType)))
            .andExpect(status().isOk());

        // Validate the GroundSticksType in the database
        List<GroundSticksType> groundSticksTypeList = groundSticksTypeRepository.findAll();
        assertThat(groundSticksTypeList).hasSize(databaseSizeBeforeUpdate);
        GroundSticksType testGroundSticksType = groundSticksTypeList.get(groundSticksTypeList.size() - 1);
        assertThat(testGroundSticksType.getGsType()).isEqualTo(UPDATED_GS_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroundSticksType() throws Exception {
        int databaseSizeBeforeUpdate = groundSticksTypeRepository.findAll().size();

        // Create the GroundSticksType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGroundSticksTypeMockMvc.perform(put("/api/ground-sticks-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksType)))
            .andExpect(status().isBadRequest());

        // Validate the GroundSticksType in the database
        List<GroundSticksType> groundSticksTypeList = groundSticksTypeRepository.findAll();
        assertThat(groundSticksTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroundSticksType() throws Exception {
        // Initialize the database
        groundSticksTypeRepository.saveAndFlush(groundSticksType);

        int databaseSizeBeforeDelete = groundSticksTypeRepository.findAll().size();

        // Get the groundSticksType
        restGroundSticksTypeMockMvc.perform(delete("/api/ground-sticks-types/{id}", groundSticksType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroundSticksType> groundSticksTypeList = groundSticksTypeRepository.findAll();
        assertThat(groundSticksTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroundSticksType.class);
        GroundSticksType groundSticksType1 = new GroundSticksType();
        groundSticksType1.setId(1L);
        GroundSticksType groundSticksType2 = new GroundSticksType();
        groundSticksType2.setId(groundSticksType1.getId());
        assertThat(groundSticksType1).isEqualTo(groundSticksType2);
        groundSticksType2.setId(2L);
        assertThat(groundSticksType1).isNotEqualTo(groundSticksType2);
        groundSticksType1.setId(null);
        assertThat(groundSticksType1).isNotEqualTo(groundSticksType2);
    }
}
