package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ElementCondition;
import com.cges.assetmng.repository.ElementConditionRepository;
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
 * REST controller for managing ElementCondition.
 */
@RestController
@RequestMapping("/api")
public class ElementConditionResource {

    private final Logger log = LoggerFactory.getLogger(ElementConditionResource.class);

    private static final String ENTITY_NAME = "elementCondition";

    private final ElementConditionRepository elementConditionRepository;

    public ElementConditionResource(ElementConditionRepository elementConditionRepository) {
        this.elementConditionRepository = elementConditionRepository;
    }

    /**
     * POST  /element-conditions : Create a new elementCondition.
     *
     * @param elementCondition the elementCondition to create
     * @return the ResponseEntity with status 201 (Created) and with body the new elementCondition, or with status 400 (Bad Request) if the elementCondition has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/element-conditions")
    @Timed
    public ResponseEntity<ElementCondition> createElementCondition(@RequestBody ElementCondition elementCondition) throws URISyntaxException {
        log.debug("REST request to save ElementCondition : {}", elementCondition);
        if (elementCondition.getId() != null) {
            throw new BadRequestAlertException("A new elementCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElementCondition result = elementConditionRepository.save(elementCondition);
        return ResponseEntity.created(new URI("/api/element-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /element-conditions : Updates an existing elementCondition.
     *
     * @param elementCondition the elementCondition to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated elementCondition,
     * or with status 400 (Bad Request) if the elementCondition is not valid,
     * or with status 500 (Internal Server Error) if the elementCondition couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/element-conditions")
    @Timed
    public ResponseEntity<ElementCondition> updateElementCondition(@RequestBody ElementCondition elementCondition) throws URISyntaxException {
        log.debug("REST request to update ElementCondition : {}", elementCondition);
        if (elementCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElementCondition result = elementConditionRepository.save(elementCondition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, elementCondition.getId().toString()))
            .body(result);
    }

    /**
     * GET  /element-conditions : get all the elementConditions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of elementConditions in body
     */
    @GetMapping("/element-conditions")
    @Timed
    public List<ElementCondition> getAllElementConditions() {
        log.debug("REST request to get all ElementConditions");
        return elementConditionRepository.findAll();
    }

    /**
     * GET  /element-conditions/:id : get the "id" elementCondition.
     *
     * @param id the id of the elementCondition to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the elementCondition, or with status 404 (Not Found)
     */
    @GetMapping("/element-conditions/{id}")
    @Timed
    public ResponseEntity<ElementCondition> getElementCondition(@PathVariable Long id) {
        log.debug("REST request to get ElementCondition : {}", id);
        Optional<ElementCondition> elementCondition = elementConditionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(elementCondition);
    }

    /**
     * DELETE  /element-conditions/:id : delete the "id" elementCondition.
     *
     * @param id the id of the elementCondition to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/element-conditions/{id}")
    @Timed
    public ResponseEntity<Void> deleteElementCondition(@PathVariable Long id) {
        log.debug("REST request to delete ElementCondition : {}", id);

        elementConditionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
