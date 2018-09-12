package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CircuitBreakerDrive;
import com.cges.assetmng.repository.CircuitBreakerDriveRepository;
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
 * REST controller for managing CircuitBreakerDrive.
 */
@RestController
@RequestMapping("/api")
public class CircuitBreakerDriveResource {

    private final Logger log = LoggerFactory.getLogger(CircuitBreakerDriveResource.class);

    private static final String ENTITY_NAME = "circuitBreakerDrive";

    private final CircuitBreakerDriveRepository circuitBreakerDriveRepository;

    public CircuitBreakerDriveResource(CircuitBreakerDriveRepository circuitBreakerDriveRepository) {
        this.circuitBreakerDriveRepository = circuitBreakerDriveRepository;
    }

    /**
     * POST  /circuit-breaker-drives : Create a new circuitBreakerDrive.
     *
     * @param circuitBreakerDrive the circuitBreakerDrive to create
     * @return the ResponseEntity with status 201 (Created) and with body the new circuitBreakerDrive, or with status 400 (Bad Request) if the circuitBreakerDrive has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/circuit-breaker-drives")
    @Timed
    public ResponseEntity<CircuitBreakerDrive> createCircuitBreakerDrive(@RequestBody CircuitBreakerDrive circuitBreakerDrive) throws URISyntaxException {
        log.debug("REST request to save CircuitBreakerDrive : {}", circuitBreakerDrive);
        if (circuitBreakerDrive.getId() != null) {
            throw new BadRequestAlertException("A new circuitBreakerDrive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CircuitBreakerDrive result = circuitBreakerDriveRepository.save(circuitBreakerDrive);
        return ResponseEntity.created(new URI("/api/circuit-breaker-drives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /circuit-breaker-drives : Updates an existing circuitBreakerDrive.
     *
     * @param circuitBreakerDrive the circuitBreakerDrive to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated circuitBreakerDrive,
     * or with status 400 (Bad Request) if the circuitBreakerDrive is not valid,
     * or with status 500 (Internal Server Error) if the circuitBreakerDrive couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/circuit-breaker-drives")
    @Timed
    public ResponseEntity<CircuitBreakerDrive> updateCircuitBreakerDrive(@RequestBody CircuitBreakerDrive circuitBreakerDrive) throws URISyntaxException {
        log.debug("REST request to update CircuitBreakerDrive : {}", circuitBreakerDrive);
        if (circuitBreakerDrive.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CircuitBreakerDrive result = circuitBreakerDriveRepository.save(circuitBreakerDrive);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, circuitBreakerDrive.getId().toString()))
            .body(result);
    }

    /**
     * GET  /circuit-breaker-drives : get all the circuitBreakerDrives.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of circuitBreakerDrives in body
     */
    @GetMapping("/circuit-breaker-drives")
    @Timed
    public List<CircuitBreakerDrive> getAllCircuitBreakerDrives() {
        log.debug("REST request to get all CircuitBreakerDrives");
        return circuitBreakerDriveRepository.findAll();
    }

    /**
     * GET  /circuit-breaker-drives/:id : get the "id" circuitBreakerDrive.
     *
     * @param id the id of the circuitBreakerDrive to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the circuitBreakerDrive, or with status 404 (Not Found)
     */
    @GetMapping("/circuit-breaker-drives/{id}")
    @Timed
    public ResponseEntity<CircuitBreakerDrive> getCircuitBreakerDrive(@PathVariable Long id) {
        log.debug("REST request to get CircuitBreakerDrive : {}", id);
        Optional<CircuitBreakerDrive> circuitBreakerDrive = circuitBreakerDriveRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(circuitBreakerDrive);
    }

    /**
     * DELETE  /circuit-breaker-drives/:id : delete the "id" circuitBreakerDrive.
     *
     * @param id the id of the circuitBreakerDrive to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/circuit-breaker-drives/{id}")
    @Timed
    public ResponseEntity<Void> deleteCircuitBreakerDrive(@PathVariable Long id) {
        log.debug("REST request to delete CircuitBreakerDrive : {}", id);

        circuitBreakerDriveRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
