package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.ElementCondition;
import com.cges.assetmng.repository.ElementConditionRepository;
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
 * Test class for the ElementConditionResource REST controller.
 *
 * @see ElementConditionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class ElementConditionResourceIntTest {

    private static final String DEFAULT_ELEMENT_CONDITION = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENT_CONDITION = "BBBBBBBBBB";

    @Autowired
    private ElementConditionRepository elementConditionRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restElementConditionMockMvc;

    private ElementCondition elementCondition;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElementConditionResource elementConditionResource = new ElementConditionResource(elementConditionRepository);
        this.restElementConditionMockMvc = MockMvcBuilders.standaloneSetup(elementConditionResource)
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
    public static ElementCondition createEntity(EntityManager em) {
        ElementCondition elementCondition = new ElementCondition()
            .elementCondition(DEFAULT_ELEMENT_CONDITION);
        return elementCondition;
    }

    @Before
    public void initTest() {
        elementCondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createElementCondition() throws Exception {
        int databaseSizeBeforeCreate = elementConditionRepository.findAll().size();

        // Create the ElementCondition
        restElementConditionMockMvc.perform(post("/api/element-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementCondition)))
            .andExpect(status().isCreated());

        // Validate the ElementCondition in the database
        List<ElementCondition> elementConditionList = elementConditionRepository.findAll();
        assertThat(elementConditionList).hasSize(databaseSizeBeforeCreate + 1);
        ElementCondition testElementCondition = elementConditionList.get(elementConditionList.size() - 1);
        assertThat(testElementCondition.getElementCondition()).isEqualTo(DEFAULT_ELEMENT_CONDITION);
    }

    @Test
    @Transactional
    public void createElementConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elementConditionRepository.findAll().size();

        // Create the ElementCondition with an existing ID
        elementCondition.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementConditionMockMvc.perform(post("/api/element-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementCondition)))
            .andExpect(status().isBadRequest());

        // Validate the ElementCondition in the database
        List<ElementCondition> elementConditionList = elementConditionRepository.findAll();
        assertThat(elementConditionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllElementConditions() throws Exception {
        // Initialize the database
        elementConditionRepository.saveAndFlush(elementCondition);

        // Get all the elementConditionList
        restElementConditionMockMvc.perform(get("/api/element-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elementCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].elementCondition").value(hasItem(DEFAULT_ELEMENT_CONDITION.toString())));
    }
    

    @Test
    @Transactional
    public void getElementCondition() throws Exception {
        // Initialize the database
        elementConditionRepository.saveAndFlush(elementCondition);

        // Get the elementCondition
        restElementConditionMockMvc.perform(get("/api/element-conditions/{id}", elementCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elementCondition.getId().intValue()))
            .andExpect(jsonPath("$.elementCondition").value(DEFAULT_ELEMENT_CONDITION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingElementCondition() throws Exception {
        // Get the elementCondition
        restElementConditionMockMvc.perform(get("/api/element-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElementCondition() throws Exception {
        // Initialize the database
        elementConditionRepository.saveAndFlush(elementCondition);

        int databaseSizeBeforeUpdate = elementConditionRepository.findAll().size();

        // Update the elementCondition
        ElementCondition updatedElementCondition = elementConditionRepository.findById(elementCondition.getId()).get();
        // Disconnect from session so that the updates on updatedElementCondition are not directly saved in db
        em.detach(updatedElementCondition);
        updatedElementCondition
            .elementCondition(UPDATED_ELEMENT_CONDITION);

        restElementConditionMockMvc.perform(put("/api/element-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedElementCondition)))
            .andExpect(status().isOk());

        // Validate the ElementCondition in the database
        List<ElementCondition> elementConditionList = elementConditionRepository.findAll();
        assertThat(elementConditionList).hasSize(databaseSizeBeforeUpdate);
        ElementCondition testElementCondition = elementConditionList.get(elementConditionList.size() - 1);
        assertThat(testElementCondition.getElementCondition()).isEqualTo(UPDATED_ELEMENT_CONDITION);
    }

    @Test
    @Transactional
    public void updateNonExistingElementCondition() throws Exception {
        int databaseSizeBeforeUpdate = elementConditionRepository.findAll().size();

        // Create the ElementCondition

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restElementConditionMockMvc.perform(put("/api/element-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementCondition)))
            .andExpect(status().isBadRequest());

        // Validate the ElementCondition in the database
        List<ElementCondition> elementConditionList = elementConditionRepository.findAll();
        assertThat(elementConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElementCondition() throws Exception {
        // Initialize the database
        elementConditionRepository.saveAndFlush(elementCondition);

        int databaseSizeBeforeDelete = elementConditionRepository.findAll().size();

        // Get the elementCondition
        restElementConditionMockMvc.perform(delete("/api/element-conditions/{id}", elementCondition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ElementCondition> elementConditionList = elementConditionRepository.findAll();
        assertThat(elementConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementCondition.class);
        ElementCondition elementCondition1 = new ElementCondition();
        elementCondition1.setId(1L);
        ElementCondition elementCondition2 = new ElementCondition();
        elementCondition2.setId(elementCondition1.getId());
        assertThat(elementCondition1).isEqualTo(elementCondition2);
        elementCondition2.setId(2L);
        assertThat(elementCondition1).isNotEqualTo(elementCondition2);
        elementCondition1.setId(null);
        assertThat(elementCondition1).isNotEqualTo(elementCondition2);
    }
}
