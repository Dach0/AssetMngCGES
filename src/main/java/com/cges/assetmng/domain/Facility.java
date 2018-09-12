package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Facility.
 */
@Entity
@Table(name = "facility")
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "in_operation_since")
    private Integer inOperationSince;

    @Column(name = "power")
    private Integer power;

    @Column(name = "num_of_transformers")
    private Integer numOfTransformers;

    @Column(name = "busbars")
    private String busbars;

    @Column(name = "num_of_fields")
    private Integer numOfFields;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VoltageLevel voltageLevel;

    @ManyToOne
    @JsonIgnoreProperties("")
    private FacilityMaintainingCo maintaining;

    @OneToMany(mappedBy = "facility")
    private Set<Substation> substations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInOperationSince() {
        return inOperationSince;
    }

    public Facility inOperationSince(Integer inOperationSince) {
        this.inOperationSince = inOperationSince;
        return this;
    }

    public void setInOperationSince(Integer inOperationSince) {
        this.inOperationSince = inOperationSince;
    }

    public Integer getPower() {
        return power;
    }

    public Facility power(Integer power) {
        this.power = power;
        return this;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getNumOfTransformers() {
        return numOfTransformers;
    }

    public Facility numOfTransformers(Integer numOfTransformers) {
        this.numOfTransformers = numOfTransformers;
        return this;
    }

    public void setNumOfTransformers(Integer numOfTransformers) {
        this.numOfTransformers = numOfTransformers;
    }

    public String getBusbars() {
        return busbars;
    }

    public Facility busbars(String busbars) {
        this.busbars = busbars;
        return this;
    }

    public void setBusbars(String busbars) {
        this.busbars = busbars;
    }

    public Integer getNumOfFields() {
        return numOfFields;
    }

    public Facility numOfFields(Integer numOfFields) {
        this.numOfFields = numOfFields;
        return this;
    }

    public void setNumOfFields(Integer numOfFields) {
        this.numOfFields = numOfFields;
    }

    public VoltageLevel getVoltageLevel() {
        return voltageLevel;
    }

    public Facility voltageLevel(VoltageLevel voltageLevel) {
        this.voltageLevel = voltageLevel;
        return this;
    }

    public void setVoltageLevel(VoltageLevel voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public FacilityMaintainingCo getMaintaining() {
        return maintaining;
    }

    public Facility maintaining(FacilityMaintainingCo facilityMaintainingCo) {
        this.maintaining = facilityMaintainingCo;
        return this;
    }

    public void setMaintaining(FacilityMaintainingCo facilityMaintainingCo) {
        this.maintaining = facilityMaintainingCo;
    }

    public Set<Substation> getSubstations() {
        return substations;
    }

    public Facility substations(Set<Substation> substations) {
        this.substations = substations;
        return this;
    }

    public Facility addSubstation(Substation substation) {
        this.substations.add(substation);
        substation.setFacility(this);
        return this;
    }

    public Facility removeSubstation(Substation substation) {
        this.substations.remove(substation);
        substation.setFacility(null);
        return this;
    }

    public void setSubstations(Set<Substation> substations) {
        this.substations = substations;
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
        Facility facility = (Facility) o;
        if (facility.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facility.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Facility{" +
            "id=" + getId() +
            ", inOperationSince=" + getInOperationSince() +
            ", power=" + getPower() +
            ", numOfTransformers=" + getNumOfTransformers() +
            ", busbars='" + getBusbars() + "'" +
            ", numOfFields=" + getNumOfFields() +
            "}";
    }
}
