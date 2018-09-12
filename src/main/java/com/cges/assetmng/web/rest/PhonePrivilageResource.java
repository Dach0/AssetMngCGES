package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.PhonePrivilage;
import com.cges.assetmng.repository.PhonePrivilageRepository;
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
 * REST controller for managing PhonePrivilage.
 */
@RestController
@RequestMapping("/api")
public class PhonePrivilageResource {

    private final Logger log = LoggerFactory.getLogger(PhonePrivilageResource.class);

    private static final String ENTITY_NAME = "phonePrivilage";

    private final PhonePrivilageRepository phonePrivilageRepository;

    public PhonePrivilageResource(PhonePrivilageRepository phonePrivilageRepository) {
        this.phonePrivilageRepository = phonePrivilageRepository;
    }

    /**
     * POST  /phone-privilages : Create a new phonePrivilage.
     *
     * @param phonePrivilage the phonePrivilage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phonePrivilage, or with status 400 (Bad Request) if the phonePrivilage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/phone-privilages")
    @Timed
    public ResponseEntity<PhonePrivilage> createPhonePrivilage(@RequestBody PhonePrivilage phonePrivilage) throws URISyntaxException {
        log.debug("REST request to save PhonePrivilage : {}", phonePrivilage);
        if (phonePrivilage.getId() != null) {
            throw new BadRequestAlertException("A new phonePrivilage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhonePrivilage result = phonePrivilageRepository.save(phonePrivilage);
        return ResponseEntity.created(new URI("/api/phone-privilages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /phone-privilages : Updates an existing phonePrivilage.
     *
     * @param phonePrivilage the phonePrivilage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated phonePrivilage,
     * or with status 400 (Bad Request) if the phonePrivilage is not valid,
     * or with status 500 (Internal Server Error) if the phonePrivilage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/phone-privilages")
    @Timed
    public ResponseEntity<PhonePrivilage> updatePhonePrivilage(@RequestBody PhonePrivilage phonePrivilage) throws URISyntaxException {
        log.debug("REST request to update PhonePrivilage : {}", phonePrivilage);
        if (phonePrivilage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhonePrivilage result = phonePrivilageRepository.save(phonePrivilage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, phonePrivilage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /phone-privilages : get all the phonePrivilages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of phonePrivilages in body
     */
    @GetMapping("/phone-privilages")
    @Timed
    public List<PhonePrivilage> getAllPhonePrivilages() {
        log.debug("REST request to get all PhonePrivilages");
        return phonePrivilageRepository.findAll();
    }

    /**
     * GET  /phone-privilages/:id : get the "id" phonePrivilage.
     *
     * @param id the id of the phonePrivilage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the phonePrivilage, or with status 404 (Not Found)
     */
    @GetMapping("/phone-privilages/{id}")
    @Timed
    public ResponseEntity<PhonePrivilage> getPhonePrivilage(@PathVariable Long id) {
        log.debug("REST request to get PhonePrivilage : {}", id);
        Optional<PhonePrivilage> phonePrivilage = phonePrivilageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(phonePrivilage);
    }

    /**
     * DELETE  /phone-privilages/:id : delete the "id" phonePrivilage.
     *
     * @param id the id of the phonePrivilage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/phone-privilages/{id}")
    @Timed
    public ResponseEntity<Void> deletePhonePrivilage(@PathVariable Long id) {
        log.debug("REST request to delete PhonePrivilage : {}", id);

        phonePrivilageRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
