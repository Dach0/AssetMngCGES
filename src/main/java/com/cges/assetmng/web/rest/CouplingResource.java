package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.Coupling;
import com.cges.assetmng.repository.CouplingRepository;
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
 * REST controller for managing Coupling.
 */
@RestController
@RequestMapping("/api")
public class CouplingResource {

    private final Logger log = LoggerFactory.getLogger(CouplingResource.class);

    private static final String ENTITY_NAME = "coupling";

    private final CouplingRepository couplingRepository;

    public CouplingResource(CouplingRepository couplingRepository) {
        this.couplingRepository = couplingRepository;
    }

    /**
     * POST  /couplings : Create a new coupling.
     *
     * @param coupling the coupling to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coupling, or with status 400 (Bad Request) if the coupling has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/couplings")
    @Timed
    public ResponseEntity<Coupling> createCoupling(@RequestBody Coupling coupling) throws URISyntaxException {
        log.debug("REST request to save Coupling : {}", coupling);
        if (coupling.getId() != null) {
            throw new BadRequestAlertException("A new coupling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Coupling result = couplingRepository.save(coupling);
        return ResponseEntity.created(new URI("/api/couplings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /couplings : Updates an existing coupling.
     *
     * @param coupling the coupling to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coupling,
     * or with status 400 (Bad Request) if the coupling is not valid,
     * or with status 500 (Internal Server Error) if the coupling couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/couplings")
    @Timed
    public ResponseEntity<Coupling> updateCoupling(@RequestBody Coupling coupling) throws URISyntaxException {
        log.debug("REST request to update Coupling : {}", coupling);
        if (coupling.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Coupling result = couplingRepository.save(coupling);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coupling.getId().toString()))
            .body(result);
    }

    /**
     * GET  /couplings : get all the couplings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of couplings in body
     */
    @GetMapping("/couplings")
    @Timed
    public List<Coupling> getAllCouplings() {
        log.debug("REST request to get all Couplings");
        return couplingRepository.findAll();
    }

    /**
     * GET  /couplings/:id : get the "id" coupling.
     *
     * @param id the id of the coupling to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coupling, or with status 404 (Not Found)
     */
    @GetMapping("/couplings/{id}")
    @Timed
    public ResponseEntity<Coupling> getCoupling(@PathVariable Long id) {
        log.debug("REST request to get Coupling : {}", id);
        Optional<Coupling> coupling = couplingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(coupling);
    }

    /**
     * DELETE  /couplings/:id : delete the "id" coupling.
     *
     * @param id the id of the coupling to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/couplings/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoupling(@PathVariable Long id) {
        log.debug("REST request to delete Coupling : {}", id);

        couplingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
