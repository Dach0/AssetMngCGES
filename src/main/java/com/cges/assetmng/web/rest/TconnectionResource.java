package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.Tconnection;
import com.cges.assetmng.repository.TconnectionRepository;
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
 * REST controller for managing Tconnection.
 */
@RestController
@RequestMapping("/api")
public class TconnectionResource {

    private final Logger log = LoggerFactory.getLogger(TconnectionResource.class);

    private static final String ENTITY_NAME = "tconnection";

    private final TconnectionRepository tconnectionRepository;

    public TconnectionResource(TconnectionRepository tconnectionRepository) {
        this.tconnectionRepository = tconnectionRepository;
    }

    /**
     * POST  /tconnections : Create a new tconnection.
     *
     * @param tconnection the tconnection to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tconnection, or with status 400 (Bad Request) if the tconnection has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tconnections")
    @Timed
    public ResponseEntity<Tconnection> createTconnection(@RequestBody Tconnection tconnection) throws URISyntaxException {
        log.debug("REST request to save Tconnection : {}", tconnection);
        if (tconnection.getId() != null) {
            throw new BadRequestAlertException("A new tconnection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tconnection result = tconnectionRepository.save(tconnection);
        return ResponseEntity.created(new URI("/api/tconnections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tconnections : Updates an existing tconnection.
     *
     * @param tconnection the tconnection to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tconnection,
     * or with status 400 (Bad Request) if the tconnection is not valid,
     * or with status 500 (Internal Server Error) if the tconnection couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tconnections")
    @Timed
    public ResponseEntity<Tconnection> updateTconnection(@RequestBody Tconnection tconnection) throws URISyntaxException {
        log.debug("REST request to update Tconnection : {}", tconnection);
        if (tconnection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tconnection result = tconnectionRepository.save(tconnection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tconnection.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tconnections : get all the tconnections.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tconnections in body
     */
    @GetMapping("/tconnections")
    @Timed
    public List<Tconnection> getAllTconnections() {
        log.debug("REST request to get all Tconnections");
        return tconnectionRepository.findAll();
    }

    /**
     * GET  /tconnections/:id : get the "id" tconnection.
     *
     * @param id the id of the tconnection to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tconnection, or with status 404 (Not Found)
     */
    @GetMapping("/tconnections/{id}")
    @Timed
    public ResponseEntity<Tconnection> getTconnection(@PathVariable Long id) {
        log.debug("REST request to get Tconnection : {}", id);
        Optional<Tconnection> tconnection = tconnectionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tconnection);
    }

    /**
     * DELETE  /tconnections/:id : delete the "id" tconnection.
     *
     * @param id the id of the tconnection to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tconnections/{id}")
    @Timed
    public ResponseEntity<Void> deleteTconnection(@PathVariable Long id) {
        log.debug("REST request to delete Tconnection : {}", id);

        tconnectionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
