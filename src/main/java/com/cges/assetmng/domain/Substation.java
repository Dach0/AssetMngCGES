package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Substation.
 */
@Entity
@Table(name = "substation")
public class Substation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "operation_year")
    private Integer operationYear;

    @ManyToOne
    @JsonIgnoreProperties("substations")
    private Facility facility;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Substation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOperationYear() {
        return operationYear;
    }

    public Substation operationYear(Integer operationYear) {
        this.operationYear = operationYear;
        return this;
    }

    public void setOperationYear(Integer operationYear) {
        this.operationYear = operationYear;
    }

    public Facility getFacility() {
        return facility;
    }

    public Substation facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
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
        Substation substation = (Substation) o;
        if (substation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), substation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Substation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", operationYear=" + getOperationYear() +
            "}";
    }
}
