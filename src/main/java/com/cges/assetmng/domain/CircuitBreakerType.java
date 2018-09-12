package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CircuitBreakerType.
 */
@Entity
@Table(name = "circuit_breaker_type")
public class CircuitBreakerType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cb_type")
    private String cbType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCbType() {
        return cbType;
    }

    public CircuitBreakerType cbType(String cbType) {
        this.cbType = cbType;
        return this;
    }

    public void setCbType(String cbType) {
        this.cbType = cbType;
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
        CircuitBreakerType circuitBreakerType = (CircuitBreakerType) o;
        if (circuitBreakerType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), circuitBreakerType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CircuitBreakerType{" +
            "id=" + getId() +
            ", cbType='" + getCbType() + "'" +
            "}";
    }
}
