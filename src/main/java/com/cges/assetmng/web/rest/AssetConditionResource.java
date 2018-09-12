package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.AssetCondition;
import com.cges.assetmng.repository.AssetConditionRepository;
import com.cges.assetmng.web.rest.errors.BadRequestAlertException;
import com.cges.assetmng.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AssetCondition.
 */
@RestController
@RequestMapping("/api")
public class AssetConditionResource {

    private final Logger log = LoggerFactory.getLogger(AssetConditionResource.class);

    private static final String ENTITY_NAME = "assetCondition";

    private final AssetConditionRepository assetConditionRepository;

    public AssetConditionResource(AssetConditionRepository assetConditionRepository) {
        this.assetConditionRepository = assetConditionRepository;
    }

    /**
     * POST  /asset-conditions : Create a new assetCondition.
     *
     * @param assetCondition the assetCondition to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetCondition, or with status 400 (Bad Request) if the assetCondition has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-conditions")
    @Timed
    public ResponseEntity<AssetCondition> createAssetCondition(@RequestBody AssetCondition assetCondition) throws URISyntaxException {
        log.debug("REST request to save AssetCondition : {}", assetCondition);
        if (assetCondition.getId() != null) {
            throw new BadRequestAlertException("A new assetCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetCondition result = assetConditionRepository.save(assetCondition);
        return ResponseEntity.created(new URI("/api/asset-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-conditions : Updates an existing assetCondition.
     *
     * @param assetCondition the assetCondition to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetCondition,
     * or with status 400 (Bad Request) if the assetCondition is not valid,
     * or with status 500 (Internal Server Error) if the assetCondition couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-conditions")
    @Timed
    public ResponseEntity<AssetCondition> updateAssetCondition(@RequestBody AssetCondition assetCondition) throws URISyntaxException {
        log.debug("REST request to update AssetCondition : {}", assetCondition);
        if (assetCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetCondition result = assetConditionRepository.save(assetCondition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetCondition.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-conditions : get all the assetConditions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assetConditions in body
     */
    @GetMapping("/asset-conditions")
    @Timed
    public List<AssetCondition> getAllAssetConditions() {
        log.debug("REST request to get all AssetConditions");
        return assetConditionRepository.findAll();
    }

    /**
     * GET  /asset-conditions/:id : get the "id" assetCondition.
     *
     * @param id the id of the assetCondition to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetCondition, or with status 404 (Not Found)
     */
    @GetMapping("/asset-conditions/{id}")
    @Timed
    public ResponseEntity<AssetCondition> getAssetCondition(@PathVariable Long id) {
        log.debug("REST request to get AssetCondition : {}", id);
        Optional<AssetCondition> assetCondition = assetConditionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetCondition);
    }

    /**
     * DELETE  /asset-conditions/:id : delete the "id" assetCondition.
     *
     * @param id the id of the assetCondition to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-conditions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetCondition(@PathVariable Long id) {
        log.debug("REST request to delete AssetCondition : {}", id);

        assetConditionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
