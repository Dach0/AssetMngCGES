package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.BoardMember;
import com.cges.assetmng.repository.BoardMemberRepository;
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
 * REST controller for managing BoardMember.
 */
@RestController
@RequestMapping("/api")
public class BoardMemberResource {

    private final Logger log = LoggerFactory.getLogger(BoardMemberResource.class);

    private static final String ENTITY_NAME = "boardMember";

    private final BoardMemberRepository boardMemberRepository;

    public BoardMemberResource(BoardMemberRepository boardMemberRepository) {
        this.boardMemberRepository = boardMemberRepository;
    }

    /**
     * POST  /board-members : Create a new boardMember.
     *
     * @param boardMember the boardMember to create
     * @return the ResponseEntity with status 201 (Created) and with body the new boardMember, or with status 400 (Bad Request) if the boardMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/board-members")
    @Timed
    public ResponseEntity<BoardMember> createBoardMember(@Valid @RequestBody BoardMember boardMember) throws URISyntaxException {
        log.debug("REST request to save BoardMember : {}", boardMember);
        if (boardMember.getId() != null) {
            throw new BadRequestAlertException("A new boardMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoardMember result = boardMemberRepository.save(boardMember);
        return ResponseEntity.created(new URI("/api/board-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /board-members : Updates an existing boardMember.
     *
     * @param boardMember the boardMember to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated boardMember,
     * or with status 400 (Bad Request) if the boardMember is not valid,
     * or with status 500 (Internal Server Error) if the boardMember couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/board-members")
    @Timed
    public ResponseEntity<BoardMember> updateBoardMember(@Valid @RequestBody BoardMember boardMember) throws URISyntaxException {
        log.debug("REST request to update BoardMember : {}", boardMember);
        if (boardMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BoardMember result = boardMemberRepository.save(boardMember);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, boardMember.getId().toString()))
            .body(result);
    }

    /**
     * GET  /board-members : get all the boardMembers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of boardMembers in body
     */
    @GetMapping("/board-members")
    @Timed
    public List<BoardMember> getAllBoardMembers() {
        log.debug("REST request to get all BoardMembers");
        return boardMemberRepository.findAll();
    }

    /**
     * GET  /board-members/:id : get the "id" boardMember.
     *
     * @param id the id of the boardMember to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the boardMember, or with status 404 (Not Found)
     */
    @GetMapping("/board-members/{id}")
    @Timed
    public ResponseEntity<BoardMember> getBoardMember(@PathVariable Long id) {
        log.debug("REST request to get BoardMember : {}", id);
        Optional<BoardMember> boardMember = boardMemberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(boardMember);
    }

    /**
     * DELETE  /board-members/:id : delete the "id" boardMember.
     *
     * @param id the id of the boardMember to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/board-members/{id}")
    @Timed
    public ResponseEntity<Void> deleteBoardMember(@PathVariable Long id) {
        log.debug("REST request to delete BoardMember : {}", id);

        boardMemberRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
