package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.DisconnectorDrive;
import com.cges.assetmng.repository.DisconnectorDriveRepository;
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
 * Test class for the DisconnectorDriveResource REST controller.
 *
 * @see DisconnectorDriveResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class DisconnectorDriveResourceIntTest {

    private static final String DEFAULT_DISC_DRIVE = "AAAAAAAAAA";
    private static final String UPDATED_DISC_DRIVE = "BBBBBBBBBB";

    @Autowired
    private DisconnectorDriveRepository disconnectorDriveRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisconnectorDriveMockMvc;

    private DisconnectorDrive disconnectorDrive;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisconnectorDriveResource disconnectorDriveResource = new DisconnectorDriveResource(disconnectorDriveRepository);
        this.restDisconnectorDriveMockMvc = MockMvcBuilders.standaloneSetup(disconnectorDriveResource)
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
    public static DisconnectorDrive createEntity(EntityManager em) {
        DisconnectorDrive disconnectorDrive = new DisconnectorDrive()
            .discDrive(DEFAULT_DISC_DRIVE);
        return disconnectorDrive;
    }

    @Before
    public void initTest() {
        disconnectorDrive = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisconnectorDrive() throws Exception {
        int databaseSizeBeforeCreate = disconnectorDriveRepository.findAll().size();

        // Create the DisconnectorDrive
        restDisconnectorDriveMockMvc.perform(post("/api/disconnector-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorDrive)))
            .andExpect(status().isCreated());

        // Validate the DisconnectorDrive in the database
        List<DisconnectorDrive> disconnectorDriveList = disconnectorDriveRepository.findAll();
        assertThat(disconnectorDriveList).hasSize(databaseSizeBeforeCreate + 1);
        DisconnectorDrive testDisconnectorDrive = disconnectorDriveList.get(disconnectorDriveList.size() - 1);
        assertThat(testDisconnectorDrive.getDiscDrive()).isEqualTo(DEFAULT_DISC_DRIVE);
    }

    @Test
    @Transactional
    public void createDisconnectorDriveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disconnectorDriveRepository.findAll().size();

        // Create the DisconnectorDrive with an existing ID
        disconnectorDrive.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisconnectorDriveMockMvc.perform(post("/api/disconnector-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorDrive)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorDrive in the database
        List<DisconnectorDrive> disconnectorDriveList = disconnectorDriveRepository.findAll();
        assertThat(disconnectorDriveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDisconnectorDrives() throws Exception {
        // Initialize the database
        disconnectorDriveRepository.saveAndFlush(disconnectorDrive);

        // Get all the disconnectorDriveList
        restDisconnectorDriveMockMvc.perform(get("/api/disconnector-drives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disconnectorDrive.getId().intValue())))
            .andExpect(jsonPath("$.[*].discDrive").value(hasItem(DEFAULT_DISC_DRIVE.toString())));
    }
    

    @Test
    @Transactional
    public void getDisconnectorDrive() throws Exception {
        // Initialize the database
        disconnectorDriveRepository.saveAndFlush(disconnectorDrive);

        // Get the disconnectorDrive
        restDisconnectorDriveMockMvc.perform(get("/api/disconnector-drives/{id}", disconnectorDrive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disconnectorDrive.getId().intValue()))
            .andExpect(jsonPath("$.discDrive").value(DEFAULT_DISC_DRIVE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDisconnectorDrive() throws Exception {
        // Get the disconnectorDrive
        restDisconnectorDriveMockMvc.perform(get("/api/disconnector-drives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisconnectorDrive() throws Exception {
        // Initialize the database
        disconnectorDriveRepository.saveAndFlush(disconnectorDrive);

        int databaseSizeBeforeUpdate = disconnectorDriveRepository.findAll().size();

        // Update the disconnectorDrive
        DisconnectorDrive updatedDisconnectorDrive = disconnectorDriveRepository.findById(disconnectorDrive.getId()).get();
        // Disconnect from session so that the updates on updatedDisconnectorDrive are not directly saved in db
        em.detach(updatedDisconnectorDrive);
        updatedDisconnectorDrive
            .discDrive(UPDATED_DISC_DRIVE);

        restDisconnectorDriveMockMvc.perform(put("/api/disconnector-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisconnectorDrive)))
            .andExpect(status().isOk());

        // Validate the DisconnectorDrive in the database
        List<DisconnectorDrive> disconnectorDriveList = disconnectorDriveRepository.findAll();
        assertThat(disconnectorDriveList).hasSize(databaseSizeBeforeUpdate);
        DisconnectorDrive testDisconnectorDrive = disconnectorDriveList.get(disconnectorDriveList.size() - 1);
        assertThat(testDisconnectorDrive.getDiscDrive()).isEqualTo(UPDATED_DISC_DRIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisconnectorDrive() throws Exception {
        int databaseSizeBeforeUpdate = disconnectorDriveRepository.findAll().size();

        // Create the DisconnectorDrive

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisconnectorDriveMockMvc.perform(put("/api/disconnector-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disconnectorDrive)))
            .andExpect(status().isBadRequest());

        // Validate the DisconnectorDrive in the database
        List<DisconnectorDrive> disconnectorDriveList = disconnectorDriveRepository.findAll();
        assertThat(disconnectorDriveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisconnectorDrive() throws Exception {
        // Initialize the database
        disconnectorDriveRepository.saveAndFlush(disconnectorDrive);

        int databaseSizeBeforeDelete = disconnectorDriveRepository.findAll().size();

        // Get the disconnectorDrive
        restDisconnectorDriveMockMvc.perform(delete("/api/disconnector-drives/{id}", disconnectorDrive.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisconnectorDrive> disconnectorDriveList = disconnectorDriveRepository.findAll();
        assertThat(disconnectorDriveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisconnectorDrive.class);
        DisconnectorDrive disconnectorDrive1 = new DisconnectorDrive();
        disconnectorDrive1.setId(1L);
        DisconnectorDrive disconnectorDrive2 = new DisconnectorDrive();
        disconnectorDrive2.setId(disconnectorDrive1.getId());
        assertThat(disconnectorDrive1).isEqualTo(disconnectorDrive2);
        disconnectorDrive2.setId(2L);
        assertThat(disconnectorDrive1).isNotEqualTo(disconnectorDrive2);
        disconnectorDrive1.setId(null);
        assertThat(disconnectorDrive1).isNotEqualTo(disconnectorDrive2);
    }
}
