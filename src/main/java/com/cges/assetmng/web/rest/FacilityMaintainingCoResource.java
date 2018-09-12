package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.FacilityMaintainingCo;
import com.cges.assetmng.repository.FacilityMaintainingCoRepository;
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
 * REST controller for managing FacilityMaintainingCo.
 */
@RestController
@RequestMapping("/api")
public class FacilityMaintainingCoResource {

    private final Logger log = LoggerFactory.getLogger(FacilityMaintainingCoResource.class);

    private static final String ENTITY_NAME = "facilityMaintainingCo";

    private final FacilityMaintainingCoRepository facilityMaintainingCoRepository;

    public FacilityMaintainingCoResource(FacilityMaintainingCoRepository facilityMaintainingCoRepository) {
        this.facilityMaintainingCoRepository = facilityMaintainingCoRepository;
    }

    /**
     * POST  /facility-maintaining-cos : Create a new facilityMaintainingCo.
     *
     * @param facilityMaintainingCo the facilityMaintainingCo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new facilityMaintainingCo, or with status 400 (Bad Request) if the facilityMaintainingCo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/facility-maintaining-cos")
    @Timed
    public ResponseEntity<FacilityMaintainingCo> createFacilityMaintainingCo(@RequestBody FacilityMaintainingCo facilityMaintainingCo) throws URISyntaxException {
        log.debug("REST request to save FacilityMaintainingCo : {}", facilityMaintainingCo);
        if (facilityMaintainingCo.getId() != null) {
            throw new BadRequestAlertException("A new facilityMaintainingCo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacilityMaintainingCo result = facilityMaintainingCoRepository.save(facilityMaintainingCo);
        return ResponseEntity.created(new URI("/api/facility-maintaining-cos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /facility-maintaining-cos : Updates an existing facilityMaintainingCo.
     *
     * @param facilityMaintainingCo the facilityMaintainingCo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated facilityMaintainingCo,
     * or with status 400 (Bad Request) if the facilityMaintainingCo is not valid,
     * or with status 500 (Internal Server Error) if the facilityMaintainingCo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/facility-maintaining-cos")
    @Timed
    public ResponseEntity<FacilityMaintainingCo> updateFacilityMaintainingCo(@RequestBody FacilityMaintainingCo facilityMaintainingCo) throws URISyntaxException {
        log.debug("REST request to update FacilityMaintainingCo : {}", facilityMaintainingCo);
        if (facilityMaintainingCo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FacilityMaintainingCo result = facilityMaintainingCoRepository.save(facilityMaintainingCo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facilityMaintainingCo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /facility-maintaining-cos : get all the facilityMaintainingCos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of facilityMaintainingCos in body
     */
    @GetMapping("/facility-maintaining-cos")
    @Timed
    public List<FacilityMaintainingCo> getAllFacilityMaintainingCos() {
        log.debug("REST request to get all FacilityMaintainingCos");
        return facilityMaintainingCoRepository.findAll();
    }

    /**
     * GET  /facility-maintaining-cos/:id : get the "id" facilityMaintainingCo.
     *
     * @param id the id of the facilityMaintainingCo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the facilityMaintainingCo, or with status 404 (Not Found)
     */
    @GetMapping("/facility-maintaining-cos/{id}")
    @Timed
    public ResponseEntity<FacilityMaintainingCo> getFacilityMaintainingCo(@PathVariable Long id) {
        log.debug("REST request to get FacilityMaintainingCo : {}", id);
        Optional<FacilityMaintainingCo> facilityMaintainingCo = facilityMaintainingCoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(facilityMaintainingCo);
    }

    /**
     * DELETE  /facility-maintaining-cos/:id : delete the "id" facilityMaintainingCo.
     *
     * @param id the id of the facilityMaintainingCo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/facility-maintaining-cos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFacilityMaintainingCo(@PathVariable Long id) {
        log.debug("REST request to delete FacilityMaintainingCo : {}", id);

        facilityMaintainingCoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
