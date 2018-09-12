package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.EmployeeGroup;
import com.cges.assetmng.repository.EmployeeGroupRepository;
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
 * REST controller for managing EmployeeGroup.
 */
@RestController
@RequestMapping("/api")
public class EmployeeGroupResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeGroupResource.class);

    private static final String ENTITY_NAME = "employeeGroup";

    private final EmployeeGroupRepository employeeGroupRepository;

    public EmployeeGroupResource(EmployeeGroupRepository employeeGroupRepository) {
        this.employeeGroupRepository = employeeGroupRepository;
    }

    /**
     * POST  /employee-groups : Create a new employeeGroup.
     *
     * @param employeeGroup the employeeGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeGroup, or with status 400 (Bad Request) if the employeeGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employee-groups")
    @Timed
    public ResponseEntity<EmployeeGroup> createEmployeeGroup(@Valid @RequestBody EmployeeGroup employeeGroup) throws URISyntaxException {
        log.debug("REST request to save EmployeeGroup : {}", employeeGroup);
        if (employeeGroup.getId() != null) {
            throw new BadRequestAlertException("A new employeeGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeGroup result = employeeGroupRepository.save(employeeGroup);
        return ResponseEntity.created(new URI("/api/employee-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /employee-groups : Updates an existing employeeGroup.
     *
     * @param employeeGroup the employeeGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeGroup,
     * or with status 400 (Bad Request) if the employeeGroup is not valid,
     * or with status 500 (Internal Server Error) if the employeeGroup couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employee-groups")
    @Timed
    public ResponseEntity<EmployeeGroup> updateEmployeeGroup(@Valid @RequestBody EmployeeGroup employeeGroup) throws URISyntaxException {
        log.debug("REST request to update EmployeeGroup : {}", employeeGroup);
        if (employeeGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeGroup result = employeeGroupRepository.save(employeeGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employee-groups : get all the employeeGroups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of employeeGroups in body
     */
    @GetMapping("/employee-groups")
    @Timed
    public List<EmployeeGroup> getAllEmployeeGroups() {
        log.debug("REST request to get all EmployeeGroups");
        return employeeGroupRepository.findAll();
    }

    /**
     * GET  /employee-groups/:id : get the "id" employeeGroup.
     *
     * @param id the id of the employeeGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeGroup, or with status 404 (Not Found)
     */
    @GetMapping("/employee-groups/{id}")
    @Timed
    public ResponseEntity<EmployeeGroup> getEmployeeGroup(@PathVariable Long id) {
        log.debug("REST request to get EmployeeGroup : {}", id);
        Optional<EmployeeGroup> employeeGroup = employeeGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(employeeGroup);
    }

    /**
     * DELETE  /employee-groups/:id : delete the "id" employeeGroup.
     *
     * @param id the id of the employeeGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employee-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeeGroup(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeGroup : {}", id);

        employeeGroupRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
   //ovo sam ja dodao da bih brisao iz tabele kada izaberem grupu iz aplikacije
    @RequestMapping(value = "/employee-groups/del-by-name/{groupName}", method = RequestMethod.DELETE)
    @Timed
    public ResponseEntity<Void> deleteEmployeeGroupByGroupName(@PathVariable("groupName") String grName) {
        log.debug("REST request to delete EmployeeGroup : {}", grName);

        employeeGroupRepository.deleteByGroupName(grName);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, grName)).build();
    }
    
}
