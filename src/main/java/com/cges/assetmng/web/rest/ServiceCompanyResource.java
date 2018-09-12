package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.ServiceCompany;
import com.cges.assetmng.repository.ServiceCompanyRepository;
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
 * REST controller for managing ServiceCompany.
 */
@RestController
@RequestMapping("/api")
public class ServiceCompanyResource {

    private final Logger log = LoggerFactory.getLogger(ServiceCompanyResource.class);

    private static final String ENTITY_NAME = "serviceCompany";

    private final ServiceCompanyRepository serviceCompanyRepository;

    public ServiceCompanyResource(ServiceCompanyRepository serviceCompanyRepository) {
        this.serviceCompanyRepository = serviceCompanyRepository;
    }

    /**
     * POST  /service-companies : Create a new serviceCompany.
     *
     * @param serviceCompany the serviceCompany to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceCompany, or with status 400 (Bad Request) if the serviceCompany has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/service-companies")
    @Timed
    public ResponseEntity<ServiceCompany> createServiceCompany(@RequestBody ServiceCompany serviceCompany) throws URISyntaxException {
        log.debug("REST request to save ServiceCompany : {}", serviceCompany);
        if (serviceCompany.getId() != null) {
            throw new BadRequestAlertException("A new serviceCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceCompany result = serviceCompanyRepository.save(serviceCompany);
        return ResponseEntity.created(new URI("/api/service-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /service-companies : Updates an existing serviceCompany.
     *
     * @param serviceCompany the serviceCompany to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceCompany,
     * or with status 400 (Bad Request) if the serviceCompany is not valid,
     * or with status 500 (Internal Server Error) if the serviceCompany couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/service-companies")
    @Timed
    public ResponseEntity<ServiceCompany> updateServiceCompany(@RequestBody ServiceCompany serviceCompany) throws URISyntaxException {
        log.debug("REST request to update ServiceCompany : {}", serviceCompany);
        if (serviceCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceCompany result = serviceCompanyRepository.save(serviceCompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceCompany.getId().toString()))
            .body(result);
    }

    /**
     * GET  /service-companies : get all the serviceCompanies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of serviceCompanies in body
     */
    @GetMapping("/service-companies")
    @Timed
    public List<ServiceCompany> getAllServiceCompanies() {
        log.debug("REST request to get all ServiceCompanies");
        return serviceCompanyRepository.findAll();
    }

    /**
     * GET  /service-companies/:id : get the "id" serviceCompany.
     *
     * @param id the id of the serviceCompany to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceCompany, or with status 404 (Not Found)
     */
    @GetMapping("/service-companies/{id}")
    @Timed
    public ResponseEntity<ServiceCompany> getServiceCompany(@PathVariable Long id) {
        log.debug("REST request to get ServiceCompany : {}", id);
        Optional<ServiceCompany> serviceCompany = serviceCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serviceCompany);
    }

    /**
     * DELETE  /service-companies/:id : delete the "id" serviceCompany.
     *
     * @param id the id of the serviceCompany to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/service-companies/{id}")
    @Timed
    public ResponseEntity<Void> deleteServiceCompany(@PathVariable Long id) {
        log.debug("REST request to delete ServiceCompany : {}", id);

        serviceCompanyRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
