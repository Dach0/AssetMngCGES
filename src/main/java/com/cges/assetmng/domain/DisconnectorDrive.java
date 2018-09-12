package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DisconnectorDrive.
 */
@Entity
@Table(name = "disconnector_drive")
public class DisconnectorDrive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "disc_drive")
    private String discDrive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscDrive() {
        return discDrive;
    }

    public DisconnectorDrive discDrive(String discDrive) {
        this.discDrive = discDrive;
        return this;
    }

    public void setDiscDrive(String discDrive) {
        this.discDrive = discDrive;
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
        DisconnectorDrive disconnectorDrive = (DisconnectorDrive) o;
        if (disconnectorDrive.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disconnectorDrive.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisconnectorDrive{" +
            "id=" + getId() +
            ", discDrive='" + getDiscDrive() + "'" +
            "}";
    }
}
