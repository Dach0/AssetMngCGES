package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.VoltageMeasuringTransformer;
import com.cges.assetmng.repository.VoltageMeasuringTransformerRepository;
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
 * REST controller for managing VoltageMeasuringTransformer.
 */
@RestController
@RequestMapping("/api")
public class VoltageMeasuringTransformerResource {

    private final Logger log = LoggerFactory.getLogger(VoltageMeasuringTransformerResource.class);

    private static final String ENTITY_NAME = "voltageMeasuringTransformer";

    private final VoltageMeasuringTransformerRepository voltageMeasuringTransformerRepository;

    public VoltageMeasuringTransformerResource(VoltageMeasuringTransformerRepository voltageMeasuringTransformerRepository) {
        this.voltageMeasuringTransformerRepository = voltageMeasuringTransformerRepository;
    }

    /**
     * POST  /voltage-measuring-transformers : Create a new voltageMeasuringTransformer.
     *
     * @param voltageMeasuringTransformer the voltageMeasuringTransformer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voltageMeasuringTransformer, or with status 400 (Bad Request) if the voltageMeasuringTransformer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/voltage-measuring-transformers")
    @Timed
    public ResponseEntity<VoltageMeasuringTransformer> createVoltageMeasuringTransformer(@Valid @RequestBody VoltageMeasuringTransformer voltageMeasuringTransformer) throws URISyntaxException {
        log.debug("REST request to save VoltageMeasuringTransformer : {}", voltageMeasuringTransformer);
        if (voltageMeasuringTransformer.getId() != null) {
            throw new BadRequestAlertException("A new voltageMeasuringTransformer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoltageMeasuringTransformer result = voltageMeasuringTransformerRepository.save(voltageMeasuringTransformer);
        return ResponseEntity.created(new URI("/api/voltage-measuring-transformers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /voltage-measuring-transformers : Updates an existing voltageMeasuringTransformer.
     *
     * @param voltageMeasuringTransformer the voltageMeasuringTransformer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voltageMeasuringTransformer,
     * or with status 400 (Bad Request) if the voltageMeasuringTransformer is not valid,
     * or with status 500 (Internal Server Error) if the voltageMeasuringTransformer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/voltage-measuring-transformers")
    @Timed
    public ResponseEntity<VoltageMeasuringTransformer> updateVoltageMeasuringTransformer(@Valid @RequestBody VoltageMeasuringTransformer voltageMeasuringTransformer) throws URISyntaxException {
        log.debug("REST request to update VoltageMeasuringTransformer : {}", voltageMeasuringTransformer);
        if (voltageMeasuringTransformer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VoltageMeasuringTransformer result = voltageMeasuringTransformerRepository.save(voltageMeasuringTransformer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voltageMeasuringTransformer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /voltage-measuring-transformers : get all the voltageMeasuringTransformers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of voltageMeasuringTransformers in body
     */
    @GetMapping("/voltage-measuring-transformers")
    @Timed
    public List<VoltageMeasuringTransformer> getAllVoltageMeasuringTransformers() {
        log.debug("REST request to get all VoltageMeasuringTransformers");
        return voltageMeasuringTransformerRepository.findAll();
    }

    /**
     * GET  /voltage-measuring-transformers/:id : get the "id" voltageMeasuringTransformer.
     *
     * @param id the id of the voltageMeasuringTransformer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voltageMeasuringTransformer, or with status 404 (Not Found)
     */
    @GetMapping("/voltage-measuring-transformers/{id}")
    @Timed
    public ResponseEntity<VoltageMeasuringTransformer> getVoltageMeasuringTransformer(@PathVariable Long id) {
        log.debug("REST request to get VoltageMeasuringTransformer : {}", id);
        Optional<VoltageMeasuringTransformer> voltageMeasuringTransformer = voltageMeasuringTransformerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(voltageMeasuringTransformer);
    }

    /**
     * DELETE  /voltage-measuring-transformers/:id : delete the "id" voltageMeasuringTransformer.
     *
     * @param id the id of the voltageMeasuringTransformer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/voltage-measuring-transformers/{id}")
    @Timed
    public ResponseEntity<Void> deleteVoltageMeasuringTransformer(@PathVariable Long id) {
        log.debug("REST request to delete VoltageMeasuringTransformer : {}", id);

        voltageMeasuringTransformerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
