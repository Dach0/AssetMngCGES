package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CircuitBreakerType;
import com.cges.assetmng.repository.CircuitBreakerTypeRepository;
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
 * REST controller for managing CircuitBreakerType.
 */
@RestController
@RequestMapping("/api")
public class CircuitBreakerTypeResource {

    private final Logger log = LoggerFactory.getLogger(CircuitBreakerTypeResource.class);

    private static final String ENTITY_NAME = "circuitBreakerType";

    private final CircuitBreakerTypeRepository circuitBreakerTypeRepository;

    public CircuitBreakerTypeResource(CircuitBreakerTypeRepository circuitBreakerTypeRepository) {
        this.circuitBreakerTypeRepository = circuitBreakerTypeRepository;
    }

    /**
     * POST  /circuit-breaker-types : Create a new circuitBreakerType.
     *
     * @param circuitBreakerType the circuitBreakerType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new circuitBreakerType, or with status 400 (Bad Request) if the circuitBreakerType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/circuit-breaker-types")
    @Timed
    public ResponseEntity<CircuitBreakerType> createCircuitBreakerType(@RequestBody CircuitBreakerType circuitBreakerType) throws URISyntaxException {
        log.debug("REST request to save CircuitBreakerType : {}", circuitBreakerType);
        if (circuitBreakerType.getId() != null) {
            throw new BadRequestAlertException("A new circuitBreakerType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CircuitBreakerType result = circuitBreakerTypeRepository.save(circuitBreakerType);
        return ResponseEntity.created(new URI("/api/circuit-breaker-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /circuit-breaker-types : Updates an existing circuitBreakerType.
     *
     * @param circuitBreakerType the circuitBreakerType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated circuitBreakerType,
     * or with status 400 (Bad Request) if the circuitBreakerType is not valid,
     * or with status 500 (Internal Server Error) if the circuitBreakerType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/circuit-breaker-types")
    @Timed
    public ResponseEntity<CircuitBreakerType> updateCircuitBreakerType(@RequestBody CircuitBreakerType circuitBreakerType) throws URISyntaxException {
        log.debug("REST request to update CircuitBreakerType : {}", circuitBreakerType);
        if (circuitBreakerType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CircuitBreakerType result = circuitBreakerTypeRepository.save(circuitBreakerType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, circuitBreakerType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /circuit-breaker-types : get all the circuitBreakerTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of circuitBreakerTypes in body
     */
    @GetMapping("/circuit-breaker-types")
    @Timed
    public List<CircuitBreakerType> getAllCircuitBreakerTypes() {
        log.debug("REST request to get all CircuitBreakerTypes");
        return circuitBreakerTypeRepository.findAll();
    }

    /**
     * GET  /circuit-breaker-types/:id : get the "id" circuitBreakerType.
     *
     * @param id the id of the circuitBreakerType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the circuitBreakerType, or with status 404 (Not Found)
     */
    @GetMapping("/circuit-breaker-types/{id}")
    @Timed
    public ResponseEntity<CircuitBreakerType> getCircuitBreakerType(@PathVariable Long id) {
        log.debug("REST request to get CircuitBreakerType : {}", id);
        Optional<CircuitBreakerType> circuitBreakerType = circuitBreakerTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(circuitBreakerType);
    }

    /**
     * DELETE  /circuit-breaker-types/:id : delete the "id" circuitBreakerType.
     *
     * @param id the id of the circuitBreakerType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/circuit-breaker-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCircuitBreakerType(@PathVariable Long id) {
        log.debug("REST request to delete CircuitBreakerType : {}", id);

        circuitBreakerTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
