package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Coupling.
 */
@Entity
@Table(name = "coupling")
public class Coupling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupling")
    private String coupling;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoupling() {
        return coupling;
    }

    public Coupling coupling(String coupling) {
        this.coupling = coupling;
        return this;
    }

    public void setCoupling(String coupling) {
        this.coupling = coupling;
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
        Coupling coupling = (Coupling) o;
        if (coupling.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coupling.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Coupling{" +
            "id=" + getId() +
            ", coupling='" + getCoupling() + "'" +
            "}";
    }
}
