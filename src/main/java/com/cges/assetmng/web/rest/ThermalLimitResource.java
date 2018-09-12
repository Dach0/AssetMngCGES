package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ThermalLimit;
import com.cges.assetmng.repository.ThermalLimitRepository;
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
 * REST controller for managing ThermalLimit.
 */
@RestController
@RequestMapping("/api")
public class ThermalLimitResource {

    private final Logger log = LoggerFactory.getLogger(ThermalLimitResource.class);

    private static final String ENTITY_NAME = "thermalLimit";

    private final ThermalLimitRepository thermalLimitRepository;

    public ThermalLimitResource(ThermalLimitRepository thermalLimitRepository) {
        this.thermalLimitRepository = thermalLimitRepository;
    }

    /**
     * POST  /thermal-limits : Create a new thermalLimit.
     *
     * @param thermalLimit the thermalLimit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new thermalLimit, or with status 400 (Bad Request) if the thermalLimit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/thermal-limits")
    @Timed
    public ResponseEntity<ThermalLimit> createThermalLimit(@Valid @RequestBody ThermalLimit thermalLimit) throws URISyntaxException {
        log.debug("REST request to save ThermalLimit : {}", thermalLimit);
        if (thermalLimit.getId() != null) {
            throw new BadRequestAlertException("A new thermalLimit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThermalLimit result = thermalLimitRepository.save(thermalLimit);
        return ResponseEntity.created(new URI("/api/thermal-limits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /thermal-limits : Updates an existing thermalLimit.
     *
     * @param thermalLimit the thermalLimit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated thermalLimit,
     * or with status 400 (Bad Request) if the thermalLimit is not valid,
     * or with status 500 (Internal Server Error) if the thermalLimit couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/thermal-limits")
    @Timed
    public ResponseEntity<ThermalLimit> updateThermalLimit(@Valid @RequestBody ThermalLimit thermalLimit) throws URISyntaxException {
        log.debug("REST request to update ThermalLimit : {}", thermalLimit);
        if (thermalLimit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThermalLimit result = thermalLimitRepository.save(thermalLimit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, thermalLimit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /thermal-limits : get all the thermalLimits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of thermalLimits in body
     */
    @GetMapping("/thermal-limits")
    @Timed
    public List<ThermalLimit> getAllThermalLimits() {
        log.debug("REST request to get all ThermalLimits");
        return thermalLimitRepository.findAll();
    }

    /**
     * GET  /thermal-limits/:id : get the "id" thermalLimit.
     *
     * @param id the id of the thermalLimit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the thermalLimit, or with status 404 (Not Found)
     */
    @GetMapping("/thermal-limits/{id}")
    @Timed
    public ResponseEntity<ThermalLimit> getThermalLimit(@PathVariable Long id) {
        log.debug("REST request to get ThermalLimit : {}", id);
        Optional<ThermalLimit> thermalLimit = thermalLimitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thermalLimit);
    }

    /**
     * DELETE  /thermal-limits/:id : delete the "id" thermalLimit.
     *
     * @param id the id of the thermalLimit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/thermal-limits/{id}")
    @Timed
    public ResponseEntity<Void> deleteThermalLimit(@PathVariable Long id) {
        log.debug("REST request to delete ThermalLimit : {}", id);

        thermalLimitRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
