package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DisconnectorType.
 */
@Entity
@Table(name = "disconnector_type")
public class DisconnectorType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "disc_type")
    private String discType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscType() {
        return discType;
    }

    public DisconnectorType discType(String discType) {
        this.discType = discType;
        return this;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
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
        DisconnectorType disconnectorType = (DisconnectorType) o;
        if (disconnectorType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disconnectorType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisconnectorType{" +
            "id=" + getId() +
            ", discType='" + getDiscType() + "'" +
            "}";
    }
}
