package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SurgeArrester.
 */
@Entity
@Table(name = "surge_arrester")
public class SurgeArrester implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @NotNull
    @Column(name = "ucur", nullable = false)
    private String ucur;

    @NotNull
    @Column(name = "drainage_current", nullable = false)
    private Integer drainageCurrent;

    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JsonIgnoreProperties("")
    private SurgeArresterType type;

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

    public SurgeArrester productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getUcur() {
        return ucur;
    }

    public SurgeArrester ucur(String ucur) {
        this.ucur = ucur;
        return this;
    }

    public void setUcur(String ucur) {
        this.ucur = ucur;
    }

    public Integer getDrainageCurrent() {
        return drainageCurrent;
    }

    public SurgeArrester drainageCurrent(Integer drainageCurrent) {
        this.drainageCurrent = drainageCurrent;
        return this;
    }

    public void setDrainageCurrent(Integer drainageCurrent) {
        this.drainageCurrent = drainageCurrent;
    }

    public String getSerial() {
        return serial;
    }

    public SurgeArrester serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPicture() {
        return picture;
    }

    public SurgeArrester picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public SurgeArresterType getType() {
        return type;
    }

    public SurgeArrester type(SurgeArresterType surgeArresterType) {
        this.type = surgeArresterType;
        return this;
    }

    public void setType(SurgeArresterType surgeArresterType) {
        this.type = surgeArresterType;
    }

    public Manufacturer getManufactured() {
        return manufactured;
    }

    public SurgeArrester manufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
        return this;
    }

    public void setManufactured(Manufacturer manufacturer) {
        this.manufactured = manufacturer;
    }

    public Substation getSubstation() {
        return substation;
    }

    public SurgeArrester substation(Substation substation) {
        this.substation = substation;
        return this;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
    }

    public Field getField() {
        return field;
    }

    public SurgeArrester field(Field field) {
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
        SurgeArrester surgeArrester = (SurgeArrester) o;
        if (surgeArrester.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surgeArrester.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurgeArrester{" +
            "id=" + getId() +
            ", productionYear=" + getProductionYear() +
            ", ucur='" + getUcur() + "'" +
            ", drainageCurrent=" + getDrainageCurrent() +
            ", serial='" + getSerial() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
