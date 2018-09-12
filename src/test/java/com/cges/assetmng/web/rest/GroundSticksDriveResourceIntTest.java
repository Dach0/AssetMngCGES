package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.GroundSticksDrive;
import com.cges.assetmng.repository.GroundSticksDriveRepository;
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
 * Test class for the GroundSticksDriveResource REST controller.
 *
 * @see GroundSticksDriveResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class GroundSticksDriveResourceIntTest {

    private static final String DEFAULT_GS_DRIVE = "AAAAAAAAAA";
    private static final String UPDATED_GS_DRIVE = "BBBBBBBBBB";

    @Autowired
    private GroundSticksDriveRepository groundSticksDriveRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGroundSticksDriveMockMvc;

    private GroundSticksDrive groundSticksDrive;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroundSticksDriveResource groundSticksDriveResource = new GroundSticksDriveResource(groundSticksDriveRepository);
        this.restGroundSticksDriveMockMvc = MockMvcBuilders.standaloneSetup(groundSticksDriveResource)
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
    public static GroundSticksDrive createEntity(EntityManager em) {
        GroundSticksDrive groundSticksDrive = new GroundSticksDrive()
            .gsDrive(DEFAULT_GS_DRIVE);
        return groundSticksDrive;
    }

    @Before
    public void initTest() {
        groundSticksDrive = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroundSticksDrive() throws Exception {
        int databaseSizeBeforeCreate = groundSticksDriveRepository.findAll().size();

        // Create the GroundSticksDrive
        restGroundSticksDriveMockMvc.perform(post("/api/ground-sticks-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksDrive)))
            .andExpect(status().isCreated());

        // Validate the GroundSticksDrive in the database
        List<GroundSticksDrive> groundSticksDriveList = groundSticksDriveRepository.findAll();
        assertThat(groundSticksDriveList).hasSize(databaseSizeBeforeCreate + 1);
        GroundSticksDrive testGroundSticksDrive = groundSticksDriveList.get(groundSticksDriveList.size() - 1);
        assertThat(testGroundSticksDrive.getGsDrive()).isEqualTo(DEFAULT_GS_DRIVE);
    }

    @Test
    @Transactional
    public void createGroundSticksDriveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groundSticksDriveRepository.findAll().size();

        // Create the GroundSticksDrive with an existing ID
        groundSticksDrive.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroundSticksDriveMockMvc.perform(post("/api/ground-sticks-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksDrive)))
            .andExpect(status().isBadRequest());

        // Validate the GroundSticksDrive in the database
        List<GroundSticksDrive> groundSticksDriveList = groundSticksDriveRepository.findAll();
        assertThat(groundSticksDriveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGroundSticksDrives() throws Exception {
        // Initialize the database
        groundSticksDriveRepository.saveAndFlush(groundSticksDrive);

        // Get all the groundSticksDriveList
        restGroundSticksDriveMockMvc.perform(get("/api/ground-sticks-drives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groundSticksDrive.getId().intValue())))
            .andExpect(jsonPath("$.[*].gsDrive").value(hasItem(DEFAULT_GS_DRIVE.toString())));
    }
    

    @Test
    @Transactional
    public void getGroundSticksDrive() throws Exception {
        // Initialize the database
        groundSticksDriveRepository.saveAndFlush(groundSticksDrive);

        // Get the groundSticksDrive
        restGroundSticksDriveMockMvc.perform(get("/api/ground-sticks-drives/{id}", groundSticksDrive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groundSticksDrive.getId().intValue()))
            .andExpect(jsonPath("$.gsDrive").value(DEFAULT_GS_DRIVE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGroundSticksDrive() throws Exception {
        // Get the groundSticksDrive
        restGroundSticksDriveMockMvc.perform(get("/api/ground-sticks-drives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroundSticksDrive() throws Exception {
        // Initialize the database
        groundSticksDriveRepository.saveAndFlush(groundSticksDrive);

        int databaseSizeBeforeUpdate = groundSticksDriveRepository.findAll().size();

        // Update the groundSticksDrive
        GroundSticksDrive updatedGroundSticksDrive = groundSticksDriveRepository.findById(groundSticksDrive.getId()).get();
        // Disconnect from session so that the updates on updatedGroundSticksDrive are not directly saved in db
        em.detach(updatedGroundSticksDrive);
        updatedGroundSticksDrive
            .gsDrive(UPDATED_GS_DRIVE);

        restGroundSticksDriveMockMvc.perform(put("/api/ground-sticks-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroundSticksDrive)))
            .andExpect(status().isOk());

        // Validate the GroundSticksDrive in the database
        List<GroundSticksDrive> groundSticksDriveList = groundSticksDriveRepository.findAll();
        assertThat(groundSticksDriveList).hasSize(databaseSizeBeforeUpdate);
        GroundSticksDrive testGroundSticksDrive = groundSticksDriveList.get(groundSticksDriveList.size() - 1);
        assertThat(testGroundSticksDrive.getGsDrive()).isEqualTo(UPDATED_GS_DRIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroundSticksDrive() throws Exception {
        int databaseSizeBeforeUpdate = groundSticksDriveRepository.findAll().size();

        // Create the GroundSticksDrive

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGroundSticksDriveMockMvc.perform(put("/api/ground-sticks-drives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groundSticksDrive)))
            .andExpect(status().isBadRequest());

        // Validate the GroundSticksDrive in the database
        List<GroundSticksDrive> groundSticksDriveList = groundSticksDriveRepository.findAll();
        assertThat(groundSticksDriveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroundSticksDrive() throws Exception {
        // Initialize the database
        groundSticksDriveRepository.saveAndFlush(groundSticksDrive);

        int databaseSizeBeforeDelete = groundSticksDriveRepository.findAll().size();

        // Get the groundSticksDrive
        restGroundSticksDriveMockMvc.perform(delete("/api/ground-sticks-drives/{id}", groundSticksDrive.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroundSticksDrive> groundSticksDriveList = groundSticksDriveRepository.findAll();
        assertThat(groundSticksDriveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroundSticksDrive.class);
        GroundSticksDrive groundSticksDrive1 = new GroundSticksDrive();
        groundSticksDrive1.setId(1L);
        GroundSticksDrive groundSticksDrive2 = new GroundSticksDrive();
        groundSticksDrive2.setId(groundSticksDrive1.getId());
        assertThat(groundSticksDrive1).isEqualTo(groundSticksDrive2);
        groundSticksDrive2.setId(2L);
        assertThat(groundSticksDrive1).isNotEqualTo(groundSticksDrive2);
        groundSticksDrive1.setId(null);
        assertThat(groundSticksDrive1).isNotEqualTo(groundSticksDrive2);
    }
}
