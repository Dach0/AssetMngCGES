package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "tconnection")
public class Tconnection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_conn_sub_station_name")
    private String tConnSubStationName;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation1;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Substation substation2;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettConnSubStationName() {
        return tConnSubStationName;
    }

    public Tconnection tConnSubStationName(String tConnSubStationName) {
        this.tConnSubStationName = tConnSubStationName;
        return this;
    }

    public void settConnSubStationName(String tConnSubStationName) {
        this.tConnSubStationName = tConnSubStationName;
    }

    public Substation getSubstation1() {
        return substation1;
    }

    public Tconnection substation1(Substation substation) {
        this.substation1 = substation;
        return this;
    }

    public void setSubstation1(Substation substation) {
        this.substation1 = substation;
    }

    public Substation getSubstation2() {
        return substation2;
    }

    public Tconnection substation2(Substation substation) {
        this.substation2 = substation;
        return this;
    }

    public void setSubstation2(Substation substation) {
        this.substation2 = substation;
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
        Tconnection tconnection = (Tconnection) o;
        if (tconnection.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tconnection.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tconnection{" +
            "id=" + getId() +
            ", tConnSubStationName='" + gettConnSubStationName() + "'" +
            "}";
    }
}
