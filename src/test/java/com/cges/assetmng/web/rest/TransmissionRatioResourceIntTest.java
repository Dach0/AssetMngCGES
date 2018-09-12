package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.TransmissionRatio;
import com.cges.assetmng.repository.TransmissionRatioRepository;
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
 * Test class for the TransmissionRatioResource REST controller.
 *
 * @see TransmissionRatioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TransmissionRatioResourceIntTest {

    private static final String DEFAULT_TRANSMISSION_RATIO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSMISSION_RATIO = "BBBBBBBBBB";

    @Autowired
    private TransmissionRatioRepository transmissionRatioRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransmissionRatioMockMvc;

    private TransmissionRatio transmissionRatio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransmissionRatioResource transmissionRatioResource = new TransmissionRatioResource(transmissionRatioRepository);
        this.restTransmissionRatioMockMvc = MockMvcBuilders.standaloneSetup(transmissionRatioResource)
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
    public static TransmissionRatio createEntity(EntityManager em) {
        TransmissionRatio transmissionRatio = new TransmissionRatio()
            .transmissionRatio(DEFAULT_TRANSMISSION_RATIO);
        return transmissionRatio;
    }

    @Before
    public void initTest() {
        transmissionRatio = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransmissionRatio() throws Exception {
        int databaseSizeBeforeCreate = transmissionRatioRepository.findAll().size();

        // Create the TransmissionRatio
        restTransmissionRatioMockMvc.perform(post("/api/transmission-ratios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissionRatio)))
            .andExpect(status().isCreated());

        // Validate the TransmissionRatio in the database
        List<TransmissionRatio> transmissionRatioList = transmissionRatioRepository.findAll();
        assertThat(transmissionRatioList).hasSize(databaseSizeBeforeCreate + 1);
        TransmissionRatio testTransmissionRatio = transmissionRatioList.get(transmissionRatioList.size() - 1);
        assertThat(testTransmissionRatio.getTransmissionRatio()).isEqualTo(DEFAULT_TRANSMISSION_RATIO);
    }

    @Test
    @Transactional
    public void createTransmissionRatioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transmissionRatioRepository.findAll().size();

        // Create the TransmissionRatio with an existing ID
        transmissionRatio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransmissionRatioMockMvc.perform(post("/api/transmission-ratios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissionRatio)))
            .andExpect(status().isBadRequest());

        // Validate the TransmissionRatio in the database
        List<TransmissionRatio> transmissionRatioList = transmissionRatioRepository.findAll();
        assertThat(transmissionRatioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransmissionRatios() throws Exception {
        // Initialize the database
        transmissionRatioRepository.saveAndFlush(transmissionRatio);

        // Get all the transmissionRatioList
        restTransmissionRatioMockMvc.perform(get("/api/transmission-ratios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transmissionRatio.getId().intValue())))
            .andExpect(jsonPath("$.[*].transmissionRatio").value(hasItem(DEFAULT_TRANSMISSION_RATIO.toString())));
    }
    

    @Test
    @Transactional
    public void getTransmissionRatio() throws Exception {
        // Initialize the database
        transmissionRatioRepository.saveAndFlush(transmissionRatio);

        // Get the transmissionRatio
        restTransmissionRatioMockMvc.perform(get("/api/transmission-ratios/{id}", transmissionRatio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transmissionRatio.getId().intValue()))
            .andExpect(jsonPath("$.transmissionRatio").value(DEFAULT_TRANSMISSION_RATIO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransmissionRatio() throws Exception {
        // Get the transmissionRatio
        restTransmissionRatioMockMvc.perform(get("/api/transmission-ratios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransmissionRatio() throws Exception {
        // Initialize the database
        transmissionRatioRepository.saveAndFlush(transmissionRatio);

        int databaseSizeBeforeUpdate = transmissionRatioRepository.findAll().size();

        // Update the transmissionRatio
        TransmissionRatio updatedTransmissionRatio = transmissionRatioRepository.findById(transmissionRatio.getId()).get();
        // Disconnect from session so that the updates on updatedTransmissionRatio are not directly saved in db
        em.detach(updatedTransmissionRatio);
        updatedTransmissionRatio
            .transmissionRatio(UPDATED_TRANSMISSION_RATIO);

        restTransmissionRatioMockMvc.perform(put("/api/transmission-ratios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransmissionRatio)))
            .andExpect(status().isOk());

        // Validate the TransmissionRatio in the database
        List<TransmissionRatio> transmissionRatioList = transmissionRatioRepository.findAll();
        assertThat(transmissionRatioList).hasSize(databaseSizeBeforeUpdate);
        TransmissionRatio testTransmissionRatio = transmissionRatioList.get(transmissionRatioList.size() - 1);
        assertThat(testTransmissionRatio.getTransmissionRatio()).isEqualTo(UPDATED_TRANSMISSION_RATIO);
    }

    @Test
    @Transactional
    public void updateNonExistingTransmissionRatio() throws Exception {
        int databaseSizeBeforeUpdate = transmissionRatioRepository.findAll().size();

        // Create the TransmissionRatio

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransmissionRatioMockMvc.perform(put("/api/transmission-ratios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissionRatio)))
            .andExpect(status().isBadRequest());

        // Validate the TransmissionRatio in the database
        List<TransmissionRatio> transmissionRatioList = transmissionRatioRepository.findAll();
        assertThat(transmissionRatioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransmissionRatio() throws Exception {
        // Initialize the database
        transmissionRatioRepository.saveAndFlush(transmissionRatio);

        int databaseSizeBeforeDelete = transmissionRatioRepository.findAll().size();

        // Get the transmissionRatio
        restTransmissionRatioMockMvc.perform(delete("/api/transmission-ratios/{id}", transmissionRatio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransmissionRatio> transmissionRatioList = transmissionRatioRepository.findAll();
        assertThat(transmissionRatioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransmissionRatio.class);
        TransmissionRatio transmissionRatio1 = new TransmissionRatio();
        transmissionRatio1.setId(1L);
        TransmissionRatio transmissionRatio2 = new TransmissionRatio();
        transmissionRatio2.setId(transmissionRatio1.getId());
        assertThat(transmissionRatio1).isEqualTo(transmissionRatio2);
        transmissionRatio2.setId(2L);
        assertThat(transmissionRatio1).isNotEqualTo(transmissionRatio2);
        transmissionRatio1.setId(null);
        assertThat(transmissionRatio1).isNotEqualTo(transmissionRatio2);
    }
}
