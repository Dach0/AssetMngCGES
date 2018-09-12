package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CircuitBreaker.
 */
@Entity
@Table(name = "circuit_breaker")
public class CircuitBreaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @NotNull
    @Column(name = "jhi_current", nullable = false)
    private Integer current;

    @NotNull
    @Column(name = "short_circuit_current", nullable = false)
    private Integer shortCircuitCurrent;

    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CircuitBreakerDrive drive;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CircuitBreakerType type;

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

    public CircuitBreaker productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getCurrent() {
        return current;
    }

    public CircuitBreaker current(Integer current) {
        this.current = current;
        return this;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getShortCircuitCurrent() {
        return shortCircuitCurrent;
    }

    public CircuitBreaker shortCircuitCurrent(Integer shortCircuitCurrent) {
        this.shortCircuitCurrent = shortCircuitCurrent;
        return this;
    }

    public void setShortCircuitCurrent(Integer shortCircuitCurrent) {
        this.shortCircuitCurrent = shortCircuitCurrent;
    }

    public String getSerial() {
        return serial;
    }

    public CircuitBreaker serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPicture() {
        return picture;
    }

    public CircuitBreaker picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public CircuitBreakerDrive getDrive() {
        return drive;
    }

    public CircuitBreaker drive(CircuitBreakerDrive circuitBreakerDrive) {
        this.drive = circuitBreakerDrive;
        return this;
    }

    public void setDrive(CircuitBreakerDrive circuitBreakerDrive) {
        this.drive = circuitBreakerDrive;
    }

    public CircuitBreakerType getType() {
        return type;
    }

    public CircuitBreaker type(CircuitBreakerType circuitBreakerType) {
        this.type = circuitBreakerType;
        return this;
    }

    public void setType(CircuitBreakerType circuitBreakerType) {
        this.type = circuitBreakerType;
    }

    public Manufacturer getManufactured() {
        return manufactured;
    }

    public CircuitBreaker manufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
        return this;
    }

    public void setManufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
    }

    public Substation getSubstation() {
        return substation;
    }

    public CircuitBreaker substation(Substation substation) {
        this.substation = substation;
        return this;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
    }

    public Field getField() {
        return field;
    }

    public CircuitBreaker field(Field field) {
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
        CircuitBreaker circuitBreaker = (CircuitBreaker) o;
        if (circuitBreaker.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), circuitBreaker.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CircuitBreaker{" +
            "id=" + getId() +
            ", productionYear=" + getProductionYear() +
            ", current=" + getCurrent() +
            ", shortCircuitCurrent=" + getShortCircuitCurrent() +
            ", serial='" + getSerial() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
