package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.EmployeeGroup;
import com.cges.assetmng.repository.EmployeeGroupRepository;
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
 * Test class for the EmployeeGroupResource REST controller.
 *
 * @see EmployeeGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class EmployeeGroupResourceIntTest {

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    @Autowired
    private EmployeeGroupRepository employeeGroupRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeeGroupMockMvc;

    private EmployeeGroup employeeGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeGroupResource employeeGroupResource = new EmployeeGroupResource(employeeGroupRepository);
        this.restEmployeeGroupMockMvc = MockMvcBuilders.standaloneSetup(employeeGroupResource)
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
    public static EmployeeGroup createEntity(EntityManager em) {
        EmployeeGroup employeeGroup = new EmployeeGroup()
            .groupName(DEFAULT_GROUP_NAME);
        return employeeGroup;
    }

    @Before
    public void initTest() {
        employeeGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeeGroup() throws Exception {
        int databaseSizeBeforeCreate = employeeGroupRepository.findAll().size();

        // Create the EmployeeGroup
        restEmployeeGroupMockMvc.perform(post("/api/employee-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeGroup)))
            .andExpect(status().isCreated());

        // Validate the EmployeeGroup in the database
        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeGroup testEmployeeGroup = employeeGroupList.get(employeeGroupList.size() - 1);
        assertThat(testEmployeeGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
    }

    @Test
    @Transactional
    public void createEmployeeGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeGroupRepository.findAll().size();

        // Create the EmployeeGroup with an existing ID
        employeeGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeGroupMockMvc.perform(post("/api/employee-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeGroup)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeGroup in the database
        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGroupNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeGroupRepository.findAll().size();
        // set the field null
        employeeGroup.setGroupName(null);

        // Create the EmployeeGroup, which fails.

        restEmployeeGroupMockMvc.perform(post("/api/employee-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeGroup)))
            .andExpect(status().isBadRequest());

        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployeeGroups() throws Exception {
        // Initialize the database
        employeeGroupRepository.saveAndFlush(employeeGroup);

        // Get all the employeeGroupList
        restEmployeeGroupMockMvc.perform(get("/api/employee-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getEmployeeGroup() throws Exception {
        // Initialize the database
        employeeGroupRepository.saveAndFlush(employeeGroup);

        // Get the employeeGroup
        restEmployeeGroupMockMvc.perform(get("/api/employee-groups/{id}", employeeGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employeeGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmployeeGroup() throws Exception {
        // Get the employeeGroup
        restEmployeeGroupMockMvc.perform(get("/api/employee-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeeGroup() throws Exception {
        // Initialize the database
        employeeGroupRepository.saveAndFlush(employeeGroup);

        int databaseSizeBeforeUpdate = employeeGroupRepository.findAll().size();

        // Update the employeeGroup
        EmployeeGroup updatedEmployeeGroup = employeeGroupRepository.findById(employeeGroup.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeeGroup are not directly saved in db
        em.detach(updatedEmployeeGroup);
        updatedEmployeeGroup
            .groupName(UPDATED_GROUP_NAME);

        restEmployeeGroupMockMvc.perform(put("/api/employee-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmployeeGroup)))
            .andExpect(status().isOk());

        // Validate the EmployeeGroup in the database
        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeUpdate);
        EmployeeGroup testEmployeeGroup = employeeGroupList.get(employeeGroupList.size() - 1);
        assertThat(testEmployeeGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeeGroup() throws Exception {
        int databaseSizeBeforeUpdate = employeeGroupRepository.findAll().size();

        // Create the EmployeeGroup

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeeGroupMockMvc.perform(put("/api/employee-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeGroup)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeGroup in the database
        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployeeGroup() throws Exception {
        // Initialize the database
        employeeGroupRepository.saveAndFlush(employeeGroup);

        int databaseSizeBeforeDelete = employeeGroupRepository.findAll().size();

        // Get the employeeGroup
        restEmployeeGroupMockMvc.perform(delete("/api/employee-groups/{id}", employeeGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EmployeeGroup> employeeGroupList = employeeGroupRepository.findAll();
        assertThat(employeeGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeGroup.class);
        EmployeeGroup employeeGroup1 = new EmployeeGroup();
        employeeGroup1.setId(1L);
        EmployeeGroup employeeGroup2 = new EmployeeGroup();
        employeeGroup2.setId(employeeGroup1.getId());
        assertThat(employeeGroup1).isEqualTo(employeeGroup2);
        employeeGroup2.setId(2L);
        assertThat(employeeGroup1).isNotEqualTo(employeeGroup2);
        employeeGroup1.setId(null);
        assertThat(employeeGroup1).isNotEqualTo(employeeGroup2);
    }
}
