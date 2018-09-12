package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ServiceType;
import com.cges.assetmng.repository.ServiceTypeRepository;
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
 * REST controller for managing ServiceType.
 */
@RestController
@RequestMapping("/api")
public class ServiceTypeResource {

    private final Logger log = LoggerFactory.getLogger(ServiceTypeResource.class);

    private static final String ENTITY_NAME = "serviceType";

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeResource(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    /**
     * POST  /service-types : Create a new serviceType.
     *
     * @param serviceType the serviceType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceType, or with status 400 (Bad Request) if the serviceType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/service-types")
    @Timed
    public ResponseEntity<ServiceType> createServiceType(@RequestBody ServiceType serviceType) throws URISyntaxException {
        log.debug("REST request to save ServiceType : {}", serviceType);
        if (serviceType.getId() != null) {
            throw new BadRequestAlertException("A new serviceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceType result = serviceTypeRepository.save(serviceType);
        return ResponseEntity.created(new URI("/api/service-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /service-types : Updates an existing serviceType.
     *
     * @param serviceType the serviceType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceType,
     * or with status 400 (Bad Request) if the serviceType is not valid,
     * or with status 500 (Internal Server Error) if the serviceType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/service-types")
    @Timed
    public ResponseEntity<ServiceType> updateServiceType(@RequestBody ServiceType serviceType) throws URISyntaxException {
        log.debug("REST request to update ServiceType : {}", serviceType);
        if (serviceType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceType result = serviceTypeRepository.save(serviceType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /service-types : get all the serviceTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of serviceTypes in body
     */
    @GetMapping("/service-types")
    @Timed
    public List<ServiceType> getAllServiceTypes() {
        log.debug("REST request to get all ServiceTypes");
        return serviceTypeRepository.findAll();
    }

    /**
     * GET  /service-types/:id : get the "id" serviceType.
     *
     * @param id the id of the serviceType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceType, or with status 404 (Not Found)
     */
    @GetMapping("/service-types/{id}")
    @Timed
    public ResponseEntity<ServiceType> getServiceType(@PathVariable Long id) {
        log.debug("REST request to get ServiceType : {}", id);
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serviceType);
    }

    /**
     * DELETE  /service-types/:id : delete the "id" serviceType.
     *
     * @param id the id of the serviceType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/service-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        log.debug("REST request to delete ServiceType : {}", id);

        serviceTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
