package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.DisconnectorType;
import com.cges.assetmng.repository.DisconnectorTypeRepository;
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
 * REST controller for managing DisconnectorType.
 */
@RestController
@RequestMapping("/api")
public class DisconnectorTypeResource {

    private final Logger log = LoggerFactory.getLogger(DisconnectorTypeResource.class);

    private static final String ENTITY_NAME = "disconnectorType";

    private final DisconnectorTypeRepository disconnectorTypeRepository;

    public DisconnectorTypeResource(DisconnectorTypeRepository disconnectorTypeRepository) {
        this.disconnectorTypeRepository = disconnectorTypeRepository;
    }

    /**
     * POST  /disconnector-types : Create a new disconnectorType.
     *
     * @param disconnectorType the disconnectorType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disconnectorType, or with status 400 (Bad Request) if the disconnectorType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disconnector-types")
    @Timed
    public ResponseEntity<DisconnectorType> createDisconnectorType(@RequestBody DisconnectorType disconnectorType) throws URISyntaxException {
        log.debug("REST request to save DisconnectorType : {}", disconnectorType);
        if (disconnectorType.getId() != null) {
            throw new BadRequestAlertException("A new disconnectorType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisconnectorType result = disconnectorTypeRepository.save(disconnectorType);
        return ResponseEntity.created(new URI("/api/disconnector-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disconnector-types : Updates an existing disconnectorType.
     *
     * @param disconnectorType the disconnectorType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disconnectorType,
     * or with status 400 (Bad Request) if the disconnectorType is not valid,
     * or with status 500 (Internal Server Error) if the disconnectorType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disconnector-types")
    @Timed
    public ResponseEntity<DisconnectorType> updateDisconnectorType(@RequestBody DisconnectorType disconnectorType) throws URISyntaxException {
        log.debug("REST request to update DisconnectorType : {}", disconnectorType);
        if (disconnectorType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisconnectorType result = disconnectorTypeRepository.save(disconnectorType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disconnectorType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disconnector-types : get all the disconnectorTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disconnectorTypes in body
     */
    @GetMapping("/disconnector-types")
    @Timed
    public List<DisconnectorType> getAllDisconnectorTypes() {
        log.debug("REST request to get all DisconnectorTypes");
        return disconnectorTypeRepository.findAll();
    }

    /**
     * GET  /disconnector-types/:id : get the "id" disconnectorType.
     *
     * @param id the id of the disconnectorType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disconnectorType, or with status 404 (Not Found)
     */
    @GetMapping("/disconnector-types/{id}")
    @Timed
    public ResponseEntity<DisconnectorType> getDisconnectorType(@PathVariable Long id) {
        log.debug("REST request to get DisconnectorType : {}", id);
        Optional<DisconnectorType> disconnectorType = disconnectorTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disconnectorType);
    }

    /**
     * DELETE  /disconnector-types/:id : delete the "id" disconnectorType.
     *
     * @param id the id of the disconnectorType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disconnector-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisconnectorType(@PathVariable Long id) {
        log.debug("REST request to delete DisconnectorType : {}", id);

        disconnectorTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
