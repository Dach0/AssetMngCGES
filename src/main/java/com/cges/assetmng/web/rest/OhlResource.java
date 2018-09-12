package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.Ohl;
import com.cges.assetmng.repository.OhlRepository;
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
 * REST controller for managing Ohl.
 */
@RestController
@RequestMapping("/api")
public class OhlResource {

    private final Logger log = LoggerFactory.getLogger(OhlResource.class);

    private static final String ENTITY_NAME = "ohl";

    private final OhlRepository ohlRepository;

    public OhlResource(OhlRepository ohlRepository) {
        this.ohlRepository = ohlRepository;
    }

    /**
     * POST  /ohls : Create a new ohl.
     *
     * @param ohl the ohl to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ohl, or with status 400 (Bad Request) if the ohl has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ohls")
    @Timed
    public ResponseEntity<Ohl> createOhl(@Valid @RequestBody Ohl ohl) throws URISyntaxException {
        log.debug("REST request to save Ohl : {}", ohl);
        if (ohl.getId() != null) {
            throw new BadRequestAlertException("A new ohl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ohl result = ohlRepository.save(ohl);
        return ResponseEntity.created(new URI("/api/ohls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ohls : Updates an existing ohl.
     *
     * @param ohl the ohl to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ohl,
     * or with status 400 (Bad Request) if the ohl is not valid,
     * or with status 500 (Internal Server Error) if the ohl couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ohls")
    @Timed
    public ResponseEntity<Ohl> updateOhl(@Valid @RequestBody Ohl ohl) throws URISyntaxException {
        log.debug("REST request to update Ohl : {}", ohl);
        if (ohl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ohl result = ohlRepository.save(ohl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ohl.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ohls : get all the ohls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ohls in body
     */
    @GetMapping("/ohls")
    @Timed
    public List<Ohl> getAllOhls() {
        log.debug("REST request to get all Ohls");
        return ohlRepository.findAll();
    }

    /**
     * GET  /ohls/:id : get the "id" ohl.
     *
     * @param id the id of the ohl to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ohl, or with status 404 (Not Found)
     */
    @GetMapping("/ohls/{id}")
    @Timed
    public ResponseEntity<Ohl> getOhl(@PathVariable Long id) {
        log.debug("REST request to get Ohl : {}", id);
        Optional<Ohl> ohl = ohlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ohl);
    }

    /**
     * DELETE  /ohls/:id : delete the "id" ohl.
     *
     * @param id the id of the ohl to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ohls/{id}")
    @Timed
    public ResponseEntity<Void> deleteOhl(@PathVariable Long id) {
        log.debug("REST request to delete Ohl : {}", id);

        ohlRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
