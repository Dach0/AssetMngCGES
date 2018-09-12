package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Manufacturer.
 */
@Entity
@Table(name = "manufacturer")
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufact_name")
    private String manufactName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufactName() {
        return manufactName;
    }

    public Manufacturer manufactName(String manufactName) {
        this.manufactName = manufactName;
        return this;
    }

    public void setManufactName(String manufactName) {
        this.manufactName = manufactName;
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
        Manufacturer manufacturer = (Manufacturer) o;
        if (manufacturer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manufacturer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
            "id=" + getId() +
            ", manufactName='" + getManufactName() + "'" +
            "}";
    }
}
