package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Qualification.
 */
@Entity
@Table(name = "qualification")
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "proff_qualification", nullable = false)
    private String proffQualification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProffQualification() {
        return proffQualification;
    }

    public Qualification proffQualification(String proffQualification) {
        this.proffQualification = proffQualification;
        return this;
    }

    public void setProffQualification(String proffQualification) {
        this.proffQualification = proffQualification;
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
        Qualification qualification = (Qualification) o;
        if (qualification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qualification.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Qualification{" +
            "id=" + getId() +
            ", proffQualification='" + getProffQualification() + "'" +
            "}";
    }
}
