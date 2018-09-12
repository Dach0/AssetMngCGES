package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.CmtType;
import com.cges.assetmng.repository.CmtTypeRepository;
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
 * Test class for the CmtTypeResource REST controller.
 *
 * @see CmtTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CmtTypeResourceIntTest {

    private static final String DEFAULT_CMT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CMT_TYPE = "BBBBBBBBBB";

    @Autowired
    private CmtTypeRepository cmtTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCmtTypeMockMvc;

    private CmtType cmtType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CmtTypeResource cmtTypeResource = new CmtTypeResource(cmtTypeRepository);
        this.restCmtTypeMockMvc = MockMvcBuilders.standaloneSetup(cmtTypeResource)
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
    public static CmtType createEntity(EntityManager em) {
        CmtType cmtType = new CmtType()
            .cmtType(DEFAULT_CMT_TYPE);
        return cmtType;
    }

    @Before
    public void initTest() {
        cmtType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCmtType() throws Exception {
        int databaseSizeBeforeCreate = cmtTypeRepository.findAll().size();

        // Create the CmtType
        restCmtTypeMockMvc.perform(post("/api/cmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmtType)))
            .andExpect(status().isCreated());

        // Validate the CmtType in the database
        List<CmtType> cmtTypeList = cmtTypeRepository.findAll();
        assertThat(cmtTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CmtType testCmtType = cmtTypeList.get(cmtTypeList.size() - 1);
        assertThat(testCmtType.getCmtType()).isEqualTo(DEFAULT_CMT_TYPE);
    }

    @Test
    @Transactional
    public void createCmtTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cmtTypeRepository.findAll().size();

        // Create the CmtType with an existing ID
        cmtType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCmtTypeMockMvc.perform(post("/api/cmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmtType)))
            .andExpect(status().isBadRequest());

        // Validate the CmtType in the database
        List<CmtType> cmtTypeList = cmtTypeRepository.findAll();
        assertThat(cmtTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCmtTypes() throws Exception {
        // Initialize the database
        cmtTypeRepository.saveAndFlush(cmtType);

        // Get all the cmtTypeList
        restCmtTypeMockMvc.perform(get("/api/cmt-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cmtType.getId().intValue())))
            .andExpect(jsonPath("$.[*].cmtType").value(hasItem(DEFAULT_CMT_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getCmtType() throws Exception {
        // Initialize the database
        cmtTypeRepository.saveAndFlush(cmtType);

        // Get the cmtType
        restCmtTypeMockMvc.perform(get("/api/cmt-types/{id}", cmtType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cmtType.getId().intValue()))
            .andExpect(jsonPath("$.cmtType").value(DEFAULT_CMT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCmtType() throws Exception {
        // Get the cmtType
        restCmtTypeMockMvc.perform(get("/api/cmt-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCmtType() throws Exception {
        // Initialize the database
        cmtTypeRepository.saveAndFlush(cmtType);

        int databaseSizeBeforeUpdate = cmtTypeRepository.findAll().size();

        // Update the cmtType
        CmtType updatedCmtType = cmtTypeRepository.findById(cmtType.getId()).get();
        // Disconnect from session so that the updates on updatedCmtType are not directly saved in db
        em.detach(updatedCmtType);
        updatedCmtType
            .cmtType(UPDATED_CMT_TYPE);

        restCmtTypeMockMvc.perform(put("/api/cmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCmtType)))
            .andExpect(status().isOk());

        // Validate the CmtType in the database
        List<CmtType> cmtTypeList = cmtTypeRepository.findAll();
        assertThat(cmtTypeList).hasSize(databaseSizeBeforeUpdate);
        CmtType testCmtType = cmtTypeList.get(cmtTypeList.size() - 1);
        assertThat(testCmtType.getCmtType()).isEqualTo(UPDATED_CMT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCmtType() throws Exception {
        int databaseSizeBeforeUpdate = cmtTypeRepository.findAll().size();

        // Create the CmtType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCmtTypeMockMvc.perform(put("/api/cmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmtType)))
            .andExpect(status().isBadRequest());

        // Validate the CmtType in the database
        List<CmtType> cmtTypeList = cmtTypeRepository.findAll();
        assertThat(cmtTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCmtType() throws Exception {
        // Initialize the database
        cmtTypeRepository.saveAndFlush(cmtType);

        int databaseSizeBeforeDelete = cmtTypeRepository.findAll().size();

        // Get the cmtType
        restCmtTypeMockMvc.perform(delete("/api/cmt-types/{id}", cmtType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CmtType> cmtTypeList = cmtTypeRepository.findAll();
        assertThat(cmtTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmtType.class);
        CmtType cmtType1 = new CmtType();
        cmtType1.setId(1L);
        CmtType cmtType2 = new CmtType();
        cmtType2.setId(cmtType1.getId());
        assertThat(cmtType1).isEqualTo(cmtType2);
        cmtType2.setId(2L);
        assertThat(cmtType1).isNotEqualTo(cmtType2);
        cmtType1.setId(null);
        assertThat(cmtType1).isNotEqualTo(cmtType2);
    }
}
