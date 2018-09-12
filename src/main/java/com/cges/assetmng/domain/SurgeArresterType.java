package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SurgeArresterType.
 */
@Entity
@Table(name = "surge_arrester_type")
public class SurgeArresterType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sa_type")
    private String saType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaType() {
        return saType;
    }

    public SurgeArresterType saType(String saType) {
        this.saType = saType;
        return this;
    }

    public void setSaType(String saType) {
        this.saType = saType;
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
        SurgeArresterType surgeArresterType = (SurgeArresterType) o;
        if (surgeArresterType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surgeArresterType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurgeArresterType{" +
            "id=" + getId() +
            ", saType='" + getSaType() + "'" +
            "}";
    }
}
