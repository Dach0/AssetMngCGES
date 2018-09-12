package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Service.
 */
@Entity
@Table(name = "service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "repair_price")
    private Double repairPrice;

    @Column(name = "spare_parts_price")
    private Double sparePartsPrice;

    @Column(name = "serviced_from")
    private LocalDate servicedFrom;

    @Column(name = "serviced_to")
    private LocalDate servicedTo;

    @Column(name = "attachments")
    private String attachments;

    @Lob
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ServiceCompany doneByComp;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Employee doneByEmpl;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ServiceType type;

    @OneToMany(mappedBy = "service")
    private Set<Transformer> transformers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Service serviceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Double getRepairPrice() {
        return repairPrice;
    }

    public Service repairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
        return this;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public Double getSparePartsPrice() {
        return sparePartsPrice;
    }

    public Service sparePartsPrice(Double sparePartsPrice) {
        this.sparePartsPrice = sparePartsPrice;
        return this;
    }

    public void setSparePartsPrice(Double sparePartsPrice) {
        this.sparePartsPrice = sparePartsPrice;
    }

    public LocalDate getServicedFrom() {
        return servicedFrom;
    }

    public Service servicedFrom(LocalDate servicedFrom) {
        this.servicedFrom = servicedFrom;
        return this;
    }

    public void setServicedFrom(LocalDate servicedFrom) {
        this.servicedFrom = servicedFrom;
    }

    public LocalDate getServicedTo() {
        return servicedTo;
    }

    public Service servicedTo(LocalDate servicedTo) {
        this.servicedTo = servicedTo;
        return this;
    }

    public void setServicedTo(LocalDate servicedTo) {
        this.servicedTo = servicedTo;
    }

    public String getAttachments() {
        return attachments;
    }

    public Service attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getNotes() {
        return notes;
    }

    public Service notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ServiceCompany getDoneByComp() {
        return doneByComp;
    }

    public Service doneByComp(ServiceCompany serviceCompany) {
        this.doneByComp = serviceCompany;
        return this;
    }

    public void setDoneByComp(ServiceCompany serviceCompany) {
        this.doneByComp = serviceCompany;
    }

    public Employee getDoneByEmpl() {
        return doneByEmpl;
    }

    public Service doneByEmpl(Employee employee) {
        this.doneByEmpl = employee;
        return this;
    }

    public void setDoneByEmpl(Employee employee) {
        this.doneByEmpl = employee;
    }

    public ServiceType getType() {
        return type;
    }

    public Service type(ServiceType serviceType) {
        this.type = serviceType;
        return this;
    }

    public void setType(ServiceType serviceType) {
        this.type = serviceType;
    }

    public Set<Transformer> getTransformers() {
        return transformers;
    }

    public Service transformers(Set<Transformer> transformers) {
        this.transformers = transformers;
        return this;
    }

    public Service addTransformer(Transformer transformer) {
        this.transformers.add(transformer);
        transformer.setService(this);
        return this;
    }

    public Service removeTransformer(Transformer transformer) {
        this.transformers.remove(transformer);
        transformer.setService(null);
        return this;
    }

    public void setTransformers(Set<Transformer> transformers) {
        this.transformers = transformers;
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
        Service service = (Service) o;
        if (service.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), service.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Service{" +
            "id=" + getId() +
            ", serviceDescription='" + getServiceDescription() + "'" +
            ", repairPrice=" + getRepairPrice() +
            ", sparePartsPrice=" + getSparePartsPrice() +
            ", servicedFrom='" + getServicedFrom() + "'" +
            ", servicedTo='" + getServicedTo() + "'" +
            ", attachments='" + getAttachments() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
