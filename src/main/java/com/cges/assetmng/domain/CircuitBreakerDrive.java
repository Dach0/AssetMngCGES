package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CircuitBreakerDrive.
 */
@Entity
@Table(name = "circuit_breaker_drive")
public class CircuitBreakerDrive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cb_drive")
    private String cbDrive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCbDrive() {
        return cbDrive;
    }

    public CircuitBreakerDrive cbDrive(String cbDrive) {
        this.cbDrive = cbDrive;
        return this;
    }

    public void setCbDrive(String cbDrive) {
        this.cbDrive = cbDrive;
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
        CircuitBreakerDrive circuitBreakerDrive = (CircuitBreakerDrive) o;
        if (circuitBreakerDrive.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), circuitBreakerDrive.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CircuitBreakerDrive{" +
            "id=" + getId() +
            ", cbDrive='" + getCbDrive() + "'" +
            "}";
    }
}
