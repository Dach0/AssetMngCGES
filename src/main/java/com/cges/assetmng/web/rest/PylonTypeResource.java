package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.PylonType;
import com.cges.assetmng.repository.PylonTypeRepository;
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
 * REST controller for managing PylonType.
 */
@RestController
@RequestMapping("/api")
public class PylonTypeResource {

    private final Logger log = LoggerFactory.getLogger(PylonTypeResource.class);

    private static final String ENTITY_NAME = "pylonType";

    private final PylonTypeRepository pylonTypeRepository;

    public PylonTypeResource(PylonTypeRepository pylonTypeRepository) {
        this.pylonTypeRepository = pylonTypeRepository;
    }

    /**
     * POST  /pylon-types : Create a new pylonType.
     *
     * @param pylonType the pylonType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pylonType, or with status 400 (Bad Request) if the pylonType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pylon-types")
    @Timed
    public ResponseEntity<PylonType> createPylonType(@Valid @RequestBody PylonType pylonType) throws URISyntaxException {
        log.debug("REST request to save PylonType : {}", pylonType);
        if (pylonType.getId() != null) {
            throw new BadRequestAlertException("A new pylonType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PylonType result = pylonTypeRepository.save(pylonType);
        return ResponseEntity.created(new URI("/api/pylon-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pylon-types : Updates an existing pylonType.
     *
     * @param pylonType the pylonType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pylonType,
     * or with status 400 (Bad Request) if the pylonType is not valid,
     * or with status 500 (Internal Server Error) if the pylonType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pylon-types")
    @Timed
    public ResponseEntity<PylonType> updatePylonType(@Valid @RequestBody PylonType pylonType) throws URISyntaxException {
        log.debug("REST request to update PylonType : {}", pylonType);
        if (pylonType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PylonType result = pylonTypeRepository.save(pylonType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pylonType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pylon-types : get all the pylonTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pylonTypes in body
     */
    @GetMapping("/pylon-types")
    @Timed
    public List<PylonType> getAllPylonTypes() {
        log.debug("REST request to get all PylonTypes");
        return pylonTypeRepository.findAll();
    }

    /**
     * GET  /pylon-types/:id : get the "id" pylonType.
     *
     * @param id the id of the pylonType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pylonType, or with status 404 (Not Found)
     */
    @GetMapping("/pylon-types/{id}")
    @Timed
    public ResponseEntity<PylonType> getPylonType(@PathVariable Long id) {
        log.debug("REST request to get PylonType : {}", id);
        Optional<PylonType> pylonType = pylonTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pylonType);
    }

    /**
     * DELETE  /pylon-types/:id : delete the "id" pylonType.
     *
     * @param id the id of the pylonType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pylon-types/{id}")
    @Timed
    public ResponseEntity<Void> deletePylonType(@PathVariable Long id) {
        log.debug("REST request to delete PylonType : {}", id);

        pylonTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
