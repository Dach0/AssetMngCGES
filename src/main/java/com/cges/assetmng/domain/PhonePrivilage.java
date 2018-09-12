package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PhonePrivilage.
 */
@Entity
@Table(name = "phone_privilage")
public class PhonePrivilage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "privilage")
    private String privilage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilage() {
        return privilage;
    }

    public PhonePrivilage privilage(String privilage) {
        this.privilage = privilage;
        return this;
    }

    public void setPrivilage(String privilage) {
        this.privilage = privilage;
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
        PhonePrivilage phonePrivilage = (PhonePrivilage) o;
        if (phonePrivilage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phonePrivilage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhonePrivilage{" +
            "id=" + getId() +
            ", privilage='" + getPrivilage() + "'" +
            "}";
    }
}
