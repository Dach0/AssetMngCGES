package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.Substation;
import com.cges.assetmng.repository.SubstationRepository;
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
 * REST controller for managing Substation.
 */
@RestController
@RequestMapping("/api")
public class SubstationResource {

    private final Logger log = LoggerFactory.getLogger(SubstationResource.class);

    private static final String ENTITY_NAME = "substation";

    private final SubstationRepository substationRepository;

    public SubstationResource(SubstationRepository substationRepository) {
        this.substationRepository = substationRepository;
    }

    /**
     * POST  /substations : Create a new substation.
     *
     * @param substation the substation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new substation, or with status 400 (Bad Request) if the substation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/substations")
    @Timed
    public ResponseEntity<Substation> createSubstation(@Valid @RequestBody Substation substation) throws URISyntaxException {
        log.debug("REST request to save Substation : {}", substation);
        if (substation.getId() != null) {
            throw new BadRequestAlertException("A new substation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Substation result = substationRepository.save(substation);
        return ResponseEntity.created(new URI("/api/substations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /substations : Updates an existing substation.
     *
     * @param substation the substation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated substation,
     * or with status 400 (Bad Request) if the substation is not valid,
     * or with status 500 (Internal Server Error) if the substation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/substations")
    @Timed
    public ResponseEntity<Substation> updateSubstation(@Valid @RequestBody Substation substation) throws URISyntaxException {
        log.debug("REST request to update Substation : {}", substation);
        if (substation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Substation result = substationRepository.save(substation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, substation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /substations : get all the substations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of substations in body
     */
    @GetMapping("/substations")
    @Timed
    public List<Substation> getAllSubstations() {
        log.debug("REST request to get all Substations");
        return substationRepository.findAll();
    }

    /**
     * GET  /substations/:id : get the "id" substation.
     *
     * @param id the id of the substation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the substation, or with status 404 (Not Found)
     */
    @GetMapping("/substations/{id}")
    @Timed
    public ResponseEntity<Substation> getSubstation(@PathVariable Long id) {
        log.debug("REST request to get Substation : {}", id);
        Optional<Substation> substation = substationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(substation);
    }

    /**
     * DELETE  /substations/:id : delete the "id" substation.
     *
     * @param id the id of the substation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/substations/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubstation(@PathVariable Long id) {
        log.debug("REST request to delete Substation : {}", id);

        substationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
