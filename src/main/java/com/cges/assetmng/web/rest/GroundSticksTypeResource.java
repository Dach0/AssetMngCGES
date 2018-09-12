package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.GroundSticksType;
import com.cges.assetmng.repository.GroundSticksTypeRepository;
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
 * REST controller for managing GroundSticksType.
 */
@RestController
@RequestMapping("/api")
public class GroundSticksTypeResource {

    private final Logger log = LoggerFactory.getLogger(GroundSticksTypeResource.class);

    private static final String ENTITY_NAME = "groundSticksType";

    private final GroundSticksTypeRepository groundSticksTypeRepository;

    public GroundSticksTypeResource(GroundSticksTypeRepository groundSticksTypeRepository) {
        this.groundSticksTypeRepository = groundSticksTypeRepository;
    }

    /**
     * POST  /ground-sticks-types : Create a new groundSticksType.
     *
     * @param groundSticksType the groundSticksType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groundSticksType, or with status 400 (Bad Request) if the groundSticksType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ground-sticks-types")
    @Timed
    public ResponseEntity<GroundSticksType> createGroundSticksType(@RequestBody GroundSticksType groundSticksType) throws URISyntaxException {
        log.debug("REST request to save GroundSticksType : {}", groundSticksType);
        if (groundSticksType.getId() != null) {
            throw new BadRequestAlertException("A new groundSticksType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroundSticksType result = groundSticksTypeRepository.save(groundSticksType);
        return ResponseEntity.created(new URI("/api/ground-sticks-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ground-sticks-types : Updates an existing groundSticksType.
     *
     * @param groundSticksType the groundSticksType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groundSticksType,
     * or with status 400 (Bad Request) if the groundSticksType is not valid,
     * or with status 500 (Internal Server Error) if the groundSticksType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ground-sticks-types")
    @Timed
    public ResponseEntity<GroundSticksType> updateGroundSticksType(@RequestBody GroundSticksType groundSticksType) throws URISyntaxException {
        log.debug("REST request to update GroundSticksType : {}", groundSticksType);
        if (groundSticksType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroundSticksType result = groundSticksTypeRepository.save(groundSticksType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groundSticksType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ground-sticks-types : get all the groundSticksTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of groundSticksTypes in body
     */
    @GetMapping("/ground-sticks-types")
    @Timed
    public List<GroundSticksType> getAllGroundSticksTypes() {
        log.debug("REST request to get all GroundSticksTypes");
        return groundSticksTypeRepository.findAll();
    }

    /**
     * GET  /ground-sticks-types/:id : get the "id" groundSticksType.
     *
     * @param id the id of the groundSticksType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groundSticksType, or with status 404 (Not Found)
     */
    @GetMapping("/ground-sticks-types/{id}")
    @Timed
    public ResponseEntity<GroundSticksType> getGroundSticksType(@PathVariable Long id) {
        log.debug("REST request to get GroundSticksType : {}", id);
        Optional<GroundSticksType> groundSticksType = groundSticksTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(groundSticksType);
    }

    /**
     * DELETE  /ground-sticks-types/:id : delete the "id" groundSticksType.
     *
     * @param id the id of the groundSticksType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ground-sticks-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroundSticksType(@PathVariable Long id) {
        log.debug("REST request to delete GroundSticksType : {}", id);

        groundSticksTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
