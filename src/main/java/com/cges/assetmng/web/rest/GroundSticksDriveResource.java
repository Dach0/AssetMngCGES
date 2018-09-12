package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.GroundSticksDrive;
import com.cges.assetmng.repository.GroundSticksDriveRepository;
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
 * REST controller for managing GroundSticksDrive.
 */
@RestController
@RequestMapping("/api")
public class GroundSticksDriveResource {

    private final Logger log = LoggerFactory.getLogger(GroundSticksDriveResource.class);

    private static final String ENTITY_NAME = "groundSticksDrive";

    private final GroundSticksDriveRepository groundSticksDriveRepository;

    public GroundSticksDriveResource(GroundSticksDriveRepository groundSticksDriveRepository) {
        this.groundSticksDriveRepository = groundSticksDriveRepository;
    }

    /**
     * POST  /ground-sticks-drives : Create a new groundSticksDrive.
     *
     * @param groundSticksDrive the groundSticksDrive to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groundSticksDrive, or with status 400 (Bad Request) if the groundSticksDrive has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ground-sticks-drives")
    @Timed
    public ResponseEntity<GroundSticksDrive> createGroundSticksDrive(@RequestBody GroundSticksDrive groundSticksDrive) throws URISyntaxException {
        log.debug("REST request to save GroundSticksDrive : {}", groundSticksDrive);
        if (groundSticksDrive.getId() != null) {
            throw new BadRequestAlertException("A new groundSticksDrive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroundSticksDrive result = groundSticksDriveRepository.save(groundSticksDrive);
        return ResponseEntity.created(new URI("/api/ground-sticks-drives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ground-sticks-drives : Updates an existing groundSticksDrive.
     *
     * @param groundSticksDrive the groundSticksDrive to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groundSticksDrive,
     * or with status 400 (Bad Request) if the groundSticksDrive is not valid,
     * or with status 500 (Internal Server Error) if the groundSticksDrive couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ground-sticks-drives")
    @Timed
    public ResponseEntity<GroundSticksDrive> updateGroundSticksDrive(@RequestBody GroundSticksDrive groundSticksDrive) throws URISyntaxException {
        log.debug("REST request to update GroundSticksDrive : {}", groundSticksDrive);
        if (groundSticksDrive.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroundSticksDrive result = groundSticksDriveRepository.save(groundSticksDrive);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groundSticksDrive.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ground-sticks-drives : get all the groundSticksDrives.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of groundSticksDrives in body
     */
    @GetMapping("/ground-sticks-drives")
    @Timed
    public List<GroundSticksDrive> getAllGroundSticksDrives() {
        log.debug("REST request to get all GroundSticksDrives");
        return groundSticksDriveRepository.findAll();
    }

    /**
     * GET  /ground-sticks-drives/:id : get the "id" groundSticksDrive.
     *
     * @param id the id of the groundSticksDrive to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groundSticksDrive, or with status 404 (Not Found)
     */
    @GetMapping("/ground-sticks-drives/{id}")
    @Timed
    public ResponseEntity<GroundSticksDrive> getGroundSticksDrive(@PathVariable Long id) {
        log.debug("REST request to get GroundSticksDrive : {}", id);
        Optional<GroundSticksDrive> groundSticksDrive = groundSticksDriveRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(groundSticksDrive);
    }

    /**
     * DELETE  /ground-sticks-drives/:id : delete the "id" groundSticksDrive.
     *
     * @param id the id of the groundSticksDrive to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ground-sticks-drives/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroundSticksDrive(@PathVariable Long id) {
        log.debug("REST request to delete GroundSticksDrive : {}", id);

        groundSticksDriveRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
