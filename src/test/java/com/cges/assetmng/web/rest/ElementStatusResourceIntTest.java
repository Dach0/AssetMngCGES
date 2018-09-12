package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.ElementStatus;
import com.cges.assetmng.repository.ElementStatusRepository;
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
 * Test class for the ElementStatusResource REST controller.
 *
 * @see ElementStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class ElementStatusResourceIntTest {

    private static final String DEFAULT_ELEMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENT_STATUS = "BBBBBBBBBB";

    @Autowired
    private ElementStatusRepository elementStatusRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restElementStatusMockMvc;

    private ElementStatus elementStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElementStatusResource elementStatusResource = new ElementStatusResource(elementStatusRepository);
        this.restElementStatusMockMvc = MockMvcBuilders.standaloneSetup(elementStatusResource)
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
    public static ElementStatus createEntity(EntityManager em) {
        ElementStatus elementStatus = new ElementStatus()
            .elementStatus(DEFAULT_ELEMENT_STATUS);
        return elementStatus;
    }

    @Before
    public void initTest() {
        elementStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createElementStatus() throws Exception {
        int databaseSizeBeforeCreate = elementStatusRepository.findAll().size();

        // Create the ElementStatus
        restElementStatusMockMvc.perform(post("/api/element-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementStatus)))
            .andExpect(status().isCreated());

        // Validate the ElementStatus in the database
        List<ElementStatus> elementStatusList = elementStatusRepository.findAll();
        assertThat(elementStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ElementStatus testElementStatus = elementStatusList.get(elementStatusList.size() - 1);
        assertThat(testElementStatus.getElementStatus()).isEqualTo(DEFAULT_ELEMENT_STATUS);
    }

    @Test
    @Transactional
    public void createElementStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elementStatusRepository.findAll().size();

        // Create the ElementStatus with an existing ID
        elementStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementStatusMockMvc.perform(post("/api/element-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ElementStatus in the database
        List<ElementStatus> elementStatusList = elementStatusRepository.findAll();
        assertThat(elementStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllElementStatuses() throws Exception {
        // Initialize the database
        elementStatusRepository.saveAndFlush(elementStatus);

        // Get all the elementStatusList
        restElementStatusMockMvc.perform(get("/api/element-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elementStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].elementStatus").value(hasItem(DEFAULT_ELEMENT_STATUS.toString())));
    }
    

    @Test
    @Transactional
    public void getElementStatus() throws Exception {
        // Initialize the database
        elementStatusRepository.saveAndFlush(elementStatus);

        // Get the elementStatus
        restElementStatusMockMvc.perform(get("/api/element-statuses/{id}", elementStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elementStatus.getId().intValue()))
            .andExpect(jsonPath("$.elementStatus").value(DEFAULT_ELEMENT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingElementStatus() throws Exception {
        // Get the elementStatus
        restElementStatusMockMvc.perform(get("/api/element-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElementStatus() throws Exception {
        // Initialize the database
        elementStatusRepository.saveAndFlush(elementStatus);

        int databaseSizeBeforeUpdate = elementStatusRepository.findAll().size();

        // Update the elementStatus
        ElementStatus updatedElementStatus = elementStatusRepository.findById(elementStatus.getId()).get();
        // Disconnect from session so that the updates on updatedElementStatus are not directly saved in db
        em.detach(updatedElementStatus);
        updatedElementStatus
            .elementStatus(UPDATED_ELEMENT_STATUS);

        restElementStatusMockMvc.perform(put("/api/element-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedElementStatus)))
            .andExpect(status().isOk());

        // Validate the ElementStatus in the database
        List<ElementStatus> elementStatusList = elementStatusRepository.findAll();
        assertThat(elementStatusList).hasSize(databaseSizeBeforeUpdate);
        ElementStatus testElementStatus = elementStatusList.get(elementStatusList.size() - 1);
        assertThat(testElementStatus.getElementStatus()).isEqualTo(UPDATED_ELEMENT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingElementStatus() throws Exception {
        int databaseSizeBeforeUpdate = elementStatusRepository.findAll().size();

        // Create the ElementStatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restElementStatusMockMvc.perform(put("/api/element-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ElementStatus in the database
        List<ElementStatus> elementStatusList = elementStatusRepository.findAll();
        assertThat(elementStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElementStatus() throws Exception {
        // Initialize the database
        elementStatusRepository.saveAndFlush(elementStatus);

        int databaseSizeBeforeDelete = elementStatusRepository.findAll().size();

        // Get the elementStatus
        restElementStatusMockMvc.perform(delete("/api/element-statuses/{id}", elementStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ElementStatus> elementStatusList = elementStatusRepository.findAll();
        assertThat(elementStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementStatus.class);
        ElementStatus elementStatus1 = new ElementStatus();
        elementStatus1.setId(1L);
        ElementStatus elementStatus2 = new ElementStatus();
        elementStatus2.setId(elementStatus1.getId());
        assertThat(elementStatus1).isEqualTo(elementStatus2);
        elementStatus2.setId(2L);
        assertThat(elementStatus1).isNotEqualTo(elementStatus2);
        elementStatus1.setId(null);
        assertThat(elementStatus1).isNotEqualTo(elementStatus2);
    }
}
