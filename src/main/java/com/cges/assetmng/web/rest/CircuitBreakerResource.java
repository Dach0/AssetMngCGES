package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CircuitBreaker;
import com.cges.assetmng.repository.CircuitBreakerRepository;
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
 * REST controller for managing CircuitBreaker.
 */
@RestController
@RequestMapping("/api")
public class CircuitBreakerResource {

    private final Logger log = LoggerFactory.getLogger(CircuitBreakerResource.class);

    private static final String ENTITY_NAME = "circuitBreaker";

    private final CircuitBreakerRepository circuitBreakerRepository;

    public CircuitBreakerResource(CircuitBreakerRepository circuitBreakerRepository) {
        this.circuitBreakerRepository = circuitBreakerRepository;
    }

    /**
     * POST  /circuit-breakers : Create a new circuitBreaker.
     *
     * @param circuitBreaker the circuitBreaker to create
     * @return the ResponseEntity with status 201 (Created) and with body the new circuitBreaker, or with status 400 (Bad Request) if the circuitBreaker has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/circuit-breakers")
    @Timed
    public ResponseEntity<CircuitBreaker> createCircuitBreaker(@Valid @RequestBody CircuitBreaker circuitBreaker) throws URISyntaxException {
        log.debug("REST request to save CircuitBreaker : {}", circuitBreaker);
        if (circuitBreaker.getId() != null) {
            throw new BadRequestAlertException("A new circuitBreaker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CircuitBreaker result = circuitBreakerRepository.save(circuitBreaker);
        return ResponseEntity.created(new URI("/api/circuit-breakers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /circuit-breakers : Updates an existing circuitBreaker.
     *
     * @param circuitBreaker the circuitBreaker to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated circuitBreaker,
     * or with status 400 (Bad Request) if the circuitBreaker is not valid,
     * or with status 500 (Internal Server Error) if the circuitBreaker couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/circuit-breakers")
    @Timed
    public ResponseEntity<CircuitBreaker> updateCircuitBreaker(@Valid @RequestBody CircuitBreaker circuitBreaker) throws URISyntaxException {
        log.debug("REST request to update CircuitBreaker : {}", circuitBreaker);
        if (circuitBreaker.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CircuitBreaker result = circuitBreakerRepository.save(circuitBreaker);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, circuitBreaker.getId().toString()))
            .body(result);
    }

    /**
     * GET  /circuit-breakers : get all the circuitBreakers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of circuitBreakers in body
     */
    @GetMapping("/circuit-breakers")
    @Timed
    public List<CircuitBreaker> getAllCircuitBreakers() {
        log.debug("REST request to get all CircuitBreakers");
        return circuitBreakerRepository.findAll();
    }

    /**
     * GET  /circuit-breakers/:id : get the "id" circuitBreaker.
     *
     * @param id the id of the circuitBreaker to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the circuitBreaker, or with status 404 (Not Found)
     */
    @GetMapping("/circuit-breakers/{id}")
    @Timed
    public ResponseEntity<CircuitBreaker> getCircuitBreaker(@PathVariable Long id) {
        log.debug("REST request to get CircuitBreaker : {}", id);
        Optional<CircuitBreaker> circuitBreaker = circuitBreakerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(circuitBreaker);
    }

    /**
     * DELETE  /circuit-breakers/:id : delete the "id" circuitBreaker.
     *
     * @param id the id of the circuitBreaker to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/circuit-breakers/{id}")
    @Timed
    public ResponseEntity<Void> deleteCircuitBreaker(@PathVariable Long id) {
        log.debug("REST request to delete CircuitBreaker : {}", id);

        circuitBreakerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
