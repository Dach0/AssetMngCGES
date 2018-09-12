package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Ohl;
import com.cges.assetmng.repository.OhlRepository;
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
 * Test class for the OhlResource REST controller.
 *
 * @see OhlResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class OhlResourceIntTest {

    private static final String DEFAULT_OHL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OHL_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_OPERATION_YEAR = 1;
    private static final Integer UPDATED_OPERATION_YEAR = 2;

    private static final Double DEFAULT_LENGTH_TOTAL = 1D;
    private static final Double UPDATED_LENGTH_TOTAL = 2D;

    private static final Double DEFAULT_LENGTH_IN_MNE = 1D;
    private static final Double UPDATED_LENGTH_IN_MNE = 2D;

    private static final Double DEFAULT_R_OHM_PER_PHASE_IN_MNE = 1D;
    private static final Double UPDATED_R_OHM_PER_PHASE_IN_MNE = 2D;

    private static final Double DEFAULT_R_OHM_PER_PHASE_TOTAL = 1D;
    private static final Double UPDATED_R_OHM_PER_PHASE_TOTAL = 2D;

    private static final Double DEFAULT_X_OHM_PER_PHASE_IN_MNE = 1D;
    private static final Double UPDATED_X_OHM_PER_PHASE_IN_MNE = 2D;

    private static final Double DEFAULT_X_OHM_PER_PHASE_TOTAL = 1D;
    private static final Double UPDATED_X_OHM_PER_PHASE_TOTAL = 2D;

    private static final Double DEFAULT_B_OHM_PER_PHASE_IN_MNE = 1D;
    private static final Double UPDATED_B_OHM_PER_PHASE_IN_MNE = 2D;

    private static final Double DEFAULT_B_OHM_PER_PHASE_TOTAL = 1D;
    private static final Double UPDATED_B_OHM_PER_PHASE_TOTAL = 2D;

    private static final Double DEFAULT_R_0_OHM_PER_PHASE_IN_MNE = 1D;
    private static final Double UPDATED_R_0_OHM_PER_PHASE_IN_MNE = 2D;

    private static final Double DEFAULT_R_0_OHM_PER_PHASE_TOTAL = 1D;
    private static final Double UPDATED_R_0_OHM_PER_PHASE_TOTAL = 2D;

    private static final Double DEFAULT_X_0_OHM_PER_PHASE_IN_MNE = 1D;
    private static final Double UPDATED_X_0_OHM_PER_PHASE_IN_MNE = 2D;

    private static final Double DEFAULT_X_0_OHM_PER_PHASE_TOTAL = 1D;
    private static final Double UPDATED_X_0_OHM_PER_PHASE_TOTAL = 2D;

    private static final Integer DEFAULT_PYLON_NUMBER = 1;
    private static final Integer UPDATED_PYLON_NUMBER = 2;

    private static final Integer DEFAULT_ISOLATOR_NUMBER = 1;
    private static final Integer UPDATED_ISOLATOR_NUMBER = 2;

    @Autowired
    private OhlRepository ohlRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOhlMockMvc;

    private Ohl ohl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OhlResource ohlResource = new OhlResource(ohlRepository);
        this.restOhlMockMvc = MockMvcBuilders.standaloneSetup(ohlResource)
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
    public static Ohl createEntity(EntityManager em) {
        Ohl ohl = new Ohl()
            .ohlNumber(DEFAULT_OHL_NUMBER)
            .operationYear(DEFAULT_OPERATION_YEAR)
            .lengthTotal(DEFAULT_LENGTH_TOTAL)
            .lengthInMne(DEFAULT_LENGTH_IN_MNE)
            .rOhmPerPhaseInMne(DEFAULT_R_OHM_PER_PHASE_IN_MNE)
            .rOhmPerPhaseTotal(DEFAULT_R_OHM_PER_PHASE_TOTAL)
            .xOhmPerPhaseInMne(DEFAULT_X_OHM_PER_PHASE_IN_MNE)
            .xOhmPerPhaseTotal(DEFAULT_X_OHM_PER_PHASE_TOTAL)
            .bOhmPerPhaseInMne(DEFAULT_B_OHM_PER_PHASE_IN_MNE)
            .bOhmPerPhaseTotal(DEFAULT_B_OHM_PER_PHASE_TOTAL)
            .r0OhmPerPhaseInMne(DEFAULT_R_0_OHM_PER_PHASE_IN_MNE)
            .r0OhmPerPhaseTotal(DEFAULT_R_0_OHM_PER_PHASE_TOTAL)
            .x0OhmPerPhaseInMne(DEFAULT_X_0_OHM_PER_PHASE_IN_MNE)
            .x0OhmPerPhaseTotal(DEFAULT_X_0_OHM_PER_PHASE_TOTAL)
            .pylonNumber(DEFAULT_PYLON_NUMBER)
            .isolatorNumber(DEFAULT_ISOLATOR_NUMBER);
        return ohl;
    }

    @Before
    public void initTest() {
        ohl = createEntity(em);
    }

    @Test
    @Transactional
    public void createOhl() throws Exception {
        int databaseSizeBeforeCreate = ohlRepository.findAll().size();

        // Create the Ohl
        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isCreated());

        // Validate the Ohl in the database
        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeCreate + 1);
        Ohl testOhl = ohlList.get(ohlList.size() - 1);
        assertThat(testOhl.getOhlNumber()).isEqualTo(DEFAULT_OHL_NUMBER);
        assertThat(testOhl.getOperationYear()).isEqualTo(DEFAULT_OPERATION_YEAR);
        assertThat(testOhl.getLengthTotal()).isEqualTo(DEFAULT_LENGTH_TOTAL);
        assertThat(testOhl.getLengthInMne()).isEqualTo(DEFAULT_LENGTH_IN_MNE);
        assertThat(testOhl.getrOhmPerPhaseInMne()).isEqualTo(DEFAULT_R_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getrOhmPerPhaseTotal()).isEqualTo(DEFAULT_R_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getxOhmPerPhaseInMne()).isEqualTo(DEFAULT_X_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getxOhmPerPhaseTotal()).isEqualTo(DEFAULT_X_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getbOhmPerPhaseInMne()).isEqualTo(DEFAULT_B_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getbOhmPerPhaseTotal()).isEqualTo(DEFAULT_B_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getr0OhmPerPhaseInMne()).isEqualTo(DEFAULT_R_0_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getr0OhmPerPhaseTotal()).isEqualTo(DEFAULT_R_0_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getx0OhmPerPhaseInMne()).isEqualTo(DEFAULT_X_0_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getx0OhmPerPhaseTotal()).isEqualTo(DEFAULT_X_0_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getPylonNumber()).isEqualTo(DEFAULT_PYLON_NUMBER);
        assertThat(testOhl.getIsolatorNumber()).isEqualTo(DEFAULT_ISOLATOR_NUMBER);
    }

    @Test
    @Transactional
    public void createOhlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ohlRepository.findAll().size();

        // Create the Ohl with an existing ID
        ohl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        // Validate the Ohl in the database
        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOperationYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = ohlRepository.findAll().size();
        // set the field null
        ohl.setOperationYear(null);

        // Create the Ohl, which fails.

        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = ohlRepository.findAll().size();
        // set the field null
        ohl.setLengthTotal(null);

        // Create the Ohl, which fails.

        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthInMneIsRequired() throws Exception {
        int databaseSizeBeforeTest = ohlRepository.findAll().size();
        // set the field null
        ohl.setLengthInMne(null);

        // Create the Ohl, which fails.

        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPylonNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = ohlRepository.findAll().size();
        // set the field null
        ohl.setPylonNumber(null);

        // Create the Ohl, which fails.

        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsolatorNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = ohlRepository.findAll().size();
        // set the field null
        ohl.setIsolatorNumber(null);

        // Create the Ohl, which fails.

        restOhlMockMvc.perform(post("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOhls() throws Exception {
        // Initialize the database
        ohlRepository.saveAndFlush(ohl);

        // Get all the ohlList
        restOhlMockMvc.perform(get("/api/ohls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ohl.getId().intValue())))
            .andExpect(jsonPath("$.[*].ohlNumber").value(hasItem(DEFAULT_OHL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].operationYear").value(hasItem(DEFAULT_OPERATION_YEAR)))
            .andExpect(jsonPath("$.[*].lengthTotal").value(hasItem(DEFAULT_LENGTH_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].lengthInMne").value(hasItem(DEFAULT_LENGTH_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].rOhmPerPhaseInMne").value(hasItem(DEFAULT_R_OHM_PER_PHASE_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].rOhmPerPhaseTotal").value(hasItem(DEFAULT_R_OHM_PER_PHASE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].xOhmPerPhaseInMne").value(hasItem(DEFAULT_X_OHM_PER_PHASE_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].xOhmPerPhaseTotal").value(hasItem(DEFAULT_X_OHM_PER_PHASE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].bOhmPerPhaseInMne").value(hasItem(DEFAULT_B_OHM_PER_PHASE_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].bOhmPerPhaseTotal").value(hasItem(DEFAULT_B_OHM_PER_PHASE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].r0OhmPerPhaseInMne").value(hasItem(DEFAULT_R_0_OHM_PER_PHASE_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].r0OhmPerPhaseTotal").value(hasItem(DEFAULT_R_0_OHM_PER_PHASE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].x0OhmPerPhaseInMne").value(hasItem(DEFAULT_X_0_OHM_PER_PHASE_IN_MNE.doubleValue())))
            .andExpect(jsonPath("$.[*].x0OhmPerPhaseTotal").value(hasItem(DEFAULT_X_0_OHM_PER_PHASE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].pylonNumber").value(hasItem(DEFAULT_PYLON_NUMBER)))
            .andExpect(jsonPath("$.[*].isolatorNumber").value(hasItem(DEFAULT_ISOLATOR_NUMBER)));
    }
    

    @Test
    @Transactional
    public void getOhl() throws Exception {
        // Initialize the database
        ohlRepository.saveAndFlush(ohl);

        // Get the ohl
        restOhlMockMvc.perform(get("/api/ohls/{id}", ohl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ohl.getId().intValue()))
            .andExpect(jsonPath("$.ohlNumber").value(DEFAULT_OHL_NUMBER.toString()))
            .andExpect(jsonPath("$.operationYear").value(DEFAULT_OPERATION_YEAR))
            .andExpect(jsonPath("$.lengthTotal").value(DEFAULT_LENGTH_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.lengthInMne").value(DEFAULT_LENGTH_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.rOhmPerPhaseInMne").value(DEFAULT_R_OHM_PER_PHASE_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.rOhmPerPhaseTotal").value(DEFAULT_R_OHM_PER_PHASE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.xOhmPerPhaseInMne").value(DEFAULT_X_OHM_PER_PHASE_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.xOhmPerPhaseTotal").value(DEFAULT_X_OHM_PER_PHASE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.bOhmPerPhaseInMne").value(DEFAULT_B_OHM_PER_PHASE_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.bOhmPerPhaseTotal").value(DEFAULT_B_OHM_PER_PHASE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.r0OhmPerPhaseInMne").value(DEFAULT_R_0_OHM_PER_PHASE_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.r0OhmPerPhaseTotal").value(DEFAULT_R_0_OHM_PER_PHASE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.x0OhmPerPhaseInMne").value(DEFAULT_X_0_OHM_PER_PHASE_IN_MNE.doubleValue()))
            .andExpect(jsonPath("$.x0OhmPerPhaseTotal").value(DEFAULT_X_0_OHM_PER_PHASE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.pylonNumber").value(DEFAULT_PYLON_NUMBER))
            .andExpect(jsonPath("$.isolatorNumber").value(DEFAULT_ISOLATOR_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingOhl() throws Exception {
        // Get the ohl
        restOhlMockMvc.perform(get("/api/ohls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOhl() throws Exception {
        // Initialize the database
        ohlRepository.saveAndFlush(ohl);

        int databaseSizeBeforeUpdate = ohlRepository.findAll().size();

        // Update the ohl
        Ohl updatedOhl = ohlRepository.findById(ohl.getId()).get();
        // Disconnect from session so that the updates on updatedOhl are not directly saved in db
        em.detach(updatedOhl);
        updatedOhl
            .ohlNumber(UPDATED_OHL_NUMBER)
            .operationYear(UPDATED_OPERATION_YEAR)
            .lengthTotal(UPDATED_LENGTH_TOTAL)
            .lengthInMne(UPDATED_LENGTH_IN_MNE)
            .rOhmPerPhaseInMne(UPDATED_R_OHM_PER_PHASE_IN_MNE)
            .rOhmPerPhaseTotal(UPDATED_R_OHM_PER_PHASE_TOTAL)
            .xOhmPerPhaseInMne(UPDATED_X_OHM_PER_PHASE_IN_MNE)
            .xOhmPerPhaseTotal(UPDATED_X_OHM_PER_PHASE_TOTAL)
            .bOhmPerPhaseInMne(UPDATED_B_OHM_PER_PHASE_IN_MNE)
            .bOhmPerPhaseTotal(UPDATED_B_OHM_PER_PHASE_TOTAL)
            .r0OhmPerPhaseInMne(UPDATED_R_0_OHM_PER_PHASE_IN_MNE)
            .r0OhmPerPhaseTotal(UPDATED_R_0_OHM_PER_PHASE_TOTAL)
            .x0OhmPerPhaseInMne(UPDATED_X_0_OHM_PER_PHASE_IN_MNE)
            .x0OhmPerPhaseTotal(UPDATED_X_0_OHM_PER_PHASE_TOTAL)
            .pylonNumber(UPDATED_PYLON_NUMBER)
            .isolatorNumber(UPDATED_ISOLATOR_NUMBER);

        restOhlMockMvc.perform(put("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOhl)))
            .andExpect(status().isOk());

        // Validate the Ohl in the database
        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeUpdate);
        Ohl testOhl = ohlList.get(ohlList.size() - 1);
        assertThat(testOhl.getOhlNumber()).isEqualTo(UPDATED_OHL_NUMBER);
        assertThat(testOhl.getOperationYear()).isEqualTo(UPDATED_OPERATION_YEAR);
        assertThat(testOhl.getLengthTotal()).isEqualTo(UPDATED_LENGTH_TOTAL);
        assertThat(testOhl.getLengthInMne()).isEqualTo(UPDATED_LENGTH_IN_MNE);
        assertThat(testOhl.getrOhmPerPhaseInMne()).isEqualTo(UPDATED_R_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getrOhmPerPhaseTotal()).isEqualTo(UPDATED_R_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getxOhmPerPhaseInMne()).isEqualTo(UPDATED_X_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getxOhmPerPhaseTotal()).isEqualTo(UPDATED_X_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getbOhmPerPhaseInMne()).isEqualTo(UPDATED_B_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getbOhmPerPhaseTotal()).isEqualTo(UPDATED_B_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getr0OhmPerPhaseInMne()).isEqualTo(UPDATED_R_0_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getr0OhmPerPhaseTotal()).isEqualTo(UPDATED_R_0_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getx0OhmPerPhaseInMne()).isEqualTo(UPDATED_X_0_OHM_PER_PHASE_IN_MNE);
        assertThat(testOhl.getx0OhmPerPhaseTotal()).isEqualTo(UPDATED_X_0_OHM_PER_PHASE_TOTAL);
        assertThat(testOhl.getPylonNumber()).isEqualTo(UPDATED_PYLON_NUMBER);
        assertThat(testOhl.getIsolatorNumber()).isEqualTo(UPDATED_ISOLATOR_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingOhl() throws Exception {
        int databaseSizeBeforeUpdate = ohlRepository.findAll().size();

        // Create the Ohl

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOhlMockMvc.perform(put("/api/ohls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ohl)))
            .andExpect(status().isBadRequest());

        // Validate the Ohl in the database
        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOhl() throws Exception {
        // Initialize the database
        ohlRepository.saveAndFlush(ohl);

        int databaseSizeBeforeDelete = ohlRepository.findAll().size();

        // Get the ohl
        restOhlMockMvc.perform(delete("/api/ohls/{id}", ohl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ohl> ohlList = ohlRepository.findAll();
        assertThat(ohlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ohl.class);
        Ohl ohl1 = new Ohl();
        ohl1.setId(1L);
        Ohl ohl2 = new Ohl();
        ohl2.setId(ohl1.getId());
        assertThat(ohl1).isEqualTo(ohl2);
        ohl2.setId(2L);
        assertThat(ohl1).isNotEqualTo(ohl2);
        ohl1.setId(null);
        assertThat(ohl1).isNotEqualTo(ohl2);
    }
}
