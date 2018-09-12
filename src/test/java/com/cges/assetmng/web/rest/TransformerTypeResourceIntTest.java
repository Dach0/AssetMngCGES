package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.TransformerType;
import com.cges.assetmng.repository.TransformerTypeRepository;
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
 * Test class for the TransformerTypeResource REST controller.
 *
 * @see TransformerTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class TransformerTypeResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private TransformerTypeRepository transformerTypeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransformerTypeMockMvc;

    private TransformerType transformerType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransformerTypeResource transformerTypeResource = new TransformerTypeResource(transformerTypeRepository);
        this.restTransformerTypeMockMvc = MockMvcBuilders.standaloneSetup(transformerTypeResource)
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
    public static TransformerType createEntity(EntityManager em) {
        TransformerType transformerType = new TransformerType()
            .type(DEFAULT_TYPE);
        return transformerType;
    }

    @Before
    public void initTest() {
        transformerType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransformerType() throws Exception {
        int databaseSizeBeforeCreate = transformerTypeRepository.findAll().size();

        // Create the TransformerType
        restTransformerTypeMockMvc.perform(post("/api/transformer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformerType)))
            .andExpect(status().isCreated());

        // Validate the TransformerType in the database
        List<TransformerType> transformerTypeList = transformerTypeRepository.findAll();
        assertThat(transformerTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TransformerType testTransformerType = transformerTypeList.get(transformerTypeList.size() - 1);
        assertThat(testTransformerType.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createTransformerTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transformerTypeRepository.findAll().size();

        // Create the TransformerType with an existing ID
        transformerType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransformerTypeMockMvc.perform(post("/api/transformer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformerType)))
            .andExpect(status().isBadRequest());

        // Validate the TransformerType in the database
        List<TransformerType> transformerTypeList = transformerTypeRepository.findAll();
        assertThat(transformerTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransformerTypes() throws Exception {
        // Initialize the database
        transformerTypeRepository.saveAndFlush(transformerType);

        // Get all the transformerTypeList
        restTransformerTypeMockMvc.perform(get("/api/transformer-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transformerType.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getTransformerType() throws Exception {
        // Initialize the database
        transformerTypeRepository.saveAndFlush(transformerType);

        // Get the transformerType
        restTransformerTypeMockMvc.perform(get("/api/transformer-types/{id}", transformerType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transformerType.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransformerType() throws Exception {
        // Get the transformerType
        restTransformerTypeMockMvc.perform(get("/api/transformer-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransformerType() throws Exception {
        // Initialize the database
        transformerTypeRepository.saveAndFlush(transformerType);

        int databaseSizeBeforeUpdate = transformerTypeRepository.findAll().size();

        // Update the transformerType
        TransformerType updatedTransformerType = transformerTypeRepository.findById(transformerType.getId()).get();
        // Disconnect from session so that the updates on updatedTransformerType are not directly saved in db
        em.detach(updatedTransformerType);
        updatedTransformerType
            .type(UPDATED_TYPE);

        restTransformerTypeMockMvc.perform(put("/api/transformer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransformerType)))
            .andExpect(status().isOk());

        // Validate the TransformerType in the database
        List<TransformerType> transformerTypeList = transformerTypeRepository.findAll();
        assertThat(transformerTypeList).hasSize(databaseSizeBeforeUpdate);
        TransformerType testTransformerType = transformerTypeList.get(transformerTypeList.size() - 1);
        assertThat(testTransformerType.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransformerType() throws Exception {
        int databaseSizeBeforeUpdate = transformerTypeRepository.findAll().size();

        // Create the TransformerType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransformerTypeMockMvc.perform(put("/api/transformer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transformerType)))
            .andExpect(status().isBadRequest());

        // Validate the TransformerType in the database
        List<TransformerType> transformerTypeList = transformerTypeRepository.findAll();
        assertThat(transformerTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransformerType() throws Exception {
        // Initialize the database
        transformerTypeRepository.saveAndFlush(transformerType);

        int databaseSizeBeforeDelete = transformerTypeRepository.findAll().size();

        // Get the transformerType
        restTransformerTypeMockMvc.perform(delete("/api/transformer-types/{id}", transformerType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransformerType> transformerTypeList = transformerTypeRepository.findAll();
        assertThat(transformerTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransformerType.class);
        TransformerType transformerType1 = new TransformerType();
        transformerType1.setId(1L);
        TransformerType transformerType2 = new TransformerType();
        transformerType2.setId(transformerType1.getId());
        assertThat(transformerType1).isEqualTo(transformerType2);
        transformerType2.setId(2L);
        assertThat(transformerType1).isNotEqualTo(transformerType2);
        transformerType1.setId(null);
        assertThat(transformerType1).isNotEqualTo(transformerType2);
    }
}
