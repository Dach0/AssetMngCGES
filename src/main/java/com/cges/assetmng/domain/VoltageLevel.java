package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VoltageLevel.
 */
@Entity
@Table(name = "voltage_level")
public class VoltageLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "voltage_level", nullable = false)
    private Integer voltageLevel;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CorrectionFactor correctionFactor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoltageLevel() {
        return voltageLevel;
    }

    public VoltageLevel voltageLevel(Integer voltageLevel) {
        this.voltageLevel = voltageLevel;
        return this;
    }

    public void setVoltageLevel(Integer voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public CorrectionFactor getCorrectionFactor() {
        return correctionFactor;
    }

    public VoltageLevel correctionFactor(CorrectionFactor correctionFactor) {
        this.correctionFactor = correctionFactor;
        return this;
    }

    public void setCorrectionFactor(CorrectionFactor correctionFactor) {
        this.correctionFactor = correctionFactor;
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
        VoltageLevel voltageLevel = (VoltageLevel) o;
        if (voltageLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voltageLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VoltageLevel{" +
            "id=" + getId() +
            ", voltageLevel=" + getVoltageLevel() +
            "}";
    }
}
