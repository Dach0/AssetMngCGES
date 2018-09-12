package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.TitleInBoard;
import com.cges.assetmng.repository.TitleInBoardRepository;
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
 * REST controller for managing TitleInBoard.
 */
@RestController
@RequestMapping("/api")
public class TitleInBoardResource {

    private final Logger log = LoggerFactory.getLogger(TitleInBoardResource.class);

    private static final String ENTITY_NAME = "titleInBoard";

    private final TitleInBoardRepository titleInBoardRepository;

    public TitleInBoardResource(TitleInBoardRepository titleInBoardRepository) {
        this.titleInBoardRepository = titleInBoardRepository;
    }

    /**
     * POST  /title-in-boards : Create a new titleInBoard.
     *
     * @param titleInBoard the titleInBoard to create
     * @return the ResponseEntity with status 201 (Created) and with body the new titleInBoard, or with status 400 (Bad Request) if the titleInBoard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/title-in-boards")
    @Timed
    public ResponseEntity<TitleInBoard> createTitleInBoard(@RequestBody TitleInBoard titleInBoard) throws URISyntaxException {
        log.debug("REST request to save TitleInBoard : {}", titleInBoard);
        if (titleInBoard.getId() != null) {
            throw new BadRequestAlertException("A new titleInBoard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TitleInBoard result = titleInBoardRepository.save(titleInBoard);
        return ResponseEntity.created(new URI("/api/title-in-boards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /title-in-boards : Updates an existing titleInBoard.
     *
     * @param titleInBoard the titleInBoard to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated titleInBoard,
     * or with status 400 (Bad Request) if the titleInBoard is not valid,
     * or with status 500 (Internal Server Error) if the titleInBoard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/title-in-boards")
    @Timed
    public ResponseEntity<TitleInBoard> updateTitleInBoard(@RequestBody TitleInBoard titleInBoard) throws URISyntaxException {
        log.debug("REST request to update TitleInBoard : {}", titleInBoard);
        if (titleInBoard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TitleInBoard result = titleInBoardRepository.save(titleInBoard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, titleInBoard.getId().toString()))
            .body(result);
    }

    /**
     * GET  /title-in-boards : get all the titleInBoards.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of titleInBoards in body
     */
    @GetMapping("/title-in-boards")
    @Timed
    public List<TitleInBoard> getAllTitleInBoards() {
        log.debug("REST request to get all TitleInBoards");
        return titleInBoardRepository.findAll();
    }

    /**
     * GET  /title-in-boards/:id : get the "id" titleInBoard.
     *
     * @param id the id of the titleInBoard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the titleInBoard, or with status 404 (Not Found)
     */
    @GetMapping("/title-in-boards/{id}")
    @Timed
    public ResponseEntity<TitleInBoard> getTitleInBoard(@PathVariable Long id) {
        log.debug("REST request to get TitleInBoard : {}", id);
        Optional<TitleInBoard> titleInBoard = titleInBoardRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(titleInBoard);
    }

    /**
     * DELETE  /title-in-boards/:id : delete the "id" titleInBoard.
     *
     * @param id the id of the titleInBoard to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/title-in-boards/{id}")
    @Timed
    public ResponseEntity<Void> deleteTitleInBoard(@PathVariable Long id) {
        log.debug("REST request to delete TitleInBoard : {}", id);

        titleInBoardRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
