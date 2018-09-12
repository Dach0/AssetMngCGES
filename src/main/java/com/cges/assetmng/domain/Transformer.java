package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Transformer.
 */
@Entity
@Table(name = "transformer")
public class Transformer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @NotNull
    @Column(name = "installation_year", nullable = false)
    private Integer installationYear;

    @NotNull
    @Column(name = "short_circuit_voltage", nullable = false)
    private Double shortCircuitVoltage;

    @NotNull
    @Column(name = "is_earth_grounding", nullable = false)
    private Boolean isEarthGrounding;

    @NotNull
    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ElementCondition condition;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ElementStatus status;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Coupling coupling;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Power power;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private TransmissionRatio transRatio;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Manufacturer manufacturer;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private TransformerType type;

    @ManyToOne
    @JsonIgnoreProperties("transformers")
    private Service service;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TransformatorNumber redniBroj;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation;

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

    public Transformer productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getInstallationYear() {
        return installationYear;
    }

    public Transformer installationYear(Integer installationYear) {
        this.installationYear = installationYear;
        return this;
    }

    public void setInstallationYear(Integer installationYear) {
        this.installationYear = installationYear;
    }

    public Double getShortCircuitVoltage() {
        return shortCircuitVoltage;
    }

    public Transformer shortCircuitVoltage(Double shortCircuitVoltage) {
        this.shortCircuitVoltage = shortCircuitVoltage;
        return this;
    }

    public void setShortCircuitVoltage(Double shortCircuitVoltage) {
        this.shortCircuitVoltage = shortCircuitVoltage;
    }

    public Boolean isIsEarthGrounding() {
        return isEarthGrounding;
    }

    public Transformer isEarthGrounding(Boolean isEarthGrounding) {
        this.isEarthGrounding = isEarthGrounding;
        return this;
    }

    public void setIsEarthGrounding(Boolean isEarthGrounding) {
        this.isEarthGrounding = isEarthGrounding;
    }

    public String getPicture() {
        return picture;
    }

    public Transformer picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Transformer serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ElementCondition getCondition() {
        return condition;
    }

    public Transformer condition(ElementCondition elementCondition) {
        this.condition = elementCondition;
        return this;
    }

    public void setCondition(ElementCondition elementCondition) {
        this.condition = elementCondition;
    }

    public ElementStatus getStatus() {
        return status;
    }

    public Transformer status(ElementStatus elementStatus) {
        this.status = elementStatus;
        return this;
    }

    public void setStatus(ElementStatus elementStatus) {
        this.status = elementStatus;
    }

    public Coupling getCoupling() {
        return coupling;
    }

    public Transformer coupling(Coupling coupling) {
        this.coupling = coupling;
        return this;
    }

    public void setCoupling(Coupling coupling) {
        this.coupling = coupling;
    }

    public Power getPower() {
        return power;
    }

    public Transformer power(Power power) {
        this.power = power;
        return this;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public TransmissionRatio getTransRatio() {
        return transRatio;
    }

    public Transformer transRatio(TransmissionRatio transmissionRatio) {
        this.transRatio = transmissionRatio;
        return this;
    }

    public void setTransRatio(TransmissionRatio transmissionRatio) {
        this.transRatio = transmissionRatio;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Transformer manufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TransformerType getType() {
        return type;
    }

    public Transformer type(TransformerType transformerType) {
        this.type = transformerType;
        return this;
    }

    public void setType(TransformerType transformerType) {
        this.type = transformerType;
    }

    public Service getService() {
        return service;
    }

    public Transformer service(Service service) {
        this.service = service;
        return this;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TransformatorNumber getRedniBroj() {
        return redniBroj;
    }

    public Transformer redniBroj(TransformatorNumber transformatorNumber) {
        this.redniBroj = transformatorNumber;
        return this;
    }

    public void setRedniBroj(TransformatorNumber transformatorNumber) {
        this.redniBroj = transformatorNumber;
    }

    public Substation getSubstation() {
        return substation;
    }

    public Transformer substation(Substation substation) {
        this.substation = substation;
        return this;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
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
        Transformer transformer = (Transformer) o;
        if (transformer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transformer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transformer{" +
            "id=" + getId() +
            ", productionYear=" + getProductionYear() +
            ", installationYear=" + getInstallationYear() +
            ", shortCircuitVoltage=" + getShortCircuitVoltage() +
            ", isEarthGrounding='" + isIsEarthGrounding() + "'" +
            ", picture='" + getPicture() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            "}";
    }
}
