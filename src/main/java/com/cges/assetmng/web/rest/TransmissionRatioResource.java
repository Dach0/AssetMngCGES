package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.TransmissionRatio;
import com.cges.assetmng.repository.TransmissionRatioRepository;
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
 * REST controller for managing TransmissionRatio.
 */
@RestController
@RequestMapping("/api")
public class TransmissionRatioResource {

    private final Logger log = LoggerFactory.getLogger(TransmissionRatioResource.class);

    private static final String ENTITY_NAME = "transmissionRatio";

    private final TransmissionRatioRepository transmissionRatioRepository;

    public TransmissionRatioResource(TransmissionRatioRepository transmissionRatioRepository) {
        this.transmissionRatioRepository = transmissionRatioRepository;
    }

    /**
     * POST  /transmission-ratios : Create a new transmissionRatio.
     *
     * @param transmissionRatio the transmissionRatio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transmissionRatio, or with status 400 (Bad Request) if the transmissionRatio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transmission-ratios")
    @Timed
    public ResponseEntity<TransmissionRatio> createTransmissionRatio(@RequestBody TransmissionRatio transmissionRatio) throws URISyntaxException {
        log.debug("REST request to save TransmissionRatio : {}", transmissionRatio);
        if (transmissionRatio.getId() != null) {
            throw new BadRequestAlertException("A new transmissionRatio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransmissionRatio result = transmissionRatioRepository.save(transmissionRatio);
        return ResponseEntity.created(new URI("/api/transmission-ratios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transmission-ratios : Updates an existing transmissionRatio.
     *
     * @param transmissionRatio the transmissionRatio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transmissionRatio,
     * or with status 400 (Bad Request) if the transmissionRatio is not valid,
     * or with status 500 (Internal Server Error) if the transmissionRatio couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transmission-ratios")
    @Timed
    public ResponseEntity<TransmissionRatio> updateTransmissionRatio(@RequestBody TransmissionRatio transmissionRatio) throws URISyntaxException {
        log.debug("REST request to update TransmissionRatio : {}", transmissionRatio);
        if (transmissionRatio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransmissionRatio result = transmissionRatioRepository.save(transmissionRatio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transmissionRatio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transmission-ratios : get all the transmissionRatios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of transmissionRatios in body
     */
    @GetMapping("/transmission-ratios")
    @Timed
    public List<TransmissionRatio> getAllTransmissionRatios() {
        log.debug("REST request to get all TransmissionRatios");
        return transmissionRatioRepository.findAll();
    }

    /**
     * GET  /transmission-ratios/:id : get the "id" transmissionRatio.
     *
     * @param id the id of the transmissionRatio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transmissionRatio, or with status 404 (Not Found)
     */
    @GetMapping("/transmission-ratios/{id}")
    @Timed
    public ResponseEntity<TransmissionRatio> getTransmissionRatio(@PathVariable Long id) {
        log.debug("REST request to get TransmissionRatio : {}", id);
        Optional<TransmissionRatio> transmissionRatio = transmissionRatioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transmissionRatio);
    }

    /**
     * DELETE  /transmission-ratios/:id : delete the "id" transmissionRatio.
     *
     * @param id the id of the transmissionRatio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transmission-ratios/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransmissionRatio(@PathVariable Long id) {
        log.debug("REST request to delete TransmissionRatio : {}", id);

        transmissionRatioRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
