package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CorrectionFactor;
import com.cges.assetmng.repository.CorrectionFactorRepository;
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
 * REST controller for managing CorrectionFactor.
 */
@RestController
@RequestMapping("/api")
public class CorrectionFactorResource {

    private final Logger log = LoggerFactory.getLogger(CorrectionFactorResource.class);

    private static final String ENTITY_NAME = "correctionFactor";

    private final CorrectionFactorRepository correctionFactorRepository;

    public CorrectionFactorResource(CorrectionFactorRepository correctionFactorRepository) {
        this.correctionFactorRepository = correctionFactorRepository;
    }

    /**
     * POST  /correction-factors : Create a new correctionFactor.
     *
     * @param correctionFactor the correctionFactor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new correctionFactor, or with status 400 (Bad Request) if the correctionFactor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/correction-factors")
    @Timed
    public ResponseEntity<CorrectionFactor> createCorrectionFactor(@Valid @RequestBody CorrectionFactor correctionFactor) throws URISyntaxException {
        log.debug("REST request to save CorrectionFactor : {}", correctionFactor);
        if (correctionFactor.getId() != null) {
            throw new BadRequestAlertException("A new correctionFactor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CorrectionFactor result = correctionFactorRepository.save(correctionFactor);
        return ResponseEntity.created(new URI("/api/correction-factors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /correction-factors : Updates an existing correctionFactor.
     *
     * @param correctionFactor the correctionFactor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated correctionFactor,
     * or with status 400 (Bad Request) if the correctionFactor is not valid,
     * or with status 500 (Internal Server Error) if the correctionFactor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/correction-factors")
    @Timed
    public ResponseEntity<CorrectionFactor> updateCorrectionFactor(@Valid @RequestBody CorrectionFactor correctionFactor) throws URISyntaxException {
        log.debug("REST request to update CorrectionFactor : {}", correctionFactor);
        if (correctionFactor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CorrectionFactor result = correctionFactorRepository.save(correctionFactor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, correctionFactor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /correction-factors : get all the correctionFactors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of correctionFactors in body
     */
    @GetMapping("/correction-factors")
    @Timed
    public List<CorrectionFactor> getAllCorrectionFactors() {
        log.debug("REST request to get all CorrectionFactors");
        return correctionFactorRepository.findAll();
    }

    /**
     * GET  /correction-factors/:id : get the "id" correctionFactor.
     *
     * @param id the id of the correctionFactor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the correctionFactor, or with status 404 (Not Found)
     */
    @GetMapping("/correction-factors/{id}")
    @Timed
    public ResponseEntity<CorrectionFactor> getCorrectionFactor(@PathVariable Long id) {
        log.debug("REST request to get CorrectionFactor : {}", id);
        Optional<CorrectionFactor> correctionFactor = correctionFactorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(correctionFactor);
    }

    /**
     * DELETE  /correction-factors/:id : delete the "id" correctionFactor.
     *
     * @param id the id of the correctionFactor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/correction-factors/{id}")
    @Timed
    public ResponseEntity<Void> deleteCorrectionFactor(@PathVariable Long id) {
        log.debug("REST request to delete CorrectionFactor : {}", id);

        correctionFactorRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
