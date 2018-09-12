package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Substation;
import com.cges.assetmng.repository.SubstationRepository;
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
 * Test class for the SubstationResource REST controller.
 *
 * @see SubstationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class SubstationResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_OPERATION_YEAR = 1;
    private static final Integer UPDATED_OPERATION_YEAR = 2;

    @Autowired
    private SubstationRepository substationRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubstationMockMvc;

    private Substation substation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubstationResource substationResource = new SubstationResource(substationRepository);
        this.restSubstationMockMvc = MockMvcBuilders.standaloneSetup(substationResource)
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
    public static Substation createEntity(EntityManager em) {
        Substation substation = new Substation()
            .name(DEFAULT_NAME)
            .operationYear(DEFAULT_OPERATION_YEAR);
        return substation;
    }

    @Before
    public void initTest() {
        substation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubstation() throws Exception {
        int databaseSizeBeforeCreate = substationRepository.findAll().size();

        // Create the Substation
        restSubstationMockMvc.perform(post("/api/substations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(substation)))
            .andExpect(status().isCreated());

        // Validate the Substation in the database
        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeCreate + 1);
        Substation testSubstation = substationList.get(substationList.size() - 1);
        assertThat(testSubstation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSubstation.getOperationYear()).isEqualTo(DEFAULT_OPERATION_YEAR);
    }

    @Test
    @Transactional
    public void createSubstationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = substationRepository.findAll().size();

        // Create the Substation with an existing ID
        substation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubstationMockMvc.perform(post("/api/substations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(substation)))
            .andExpect(status().isBadRequest());

        // Validate the Substation in the database
        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = substationRepository.findAll().size();
        // set the field null
        substation.setName(null);

        // Create the Substation, which fails.

        restSubstationMockMvc.perform(post("/api/substations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(substation)))
            .andExpect(status().isBadRequest());

        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubstations() throws Exception {
        // Initialize the database
        substationRepository.saveAndFlush(substation);

        // Get all the substationList
        restSubstationMockMvc.perform(get("/api/substations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(substation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].operationYear").value(hasItem(DEFAULT_OPERATION_YEAR)));
    }
    

    @Test
    @Transactional
    public void getSubstation() throws Exception {
        // Initialize the database
        substationRepository.saveAndFlush(substation);

        // Get the substation
        restSubstationMockMvc.perform(get("/api/substations/{id}", substation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(substation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.operationYear").value(DEFAULT_OPERATION_YEAR));
    }
    @Test
    @Transactional
    public void getNonExistingSubstation() throws Exception {
        // Get the substation
        restSubstationMockMvc.perform(get("/api/substations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubstation() throws Exception {
        // Initialize the database
        substationRepository.saveAndFlush(substation);

        int databaseSizeBeforeUpdate = substationRepository.findAll().size();

        // Update the substation
        Substation updatedSubstation = substationRepository.findById(substation.getId()).get();
        // Disconnect from session so that the updates on updatedSubstation are not directly saved in db
        em.detach(updatedSubstation);
        updatedSubstation
            .name(UPDATED_NAME)
            .operationYear(UPDATED_OPERATION_YEAR);

        restSubstationMockMvc.perform(put("/api/substations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubstation)))
            .andExpect(status().isOk());

        // Validate the Substation in the database
        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeUpdate);
        Substation testSubstation = substationList.get(substationList.size() - 1);
        assertThat(testSubstation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSubstation.getOperationYear()).isEqualTo(UPDATED_OPERATION_YEAR);
    }

    @Test
    @Transactional
    public void updateNonExistingSubstation() throws Exception {
        int databaseSizeBeforeUpdate = substationRepository.findAll().size();

        // Create the Substation

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSubstationMockMvc.perform(put("/api/substations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(substation)))
            .andExpect(status().isBadRequest());

        // Validate the Substation in the database
        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubstation() throws Exception {
        // Initialize the database
        substationRepository.saveAndFlush(substation);

        int databaseSizeBeforeDelete = substationRepository.findAll().size();

        // Get the substation
        restSubstationMockMvc.perform(delete("/api/substations/{id}", substation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Substation> substationList = substationRepository.findAll();
        assertThat(substationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Substation.class);
        Substation substation1 = new Substation();
        substation1.setId(1L);
        Substation substation2 = new Substation();
        substation2.setId(substation1.getId());
        assertThat(substation1).isEqualTo(substation2);
        substation2.setId(2L);
        assertThat(substation1).isNotEqualTo(substation2);
        substation1.setId(null);
        assertThat(substation1).isNotEqualTo(substation2);
    }
}
