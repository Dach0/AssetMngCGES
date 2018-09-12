package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Coupling;
import com.cges.assetmng.repository.CouplingRepository;
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
 * Test class for the CouplingResource REST controller.
 *
 * @see CouplingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class CouplingResourceIntTest {

    private static final String DEFAULT_COUPLING = "AAAAAAAAAA";
    private static final String UPDATED_COUPLING = "BBBBBBBBBB";

    @Autowired
    private CouplingRepository couplingRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCouplingMockMvc;

    private Coupling coupling;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CouplingResource couplingResource = new CouplingResource(couplingRepository);
        this.restCouplingMockMvc = MockMvcBuilders.standaloneSetup(couplingResource)
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
    public static Coupling createEntity(EntityManager em) {
        Coupling coupling = new Coupling()
            .coupling(DEFAULT_COUPLING);
        return coupling;
    }

    @Before
    public void initTest() {
        coupling = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoupling() throws Exception {
        int databaseSizeBeforeCreate = couplingRepository.findAll().size();

        // Create the Coupling
        restCouplingMockMvc.perform(post("/api/couplings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coupling)))
            .andExpect(status().isCreated());

        // Validate the Coupling in the database
        List<Coupling> couplingList = couplingRepository.findAll();
        assertThat(couplingList).hasSize(databaseSizeBeforeCreate + 1);
        Coupling testCoupling = couplingList.get(couplingList.size() - 1);
        assertThat(testCoupling.getCoupling()).isEqualTo(DEFAULT_COUPLING);
    }

    @Test
    @Transactional
    public void createCouplingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = couplingRepository.findAll().size();

        // Create the Coupling with an existing ID
        coupling.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCouplingMockMvc.perform(post("/api/couplings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coupling)))
            .andExpect(status().isBadRequest());

        // Validate the Coupling in the database
        List<Coupling> couplingList = couplingRepository.findAll();
        assertThat(couplingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCouplings() throws Exception {
        // Initialize the database
        couplingRepository.saveAndFlush(coupling);

        // Get all the couplingList
        restCouplingMockMvc.perform(get("/api/couplings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coupling.getId().intValue())))
            .andExpect(jsonPath("$.[*].coupling").value(hasItem(DEFAULT_COUPLING.toString())));
    }
    

    @Test
    @Transactional
    public void getCoupling() throws Exception {
        // Initialize the database
        couplingRepository.saveAndFlush(coupling);

        // Get the coupling
        restCouplingMockMvc.perform(get("/api/couplings/{id}", coupling.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coupling.getId().intValue()))
            .andExpect(jsonPath("$.coupling").value(DEFAULT_COUPLING.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCoupling() throws Exception {
        // Get the coupling
        restCouplingMockMvc.perform(get("/api/couplings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoupling() throws Exception {
        // Initialize the database
        couplingRepository.saveAndFlush(coupling);

        int databaseSizeBeforeUpdate = couplingRepository.findAll().size();

        // Update the coupling
        Coupling updatedCoupling = couplingRepository.findById(coupling.getId()).get();
        // Disconnect from session so that the updates on updatedCoupling are not directly saved in db
        em.detach(updatedCoupling);
        updatedCoupling
            .coupling(UPDATED_COUPLING);

        restCouplingMockMvc.perform(put("/api/couplings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCoupling)))
            .andExpect(status().isOk());

        // Validate the Coupling in the database
        List<Coupling> couplingList = couplingRepository.findAll();
        assertThat(couplingList).hasSize(databaseSizeBeforeUpdate);
        Coupling testCoupling = couplingList.get(couplingList.size() - 1);
        assertThat(testCoupling.getCoupling()).isEqualTo(UPDATED_COUPLING);
    }

    @Test
    @Transactional
    public void updateNonExistingCoupling() throws Exception {
        int databaseSizeBeforeUpdate = couplingRepository.findAll().size();

        // Create the Coupling

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCouplingMockMvc.perform(put("/api/couplings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coupling)))
            .andExpect(status().isBadRequest());

        // Validate the Coupling in the database
        List<Coupling> couplingList = couplingRepository.findAll();
        assertThat(couplingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoupling() throws Exception {
        // Initialize the database
        couplingRepository.saveAndFlush(coupling);

        int databaseSizeBeforeDelete = couplingRepository.findAll().size();

        // Get the coupling
        restCouplingMockMvc.perform(delete("/api/couplings/{id}", coupling.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Coupling> couplingList = couplingRepository.findAll();
        assertThat(couplingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coupling.class);
        Coupling coupling1 = new Coupling();
        coupling1.setId(1L);
        Coupling coupling2 = new Coupling();
        coupling2.setId(coupling1.getId());
        assertThat(coupling1).isEqualTo(coupling2);
        coupling2.setId(2L);
        assertThat(coupling1).isNotEqualTo(coupling2);
        coupling1.setId(null);
        assertThat(coupling1).isNotEqualTo(coupling2);
    }
}
