package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.SurgeArresterType;
import com.cges.assetmng.repository.SurgeArresterTypeRepository;
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
 * REST controller for managing SurgeArresterType.
 */
@RestController
@RequestMapping("/api")
public class SurgeArresterTypeResource {

    private final Logger log = LoggerFactory.getLogger(SurgeArresterTypeResource.class);

    private static final String ENTITY_NAME = "surgeArresterType";

    private final SurgeArresterTypeRepository surgeArresterTypeRepository;

    public SurgeArresterTypeResource(SurgeArresterTypeRepository surgeArresterTypeRepository) {
        this.surgeArresterTypeRepository = surgeArresterTypeRepository;
    }

    /**
     * POST  /surge-arrester-types : Create a new surgeArresterType.
     *
     * @param surgeArresterType the surgeArresterType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surgeArresterType, or with status 400 (Bad Request) if the surgeArresterType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/surge-arrester-types")
    @Timed
    public ResponseEntity<SurgeArresterType> createSurgeArresterType(@RequestBody SurgeArresterType surgeArresterType) throws URISyntaxException {
        log.debug("REST request to save SurgeArresterType : {}", surgeArresterType);
        if (surgeArresterType.getId() != null) {
            throw new BadRequestAlertException("A new surgeArresterType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurgeArresterType result = surgeArresterTypeRepository.save(surgeArresterType);
        return ResponseEntity.created(new URI("/api/surge-arrester-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /surge-arrester-types : Updates an existing surgeArresterType.
     *
     * @param surgeArresterType the surgeArresterType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surgeArresterType,
     * or with status 400 (Bad Request) if the surgeArresterType is not valid,
     * or with status 500 (Internal Server Error) if the surgeArresterType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/surge-arrester-types")
    @Timed
    public ResponseEntity<SurgeArresterType> updateSurgeArresterType(@RequestBody SurgeArresterType surgeArresterType) throws URISyntaxException {
        log.debug("REST request to update SurgeArresterType : {}", surgeArresterType);
        if (surgeArresterType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurgeArresterType result = surgeArresterTypeRepository.save(surgeArresterType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surgeArresterType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /surge-arrester-types : get all the surgeArresterTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of surgeArresterTypes in body
     */
    @GetMapping("/surge-arrester-types")
    @Timed
    public List<SurgeArresterType> getAllSurgeArresterTypes() {
        log.debug("REST request to get all SurgeArresterTypes");
        return surgeArresterTypeRepository.findAll();
    }

    /**
     * GET  /surge-arrester-types/:id : get the "id" surgeArresterType.
     *
     * @param id the id of the surgeArresterType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surgeArresterType, or with status 404 (Not Found)
     */
    @GetMapping("/surge-arrester-types/{id}")
    @Timed
    public ResponseEntity<SurgeArresterType> getSurgeArresterType(@PathVariable Long id) {
        log.debug("REST request to get SurgeArresterType : {}", id);
        Optional<SurgeArresterType> surgeArresterType = surgeArresterTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(surgeArresterType);
    }

    /**
     * DELETE  /surge-arrester-types/:id : delete the "id" surgeArresterType.
     *
     * @param id the id of the surgeArresterType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/surge-arrester-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurgeArresterType(@PathVariable Long id) {
        log.debug("REST request to delete SurgeArresterType : {}", id);

        surgeArresterTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
