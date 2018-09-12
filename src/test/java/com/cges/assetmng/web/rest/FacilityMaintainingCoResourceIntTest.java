package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.FacilityMaintainingCo;
import com.cges.assetmng.repository.FacilityMaintainingCoRepository;
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
 * Test class for the FacilityMaintainingCoResource REST controller.
 *
 * @see FacilityMaintainingCoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class FacilityMaintainingCoResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private FacilityMaintainingCoRepository facilityMaintainingCoRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFacilityMaintainingCoMockMvc;

    private FacilityMaintainingCo facilityMaintainingCo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacilityMaintainingCoResource facilityMaintainingCoResource = new FacilityMaintainingCoResource(facilityMaintainingCoRepository);
        this.restFacilityMaintainingCoMockMvc = MockMvcBuilders.standaloneSetup(facilityMaintainingCoResource)
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
    public static FacilityMaintainingCo createEntity(EntityManager em) {
        FacilityMaintainingCo facilityMaintainingCo = new FacilityMaintainingCo()
            .name(DEFAULT_NAME);
        return facilityMaintainingCo;
    }

    @Before
    public void initTest() {
        facilityMaintainingCo = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacilityMaintainingCo() throws Exception {
        int databaseSizeBeforeCreate = facilityMaintainingCoRepository.findAll().size();

        // Create the FacilityMaintainingCo
        restFacilityMaintainingCoMockMvc.perform(post("/api/facility-maintaining-cos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityMaintainingCo)))
            .andExpect(status().isCreated());

        // Validate the FacilityMaintainingCo in the database
        List<FacilityMaintainingCo> facilityMaintainingCoList = facilityMaintainingCoRepository.findAll();
        assertThat(facilityMaintainingCoList).hasSize(databaseSizeBeforeCreate + 1);
        FacilityMaintainingCo testFacilityMaintainingCo = facilityMaintainingCoList.get(facilityMaintainingCoList.size() - 1);
        assertThat(testFacilityMaintainingCo.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createFacilityMaintainingCoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facilityMaintainingCoRepository.findAll().size();

        // Create the FacilityMaintainingCo with an existing ID
        facilityMaintainingCo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacilityMaintainingCoMockMvc.perform(post("/api/facility-maintaining-cos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityMaintainingCo)))
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaintainingCo in the database
        List<FacilityMaintainingCo> facilityMaintainingCoList = facilityMaintainingCoRepository.findAll();
        assertThat(facilityMaintainingCoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFacilityMaintainingCos() throws Exception {
        // Initialize the database
        facilityMaintainingCoRepository.saveAndFlush(facilityMaintainingCo);

        // Get all the facilityMaintainingCoList
        restFacilityMaintainingCoMockMvc.perform(get("/api/facility-maintaining-cos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facilityMaintainingCo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getFacilityMaintainingCo() throws Exception {
        // Initialize the database
        facilityMaintainingCoRepository.saveAndFlush(facilityMaintainingCo);

        // Get the facilityMaintainingCo
        restFacilityMaintainingCoMockMvc.perform(get("/api/facility-maintaining-cos/{id}", facilityMaintainingCo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facilityMaintainingCo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFacilityMaintainingCo() throws Exception {
        // Get the facilityMaintainingCo
        restFacilityMaintainingCoMockMvc.perform(get("/api/facility-maintaining-cos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacilityMaintainingCo() throws Exception {
        // Initialize the database
        facilityMaintainingCoRepository.saveAndFlush(facilityMaintainingCo);

        int databaseSizeBeforeUpdate = facilityMaintainingCoRepository.findAll().size();

        // Update the facilityMaintainingCo
        FacilityMaintainingCo updatedFacilityMaintainingCo = facilityMaintainingCoRepository.findById(facilityMaintainingCo.getId()).get();
        // Disconnect from session so that the updates on updatedFacilityMaintainingCo are not directly saved in db
        em.detach(updatedFacilityMaintainingCo);
        updatedFacilityMaintainingCo
            .name(UPDATED_NAME);

        restFacilityMaintainingCoMockMvc.perform(put("/api/facility-maintaining-cos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFacilityMaintainingCo)))
            .andExpect(status().isOk());

        // Validate the FacilityMaintainingCo in the database
        List<FacilityMaintainingCo> facilityMaintainingCoList = facilityMaintainingCoRepository.findAll();
        assertThat(facilityMaintainingCoList).hasSize(databaseSizeBeforeUpdate);
        FacilityMaintainingCo testFacilityMaintainingCo = facilityMaintainingCoList.get(facilityMaintainingCoList.size() - 1);
        assertThat(testFacilityMaintainingCo.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFacilityMaintainingCo() throws Exception {
        int databaseSizeBeforeUpdate = facilityMaintainingCoRepository.findAll().size();

        // Create the FacilityMaintainingCo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFacilityMaintainingCoMockMvc.perform(put("/api/facility-maintaining-cos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityMaintainingCo)))
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaintainingCo in the database
        List<FacilityMaintainingCo> facilityMaintainingCoList = facilityMaintainingCoRepository.findAll();
        assertThat(facilityMaintainingCoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFacilityMaintainingCo() throws Exception {
        // Initialize the database
        facilityMaintainingCoRepository.saveAndFlush(facilityMaintainingCo);

        int databaseSizeBeforeDelete = facilityMaintainingCoRepository.findAll().size();

        // Get the facilityMaintainingCo
        restFacilityMaintainingCoMockMvc.perform(delete("/api/facility-maintaining-cos/{id}", facilityMaintainingCo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FacilityMaintainingCo> facilityMaintainingCoList = facilityMaintainingCoRepository.findAll();
        assertThat(facilityMaintainingCoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityMaintainingCo.class);
        FacilityMaintainingCo facilityMaintainingCo1 = new FacilityMaintainingCo();
        facilityMaintainingCo1.setId(1L);
        FacilityMaintainingCo facilityMaintainingCo2 = new FacilityMaintainingCo();
        facilityMaintainingCo2.setId(facilityMaintainingCo1.getId());
        assertThat(facilityMaintainingCo1).isEqualTo(facilityMaintainingCo2);
        facilityMaintainingCo2.setId(2L);
        assertThat(facilityMaintainingCo1).isNotEqualTo(facilityMaintainingCo2);
        facilityMaintainingCo1.setId(null);
        assertThat(facilityMaintainingCo1).isNotEqualTo(facilityMaintainingCo2);
    }
}
