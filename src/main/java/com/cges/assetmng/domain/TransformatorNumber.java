package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TransformatorNumber.
 */
@Entity
@Table(name = "transformator_number")
public class TransformatorNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_number")
    private String tNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettNumber() {
        return tNumber;
    }

    public TransformatorNumber tNumber(String tNumber) {
        this.tNumber = tNumber;
        return this;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
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
        TransformatorNumber transformatorNumber = (TransformatorNumber) o;
        if (transformatorNumber.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transformatorNumber.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransformatorNumber{" +
            "id=" + getId() +
            ", tNumber='" + gettNumber() + "'" +
            "}";
    }
}
