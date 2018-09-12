package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ElementStatus.
 */
@Entity
@Table(name = "element_status")
public class ElementStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "element_status")
    private String elementStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElementStatus() {
        return elementStatus;
    }

    public ElementStatus elementStatus(String elementStatus) {
        this.elementStatus = elementStatus;
        return this;
    }

    public void setElementStatus(String elementStatus) {
        this.elementStatus = elementStatus;
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
        ElementStatus elementStatus = (ElementStatus) o;
        if (elementStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), elementStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElementStatus{" +
            "id=" + getId() +
            ", elementStatus='" + getElementStatus() + "'" +
            "}";
    }
}
