package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.TransformatorNumber;
import com.cges.assetmng.repository.TransformatorNumberRepository;
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
 * REST controller for managing TransformatorNumber.
 */
@RestController
@RequestMapping("/api")
public class TransformatorNumberResource {

    private final Logger log = LoggerFactory.getLogger(TransformatorNumberResource.class);

    private static final String ENTITY_NAME = "transformatorNumber";

    private final TransformatorNumberRepository transformatorNumberRepository;

    public TransformatorNumberResource(TransformatorNumberRepository transformatorNumberRepository) {
        this.transformatorNumberRepository = transformatorNumberRepository;
    }

    /**
     * POST  /transformator-numbers : Create a new transformatorNumber.
     *
     * @param transformatorNumber the transformatorNumber to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transformatorNumber, or with status 400 (Bad Request) if the transformatorNumber has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transformator-numbers")
    @Timed
    public ResponseEntity<TransformatorNumber> createTransformatorNumber(@RequestBody TransformatorNumber transformatorNumber) throws URISyntaxException {
        log.debug("REST request to save TransformatorNumber : {}", transformatorNumber);
        if (transformatorNumber.getId() != null) {
            throw new BadRequestAlertException("A new transformatorNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransformatorNumber result = transformatorNumberRepository.save(transformatorNumber);
        return ResponseEntity.created(new URI("/api/transformator-numbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transformator-numbers : Updates an existing transformatorNumber.
     *
     * @param transformatorNumber the transformatorNumber to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transformatorNumber,
     * or with status 400 (Bad Request) if the transformatorNumber is not valid,
     * or with status 500 (Internal Server Error) if the transformatorNumber couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transformator-numbers")
    @Timed
    public ResponseEntity<TransformatorNumber> updateTransformatorNumber(@RequestBody TransformatorNumber transformatorNumber) throws URISyntaxException {
        log.debug("REST request to update TransformatorNumber : {}", transformatorNumber);
        if (transformatorNumber.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransformatorNumber result = transformatorNumberRepository.save(transformatorNumber);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transformatorNumber.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transformator-numbers : get all the transformatorNumbers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of transformatorNumbers in body
     */
    @GetMapping("/transformator-numbers")
    @Timed
    public List<TransformatorNumber> getAllTransformatorNumbers() {
        log.debug("REST request to get all TransformatorNumbers");
        return transformatorNumberRepository.findAll();
    }

    /**
     * GET  /transformator-numbers/:id : get the "id" transformatorNumber.
     *
     * @param id the id of the transformatorNumber to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transformatorNumber, or with status 404 (Not Found)
     */
    @GetMapping("/transformator-numbers/{id}")
    @Timed
    public ResponseEntity<TransformatorNumber> getTransformatorNumber(@PathVariable Long id) {
        log.debug("REST request to get TransformatorNumber : {}", id);
        Optional<TransformatorNumber> transformatorNumber = transformatorNumberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transformatorNumber);
    }

    /**
     * DELETE  /transformator-numbers/:id : delete the "id" transformatorNumber.
     *
     * @param id the id of the transformatorNumber to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transformator-numbers/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransformatorNumber(@PathVariable Long id) {
        log.debug("REST request to delete TransformatorNumber : {}", id);

        transformatorNumberRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
