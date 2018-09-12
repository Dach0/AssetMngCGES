package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConductorCrossSect.
 */
@Entity
@Table(name = "conductor_cross_sect")
public class ConductorCrossSect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "conductor_cross_section", nullable = false)
    private String conductorCrossSection;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConductorCrossSection() {
        return conductorCrossSection;
    }

    public ConductorCrossSect conductorCrossSection(String conductorCrossSection) {
        this.conductorCrossSection = conductorCrossSection;
        return this;
    }

    public void setConductorCrossSection(String conductorCrossSection) {
        this.conductorCrossSection = conductorCrossSection;
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
        ConductorCrossSect conductorCrossSect = (ConductorCrossSect) o;
        if (conductorCrossSect.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conductorCrossSect.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConductorCrossSect{" +
            "id=" + getId() +
            ", conductorCrossSection='" + getConductorCrossSection() + "'" +
            "}";
    }
}
