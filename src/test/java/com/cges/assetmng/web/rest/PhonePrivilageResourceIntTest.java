package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.PhonePrivilage;
import com.cges.assetmng.repository.PhonePrivilageRepository;
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
 * Test class for the PhonePrivilageResource REST controller.
 *
 * @see PhonePrivilageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class PhonePrivilageResourceIntTest {

    private static final String DEFAULT_PRIVILAGE = "AAAAAAAAAA";
    private static final String UPDATED_PRIVILAGE = "BBBBBBBBBB";

    @Autowired
    private PhonePrivilageRepository phonePrivilageRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhonePrivilageMockMvc;

    private PhonePrivilage phonePrivilage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhonePrivilageResource phonePrivilageResource = new PhonePrivilageResource(phonePrivilageRepository);
        this.restPhonePrivilageMockMvc = MockMvcBuilders.standaloneSetup(phonePrivilageResource)
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
    public static PhonePrivilage createEntity(EntityManager em) {
        PhonePrivilage phonePrivilage = new PhonePrivilage()
            .privilage(DEFAULT_PRIVILAGE);
        return phonePrivilage;
    }

    @Before
    public void initTest() {
        phonePrivilage = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhonePrivilage() throws Exception {
        int databaseSizeBeforeCreate = phonePrivilageRepository.findAll().size();

        // Create the PhonePrivilage
        restPhonePrivilageMockMvc.perform(post("/api/phone-privilages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phonePrivilage)))
            .andExpect(status().isCreated());

        // Validate the PhonePrivilage in the database
        List<PhonePrivilage> phonePrivilageList = phonePrivilageRepository.findAll();
        assertThat(phonePrivilageList).hasSize(databaseSizeBeforeCreate + 1);
        PhonePrivilage testPhonePrivilage = phonePrivilageList.get(phonePrivilageList.size() - 1);
        assertThat(testPhonePrivilage.getPrivilage()).isEqualTo(DEFAULT_PRIVILAGE);
    }

    @Test
    @Transactional
    public void createPhonePrivilageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phonePrivilageRepository.findAll().size();

        // Create the PhonePrivilage with an existing ID
        phonePrivilage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhonePrivilageMockMvc.perform(post("/api/phone-privilages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phonePrivilage)))
            .andExpect(status().isBadRequest());

        // Validate the PhonePrivilage in the database
        List<PhonePrivilage> phonePrivilageList = phonePrivilageRepository.findAll();
        assertThat(phonePrivilageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPhonePrivilages() throws Exception {
        // Initialize the database
        phonePrivilageRepository.saveAndFlush(phonePrivilage);

        // Get all the phonePrivilageList
        restPhonePrivilageMockMvc.perform(get("/api/phone-privilages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phonePrivilage.getId().intValue())))
            .andExpect(jsonPath("$.[*].privilage").value(hasItem(DEFAULT_PRIVILAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getPhonePrivilage() throws Exception {
        // Initialize the database
        phonePrivilageRepository.saveAndFlush(phonePrivilage);

        // Get the phonePrivilage
        restPhonePrivilageMockMvc.perform(get("/api/phone-privilages/{id}", phonePrivilage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(phonePrivilage.getId().intValue()))
            .andExpect(jsonPath("$.privilage").value(DEFAULT_PRIVILAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPhonePrivilage() throws Exception {
        // Get the phonePrivilage
        restPhonePrivilageMockMvc.perform(get("/api/phone-privilages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhonePrivilage() throws Exception {
        // Initialize the database
        phonePrivilageRepository.saveAndFlush(phonePrivilage);

        int databaseSizeBeforeUpdate = phonePrivilageRepository.findAll().size();

        // Update the phonePrivilage
        PhonePrivilage updatedPhonePrivilage = phonePrivilageRepository.findById(phonePrivilage.getId()).get();
        // Disconnect from session so that the updates on updatedPhonePrivilage are not directly saved in db
        em.detach(updatedPhonePrivilage);
        updatedPhonePrivilage
            .privilage(UPDATED_PRIVILAGE);

        restPhonePrivilageMockMvc.perform(put("/api/phone-privilages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhonePrivilage)))
            .andExpect(status().isOk());

        // Validate the PhonePrivilage in the database
        List<PhonePrivilage> phonePrivilageList = phonePrivilageRepository.findAll();
        assertThat(phonePrivilageList).hasSize(databaseSizeBeforeUpdate);
        PhonePrivilage testPhonePrivilage = phonePrivilageList.get(phonePrivilageList.size() - 1);
        assertThat(testPhonePrivilage.getPrivilage()).isEqualTo(UPDATED_PRIVILAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingPhonePrivilage() throws Exception {
        int databaseSizeBeforeUpdate = phonePrivilageRepository.findAll().size();

        // Create the PhonePrivilage

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPhonePrivilageMockMvc.perform(put("/api/phone-privilages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phonePrivilage)))
            .andExpect(status().isBadRequest());

        // Validate the PhonePrivilage in the database
        List<PhonePrivilage> phonePrivilageList = phonePrivilageRepository.findAll();
        assertThat(phonePrivilageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhonePrivilage() throws Exception {
        // Initialize the database
        phonePrivilageRepository.saveAndFlush(phonePrivilage);

        int databaseSizeBeforeDelete = phonePrivilageRepository.findAll().size();

        // Get the phonePrivilage
        restPhonePrivilageMockMvc.perform(delete("/api/phone-privilages/{id}", phonePrivilage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PhonePrivilage> phonePrivilageList = phonePrivilageRepository.findAll();
        assertThat(phonePrivilageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhonePrivilage.class);
        PhonePrivilage phonePrivilage1 = new PhonePrivilage();
        phonePrivilage1.setId(1L);
        PhonePrivilage phonePrivilage2 = new PhonePrivilage();
        phonePrivilage2.setId(phonePrivilage1.getId());
        assertThat(phonePrivilage1).isEqualTo(phonePrivilage2);
        phonePrivilage2.setId(2L);
        assertThat(phonePrivilage1).isNotEqualTo(phonePrivilage2);
        phonePrivilage1.setId(null);
        assertThat(phonePrivilage1).isNotEqualTo(phonePrivilage2);
    }
}
