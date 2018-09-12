package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Departman.
 */
@Entity
@Table(name = "departman")
public class Departman implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dep_name")
    private String depName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public Departman depName(String depName) {
        this.depName = depName;
        return this;
    }

    public void setDepName(String depName) {
        this.depName = depName;
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
        Departman departman = (Departman) o;
        if (departman.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), departman.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Departman{" +
            "id=" + getId() +
            ", depName='" + getDepName() + "'" +
            "}";
    }
}
