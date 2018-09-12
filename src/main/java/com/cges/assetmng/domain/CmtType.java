package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CmtType.
 */
@Entity
@Table(name = "cmt_type")
public class CmtType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cmt_type")
    private String cmtType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmtType() {
        return cmtType;
    }

    public CmtType cmtType(String cmtType) {
        this.cmtType = cmtType;
        return this;
    }

    public void setCmtType(String cmtType) {
        this.cmtType = cmtType;
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
        CmtType cmtType = (CmtType) o;
        if (cmtType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cmtType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CmtType{" +
            "id=" + getId() +
            ", cmtType='" + getCmtType() + "'" +
            "}";
    }
}
