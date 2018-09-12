package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CurrentMeasuringTransformer;
import com.cges.assetmng.repository.CurrentMeasuringTransformerRepository;
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
 * REST controller for managing CurrentMeasuringTransformer.
 */
@RestController
@RequestMapping("/api")
public class CurrentMeasuringTransformerResource {

    private final Logger log = LoggerFactory.getLogger(CurrentMeasuringTransformerResource.class);

    private static final String ENTITY_NAME = "currentMeasuringTransformer";

    private final CurrentMeasuringTransformerRepository currentMeasuringTransformerRepository;

    public CurrentMeasuringTransformerResource(CurrentMeasuringTransformerRepository currentMeasuringTransformerRepository) {
        this.currentMeasuringTransformerRepository = currentMeasuringTransformerRepository;
    }

    /**
     * POST  /current-measuring-transformers : Create a new currentMeasuringTransformer.
     *
     * @param currentMeasuringTransformer the currentMeasuringTransformer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new currentMeasuringTransformer, or with status 400 (Bad Request) if the currentMeasuringTransformer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/current-measuring-transformers")
    @Timed
    public ResponseEntity<CurrentMeasuringTransformer> createCurrentMeasuringTransformer(@Valid @RequestBody CurrentMeasuringTransformer currentMeasuringTransformer) throws URISyntaxException {
        log.debug("REST request to save CurrentMeasuringTransformer : {}", currentMeasuringTransformer);
        if (currentMeasuringTransformer.getId() != null) {
            throw new BadRequestAlertException("A new currentMeasuringTransformer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CurrentMeasuringTransformer result = currentMeasuringTransformerRepository.save(currentMeasuringTransformer);
        return ResponseEntity.created(new URI("/api/current-measuring-transformers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /current-measuring-transformers : Updates an existing currentMeasuringTransformer.
     *
     * @param currentMeasuringTransformer the currentMeasuringTransformer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated currentMeasuringTransformer,
     * or with status 400 (Bad Request) if the currentMeasuringTransformer is not valid,
     * or with status 500 (Internal Server Error) if the currentMeasuringTransformer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/current-measuring-transformers")
    @Timed
    public ResponseEntity<CurrentMeasuringTransformer> updateCurrentMeasuringTransformer(@Valid @RequestBody CurrentMeasuringTransformer currentMeasuringTransformer) throws URISyntaxException {
        log.debug("REST request to update CurrentMeasuringTransformer : {}", currentMeasuringTransformer);
        if (currentMeasuringTransformer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CurrentMeasuringTransformer result = currentMeasuringTransformerRepository.save(currentMeasuringTransformer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, currentMeasuringTransformer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /current-measuring-transformers : get all the currentMeasuringTransformers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of currentMeasuringTransformers in body
     */
    @GetMapping("/current-measuring-transformers")
    @Timed
    public List<CurrentMeasuringTransformer> getAllCurrentMeasuringTransformers() {
        log.debug("REST request to get all CurrentMeasuringTransformers");
        return currentMeasuringTransformerRepository.findAll();
    }

    /**
     * GET  /current-measuring-transformers/:id : get the "id" currentMeasuringTransformer.
     *
     * @param id the id of the currentMeasuringTransformer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the currentMeasuringTransformer, or with status 404 (Not Found)
     */
    @GetMapping("/current-measuring-transformers/{id}")
    @Timed
    public ResponseEntity<CurrentMeasuringTransformer> getCurrentMeasuringTransformer(@PathVariable Long id) {
        log.debug("REST request to get CurrentMeasuringTransformer : {}", id);
        Optional<CurrentMeasuringTransformer> currentMeasuringTransformer = currentMeasuringTransformerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(currentMeasuringTransformer);
    }

    /**
     * DELETE  /current-measuring-transformers/:id : delete the "id" currentMeasuringTransformer.
     *
     * @param id the id of the currentMeasuringTransformer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/current-measuring-transformers/{id}")
    @Timed
    public ResponseEntity<Void> deleteCurrentMeasuringTransformer(@PathVariable Long id) {
        log.debug("REST request to delete CurrentMeasuringTransformer : {}", id);

        currentMeasuringTransformerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
