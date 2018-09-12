package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EarthWireCrossSect.
 */
@Entity
@Table(name = "earth_wire_cross_sect")
public class EarthWireCrossSect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "earth_wire_cross_section", nullable = false)
    private String earthWireCrossSection;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEarthWireCrossSection() {
        return earthWireCrossSection;
    }

    public EarthWireCrossSect earthWireCrossSection(String earthWireCrossSection) {
        this.earthWireCrossSection = earthWireCrossSection;
        return this;
    }

    public void setEarthWireCrossSection(String earthWireCrossSection) {
        this.earthWireCrossSection = earthWireCrossSection;
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
        EarthWireCrossSect earthWireCrossSect = (EarthWireCrossSect) o;
        if (earthWireCrossSect.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), earthWireCrossSect.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EarthWireCrossSect{" +
            "id=" + getId() +
            ", earthWireCrossSection='" + getEarthWireCrossSection() + "'" +
            "}";
    }
}
