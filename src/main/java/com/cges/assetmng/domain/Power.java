package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Power.
 */
@Entity
@Table(name = "power")
public class Power implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "power")
    private String power;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPower() {
        return power;
    }

    public Power power(String power) {
        this.power = power;
        return this;
    }

    public void setPower(String power) {
        this.power = power;
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
        Power power = (Power) o;
        if (power.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), power.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Power{" +
            "id=" + getId() +
            ", power='" + getPower() + "'" +
            "}";
    }
}
