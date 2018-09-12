package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.TransformatorNumber;
import com.cges.assetmng.repository.TransformatorNumberRepository;
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
 * Test class for the TransformatorNumberResource REST controller.
 *
 * @see TransformatorNumberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TransformatorNumberResourceIntTest {

    private static final String DEFAULT_T_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_T_NUMBER = "BBBBBBBBBB";

    @Autowired
    private TransformatorNumberRepository transformatorNumberRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransformatorNumberMockMvc;

    private TransformatorNumber transformatorNumber;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransformatorNumberResource transformatorNumberResource = new TransformatorNumberResource(transformatorNumberRepository);
        this.restTransformatorNumberMockMvc = MockMvcBuilders.standaloneSetup(transformatorNumberResource)
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
    public static TransformatorNumber createEntity(EntityManager em) {
        TransformatorNumber transformatorNumber = new TransformatorNumber()
            .tNumber(DEFAULT_T_NUMBER);
        return transformatorNumber;
    }

    @Before
    public void initTest() {
        transformatorNumber = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransformatorNumber() throws Exception {
        int databaseSizeBeforeCreate = transformatorNumberRepository.findAll().size();

        // Create the TransformatorNumber
        restTransformatorNumberMockMvc.perform(post("/api/transformator-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformatorNumber)))
            .andExpect(status().isCreated());

        // Validate the TransformatorNumber in the database
        List<TransformatorNumber> transformatorNumberList = transformatorNumberRepository.findAll();
        assertThat(transformatorNumberList).hasSize(databaseSizeBeforeCreate + 1);
        TransformatorNumber testTransformatorNumber = transformatorNumberList.get(transformatorNumberList.size() - 1);
        assertThat(testTransformatorNumber.gettNumber()).isEqualTo(DEFAULT_T_NUMBER);
    }

    @Test
    @Transactional
    public void createTransformatorNumberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transformatorNumberRepository.findAll().size();

        // Create the TransformatorNumber with an existing ID
        transformatorNumber.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransformatorNumberMockMvc.perform(post("/api/transformator-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformatorNumber)))
            .andExpect(status().isBadRequest());

        // Validate the TransformatorNumber in the database
        List<TransformatorNumber> transformatorNumberList = transformatorNumberRepository.findAll();
        assertThat(transformatorNumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransformatorNumbers() throws Exception {
        // Initialize the database
        transformatorNumberRepository.saveAndFlush(transformatorNumber);

        // Get all the transformatorNumberList
        restTransformatorNumberMockMvc.perform(get("/api/transformator-numbers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transformatorNumber.getId().intValue())))
            .andExpect(jsonPath("$.[*].tNumber").value(hasItem(DEFAULT_T_NUMBER.toString())));
    }
    

    @Test
    @Transactional
    public void getTransformatorNumber() throws Exception {
        // Initialize the database
        transformatorNumberRepository.saveAndFlush(transformatorNumber);

        // Get the transformatorNumber
        restTransformatorNumberMockMvc.perform(get("/api/transformator-numbers/{id}", transformatorNumber.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transformatorNumber.getId().intValue()))
            .andExpect(jsonPath("$.tNumber").value(DEFAULT_T_NUMBER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransformatorNumber() throws Exception {
        // Get the transformatorNumber
        restTransformatorNumberMockMvc.perform(get("/api/transformator-numbers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransformatorNumber() throws Exception {
        // Initialize the database
        transformatorNumberRepository.saveAndFlush(transformatorNumber);

        int databaseSizeBeforeUpdate = transformatorNumberRepository.findAll().size();

        // Update the transformatorNumber
        TransformatorNumber updatedTransformatorNumber = transformatorNumberRepository.findById(transformatorNumber.getId()).get();
        // Disconnect from session so that the updates on updatedTransformatorNumber are not directly saved in db
        em.detach(updatedTransformatorNumber);
        updatedTransformatorNumber
            .tNumber(UPDATED_T_NUMBER);

        restTransformatorNumberMockMvc.perform(put("/api/transformator-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransformatorNumber)))
            .andExpect(status().isOk());

        // Validate the TransformatorNumber in the database
        List<TransformatorNumber> transformatorNumberList = transformatorNumberRepository.findAll();
        assertThat(transformatorNumberList).hasSize(databaseSizeBeforeUpdate);
        TransformatorNumber testTransformatorNumber = transformatorNumberList.get(transformatorNumberList.size() - 1);
        assertThat(testTransformatorNumber.gettNumber()).isEqualTo(UPDATED_T_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingTransformatorNumber() throws Exception {
        int databaseSizeBeforeUpdate = transformatorNumberRepository.findAll().size();

        // Create the TransformatorNumber

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransformatorNumberMockMvc.perform(put("/api/transformator-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformatorNumber)))
            .andExpect(status().isBadRequest());

        // Validate the TransformatorNumber in the database
        List<TransformatorNumber> transformatorNumberList = transformatorNumberRepository.findAll();
        assertThat(transformatorNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransformatorNumber() throws Exception {
        // Initialize the database
        transformatorNumberRepository.saveAndFlush(transformatorNumber);

        int databaseSizeBeforeDelete = transformatorNumberRepository.findAll().size();

        // Get the transformatorNumber
        restTransformatorNumberMockMvc.perform(delete("/api/transformator-numbers/{id}", transformatorNumber.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransformatorNumber> transformatorNumberList = transformatorNumberRepository.findAll();
        assertThat(transformatorNumberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransformatorNumber.class);
        TransformatorNumber transformatorNumber1 = new TransformatorNumber();
        transformatorNumber1.setId(1L);
        TransformatorNumber transformatorNumber2 = new TransformatorNumber();
        transformatorNumber2.setId(transformatorNumber1.getId());
        assertThat(transformatorNumber1).isEqualTo(transformatorNumber2);
        transformatorNumber2.setId(2L);
        assertThat(transformatorNumber1).isNotEqualTo(transformatorNumber2);
        transformatorNumber1.setId(null);
        assertThat(transformatorNumber1).isNotEqualTo(transformatorNumber2);
    }
}
