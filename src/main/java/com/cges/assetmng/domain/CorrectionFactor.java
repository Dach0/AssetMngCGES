package com.cges.assetmng.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CorrectionFactor.
 */
@Entity
@Table(name = "correction_factor")
public class CorrectionFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name")
    private String templateName;

    @NotNull
    @Column(name = "degrees_30", nullable = false)
    private Double degrees30;

    @NotNull
    @Column(name = "degrees_20", nullable = false)
    private Double degrees20;

    @NotNull
    @Column(name = "degrees_10", nullable = false)
    private Double degrees10;

    @NotNull
    @Column(name = "degrees_0", nullable = false)
    private Double degrees0;

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

    public CorrectionFactor templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Double getDegrees30() {
        return degrees30;
    }

    public CorrectionFactor degrees30(Double degrees30) {
        this.degrees30 = degrees30;
        return this;
    }

    public void setDegrees30(Double degrees30) {
        this.degrees30 = degrees30;
    }

    public Double getDegrees20() {
        return degrees20;
    }

    public CorrectionFactor degrees20(Double degrees20) {
        this.degrees20 = degrees20;
        return this;
    }

    public void setDegrees20(Double degrees20) {
        this.degrees20 = degrees20;
    }

    public Double getDegrees10() {
        return degrees10;
    }

    public CorrectionFactor degrees10(Double degrees10) {
        this.degrees10 = degrees10;
        return this;
    }

    public void setDegrees10(Double degrees10) {
        this.degrees10 = degrees10;
    }

    public Double getDegrees0() {
        return degrees0;
    }

    public CorrectionFactor degrees0(Double degrees0) {
        this.degrees0 = degrees0;
        return this;
    }

    public void setDegrees0(Double degrees0) {
        this.degrees0 = degrees0;
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
        CorrectionFactor correctionFactor = (CorrectionFactor) o;
        if (correctionFactor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), correctionFactor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CorrectionFactor{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", degrees30=" + getDegrees30() +
            ", degrees20=" + getDegrees20() +
            ", degrees10=" + getDegrees10() +
            ", degrees0=" + getDegrees0() +
            "}";
    }
}
