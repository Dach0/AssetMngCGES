package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.BoardOfDirectors;
import com.cges.assetmng.repository.BoardOfDirectorsRepository;
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
 * REST controller for managing BoardOfDirectors.
 */
@RestController
@RequestMapping("/api")
public class BoardOfDirectorsResource {

    private final Logger log = LoggerFactory.getLogger(BoardOfDirectorsResource.class);

    private static final String ENTITY_NAME = "boardOfDirectors";

    private final BoardOfDirectorsRepository boardOfDirectorsRepository;

    public BoardOfDirectorsResource(BoardOfDirectorsRepository boardOfDirectorsRepository) {
        this.boardOfDirectorsRepository = boardOfDirectorsRepository;
    }

    /**
     * POST  /board-of-directors : Create a new boardOfDirectors.
     *
     * @param boardOfDirectors the boardOfDirectors to create
     * @return the ResponseEntity with status 201 (Created) and with body the new boardOfDirectors, or with status 400 (Bad Request) if the boardOfDirectors has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/board-of-directors")
    @Timed
    public ResponseEntity<BoardOfDirectors> createBoardOfDirectors(@RequestBody BoardOfDirectors boardOfDirectors) throws URISyntaxException {
        log.debug("REST request to save BoardOfDirectors : {}", boardOfDirectors);
        if (boardOfDirectors.getId() != null) {
            throw new BadRequestAlertException("A new boardOfDirectors cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoardOfDirectors result = boardOfDirectorsRepository.save(boardOfDirectors);
        return ResponseEntity.created(new URI("/api/board-of-directors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /board-of-directors : Updates an existing boardOfDirectors.
     *
     * @param boardOfDirectors the boardOfDirectors to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated boardOfDirectors,
     * or with status 400 (Bad Request) if the boardOfDirectors is not valid,
     * or with status 500 (Internal Server Error) if the boardOfDirectors couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/board-of-directors")
    @Timed
    public ResponseEntity<BoardOfDirectors> updateBoardOfDirectors(@RequestBody BoardOfDirectors boardOfDirectors) throws URISyntaxException {
        log.debug("REST request to update BoardOfDirectors : {}", boardOfDirectors);
        if (boardOfDirectors.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BoardOfDirectors result = boardOfDirectorsRepository.save(boardOfDirectors);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, boardOfDirectors.getId().toString()))
            .body(result);
    }

    /**
     * GET  /board-of-directors : get all the boardOfDirectors.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of boardOfDirectors in body
     */
    @GetMapping("/board-of-directors")
    @Timed
    public List<BoardOfDirectors> getAllBoardOfDirectors(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all BoardOfDirectors");
        return boardOfDirectorsRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /board-of-directors/:id : get the "id" boardOfDirectors.
     *
     * @param id the id of the boardOfDirectors to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the boardOfDirectors, or with status 404 (Not Found)
     */
    @GetMapping("/board-of-directors/{id}")
    @Timed
    public ResponseEntity<BoardOfDirectors> getBoardOfDirectors(@PathVariable Long id) {
        log.debug("REST request to get BoardOfDirectors : {}", id);
        Optional<BoardOfDirectors> boardOfDirectors = boardOfDirectorsRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(boardOfDirectors);
    }

    /**
     * DELETE  /board-of-directors/:id : delete the "id" boardOfDirectors.
     *
     * @param id the id of the boardOfDirectors to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/board-of-directors/{id}")
    @Timed
    public ResponseEntity<Void> deleteBoardOfDirectors(@PathVariable Long id) {
        log.debug("REST request to delete BoardOfDirectors : {}", id);

        boardOfDirectorsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
