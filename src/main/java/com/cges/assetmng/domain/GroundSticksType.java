package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GroundSticksType.
 */
@Entity
@Table(name = "ground_sticks_type")
public class GroundSticksType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gs_type")
    private String gsType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGsType() {
        return gsType;
    }

    public GroundSticksType gsType(String gsType) {
        this.gsType = gsType;
        return this;
    }

    public void setGsType(String gsType) {
        this.gsType = gsType;
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
        GroundSticksType groundSticksType = (GroundSticksType) o;
        if (groundSticksType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groundSticksType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroundSticksType{" +
            "id=" + getId() +
            ", gsType='" + getGsType() + "'" +
            "}";
    }
}
