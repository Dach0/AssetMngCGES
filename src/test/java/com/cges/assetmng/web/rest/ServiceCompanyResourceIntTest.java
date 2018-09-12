package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.ServiceCompany;
import com.cges.assetmng.repository.ServiceCompanyRepository;
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
 * Test class for the ServiceCompanyResource REST controller.
 *
 * @see ServiceCompanyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class ServiceCompanyResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    @Autowired
    private ServiceCompanyRepository serviceCompanyRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restServiceCompanyMockMvc;

    private ServiceCompany serviceCompany;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServiceCompanyResource serviceCompanyResource = new ServiceCompanyResource(serviceCompanyRepository);
        this.restServiceCompanyMockMvc = MockMvcBuilders.standaloneSetup(serviceCompanyResource)
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
    public static ServiceCompany createEntity(EntityManager em) {
        ServiceCompany serviceCompany = new ServiceCompany()
            .companyName(DEFAULT_COMPANY_NAME);
        return serviceCompany;
    }

    @Before
    public void initTest() {
        serviceCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceCompany() throws Exception {
        int databaseSizeBeforeCreate = serviceCompanyRepository.findAll().size();

        // Create the ServiceCompany
        restServiceCompanyMockMvc.perform(post("/api/service-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceCompany)))
            .andExpect(status().isCreated());

        // Validate the ServiceCompany in the database
        List<ServiceCompany> serviceCompanyList = serviceCompanyRepository.findAll();
        assertThat(serviceCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceCompany testServiceCompany = serviceCompanyList.get(serviceCompanyList.size() - 1);
        assertThat(testServiceCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void createServiceCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceCompanyRepository.findAll().size();

        // Create the ServiceCompany with an existing ID
        serviceCompany.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceCompanyMockMvc.perform(post("/api/service-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceCompany)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceCompany in the database
        List<ServiceCompany> serviceCompanyList = serviceCompanyRepository.findAll();
        assertThat(serviceCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServiceCompanies() throws Exception {
        // Initialize the database
        serviceCompanyRepository.saveAndFlush(serviceCompany);

        // Get all the serviceCompanyList
        restServiceCompanyMockMvc.perform(get("/api/service-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getServiceCompany() throws Exception {
        // Initialize the database
        serviceCompanyRepository.saveAndFlush(serviceCompany);

        // Get the serviceCompany
        restServiceCompanyMockMvc.perform(get("/api/service-companies/{id}", serviceCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serviceCompany.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingServiceCompany() throws Exception {
        // Get the serviceCompany
        restServiceCompanyMockMvc.perform(get("/api/service-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceCompany() throws Exception {
        // Initialize the database
        serviceCompanyRepository.saveAndFlush(serviceCompany);

        int databaseSizeBeforeUpdate = serviceCompanyRepository.findAll().size();

        // Update the serviceCompany
        ServiceCompany updatedServiceCompany = serviceCompanyRepository.findById(serviceCompany.getId()).get();
        // Disconnect from session so that the updates on updatedServiceCompany are not directly saved in db
        em.detach(updatedServiceCompany);
        updatedServiceCompany
            .companyName(UPDATED_COMPANY_NAME);

        restServiceCompanyMockMvc.perform(put("/api/service-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedServiceCompany)))
            .andExpect(status().isOk());

        // Validate the ServiceCompany in the database
        List<ServiceCompany> serviceCompanyList = serviceCompanyRepository.findAll();
        assertThat(serviceCompanyList).hasSize(databaseSizeBeforeUpdate);
        ServiceCompany testServiceCompany = serviceCompanyList.get(serviceCompanyList.size() - 1);
        assertThat(testServiceCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceCompany() throws Exception {
        int databaseSizeBeforeUpdate = serviceCompanyRepository.findAll().size();

        // Create the ServiceCompany

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restServiceCompanyMockMvc.perform(put("/api/service-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceCompany)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceCompany in the database
        List<ServiceCompany> serviceCompanyList = serviceCompanyRepository.findAll();
        assertThat(serviceCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceCompany() throws Exception {
        // Initialize the database
        serviceCompanyRepository.saveAndFlush(serviceCompany);

        int databaseSizeBeforeDelete = serviceCompanyRepository.findAll().size();

        // Get the serviceCompany
        restServiceCompanyMockMvc.perform(delete("/api/service-companies/{id}", serviceCompany.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ServiceCompany> serviceCompanyList = serviceCompanyRepository.findAll();
        assertThat(serviceCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceCompany.class);
        ServiceCompany serviceCompany1 = new ServiceCompany();
        serviceCompany1.setId(1L);
        ServiceCompany serviceCompany2 = new ServiceCompany();
        serviceCompany2.setId(serviceCompany1.getId());
        assertThat(serviceCompany1).isEqualTo(serviceCompany2);
        serviceCompany2.setId(2L);
        assertThat(serviceCompany1).isNotEqualTo(serviceCompany2);
        serviceCompany1.setId(null);
        assertThat(serviceCompany1).isNotEqualTo(serviceCompany2);
    }
}
