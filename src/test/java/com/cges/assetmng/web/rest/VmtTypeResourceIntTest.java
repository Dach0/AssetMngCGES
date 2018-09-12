package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.VmtType;
import com.cges.assetmng.repository.VmtTypeRepository;
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
 * Test class for the VmtTypeResource REST controller.
 *
 * @see VmtTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class VmtTypeResourceIntTest {

    private static final String DEFAULT_VMT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VMT_TYPE = "BBBBBBBBBB";

    @Autowired
    private VmtTypeRepository vmtTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVmtTypeMockMvc;

    private VmtType vmtType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VmtTypeResource vmtTypeResource = new VmtTypeResource(vmtTypeRepository);
        this.restVmtTypeMockMvc = MockMvcBuilders.standaloneSetup(vmtTypeResource)
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
    public static VmtType createEntity(EntityManager em) {
        VmtType vmtType = new VmtType()
            .vmtType(DEFAULT_VMT_TYPE);
        return vmtType;
    }

    @Before
    public void initTest() {
        vmtType = createEntity(em);
    }

    @Test
    @Transactional
    public void createVmtType() throws Exception {
        int databaseSizeBeforeCreate = vmtTypeRepository.findAll().size();

        // Create the VmtType
        restVmtTypeMockMvc.perform(post("/api/vmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vmtType)))
            .andExpect(status().isCreated());

        // Validate the VmtType in the database
        List<VmtType> vmtTypeList = vmtTypeRepository.findAll();
        assertThat(vmtTypeList).hasSize(databaseSizeBeforeCreate + 1);
        VmtType testVmtType = vmtTypeList.get(vmtTypeList.size() - 1);
        assertThat(testVmtType.getVmtType()).isEqualTo(DEFAULT_VMT_TYPE);
    }

    @Test
    @Transactional
    public void createVmtTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vmtTypeRepository.findAll().size();

        // Create the VmtType with an existing ID
        vmtType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVmtTypeMockMvc.perform(post("/api/vmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vmtType)))
            .andExpect(status().isBadRequest());

        // Validate the VmtType in the database
        List<VmtType> vmtTypeList = vmtTypeRepository.findAll();
        assertThat(vmtTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVmtTypes() throws Exception {
        // Initialize the database
        vmtTypeRepository.saveAndFlush(vmtType);

        // Get all the vmtTypeList
        restVmtTypeMockMvc.perform(get("/api/vmt-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vmtType.getId().intValue())))
            .andExpect(jsonPath("$.[*].vmtType").value(hasItem(DEFAULT_VMT_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getVmtType() throws Exception {
        // Initialize the database
        vmtTypeRepository.saveAndFlush(vmtType);

        // Get the vmtType
        restVmtTypeMockMvc.perform(get("/api/vmt-types/{id}", vmtType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vmtType.getId().intValue()))
            .andExpect(jsonPath("$.vmtType").value(DEFAULT_VMT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVmtType() throws Exception {
        // Get the vmtType
        restVmtTypeMockMvc.perform(get("/api/vmt-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVmtType() throws Exception {
        // Initialize the database
        vmtTypeRepository.saveAndFlush(vmtType);

        int databaseSizeBeforeUpdate = vmtTypeRepository.findAll().size();

        // Update the vmtType
        VmtType updatedVmtType = vmtTypeRepository.findById(vmtType.getId()).get();
        // Disconnect from session so that the updates on updatedVmtType are not directly saved in db
        em.detach(updatedVmtType);
        updatedVmtType
            .vmtType(UPDATED_VMT_TYPE);

        restVmtTypeMockMvc.perform(put("/api/vmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVmtType)))
            .andExpect(status().isOk());

        // Validate the VmtType in the database
        List<VmtType> vmtTypeList = vmtTypeRepository.findAll();
        assertThat(vmtTypeList).hasSize(databaseSizeBeforeUpdate);
        VmtType testVmtType = vmtTypeList.get(vmtTypeList.size() - 1);
        assertThat(testVmtType.getVmtType()).isEqualTo(UPDATED_VMT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingVmtType() throws Exception {
        int databaseSizeBeforeUpdate = vmtTypeRepository.findAll().size();

        // Create the VmtType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVmtTypeMockMvc.perform(put("/api/vmt-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vmtType)))
            .andExpect(status().isBadRequest());

        // Validate the VmtType in the database
        List<VmtType> vmtTypeList = vmtTypeRepository.findAll();
        assertThat(vmtTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVmtType() throws Exception {
        // Initialize the database
        vmtTypeRepository.saveAndFlush(vmtType);

        int databaseSizeBeforeDelete = vmtTypeRepository.findAll().size();

        // Get the vmtType
        restVmtTypeMockMvc.perform(delete("/api/vmt-types/{id}", vmtType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VmtType> vmtTypeList = vmtTypeRepository.findAll();
        assertThat(vmtTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VmtType.class);
        VmtType vmtType1 = new VmtType();
        vmtType1.setId(1L);
        VmtType vmtType2 = new VmtType();
        vmtType2.setId(vmtType1.getId());
        assertThat(vmtType1).isEqualTo(vmtType2);
        vmtType2.setId(2L);
        assertThat(vmtType1).isNotEqualTo(vmtType2);
        vmtType1.setId(null);
        assertThat(vmtType1).isNotEqualTo(vmtType2);
    }
}
