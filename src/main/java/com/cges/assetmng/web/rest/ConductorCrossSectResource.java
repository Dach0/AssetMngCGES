package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ConductorCrossSect;
import com.cges.assetmng.repository.ConductorCrossSectRepository;
import com.cges.assetmng.web.rest.errors.BadRequestAlertException;
import com.cges.assetmng.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ConductorCrossSect.
 */
@RestController
@RequestMapping("/api")
public class ConductorCrossSectResource {

    private final Logger log = LoggerFactory.getLogger(ConductorCrossSectResource.class);

    private static final String ENTITY_NAME = "conductorCrossSect";

    private final ConductorCrossSectRepository conductorCrossSectRepository;

    public ConductorCrossSectResource(ConductorCrossSectRepository conductorCrossSectRepository) {
        this.conductorCrossSectRepository = conductorCrossSectRepository;
    }

    /**
     * POST  /conductor-cross-sects : Create a new conductorCrossSect.
     *
     * @param conductorCrossSect the conductorCrossSect to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conductorCrossSect, or with status 400 (Bad Request) if the conductorCrossSect has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conductor-cross-sects")
    @Timed
    public ResponseEntity<ConductorCrossSect> createConductorCrossSect(@Valid @RequestBody ConductorCrossSect conductorCrossSect) throws URISyntaxException {
        log.debug("REST request to save ConductorCrossSect : {}", conductorCrossSect);
        if (conductorCrossSect.getId() != null) {
            throw new BadRequestAlertException("A new conductorCrossSect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConductorCrossSect result = conductorCrossSectRepository.save(conductorCrossSect);
        return ResponseEntity.created(new URI("/api/conductor-cross-sects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conductor-cross-sects : Updates an existing conductorCrossSect.
     *
     * @param conductorCrossSect the conductorCrossSect to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conductorCrossSect,
     * or with status 400 (Bad Request) if the conductorCrossSect is not valid,
     * or with status 500 (Internal Server Error) if the conductorCrossSect couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conductor-cross-sects")
    @Timed
    public ResponseEntity<ConductorCrossSect> updateConductorCrossSect(@Valid @RequestBody ConductorCrossSect conductorCrossSect) throws URISyntaxException {
        log.debug("REST request to update ConductorCrossSect : {}", conductorCrossSect);
        if (conductorCrossSect.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConductorCrossSect result = conductorCrossSectRepository.save(conductorCrossSect);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conductorCrossSect.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conductor-cross-sects : get all the conductorCrossSects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of conductorCrossSects in body
     */
    @GetMapping("/conductor-cross-sects")
    @Timed
    public List<ConductorCrossSect> getAllConductorCrossSects() {
        log.debug("REST request to get all ConductorCrossSects");
        return conductorCrossSectRepository.findAll();
    }

    /**
     * GET  /conductor-cross-sects/:id : get the "id" conductorCrossSect.
     *
     * @param id the id of the conductorCrossSect to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conductorCrossSect, or with status 404 (Not Found)
     */
    @GetMapping("/conductor-cross-sects/{id}")
    @Timed
    public ResponseEntity<ConductorCrossSect> getConductorCrossSect(@PathVariable Long id) {
        log.debug("REST request to get ConductorCrossSect : {}", id);
        Optional<ConductorCrossSect> conductorCrossSect = conductorCrossSectRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(conductorCrossSect);
    }

    /**
     * DELETE  /conductor-cross-sects/:id : delete the "id" conductorCrossSect.
     *
     * @param id the id of the conductorCrossSect to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conductor-cross-sects/{id}")
    @Timed
    public ResponseEntity<Void> deleteConductorCrossSect(@PathVariable Long id) {
        log.debug("REST request to delete ConductorCrossSect : {}", id);

        conductorCrossSectRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
