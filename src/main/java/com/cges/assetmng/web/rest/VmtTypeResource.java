package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.VmtType;
import com.cges.assetmng.repository.VmtTypeRepository;
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
 * REST controller for managing VmtType.
 */
@RestController
@RequestMapping("/api")
public class VmtTypeResource {

    private final Logger log = LoggerFactory.getLogger(VmtTypeResource.class);

    private static final String ENTITY_NAME = "vmtType";

    private final VmtTypeRepository vmtTypeRepository;

    public VmtTypeResource(VmtTypeRepository vmtTypeRepository) {
        this.vmtTypeRepository = vmtTypeRepository;
    }

    /**
     * POST  /vmt-types : Create a new vmtType.
     *
     * @param vmtType the vmtType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vmtType, or with status 400 (Bad Request) if the vmtType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vmt-types")
    @Timed
    public ResponseEntity<VmtType> createVmtType(@RequestBody VmtType vmtType) throws URISyntaxException {
        log.debug("REST request to save VmtType : {}", vmtType);
        if (vmtType.getId() != null) {
            throw new BadRequestAlertException("A new vmtType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VmtType result = vmtTypeRepository.save(vmtType);
        return ResponseEntity.created(new URI("/api/vmt-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vmt-types : Updates an existing vmtType.
     *
     * @param vmtType the vmtType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vmtType,
     * or with status 400 (Bad Request) if the vmtType is not valid,
     * or with status 500 (Internal Server Error) if the vmtType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vmt-types")
    @Timed
    public ResponseEntity<VmtType> updateVmtType(@RequestBody VmtType vmtType) throws URISyntaxException {
        log.debug("REST request to update VmtType : {}", vmtType);
        if (vmtType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VmtType result = vmtTypeRepository.save(vmtType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vmtType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vmt-types : get all the vmtTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vmtTypes in body
     */
    @GetMapping("/vmt-types")
    @Timed
    public List<VmtType> getAllVmtTypes() {
        log.debug("REST request to get all VmtTypes");
        return vmtTypeRepository.findAll();
    }

    /**
     * GET  /vmt-types/:id : get the "id" vmtType.
     *
     * @param id the id of the vmtType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vmtType, or with status 404 (Not Found)
     */
    @GetMapping("/vmt-types/{id}")
    @Timed
    public ResponseEntity<VmtType> getVmtType(@PathVariable Long id) {
        log.debug("REST request to get VmtType : {}", id);
        Optional<VmtType> vmtType = vmtTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vmtType);
    }

    /**
     * DELETE  /vmt-types/:id : delete the "id" vmtType.
     *
     * @param id the id of the vmtType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vmt-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteVmtType(@PathVariable Long id) {
        log.debug("REST request to delete VmtType : {}", id);

        vmtTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
