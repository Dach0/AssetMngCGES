package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.CmtType;
import com.cges.assetmng.repository.CmtTypeRepository;
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
 * REST controller for managing CmtType.
 */
@RestController
@RequestMapping("/api")
public class CmtTypeResource {

    private final Logger log = LoggerFactory.getLogger(CmtTypeResource.class);

    private static final String ENTITY_NAME = "cmtType";

    private final CmtTypeRepository cmtTypeRepository;

    public CmtTypeResource(CmtTypeRepository cmtTypeRepository) {
        this.cmtTypeRepository = cmtTypeRepository;
    }

    /**
     * POST  /cmt-types : Create a new cmtType.
     *
     * @param cmtType the cmtType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cmtType, or with status 400 (Bad Request) if the cmtType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cmt-types")
    @Timed
    public ResponseEntity<CmtType> createCmtType(@RequestBody CmtType cmtType) throws URISyntaxException {
        log.debug("REST request to save CmtType : {}", cmtType);
        if (cmtType.getId() != null) {
            throw new BadRequestAlertException("A new cmtType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CmtType result = cmtTypeRepository.save(cmtType);
        return ResponseEntity.created(new URI("/api/cmt-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cmt-types : Updates an existing cmtType.
     *
     * @param cmtType the cmtType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cmtType,
     * or with status 400 (Bad Request) if the cmtType is not valid,
     * or with status 500 (Internal Server Error) if the cmtType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cmt-types")
    @Timed
    public ResponseEntity<CmtType> updateCmtType(@RequestBody CmtType cmtType) throws URISyntaxException {
        log.debug("REST request to update CmtType : {}", cmtType);
        if (cmtType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CmtType result = cmtTypeRepository.save(cmtType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmtType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cmt-types : get all the cmtTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cmtTypes in body
     */
    @GetMapping("/cmt-types")
    @Timed
    public List<CmtType> getAllCmtTypes() {
        log.debug("REST request to get all CmtTypes");
        return cmtTypeRepository.findAll();
    }

    /**
     * GET  /cmt-types/:id : get the "id" cmtType.
     *
     * @param id the id of the cmtType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cmtType, or with status 404 (Not Found)
     */
    @GetMapping("/cmt-types/{id}")
    @Timed
    public ResponseEntity<CmtType> getCmtType(@PathVariable Long id) {
        log.debug("REST request to get CmtType : {}", id);
        Optional<CmtType> cmtType = cmtTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cmtType);
    }

    /**
     * DELETE  /cmt-types/:id : delete the "id" cmtType.
     *
     * @param id the id of the cmtType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cmt-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCmtType(@PathVariable Long id) {
        log.debug("REST request to delete CmtType : {}", id);

        cmtTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
