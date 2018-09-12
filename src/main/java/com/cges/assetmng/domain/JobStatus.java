package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A JobStatus.
 */
@Entity
@Table(name = "job_status")
public class JobStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "job_status", nullable = false)
    private String jobStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public JobStatus jobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
        return this;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobStatus jobStatus = (JobStatus) o;
        if (jobStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobStatus{" +
            "id=" + getId() +
            ", jobStatus='" + getJobStatus() + "'" +
            "}";
    }
}
