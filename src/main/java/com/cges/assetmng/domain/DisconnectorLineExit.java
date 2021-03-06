package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DisconnectorLineExit.
 */
@Entity
@Table(name = "disconnector_line_exit")
public class DisconnectorLineExit implements Serializable {

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
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DisconnectorDrive drive;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DisconnectorType type;

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

    public DisconnectorLineExit productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getCurrent() {
        return current;
    }

    public DisconnectorLineExit current(Integer current) {
        this.current = current;
        return this;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getSerial() {
        return serial;
    }

    public DisconnectorLineExit serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPicture() {
        return picture;
    }

    public DisconnectorLineExit picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public DisconnectorDrive getDrive() {
        return drive;
    }

    public DisconnectorLineExit drive(DisconnectorDrive disconnectorDrive) {
        this.drive = disconnectorDrive;
        return this;
    }

    public void setDrive(DisconnectorDrive disconnectorDrive) {
        this.drive = disconnectorDrive;
    }

    public DisconnectorType getType() {
        return type;
    }

    public DisconnectorLineExit type(DisconnectorType disconnectorType) {
        this.type = disconnectorType;
        return this;
    }

    public void setType(DisconnectorType disconnectorType) {
        this.type = disconnectorType;
    }

    public Manufacturer getManufactured() {
        return manufactured;
    }

    public DisconnectorLineExit manufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
        return this;
    }

    public void setManufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
    }

    public Substation getSubstation() {
        return substation;
    }

    public DisconnectorLineExit substation(Substation substation) {
        this.substation = substation;
        return this;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
    }

    public Field getField() {
        return field;
    }

    public DisconnectorLineExit field(Field field) {
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
        DisconnectorLineExit disconnectorLineExit = (DisconnectorLineExit) o;
        if (disconnectorLineExit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disconnectorLineExit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisconnectorLineExit{" +
            "id=" + getId() +
            ", productionYear=" + getProductionYear() +
            ", current=" + getCurrent() +
            ", serial='" + getSerial() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
