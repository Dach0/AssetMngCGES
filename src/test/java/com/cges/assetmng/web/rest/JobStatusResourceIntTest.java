package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.JobStatus;
import com.cges.assetmng.repository.JobStatusRepository;
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
 * Test class for the JobStatusResource REST controller.
 *
 * @see JobStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class JobStatusResourceIntTest {

    private static final String DEFAULT_JOB_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_JOB_STATUS = "BBBBBBBBBB";

    @Autowired
    private JobStatusRepository jobStatusRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobStatusMockMvc;

    private JobStatus jobStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobStatusResource jobStatusResource = new JobStatusResource(jobStatusRepository);
        this.restJobStatusMockMvc = MockMvcBuilders.standaloneSetup(jobStatusResource)
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
    public static JobStatus createEntity(EntityManager em) {
        JobStatus jobStatus = new JobStatus()
            .jobStatus(DEFAULT_JOB_STATUS);
        return jobStatus;
    }

    @Before
    public void initTest() {
        jobStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobStatus() throws Exception {
        int databaseSizeBeforeCreate = jobStatusRepository.findAll().size();

        // Create the JobStatus
        restJobStatusMockMvc.perform(post("/api/job-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobStatus)))
            .andExpect(status().isCreated());

        // Validate the JobStatus in the database
        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeCreate + 1);
        JobStatus testJobStatus = jobStatusList.get(jobStatusList.size() - 1);
        assertThat(testJobStatus.getJobStatus()).isEqualTo(DEFAULT_JOB_STATUS);
    }

    @Test
    @Transactional
    public void createJobStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobStatusRepository.findAll().size();

        // Create the JobStatus with an existing ID
        jobStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobStatusMockMvc.perform(post("/api/job-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobStatus)))
            .andExpect(status().isBadRequest());

        // Validate the JobStatus in the database
        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkJobStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobStatusRepository.findAll().size();
        // set the field null
        jobStatus.setJobStatus(null);

        // Create the JobStatus, which fails.

        restJobStatusMockMvc.perform(post("/api/job-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobStatus)))
            .andExpect(status().isBadRequest());

        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJobStatuses() throws Exception {
        // Initialize the database
        jobStatusRepository.saveAndFlush(jobStatus);

        // Get all the jobStatusList
        restJobStatusMockMvc.perform(get("/api/job-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobStatus").value(hasItem(DEFAULT_JOB_STATUS.toString())));
    }
    

    @Test
    @Transactional
    public void getJobStatus() throws Exception {
        // Initialize the database
        jobStatusRepository.saveAndFlush(jobStatus);

        // Get the jobStatus
        restJobStatusMockMvc.perform(get("/api/job-statuses/{id}", jobStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobStatus.getId().intValue()))
            .andExpect(jsonPath("$.jobStatus").value(DEFAULT_JOB_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingJobStatus() throws Exception {
        // Get the jobStatus
        restJobStatusMockMvc.perform(get("/api/job-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobStatus() throws Exception {
        // Initialize the database
        jobStatusRepository.saveAndFlush(jobStatus);

        int databaseSizeBeforeUpdate = jobStatusRepository.findAll().size();

        // Update the jobStatus
        JobStatus updatedJobStatus = jobStatusRepository.findById(jobStatus.getId()).get();
        // Disconnect from session so that the updates on updatedJobStatus are not directly saved in db
        em.detach(updatedJobStatus);
        updatedJobStatus
            .jobStatus(UPDATED_JOB_STATUS);

        restJobStatusMockMvc.perform(put("/api/job-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJobStatus)))
            .andExpect(status().isOk());

        // Validate the JobStatus in the database
        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeUpdate);
        JobStatus testJobStatus = jobStatusList.get(jobStatusList.size() - 1);
        assertThat(testJobStatus.getJobStatus()).isEqualTo(UPDATED_JOB_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingJobStatus() throws Exception {
        int databaseSizeBeforeUpdate = jobStatusRepository.findAll().size();

        // Create the JobStatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJobStatusMockMvc.perform(put("/api/job-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobStatus)))
            .andExpect(status().isBadRequest());

        // Validate the JobStatus in the database
        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJobStatus() throws Exception {
        // Initialize the database
        jobStatusRepository.saveAndFlush(jobStatus);

        int databaseSizeBeforeDelete = jobStatusRepository.findAll().size();

        // Get the jobStatus
        restJobStatusMockMvc.perform(delete("/api/job-statuses/{id}", jobStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobStatus> jobStatusList = jobStatusRepository.findAll();
        assertThat(jobStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobStatus.class);
        JobStatus jobStatus1 = new JobStatus();
        jobStatus1.setId(1L);
        JobStatus jobStatus2 = new JobStatus();
        jobStatus2.setId(jobStatus1.getId());
        assertThat(jobStatus1).isEqualTo(jobStatus2);
        jobStatus2.setId(2L);
        assertThat(jobStatus1).isNotEqualTo(jobStatus2);
        jobStatus1.setId(null);
        assertThat(jobStatus1).isNotEqualTo(jobStatus2);
    }
}
