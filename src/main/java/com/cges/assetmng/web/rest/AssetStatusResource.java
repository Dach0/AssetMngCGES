package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.AssetStatus;
import com.cges.assetmng.repository.AssetStatusRepository;
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
 * REST controller for managing AssetStatus.
 */
@RestController
@RequestMapping("/api")
public class AssetStatusResource {

    private final Logger log = LoggerFactory.getLogger(AssetStatusResource.class);

    private static final String ENTITY_NAME = "assetStatus";

    private final AssetStatusRepository assetStatusRepository;

    public AssetStatusResource(AssetStatusRepository assetStatusRepository) {
        this.assetStatusRepository = assetStatusRepository;
    }

    /**
     * POST  /asset-statuses : Create a new assetStatus.
     *
     * @param assetStatus the assetStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetStatus, or with status 400 (Bad Request) if the assetStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-statuses")
    @Timed
    public ResponseEntity<AssetStatus> createAssetStatus(@RequestBody AssetStatus assetStatus) throws URISyntaxException {
        log.debug("REST request to save AssetStatus : {}", assetStatus);
        if (assetStatus.getId() != null) {
            throw new BadRequestAlertException("A new assetStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetStatus result = assetStatusRepository.save(assetStatus);
        return ResponseEntity.created(new URI("/api/asset-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-statuses : Updates an existing assetStatus.
     *
     * @param assetStatus the assetStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetStatus,
     * or with status 400 (Bad Request) if the assetStatus is not valid,
     * or with status 500 (Internal Server Error) if the assetStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-statuses")
    @Timed
    public ResponseEntity<AssetStatus> updateAssetStatus(@RequestBody AssetStatus assetStatus) throws URISyntaxException {
        log.debug("REST request to update AssetStatus : {}", assetStatus);
        if (assetStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetStatus result = assetStatusRepository.save(assetStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-statuses : get all the assetStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assetStatuses in body
     */
    @GetMapping("/asset-statuses")
    @Timed
    public List<AssetStatus> getAllAssetStatuses() {
        log.debug("REST request to get all AssetStatuses");
        return assetStatusRepository.findAll();
    }

    /**
     * GET  /asset-statuses/:id : get the "id" assetStatus.
     *
     * @param id the id of the assetStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetStatus, or with status 404 (Not Found)
     */
    @GetMapping("/asset-statuses/{id}")
    @Timed
    public ResponseEntity<AssetStatus> getAssetStatus(@PathVariable Long id) {
        log.debug("REST request to get AssetStatus : {}", id);
        Optional<AssetStatus> assetStatus = assetStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetStatus);
    }

    /**
     * DELETE  /asset-statuses/:id : delete the "id" assetStatus.
     *
     * @param id the id of the assetStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetStatus(@PathVariable Long id) {
        log.debug("REST request to delete AssetStatus : {}", id);

        assetStatusRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
