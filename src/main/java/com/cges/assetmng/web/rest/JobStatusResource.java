package com.cges.assetmng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cges.assetmng.domain.JobStatus;
import com.cges.assetmng.repository.JobStatusRepository;
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
 * REST controller for managing JobStatus.
 */
@RestController
@RequestMapping("/api")
public class JobStatusResource {

    private final Logger log = LoggerFactory.getLogger(JobStatusResource.class);

    private static final String ENTITY_NAME = "jobStatus";

    private final JobStatusRepository jobStatusRepository;

    public JobStatusResource(JobStatusRepository jobStatusRepository) {
        this.jobStatusRepository = jobStatusRepository;
    }

    /**
     * POST  /job-statuses : Create a new jobStatus.
     *
     * @param jobStatus the jobStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobStatus, or with status 400 (Bad Request) if the jobStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-statuses")
    @Timed
    public ResponseEntity<JobStatus> createJobStatus(@Valid @RequestBody JobStatus jobStatus) throws URISyntaxException {
        log.debug("REST request to save JobStatus : {}", jobStatus);
        if (jobStatus.getId() != null) {
            throw new BadRequestAlertException("A new jobStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobStatus result = jobStatusRepository.save(jobStatus);
        return ResponseEntity.created(new URI("/api/job-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-statuses : Updates an existing jobStatus.
     *
     * @param jobStatus the jobStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobStatus,
     * or with status 400 (Bad Request) if the jobStatus is not valid,
     * or with status 500 (Internal Server Error) if the jobStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-statuses")
    @Timed
    public ResponseEntity<JobStatus> updateJobStatus(@Valid @RequestBody JobStatus jobStatus) throws URISyntaxException {
        log.debug("REST request to update JobStatus : {}", jobStatus);
        if (jobStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobStatus result = jobStatusRepository.save(jobStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-statuses : get all the jobStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobStatuses in body
     */
    @GetMapping("/job-statuses")
    @Timed
    public List<JobStatus> getAllJobStatuses() {
        log.debug("REST request to get all JobStatuses");
        return jobStatusRepository.findAll();
    }

    /**
     * GET  /job-statuses/:id : get the "id" jobStatus.
     *
     * @param id the id of the jobStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobStatus, or with status 404 (Not Found)
     */
    @GetMapping("/job-statuses/{id}")
    @Timed
    public ResponseEntity<JobStatus> getJobStatus(@PathVariable Long id) {
        log.debug("REST request to get JobStatus : {}", id);
        Optional<JobStatus> jobStatus = jobStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jobStatus);
    }

    /**
     * DELETE  /job-statuses/:id : delete the "id" jobStatus.
     *
     * @param id the id of the jobStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobStatus(@PathVariable Long id) {
        log.debug("REST request to delete JobStatus : {}", id);

        jobStatusRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
