package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Ohl.
 */
@Entity
@Table(name = "ohl")
public class Ohl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(name = "ohl_number", length = 45)
    private String ohlNumber;

    @NotNull
    @Column(name = "operation_year", nullable = false)
    private Integer operationYear;

    @NotNull
    @Column(name = "length_total", nullable = false)
    private Double lengthTotal;

    @NotNull
    @Column(name = "length_in_mne", nullable = false)
    private Double lengthInMne;

    @Column(name = "r_ohm_per_phase_in_mne")
    private Double rOhmPerPhaseInMne;

    @Column(name = "r_ohm_per_phase_total")
    private Double rOhmPerPhaseTotal;

    @Column(name = "x_ohm_per_phase_in_mne")
    private Double xOhmPerPhaseInMne;

    @Column(name = "x_ohm_per_phase_total")
    private Double xOhmPerPhaseTotal;

    @Column(name = "b_ohm_per_phase_in_mne")
    private Double bOhmPerPhaseInMne;

    @Column(name = "b_ohm_per_phase_total")
    private Double bOhmPerPhaseTotal;

    @Column(name = "r_0_ohm_per_phase_in_mne")
    private Double r0OhmPerPhaseInMne;

    @Column(name = "r_0_ohm_per_phase_total")
    private Double r0OhmPerPhaseTotal;

    @Column(name = "x_0_ohm_per_phase_in_mne")
    private Double x0OhmPerPhaseInMne;

    @Column(name = "x_0_ohm_per_phase_total")
    private Double x0OhmPerPhaseTotal;

    @NotNull
    @Column(name = "pylon_number", nullable = false)
    private Integer pylonNumber;

    @NotNull
    @Column(name = "isolator_number", nullable = false)
    private Integer isolatorNumber;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation1;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation2;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Tconnection tConnection;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VoltageLevel voltageLevel;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PylonType ohlConstructionPart;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ThermalLimit thermalLimit;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ConductorCrossSect condCrossSect;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EarthWireCrossSect earthCrossSect;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOhlNumber() {
        return ohlNumber;
    }

    public Ohl ohlNumber(String ohlNumber) {
        this.ohlNumber = ohlNumber;
        return this;
    }

    public void setOhlNumber(String ohlNumber) {
        this.ohlNumber = ohlNumber;
    }

    public Integer getOperationYear() {
        return operationYear;
    }

    public Ohl operationYear(Integer operationYear) {
        this.operationYear = operationYear;
        return this;
    }

    public void setOperationYear(Integer operationYear) {
        this.operationYear = operationYear;
    }

    public Double getLengthTotal() {
        return lengthTotal;
    }

    public Ohl lengthTotal(Double lengthTotal) {
        this.lengthTotal = lengthTotal;
        return this;
    }

    public void setLengthTotal(Double lengthTotal) {
        this.lengthTotal = lengthTotal;
    }

    public Double getLengthInMne() {
        return lengthInMne;
    }

    public Ohl lengthInMne(Double lengthInMne) {
        this.lengthInMne = lengthInMne;
        return this;
    }

    public void setLengthInMne(Double lengthInMne) {
        this.lengthInMne = lengthInMne;
    }

    public Double getrOhmPerPhaseInMne() {
        return rOhmPerPhaseInMne;
    }

    public Ohl rOhmPerPhaseInMne(Double rOhmPerPhaseInMne) {
        this.rOhmPerPhaseInMne = rOhmPerPhaseInMne;
        return this;
    }

    public void setrOhmPerPhaseInMne(Double rOhmPerPhaseInMne) {
        this.rOhmPerPhaseInMne = rOhmPerPhaseInMne;
    }

    public Double getrOhmPerPhaseTotal() {
        return rOhmPerPhaseTotal;
    }

    public Ohl rOhmPerPhaseTotal(Double rOhmPerPhaseTotal) {
        this.rOhmPerPhaseTotal = rOhmPerPhaseTotal;
        return this;
    }

    public void setrOhmPerPhaseTotal(Double rOhmPerPhaseTotal) {
        this.rOhmPerPhaseTotal = rOhmPerPhaseTotal;
    }

    public Double getxOhmPerPhaseInMne() {
        return xOhmPerPhaseInMne;
    }

    public Ohl xOhmPerPhaseInMne(Double xOhmPerPhaseInMne) {
        this.xOhmPerPhaseInMne = xOhmPerPhaseInMne;
        return this;
    }

    public void setxOhmPerPhaseInMne(Double xOhmPerPhaseInMne) {
        this.xOhmPerPhaseInMne = xOhmPerPhaseInMne;
    }

    public Double getxOhmPerPhaseTotal() {
        return xOhmPerPhaseTotal;
    }

    public Ohl xOhmPerPhaseTotal(Double xOhmPerPhaseTotal) {
        this.xOhmPerPhaseTotal = xOhmPerPhaseTotal;
        return this;
    }

    public void setxOhmPerPhaseTotal(Double xOhmPerPhaseTotal) {
        this.xOhmPerPhaseTotal = xOhmPerPhaseTotal;
    }

    public Double getbOhmPerPhaseInMne() {
        return bOhmPerPhaseInMne;
    }

    public Ohl bOhmPerPhaseInMne(Double bOhmPerPhaseInMne) {
        this.bOhmPerPhaseInMne = bOhmPerPhaseInMne;
        return this;
    }

    public void setbOhmPerPhaseInMne(Double bOhmPerPhaseInMne) {
        this.bOhmPerPhaseInMne = bOhmPerPhaseInMne;
    }

    public Double getbOhmPerPhaseTotal() {
        return bOhmPerPhaseTotal;
    }

    public Ohl bOhmPerPhaseTotal(Double bOhmPerPhaseTotal) {
        this.bOhmPerPhaseTotal = bOhmPerPhaseTotal;
        return this;
    }

    public void setbOhmPerPhaseTotal(Double bOhmPerPhaseTotal) {
        this.bOhmPerPhaseTotal = bOhmPerPhaseTotal;
    }

    public Double getr0OhmPerPhaseInMne() {
        return r0OhmPerPhaseInMne;
    }

    public Ohl r0OhmPerPhaseInMne(Double r0OhmPerPhaseInMne) {
        this.r0OhmPerPhaseInMne = r0OhmPerPhaseInMne;
        return this;
    }

    public void setr0OhmPerPhaseInMne(Double r0OhmPerPhaseInMne) {
        this.r0OhmPerPhaseInMne = r0OhmPerPhaseInMne;
    }

    public Double getr0OhmPerPhaseTotal() {
        return r0OhmPerPhaseTotal;
    }

    public Ohl r0OhmPerPhaseTotal(Double r0OhmPerPhaseTotal) {
        this.r0OhmPerPhaseTotal = r0OhmPerPhaseTotal;
        return this;
    }

    public void setr0OhmPerPhaseTotal(Double r0OhmPerPhaseTotal) {
        this.r0OhmPerPhaseTotal = r0OhmPerPhaseTotal;
    }

    public Double getx0OhmPerPhaseInMne() {
        return x0OhmPerPhaseInMne;
    }

    public Ohl x0OhmPerPhaseInMne(Double x0OhmPerPhaseInMne) {
        this.x0OhmPerPhaseInMne = x0OhmPerPhaseInMne;
        return this;
    }

    public void setx0OhmPerPhaseInMne(Double x0OhmPerPhaseInMne) {
        this.x0OhmPerPhaseInMne = x0OhmPerPhaseInMne;
    }

    public Double getx0OhmPerPhaseTotal() {
        return x0OhmPerPhaseTotal;
    }

    public Ohl x0OhmPerPhaseTotal(Double x0OhmPerPhaseTotal) {
        this.x0OhmPerPhaseTotal = x0OhmPerPhaseTotal;
        return this;
    }

    public void setx0OhmPerPhaseTotal(Double x0OhmPerPhaseTotal) {
        this.x0OhmPerPhaseTotal = x0OhmPerPhaseTotal;
    }

    public Integer getPylonNumber() {
        return pylonNumber;
    }

    public Ohl pylonNumber(Integer pylonNumber) {
        this.pylonNumber = pylonNumber;
        return this;
    }

    public void setPylonNumber(Integer pylonNumber) {
        this.pylonNumber = pylonNumber;
    }

    public Integer getIsolatorNumber() {
        return isolatorNumber;
    }

    public Ohl isolatorNumber(Integer isolatorNumber) {
        this.isolatorNumber = isolatorNumber;
        return this;
    }

    public void setIsolatorNumber(Integer isolatorNumber) {
        this.isolatorNumber = isolatorNumber;
    }

    public Substation getSubstation1() {
        return substation1;
    }

    public Ohl substation1(Substation substation) {
        this.substation1 = substation;
        return this;
    }

    public void setSubstation1(Substation substation) {
        this.substation1 = substation;
    }

    public Substation getSubstation2() {
        return substation2;
    }

    public Ohl substation2(Substation substation) {
        this.substation2 = substation;
        return this;
    }

    public void setSubstation2(Substation substation) {
        this.substation2 = substation;
    }

    public Tconnection getTConnection() {
        return tConnection;
    }

    public Ohl tConnection(Tconnection tconnection) {
        this.tConnection = tconnection;
        return this;
    }

    public void setTConnection(Tconnection tconnection) {
        this.tConnection = tconnection;
    }

    public VoltageLevel getVoltageLevel() {
        return voltageLevel;
    }

    public Ohl voltageLevel(VoltageLevel voltageLevel) {
        this.voltageLevel = voltageLevel;
        return this;
    }

    public void setVoltageLevel(VoltageLevel voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public PylonType getOhlConstructionPart() {
        return ohlConstructionPart;
    }

    public Ohl ohlConstructionPart(PylonType pylonType) {
        this.ohlConstructionPart = pylonType;
        return this;
    }

    public void setOhlConstructionPart(PylonType pylonType) {
        this.ohlConstructionPart = pylonType;
    }

    public ThermalLimit getThermalLimit() {
        return thermalLimit;
    }

    public Ohl thermalLimit(ThermalLimit thermalLimit) {
        this.thermalLimit = thermalLimit;
        return this;
    }

    public void setThermalLimit(ThermalLimit thermalLimit) {
        this.thermalLimit = thermalLimit;
    }

    public ConductorCrossSect getCondCrossSect() {
        return condCrossSect;
    }

    public Ohl condCrossSect(ConductorCrossSect conductorCrossSect) {
        this.condCrossSect = conductorCrossSect;
        return this;
    }

    public void setCondCrossSect(ConductorCrossSect conductorCrossSect) {
        this.condCrossSect = conductorCrossSect;
    }

    public EarthWireCrossSect getEarthCrossSect() {
        return earthCrossSect;
    }

    public Ohl earthCrossSect(EarthWireCrossSect earthWireCrossSect) {
        this.earthCrossSect = earthWireCrossSect;
        return this;
    }

    public void setEarthCrossSect(EarthWireCrossSect earthWireCrossSect) {
        this.earthCrossSect = earthWireCrossSect;
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
        Ohl ohl = (Ohl) o;
        if (ohl.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ohl.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ohl{" +
            "id=" + getId() +
            ", ohlNumber='" + getOhlNumber() + "'" +
            ", operationYear=" + getOperationYear() +
            ", lengthTotal=" + getLengthTotal() +
            ", lengthInMne=" + getLengthInMne() +
            ", rOhmPerPhaseInMne=" + getrOhmPerPhaseInMne() +
            ", rOhmPerPhaseTotal=" + getrOhmPerPhaseTotal() +
            ", xOhmPerPhaseInMne=" + getxOhmPerPhaseInMne() +
            ", xOhmPerPhaseTotal=" + getxOhmPerPhaseTotal() +
            ", bOhmPerPhaseInMne=" + getbOhmPerPhaseInMne() +
            ", bOhmPerPhaseTotal=" + getbOhmPerPhaseTotal() +
            ", r0OhmPerPhaseInMne=" + getr0OhmPerPhaseInMne() +
            ", r0OhmPerPhaseTotal=" + getr0OhmPerPhaseTotal() +
            ", x0OhmPerPhaseInMne=" + getx0OhmPerPhaseInMne() +
            ", x0OhmPerPhaseTotal=" + getx0OhmPerPhaseTotal() +
            ", pylonNumber=" + getPylonNumber() +
            ", isolatorNumber=" + getIsolatorNumber() +
            "}";
    }
}
