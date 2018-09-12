package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TransmissionRatio.
 */
@Entity
@Table(name = "transmission_ratio")
public class TransmissionRatio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transmission_ratio")
    private String transmissionRatio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransmissionRatio() {
        return transmissionRatio;
    }

    public TransmissionRatio transmissionRatio(String transmissionRatio) {
        this.transmissionRatio = transmissionRatio;
        return this;
    }

    public void setTransmissionRatio(String transmissionRatio) {
        this.transmissionRatio = transmissionRatio;
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
        TransmissionRatio transmissionRatio = (TransmissionRatio) o;
        if (transmissionRatio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transmissionRatio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransmissionRatio{" +
            "id=" + getId() +
            ", transmissionRatio='" + getTransmissionRatio() + "'" +
            "}";
    }
}
