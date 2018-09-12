package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VoltageMeasuringTransformer.
 */
@Entity
@Table(name = "voltage_measuring_transformer")
public class VoltageMeasuringTransformer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @NotNull
    @Column(name = "transmission_ratio", nullable = false)
    private String transmissionRatio;

    @NotNull
    @Column(name = "accuracy_class", nullable = false)
    private Double accuracyClass;

    @NotNull
    @Column(name = "nominal_power", nullable = false)
    private Integer nominalPower;

    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VmtType type;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Manufacturer manufactured;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Field field;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public VoltageMeasuringTransformer productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getTransmissionRatio() {
        return transmissionRatio;
    }

    public VoltageMeasuringTransformer transmissionRatio(String transmissionRatio) {
        this.transmissionRatio = transmissionRatio;
        return this;
    }

    public void setTransmissionRatio(String transmissionRatio) {
        this.transmissionRatio = transmissionRatio;
    }

    public Double getAccuracyClass() {
        return accuracyClass;
    }

    public VoltageMeasuringTransformer accuracyClass(Double accuracyClass) {
        this.accuracyClass = accuracyClass;
        return this;
    }

    public void setAccuracyClass(Double accuracyClass) {
        this.accuracyClass = accuracyClass;
    }

    public Integer getNominalPower() {
        return nominalPower;
    }

    public VoltageMeasuringTransformer nominalPower(Integer nominalPower) {
        this.nominalPower = nominalPower;
        return this;
    }

    public void setNominalPower(Integer nominalPower) {
        this.nominalPower = nominalPower;
    }

    public String getSerial() {
        return serial;
    }

    public VoltageMeasuringTransformer serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPicture() {
        return picture;
    }

    public VoltageMeasuringTransformer picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public VmtType getType() {
        return type;
    }

    public VoltageMeasuringTransformer type(VmtType vmtType) {
        this.type = vmtType;
        return this;
    }

    public void setType(VmtType vmtType) {
        this.type = vmtType;
    }

    public Manufacturer getManufactured() {
        return manufactured;
    }

    public VoltageMeasuringTransformer manufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
        return this;
    }

    public void setManufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
    }

    public Substation getSubstation() {
        return substation;
    }

    public VoltageMeasuringTransformer substation(Substation substation) {
        this.substation = substation;
        return this;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
    }

    public Field getField() {
        return field;
    }

    public VoltageMeasuringTransformer field(Field field) {
        this.field = field;
        return this;
    }

    public void setField(Field field) {
        this.field = field;
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
        VoltageMeasuringTransformer voltageMeasuringTransformer = (VoltageMeasuringTransformer) o;
        if (voltageMeasuringTransformer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voltageMeasuringTransformer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VoltageMeasuringTransformer{" +
            "id=" + getId() +
            ", productionYear=" + getProductionYear() +
            ", transmissionRatio='" + getTransmissionRatio() + "'" +
            ", accuracyClass=" + getAccuracyClass() +
            ", nominalPower=" + getNominalPower() +
            ", serial='" + getSerial() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
