package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.Power;
import com.cges.assetmng.repository.PowerRepository;
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
 * REST controller for managing Power.
 */
@RestController
@RequestMapping("/api")
public class PowerResource {

    private final Logger log = LoggerFactory.getLogger(PowerResource.class);

    private static final String ENTITY_NAME = "power";

    private final PowerRepository powerRepository;

    public PowerResource(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    /**
     * POST  /powers : Create a new power.
     *
     * @param power the power to create
     * @return the ResponseEntity with status 201 (Created) and with body the new power, or with status 400 (Bad Request) if the power has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/powers")
    @Timed
    public ResponseEntity<Power> createPower(@RequestBody Power power) throws URISyntaxException {
        log.debug("REST request to save Power : {}", power);
        if (power.getId() != null) {
            throw new BadRequestAlertException("A new power cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Power result = powerRepository.save(power);
        return ResponseEntity.created(new URI("/api/powers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /powers : Updates an existing power.
     *
     * @param power the power to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated power,
     * or with status 400 (Bad Request) if the power is not valid,
     * or with status 500 (Internal Server Error) if the power couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/powers")
    @Timed
    public ResponseEntity<Power> updatePower(@RequestBody Power power) throws URISyntaxException {
        log.debug("REST request to update Power : {}", power);
        if (power.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Power result = powerRepository.save(power);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, power.getId().toString()))
            .body(result);
    }

    /**
     * GET  /powers : get all the powers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of powers in body
     */
    @GetMapping("/powers")
    @Timed
    public List<Power> getAllPowers() {
        log.debug("REST request to get all Powers");
        return powerRepository.findAll();
    }

    /**
     * GET  /powers/:id : get the "id" power.
     *
     * @param id the id of the power to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the power, or with status 404 (Not Found)
     */
    @GetMapping("/powers/{id}")
    @Timed
    public ResponseEntity<Power> getPower(@PathVariable Long id) {
        log.debug("REST request to get Power : {}", id);
        Optional<Power> power = powerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(power);
    }

    /**
     * DELETE  /powers/:id : delete the "id" power.
     *
     * @param id the id of the power to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/powers/{id}")
    @Timed
    public ResponseEntity<Void> deletePower(@PathVariable Long id) {
        log.debug("REST request to delete Power : {}", id);

        powerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
