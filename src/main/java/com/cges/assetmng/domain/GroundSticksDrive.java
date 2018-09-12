package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GroundSticksDrive.
 */
@Entity
@Table(name = "ground_sticks_drive")
public class GroundSticksDrive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gs_drive")
    private String gsDrive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGsDrive() {
        return gsDrive;
    }

    public GroundSticksDrive gsDrive(String gsDrive) {
        this.gsDrive = gsDrive;
        return this;
    }

    public void setGsDrive(String gsDrive) {
        this.gsDrive = gsDrive;
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
        GroundSticksDrive groundSticksDrive = (GroundSticksDrive) o;
        if (groundSticksDrive.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groundSticksDrive.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroundSticksDrive{" +
            "id=" + getId() +
            ", gsDrive='" + getGsDrive() + "'" +
            "}";
    }
}
