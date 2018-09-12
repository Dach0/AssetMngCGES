package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.DisconnectorDrive;
import com.cges.assetmng.repository.DisconnectorDriveRepository;
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
 * REST controller for managing DisconnectorDrive.
 */
@RestController
@RequestMapping("/api")
public class DisconnectorDriveResource {

    private final Logger log = LoggerFactory.getLogger(DisconnectorDriveResource.class);

    private static final String ENTITY_NAME = "disconnectorDrive";

    private final DisconnectorDriveRepository disconnectorDriveRepository;

    public DisconnectorDriveResource(DisconnectorDriveRepository disconnectorDriveRepository) {
        this.disconnectorDriveRepository = disconnectorDriveRepository;
    }

    /**
     * POST  /disconnector-drives : Create a new disconnectorDrive.
     *
     * @param disconnectorDrive the disconnectorDrive to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disconnectorDrive, or with status 400 (Bad Request) if the disconnectorDrive has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disconnector-drives")
    @Timed
    public ResponseEntity<DisconnectorDrive> createDisconnectorDrive(@RequestBody DisconnectorDrive disconnectorDrive) throws URISyntaxException {
        log.debug("REST request to save DisconnectorDrive : {}", disconnectorDrive);
        if (disconnectorDrive.getId() != null) {
            throw new BadRequestAlertException("A new disconnectorDrive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisconnectorDrive result = disconnectorDriveRepository.save(disconnectorDrive);
        return ResponseEntity.created(new URI("/api/disconnector-drives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disconnector-drives : Updates an existing disconnectorDrive.
     *
     * @param disconnectorDrive the disconnectorDrive to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disconnectorDrive,
     * or with status 400 (Bad Request) if the disconnectorDrive is not valid,
     * or with status 500 (Internal Server Error) if the disconnectorDrive couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disconnector-drives")
    @Timed
    public ResponseEntity<DisconnectorDrive> updateDisconnectorDrive(@RequestBody DisconnectorDrive disconnectorDrive) throws URISyntaxException {
        log.debug("REST request to update DisconnectorDrive : {}", disconnectorDrive);
        if (disconnectorDrive.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisconnectorDrive result = disconnectorDriveRepository.save(disconnectorDrive);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disconnectorDrive.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disconnector-drives : get all the disconnectorDrives.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disconnectorDrives in body
     */
    @GetMapping("/disconnector-drives")
    @Timed
    public List<DisconnectorDrive> getAllDisconnectorDrives() {
        log.debug("REST request to get all DisconnectorDrives");
        return disconnectorDriveRepository.findAll();
    }

    /**
     * GET  /disconnector-drives/:id : get the "id" disconnectorDrive.
     *
     * @param id the id of the disconnectorDrive to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disconnectorDrive, or with status 404 (Not Found)
     */
    @GetMapping("/disconnector-drives/{id}")
    @Timed
    public ResponseEntity<DisconnectorDrive> getDisconnectorDrive(@PathVariable Long id) {
        log.debug("REST request to get DisconnectorDrive : {}", id);
        Optional<DisconnectorDrive> disconnectorDrive = disconnectorDriveRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disconnectorDrive);
    }

    /**
     * DELETE  /disconnector-drives/:id : delete the "id" disconnectorDrive.
     *
     * @param id the id of the disconnectorDrive to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disconnector-drives/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisconnectorDrive(@PathVariable Long id) {
        log.debug("REST request to delete DisconnectorDrive : {}", id);

        disconnectorDriveRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
