package com.cges.assetmng.web.rest;

import com.cges.assetmng.AssetMenagementCgesApp;

import com.cges.assetmng.domain.Asset;
import com.cges.assetmng.repository.AssetRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.cges.assetmng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AssetResource REST controller.
 *
 * @see AssetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssetMenagementCgesApp.class)
public class AssetResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MANUFACTURER = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURER = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_OBLIGATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_OBLIGATION = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PURCHASE_PRICE = 1D;
    private static final Double UPDATED_PURCHASE_PRICE = 2D;

    private static final Double DEFAULT_MARKET_PRICE = 1D;
    private static final Double UPDATED_MARKET_PRICE = 2D;

    private static final Double DEFAULT_SCRAP_PRICE = 1D;
    private static final Double UPDATED_SCRAP_PRICE = 2D;

    private static final LocalDate DEFAULT_PURCHASED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PURCHASED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_IN_SERVICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IN_SERVICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_WARRENTY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_WARRENTY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENTS = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENTS = "BBBBBBBBBB";

    @Autowired
    private AssetRepository assetRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetMockMvc;

    private Asset asset;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetResource assetResource = new AssetResource(assetRepository);
        this.restAssetMockMvc = MockMvcBuilders.standaloneSetup(assetResource)
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
    public static Asset createEntity(EntityManager em) {
        Asset asset = new Asset()
            .description(DEFAULT_DESCRIPTION)
            .manufacturer(DEFAULT_MANUFACTURER)
            .brand(DEFAULT_BRAND)
            .model(DEFAULT_MODEL)
            .picture(DEFAULT_PICTURE)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .dateOfObligation(DEFAULT_DATE_OF_OBLIGATION)
            .purchasePrice(DEFAULT_PURCHASE_PRICE)
            .marketPrice(DEFAULT_MARKET_PRICE)
            .scrapPrice(DEFAULT_SCRAP_PRICE)
            .purchasedDate(DEFAULT_PURCHASED_DATE)
            .inServiceDate(DEFAULT_IN_SERVICE_DATE)
            .warrenty(DEFAULT_WARRENTY)
            .notes(DEFAULT_NOTES)
            .attachments(DEFAULT_ATTACHMENTS);
        return asset;
    }

    @Before
    public void initTest() {
        asset = createEntity(em);
    }

    @Test
    @Transactional
    public void createAsset() throws Exception {
        int databaseSizeBeforeCreate = assetRepository.findAll().size();

        // Create the Asset
        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isCreated());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate + 1);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAsset.getManufacturer()).isEqualTo(DEFAULT_MANUFACTURER);
        assertThat(testAsset.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testAsset.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testAsset.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testAsset.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testAsset.getDateOfObligation()).isEqualTo(DEFAULT_DATE_OF_OBLIGATION);
        assertThat(testAsset.getPurchasePrice()).isEqualTo(DEFAULT_PURCHASE_PRICE);
        assertThat(testAsset.getMarketPrice()).isEqualTo(DEFAULT_MARKET_PRICE);
        assertThat(testAsset.getScrapPrice()).isEqualTo(DEFAULT_SCRAP_PRICE);
        assertThat(testAsset.getPurchasedDate()).isEqualTo(DEFAULT_PURCHASED_DATE);
        assertThat(testAsset.getInServiceDate()).isEqualTo(DEFAULT_IN_SERVICE_DATE);
        assertThat(testAsset.getWarrenty()).isEqualTo(DEFAULT_WARRENTY);
        assertThat(testAsset.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testAsset.getAttachments()).isEqualTo(DEFAULT_ATTACHMENTS);
    }

    @Test
    @Transactional
    public void createAssetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetRepository.findAll().size();

        // Create the Asset with an existing ID
        asset.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssets() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList
        restAssetMockMvc.perform(get("/api/assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asset.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].manufacturer").value(hasItem(DEFAULT_MANUFACTURER.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].dateOfObligation").value(hasItem(DEFAULT_DATE_OF_OBLIGATION.toString())))
            .andExpect(jsonPath("$.[*].purchasePrice").value(hasItem(DEFAULT_PURCHASE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].marketPrice").value(hasItem(DEFAULT_MARKET_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].scrapPrice").value(hasItem(DEFAULT_SCRAP_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].purchasedDate").value(hasItem(DEFAULT_PURCHASED_DATE.toString())))
            .andExpect(jsonPath("$.[*].inServiceDate").value(hasItem(DEFAULT_IN_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].warrenty").value(hasItem(DEFAULT_WARRENTY.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].attachments").value(hasItem(DEFAULT_ATTACHMENTS.toString())));
    }
    

    @Test
    @Transactional
    public void getAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get the asset
        restAssetMockMvc.perform(get("/api/assets/{id}", asset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(asset.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.manufacturer").value(DEFAULT_MANUFACTURER.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.dateOfObligation").value(DEFAULT_DATE_OF_OBLIGATION.toString()))
            .andExpect(jsonPath("$.purchasePrice").value(DEFAULT_PURCHASE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.marketPrice").value(DEFAULT_MARKET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.scrapPrice").value(DEFAULT_SCRAP_PRICE.doubleValue()))
            .andExpect(jsonPath("$.purchasedDate").value(DEFAULT_PURCHASED_DATE.toString()))
            .andExpect(jsonPath("$.inServiceDate").value(DEFAULT_IN_SERVICE_DATE.toString()))
            .andExpect(jsonPath("$.warrenty").value(DEFAULT_WARRENTY.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.attachments").value(DEFAULT_ATTACHMENTS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAsset() throws Exception {
        // Get the asset
        restAssetMockMvc.perform(get("/api/assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Update the asset
        Asset updatedAsset = assetRepository.findById(asset.getId()).get();
        // Disconnect from session so that the updates on updatedAsset are not directly saved in db
        em.detach(updatedAsset);
        updatedAsset
            .description(UPDATED_DESCRIPTION)
            .manufacturer(UPDATED_MANUFACTURER)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .picture(UPDATED_PICTURE)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .dateOfObligation(UPDATED_DATE_OF_OBLIGATION)
            .purchasePrice(UPDATED_PURCHASE_PRICE)
            .marketPrice(UPDATED_MARKET_PRICE)
            .scrapPrice(UPDATED_SCRAP_PRICE)
            .purchasedDate(UPDATED_PURCHASED_DATE)
            .inServiceDate(UPDATED_IN_SERVICE_DATE)
            .warrenty(UPDATED_WARRENTY)
            .notes(UPDATED_NOTES)
            .attachments(UPDATED_ATTACHMENTS);

        restAssetMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAsset)))
            .andExpect(status().isOk());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAsset.getManufacturer()).isEqualTo(UPDATED_MANUFACTURER);
        assertThat(testAsset.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testAsset.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testAsset.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testAsset.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testAsset.getDateOfObligation()).isEqualTo(UPDATED_DATE_OF_OBLIGATION);
        assertThat(testAsset.getPurchasePrice()).isEqualTo(UPDATED_PURCHASE_PRICE);
        assertThat(testAsset.getMarketPrice()).isEqualTo(UPDATED_MARKET_PRICE);
        assertThat(testAsset.getScrapPrice()).isEqualTo(UPDATED_SCRAP_PRICE);
        assertThat(testAsset.getPurchasedDate()).isEqualTo(UPDATED_PURCHASED_DATE);
        assertThat(testAsset.getInServiceDate()).isEqualTo(UPDATED_IN_SERVICE_DATE);
        assertThat(testAsset.getWarrenty()).isEqualTo(UPDATED_WARRENTY);
        assertThat(testAsset.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testAsset.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Create the Asset

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeDelete = assetRepository.findAll().size();

        // Get the asset
        restAssetMockMvc.perform(delete("/api/assets/{id}", asset.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Asset.class);
        Asset asset1 = new Asset();
        asset1.setId(1L);
        Asset asset2 = new Asset();
        asset2.setId(asset1.getId());
        assertThat(asset1).isEqualTo(asset2);
        asset2.setId(2L);
        assertThat(asset1).isNotEqualTo(asset2);
        asset1.setId(null);
        assertThat(asset1).isNotEqualTo(asset2);
    }
}
