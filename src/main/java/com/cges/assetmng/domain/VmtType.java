package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VmtType.
 */
@Entity
@Table(name = "vmt_type")
public class VmtType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vmt_type")
    private String vmtType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVmtType() {
        return vmtType;
    }

    public VmtType vmtType(String vmtType) {
        this.vmtType = vmtType;
        return this;
    }

    public void setVmtType(String vmtType) {
        this.vmtType = vmtType;
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
        VmtType vmtType = (VmtType) o;
        if (vmtType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vmtType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VmtType{" +
            "id=" + getId() +
            ", vmtType='" + getVmtType() + "'" +
            "}";
    }
}
