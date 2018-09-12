package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.GroundingSticks;
import com.cges.assetmng.repository.GroundingSticksRepository;
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
 * REST controller for managing GroundingSticks.
 */
@RestController
@RequestMapping("/api")
public class GroundingSticksResource {

    private final Logger log = LoggerFactory.getLogger(GroundingSticksResource.class);

    private static final String ENTITY_NAME = "groundingSticks";

    private final GroundingSticksRepository groundingSticksRepository;

    public GroundingSticksResource(GroundingSticksRepository groundingSticksRepository) {
        this.groundingSticksRepository = groundingSticksRepository;
    }

    /**
     * POST  /grounding-sticks : Create a new groundingSticks.
     *
     * @param groundingSticks the groundingSticks to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groundingSticks, or with status 400 (Bad Request) if the groundingSticks has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grounding-sticks")
    @Timed
    public ResponseEntity<GroundingSticks> createGroundingSticks(@Valid @RequestBody GroundingSticks groundingSticks) throws URISyntaxException {
        log.debug("REST request to save GroundingSticks : {}", groundingSticks);
        if (groundingSticks.getId() != null) {
            throw new BadRequestAlertException("A new groundingSticks cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroundingSticks result = groundingSticksRepository.save(groundingSticks);
        return ResponseEntity.created(new URI("/api/grounding-sticks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grounding-sticks : Updates an existing groundingSticks.
     *
     * @param groundingSticks the groundingSticks to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groundingSticks,
     * or with status 400 (Bad Request) if the groundingSticks is not valid,
     * or with status 500 (Internal Server Error) if the groundingSticks couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grounding-sticks")
    @Timed
    public ResponseEntity<GroundingSticks> updateGroundingSticks(@Valid @RequestBody GroundingSticks groundingSticks) throws URISyntaxException {
        log.debug("REST request to update GroundingSticks : {}", groundingSticks);
        if (groundingSticks.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroundingSticks result = groundingSticksRepository.save(groundingSticks);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groundingSticks.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grounding-sticks : get all the groundingSticks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of groundingSticks in body
     */
    @GetMapping("/grounding-sticks")
    @Timed
    public List<GroundingSticks> getAllGroundingSticks() {
        log.debug("REST request to get all GroundingSticks");
        return groundingSticksRepository.findAll();
    }

    /**
     * GET  /grounding-sticks/:id : get the "id" groundingSticks.
     *
     * @param id the id of the groundingSticks to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groundingSticks, or with status 404 (Not Found)
     */
    @GetMapping("/grounding-sticks/{id}")
    @Timed
    public ResponseEntity<GroundingSticks> getGroundingSticks(@PathVariable Long id) {
        log.debug("REST request to get GroundingSticks : {}", id);
        Optional<GroundingSticks> groundingSticks = groundingSticksRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(groundingSticks);
    }

    /**
     * DELETE  /grounding-sticks/:id : delete the "id" groundingSticks.
     *
     * @param id the id of the groundingSticks to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grounding-sticks/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroundingSticks(@PathVariable Long id) {
        log.debug("REST request to delete GroundingSticks : {}", id);

        groundingSticksRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
