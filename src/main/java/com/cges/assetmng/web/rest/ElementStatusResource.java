package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ElementStatus;
import com.cges.assetmng.repository.ElementStatusRepository;
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
 * REST controller for managing ElementStatus.
 */
@RestController
@RequestMapping("/api")
public class ElementStatusResource {

    private final Logger log = LoggerFactory.getLogger(ElementStatusResource.class);

    private static final String ENTITY_NAME = "elementStatus";

    private final ElementStatusRepository elementStatusRepository;

    public ElementStatusResource(ElementStatusRepository elementStatusRepository) {
        this.elementStatusRepository = elementStatusRepository;
    }

    /**
     * POST  /element-statuses : Create a new elementStatus.
     *
     * @param elementStatus the elementStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new elementStatus, or with status 400 (Bad Request) if the elementStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/element-statuses")
    @Timed
    public ResponseEntity<ElementStatus> createElementStatus(@RequestBody ElementStatus elementStatus) throws URISyntaxException {
        log.debug("REST request to save ElementStatus : {}", elementStatus);
        if (elementStatus.getId() != null) {
            throw new BadRequestAlertException("A new elementStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElementStatus result = elementStatusRepository.save(elementStatus);
        return ResponseEntity.created(new URI("/api/element-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /element-statuses : Updates an existing elementStatus.
     *
     * @param elementStatus the elementStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated elementStatus,
     * or with status 400 (Bad Request) if the elementStatus is not valid,
     * or with status 500 (Internal Server Error) if the elementStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/element-statuses")
    @Timed
    public ResponseEntity<ElementStatus> updateElementStatus(@RequestBody ElementStatus elementStatus) throws URISyntaxException {
        log.debug("REST request to update ElementStatus : {}", elementStatus);
        if (elementStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElementStatus result = elementStatusRepository.save(elementStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, elementStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /element-statuses : get all the elementStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of elementStatuses in body
     */
    @GetMapping("/element-statuses")
    @Timed
    public List<ElementStatus> getAllElementStatuses() {
        log.debug("REST request to get all ElementStatuses");
        return elementStatusRepository.findAll();
    }

    /**
     * GET  /element-statuses/:id : get the "id" elementStatus.
     *
     * @param id the id of the elementStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the elementStatus, or with status 404 (Not Found)
     */
    @GetMapping("/element-statuses/{id}")
    @Timed
    public ResponseEntity<ElementStatus> getElementStatus(@PathVariable Long id) {
        log.debug("REST request to get ElementStatus : {}", id);
        Optional<ElementStatus> elementStatus = elementStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(elementStatus);
    }

    /**
     * DELETE  /element-statuses/:id : delete the "id" elementStatus.
     *
     * @param id the id of the elementStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/element-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteElementStatus(@PathVariable Long id) {
        log.debug("REST request to delete ElementStatus : {}", id);

        elementStatusRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
