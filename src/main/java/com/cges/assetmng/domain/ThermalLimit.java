package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ThermalLimit.
 */
@Entity
@Table(name = "thermal_limit")
public class ThermalLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "template_name", nullable = false)
    private String templateName;

    @NotNull
    @Column(name = "i_max_summer", nullable = false)
    private Integer iMaxSummer;

    @NotNull
    @Column(name = "i_max_winter", nullable = false)
    private Integer iMaxWinter;

    @NotNull
    @Column(name = "p_max_summer", nullable = false)
    private Integer pMaxSummer;

    @NotNull
    @Column(name = "p_max_winter", nullable = false)
    private Integer pMaxWinter;

    @Column(name = "thermal_limit")
    private Integer thermalLimit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public ThermalLimit templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getiMaxSummer() {
        return iMaxSummer;
    }

    public ThermalLimit iMaxSummer(Integer iMaxSummer) {
        this.iMaxSummer = iMaxSummer;
        return this;
    }

    public void setiMaxSummer(Integer iMaxSummer) {
        this.iMaxSummer = iMaxSummer;
    }

    public Integer getiMaxWinter() {
        return iMaxWinter;
    }

    public ThermalLimit iMaxWinter(Integer iMaxWinter) {
        this.iMaxWinter = iMaxWinter;
        return this;
    }

    public void setiMaxWinter(Integer iMaxWinter) {
        this.iMaxWinter = iMaxWinter;
    }

    public Integer getpMaxSummer() {
        return pMaxSummer;
    }

    public ThermalLimit pMaxSummer(Integer pMaxSummer) {
        this.pMaxSummer = pMaxSummer;
        return this;
    }

    public void setpMaxSummer(Integer pMaxSummer) {
        this.pMaxSummer = pMaxSummer;
    }

    public Integer getpMaxWinter() {
        return pMaxWinter;
    }

    public ThermalLimit pMaxWinter(Integer pMaxWinter) {
        this.pMaxWinter = pMaxWinter;
        return this;
    }

    public void setpMaxWinter(Integer pMaxWinter) {
        this.pMaxWinter = pMaxWinter;
    }

    public Integer getThermalLimit() {
        return thermalLimit;
    }

    public ThermalLimit thermalLimit(Integer thermalLimit) {
        this.thermalLimit = thermalLimit;
        return this;
    }

    public void setThermalLimit(Integer thermalLimit) {
        this.thermalLimit = thermalLimit;
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
        ThermalLimit thermalLimit = (ThermalLimit) o;
        if (thermalLimit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thermalLimit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThermalLimit{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", iMaxSummer=" + getiMaxSummer() +
            ", iMaxWinter=" + getiMaxWinter() +
            ", pMaxSummer=" + getpMaxSummer() +
            ", pMaxWinter=" + getpMaxWinter() +
            ", thermalLimit=" + getThermalLimit() +
            "}";
    }
}
