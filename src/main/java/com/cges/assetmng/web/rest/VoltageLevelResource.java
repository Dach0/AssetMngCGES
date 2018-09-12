package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.VoltageLevel;
import com.cges.assetmng.repository.VoltageLevelRepository;
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
 * REST controller for managing VoltageLevel.
 */
@RestController
@RequestMapping("/api")
public class VoltageLevelResource {

    private final Logger log = LoggerFactory.getLogger(VoltageLevelResource.class);

    private static final String ENTITY_NAME = "voltageLevel";

    private final VoltageLevelRepository voltageLevelRepository;

    public VoltageLevelResource(VoltageLevelRepository voltageLevelRepository) {
        this.voltageLevelRepository = voltageLevelRepository;
    }

    /**
     * POST  /voltage-levels : Create a new voltageLevel.
     *
     * @param voltageLevel the voltageLevel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voltageLevel, or with status 400 (Bad Request) if the voltageLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/voltage-levels")
    @Timed
    public ResponseEntity<VoltageLevel> createVoltageLevel(@Valid @RequestBody VoltageLevel voltageLevel) throws URISyntaxException {
        log.debug("REST request to save VoltageLevel : {}", voltageLevel);
        if (voltageLevel.getId() != null) {
            throw new BadRequestAlertException("A new voltageLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoltageLevel result = voltageLevelRepository.save(voltageLevel);
        return ResponseEntity.created(new URI("/api/voltage-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /voltage-levels : Updates an existing voltageLevel.
     *
     * @param voltageLevel the voltageLevel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voltageLevel,
     * or with status 400 (Bad Request) if the voltageLevel is not valid,
     * or with status 500 (Internal Server Error) if the voltageLevel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/voltage-levels")
    @Timed
    public ResponseEntity<VoltageLevel> updateVoltageLevel(@Valid @RequestBody VoltageLevel voltageLevel) throws URISyntaxException {
        log.debug("REST request to update VoltageLevel : {}", voltageLevel);
        if (voltageLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VoltageLevel result = voltageLevelRepository.save(voltageLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voltageLevel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /voltage-levels : get all the voltageLevels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of voltageLevels in body
     */
    @GetMapping("/voltage-levels")
    @Timed
    public List<VoltageLevel> getAllVoltageLevels() {
        log.debug("REST request to get all VoltageLevels");
        return voltageLevelRepository.findAll();
    }

    /**
     * GET  /voltage-levels/:id : get the "id" voltageLevel.
     *
     * @param id the id of the voltageLevel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voltageLevel, or with status 404 (Not Found)
     */
    @GetMapping("/voltage-levels/{id}")
    @Timed
    public ResponseEntity<VoltageLevel> getVoltageLevel(@PathVariable Long id) {
        log.debug("REST request to get VoltageLevel : {}", id);
        Optional<VoltageLevel> voltageLevel = voltageLevelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(voltageLevel);
    }

    /**
     * DELETE  /voltage-levels/:id : delete the "id" voltageLevel.
     *
     * @param id the id of the voltageLevel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/voltage-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteVoltageLevel(@PathVariable Long id) {
        log.debug("REST request to delete VoltageLevel : {}", id);

        voltageLevelRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
