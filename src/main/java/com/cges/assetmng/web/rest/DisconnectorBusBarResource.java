package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.DisconnectorBusBar;
import com.cges.assetmng.repository.DisconnectorBusBarRepository;
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
 * REST controller for managing DisconnectorBusBar.
 */
@RestController
@RequestMapping("/api")
public class DisconnectorBusBarResource {

    private final Logger log = LoggerFactory.getLogger(DisconnectorBusBarResource.class);

    private static final String ENTITY_NAME = "disconnectorBusBar";

    private final DisconnectorBusBarRepository disconnectorBusBarRepository;

    public DisconnectorBusBarResource(DisconnectorBusBarRepository disconnectorBusBarRepository) {
        this.disconnectorBusBarRepository = disconnectorBusBarRepository;
    }

    /**
     * POST  /disconnector-bus-bars : Create a new disconnectorBusBar.
     *
     * @param disconnectorBusBar the disconnectorBusBar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disconnectorBusBar, or with status 400 (Bad Request) if the disconnectorBusBar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disconnector-bus-bars")
    @Timed
    public ResponseEntity<DisconnectorBusBar> createDisconnectorBusBar(@Valid @RequestBody DisconnectorBusBar disconnectorBusBar) throws URISyntaxException {
        log.debug("REST request to save DisconnectorBusBar : {}", disconnectorBusBar);
        if (disconnectorBusBar.getId() != null) {
            throw new BadRequestAlertException("A new disconnectorBusBar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisconnectorBusBar result = disconnectorBusBarRepository.save(disconnectorBusBar);
        return ResponseEntity.created(new URI("/api/disconnector-bus-bars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disconnector-bus-bars : Updates an existing disconnectorBusBar.
     *
     * @param disconnectorBusBar the disconnectorBusBar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disconnectorBusBar,
     * or with status 400 (Bad Request) if the disconnectorBusBar is not valid,
     * or with status 500 (Internal Server Error) if the disconnectorBusBar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disconnector-bus-bars")
    @Timed
    public ResponseEntity<DisconnectorBusBar> updateDisconnectorBusBar(@Valid @RequestBody DisconnectorBusBar disconnectorBusBar) throws URISyntaxException {
        log.debug("REST request to update DisconnectorBusBar : {}", disconnectorBusBar);
        if (disconnectorBusBar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisconnectorBusBar result = disconnectorBusBarRepository.save(disconnectorBusBar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disconnectorBusBar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disconnector-bus-bars : get all the disconnectorBusBars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disconnectorBusBars in body
     */
    @GetMapping("/disconnector-bus-bars")
    @Timed
    public List<DisconnectorBusBar> getAllDisconnectorBusBars() {
        log.debug("REST request to get all DisconnectorBusBars");
        return disconnectorBusBarRepository.findAll();
    }

    /**
     * GET  /disconnector-bus-bars/:id : get the "id" disconnectorBusBar.
     *
     * @param id the id of the disconnectorBusBar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disconnectorBusBar, or with status 404 (Not Found)
     */
    @GetMapping("/disconnector-bus-bars/{id}")
    @Timed
    public ResponseEntity<DisconnectorBusBar> getDisconnectorBusBar(@PathVariable Long id) {
        log.debug("REST request to get DisconnectorBusBar : {}", id);
        Optional<DisconnectorBusBar> disconnectorBusBar = disconnectorBusBarRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disconnectorBusBar);
    }

    /**
     * DELETE  /disconnector-bus-bars/:id : delete the "id" disconnectorBusBar.
     *
     * @param id the id of the disconnectorBusBar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disconnector-bus-bars/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisconnectorBusBar(@PathVariable Long id) {
        log.debug("REST request to delete DisconnectorBusBar : {}", id);

        disconnectorBusBarRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
