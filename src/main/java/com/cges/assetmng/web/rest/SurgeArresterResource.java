package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.SurgeArrester;
import com.cges.assetmng.repository.SurgeArresterRepository;
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
 * REST controller for managing SurgeArrester.
 */
@RestController
@RequestMapping("/api")
public class SurgeArresterResource {

    private final Logger log = LoggerFactory.getLogger(SurgeArresterResource.class);

    private static final String ENTITY_NAME = "surgeArrester";

    private final SurgeArresterRepository surgeArresterRepository;

    public SurgeArresterResource(SurgeArresterRepository surgeArresterRepository) {
        this.surgeArresterRepository = surgeArresterRepository;
    }

    /**
     * POST  /surge-arresters : Create a new surgeArrester.
     *
     * @param surgeArrester the surgeArrester to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surgeArrester, or with status 400 (Bad Request) if the surgeArrester has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/surge-arresters")
    @Timed
    public ResponseEntity<SurgeArrester> createSurgeArrester(@Valid @RequestBody SurgeArrester surgeArrester) throws URISyntaxException {
        log.debug("REST request to save SurgeArrester : {}", surgeArrester);
        if (surgeArrester.getId() != null) {
            throw new BadRequestAlertException("A new surgeArrester cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurgeArrester result = surgeArresterRepository.save(surgeArrester);
        return ResponseEntity.created(new URI("/api/surge-arresters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /surge-arresters : Updates an existing surgeArrester.
     *
     * @param surgeArrester the surgeArrester to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surgeArrester,
     * or with status 400 (Bad Request) if the surgeArrester is not valid,
     * or with status 500 (Internal Server Error) if the surgeArrester couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/surge-arresters")
    @Timed
    public ResponseEntity<SurgeArrester> updateSurgeArrester(@Valid @RequestBody SurgeArrester surgeArrester) throws URISyntaxException {
        log.debug("REST request to update SurgeArrester : {}", surgeArrester);
        if (surgeArrester.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurgeArrester result = surgeArresterRepository.save(surgeArrester);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surgeArrester.getId().toString()))
            .body(result);
    }

    /**
     * GET  /surge-arresters : get all the surgeArresters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of surgeArresters in body
     */
    @GetMapping("/surge-arresters")
    @Timed
    public List<SurgeArrester> getAllSurgeArresters() {
        log.debug("REST request to get all SurgeArresters");
        return surgeArresterRepository.findAll();
    }

    /**
     * GET  /surge-arresters/:id : get the "id" surgeArrester.
     *
     * @param id the id of the surgeArrester to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surgeArrester, or with status 404 (Not Found)
     */
    @GetMapping("/surge-arresters/{id}")
    @Timed
    public ResponseEntity<SurgeArrester> getSurgeArrester(@PathVariable Long id) {
        log.debug("REST request to get SurgeArrester : {}", id);
        Optional<SurgeArrester> surgeArrester = surgeArresterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(surgeArrester);
    }

    /**
     * DELETE  /surge-arresters/:id : delete the "id" surgeArrester.
     *
     * @param id the id of the surgeArrester to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/surge-arresters/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurgeArrester(@PathVariable Long id) {
        log.debug("REST request to delete SurgeArrester : {}", id);

        surgeArresterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
