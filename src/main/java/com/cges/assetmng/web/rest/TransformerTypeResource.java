package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.TransformerType;
import com.cges.assetmng.repository.TransformerTypeRepository;
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
 * REST controller for managing TransformerType.
 */
@RestController
@RequestMapping("/api")
public class TransformerTypeResource {

    private final Logger log = LoggerFactory.getLogger(TransformerTypeResource.class);

    private static final String ENTITY_NAME = "transformerType";

    private final TransformerTypeRepository transformerTypeRepository;

    public TransformerTypeResource(TransformerTypeRepository transformerTypeRepository) {
        this.transformerTypeRepository = transformerTypeRepository;
    }

    /**
     * POST  /transformer-types : Create a new transformerType.
     *
     * @param transformerType the transformerType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transformerType, or with status 400 (Bad Request) if the transformerType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transformer-types")
    @Timed
    public ResponseEntity<TransformerType> createTransformerType(@RequestBody TransformerType transformerType) throws URISyntaxException {
        log.debug("REST request to save TransformerType : {}", transformerType);
        if (transformerType.getId() != null) {
            throw new BadRequestAlertException("A new transformerType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransformerType result = transformerTypeRepository.save(transformerType);
        return ResponseEntity.created(new URI("/api/transformer-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transformer-types : Updates an existing transformerType.
     *
     * @param transformerType the transformerType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transformerType,
     * or with status 400 (Bad Request) if the transformerType is not valid,
     * or with status 500 (Internal Server Error) if the transformerType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transformer-types")
    @Timed
    public ResponseEntity<TransformerType> updateTransformerType(@RequestBody TransformerType transformerType) throws URISyntaxException {
        log.debug("REST request to update TransformerType : {}", transformerType);
        if (transformerType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransformerType result = transformerTypeRepository.save(transformerType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transformerType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transformer-types : get all the transformerTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of transformerTypes in body
     */
    @GetMapping("/transformer-types")
    @Timed
    public List<TransformerType> getAllTransformerTypes() {
        log.debug("REST request to get all TransformerTypes");
        return transformerTypeRepository.findAll();
    }

    /**
     * GET  /transformer-types/:id : get the "id" transformerType.
     *
     * @param id the id of the transformerType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transformerType, or with status 404 (Not Found)
     */
    @GetMapping("/transformer-types/{id}")
    @Timed
    public ResponseEntity<TransformerType> getTransformerType(@PathVariable Long id) {
        log.debug("REST request to get TransformerType : {}", id);
        Optional<TransformerType> transformerType = transformerTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transformerType);
    }

    /**
     * DELETE  /transformer-types/:id : delete the "id" transformerType.
     *
     * @param id the id of the transformerType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transformer-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransformerType(@PathVariable Long id) {
        log.debug("REST request to delete TransformerType : {}", id);

        transformerTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
