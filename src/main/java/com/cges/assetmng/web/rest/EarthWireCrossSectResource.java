package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.EarthWireCrossSect;
import com.cges.assetmng.repository.EarthWireCrossSectRepository;
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
 * REST controller for managing EarthWireCrossSect.
 */
@RestController
@RequestMapping("/api")
public class EarthWireCrossSectResource {

    private final Logger log = LoggerFactory.getLogger(EarthWireCrossSectResource.class);

    private static final String ENTITY_NAME = "earthWireCrossSect";

    private final EarthWireCrossSectRepository earthWireCrossSectRepository;

    public EarthWireCrossSectResource(EarthWireCrossSectRepository earthWireCrossSectRepository) {
        this.earthWireCrossSectRepository = earthWireCrossSectRepository;
    }

    /**
     * POST  /earth-wire-cross-sects : Create a new earthWireCrossSect.
     *
     * @param earthWireCrossSect the earthWireCrossSect to create
     * @return the ResponseEntity with status 201 (Created) and with body the new earthWireCrossSect, or with status 400 (Bad Request) if the earthWireCrossSect has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/earth-wire-cross-sects")
    @Timed
    public ResponseEntity<EarthWireCrossSect> createEarthWireCrossSect(@Valid @RequestBody EarthWireCrossSect earthWireCrossSect) throws URISyntaxException {
        log.debug("REST request to save EarthWireCrossSect : {}", earthWireCrossSect);
        if (earthWireCrossSect.getId() != null) {
            throw new BadRequestAlertException("A new earthWireCrossSect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EarthWireCrossSect result = earthWireCrossSectRepository.save(earthWireCrossSect);
        return ResponseEntity.created(new URI("/api/earth-wire-cross-sects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /earth-wire-cross-sects : Updates an existing earthWireCrossSect.
     *
     * @param earthWireCrossSect the earthWireCrossSect to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated earthWireCrossSect,
     * or with status 400 (Bad Request) if the earthWireCrossSect is not valid,
     * or with status 500 (Internal Server Error) if the earthWireCrossSect couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/earth-wire-cross-sects")
    @Timed
    public ResponseEntity<EarthWireCrossSect> updateEarthWireCrossSect(@Valid @RequestBody EarthWireCrossSect earthWireCrossSect) throws URISyntaxException {
        log.debug("REST request to update EarthWireCrossSect : {}", earthWireCrossSect);
        if (earthWireCrossSect.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EarthWireCrossSect result = earthWireCrossSectRepository.save(earthWireCrossSect);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, earthWireCrossSect.getId().toString()))
            .body(result);
    }

    /**
     * GET  /earth-wire-cross-sects : get all the earthWireCrossSects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of earthWireCrossSects in body
     */
    @GetMapping("/earth-wire-cross-sects")
    @Timed
    public List<EarthWireCrossSect> getAllEarthWireCrossSects() {
        log.debug("REST request to get all EarthWireCrossSects");
        return earthWireCrossSectRepository.findAll();
    }

    /**
     * GET  /earth-wire-cross-sects/:id : get the "id" earthWireCrossSect.
     *
     * @param id the id of the earthWireCrossSect to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the earthWireCrossSect, or with status 404 (Not Found)
     */
    @GetMapping("/earth-wire-cross-sects/{id}")
    @Timed
    public ResponseEntity<EarthWireCrossSect> getEarthWireCrossSect(@PathVariable Long id) {
        log.debug("REST request to get EarthWireCrossSect : {}", id);
        Optional<EarthWireCrossSect> earthWireCrossSect = earthWireCrossSectRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(earthWireCrossSect);
    }

    /**
     * DELETE  /earth-wire-cross-sects/:id : delete the "id" earthWireCrossSect.
     *
     * @param id the id of the earthWireCrossSect to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/earth-wire-cross-sects/{id}")
    @Timed
    public ResponseEntity<Void> deleteEarthWireCrossSect(@PathVariable Long id) {
        log.debug("REST request to delete EarthWireCrossSect : {}", id);

        earthWireCrossSectRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
