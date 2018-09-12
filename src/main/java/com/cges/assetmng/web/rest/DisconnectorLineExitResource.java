package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.DisconnectorLineExit;
import com.cges.assetmng.repository.DisconnectorLineExitRepository;
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
 * REST controller for managing DisconnectorLineExit.
 */
@RestController
@RequestMapping("/api")
public class DisconnectorLineExitResource {

    private final Logger log = LoggerFactory.getLogger(DisconnectorLineExitResource.class);

    private static final String ENTITY_NAME = "disconnectorLineExit";

    private final DisconnectorLineExitRepository disconnectorLineExitRepository;

    public DisconnectorLineExitResource(DisconnectorLineExitRepository disconnectorLineExitRepository) {
        this.disconnectorLineExitRepository = disconnectorLineExitRepository;
    }

    /**
     * POST  /disconnector-line-exits : Create a new disconnectorLineExit.
     *
     * @param disconnectorLineExit the disconnectorLineExit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disconnectorLineExit, or with status 400 (Bad Request) if the disconnectorLineExit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disconnector-line-exits")
    @Timed
    public ResponseEntity<DisconnectorLineExit> createDisconnectorLineExit(@Valid @RequestBody DisconnectorLineExit disconnectorLineExit) throws URISyntaxException {
        log.debug("REST request to save DisconnectorLineExit : {}", disconnectorLineExit);
        if (disconnectorLineExit.getId() != null) {
            throw new BadRequestAlertException("A new disconnectorLineExit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisconnectorLineExit result = disconnectorLineExitRepository.save(disconnectorLineExit);
        return ResponseEntity.created(new URI("/api/disconnector-line-exits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disconnector-line-exits : Updates an existing disconnectorLineExit.
     *
     * @param disconnectorLineExit the disconnectorLineExit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disconnectorLineExit,
     * or with status 400 (Bad Request) if the disconnectorLineExit is not valid,
     * or with status 500 (Internal Server Error) if the disconnectorLineExit couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disconnector-line-exits")
    @Timed
    public ResponseEntity<DisconnectorLineExit> updateDisconnectorLineExit(@Valid @RequestBody DisconnectorLineExit disconnectorLineExit) throws URISyntaxException {
        log.debug("REST request to update DisconnectorLineExit : {}", disconnectorLineExit);
        if (disconnectorLineExit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisconnectorLineExit result = disconnectorLineExitRepository.save(disconnectorLineExit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disconnectorLineExit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disconnector-line-exits : get all the disconnectorLineExits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disconnectorLineExits in body
     */
    @GetMapping("/disconnector-line-exits")
    @Timed
    public List<DisconnectorLineExit> getAllDisconnectorLineExits() {
        log.debug("REST request to get all DisconnectorLineExits");
        return disconnectorLineExitRepository.findAll();
    }

    /**
     * GET  /disconnector-line-exits/:id : get the "id" disconnectorLineExit.
     *
     * @param id the id of the disconnectorLineExit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disconnectorLineExit, or with status 404 (Not Found)
     */
    @GetMapping("/disconnector-line-exits/{id}")
    @Timed
    public ResponseEntity<DisconnectorLineExit> getDisconnectorLineExit(@PathVariable Long id) {
        log.debug("REST request to get DisconnectorLineExit : {}", id);
        Optional<DisconnectorLineExit> disconnectorLineExit = disconnectorLineExitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disconnectorLineExit);
    }

    /**
     * DELETE  /disconnector-line-exits/:id : delete the "id" disconnectorLineExit.
     *
     * @param id the id of the disconnectorLineExit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disconnector-line-exits/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisconnectorLineExit(@PathVariable Long id) {
        log.debug("REST request to delete DisconnectorLineExit : {}", id);

        disconnectorLineExitRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
