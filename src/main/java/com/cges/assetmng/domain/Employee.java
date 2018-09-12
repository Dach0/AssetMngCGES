package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Column(name = "tel_num_1", nullable = false)
    private String telNum1;

    @Column(name = "tel_num_2")
    private String telNum2;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "picture_empl")
    private String pictureEmpl;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "attachments")
    private String attachments;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PhonePrivilage phPrivilage;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EmployeeGroup group;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Sector sector;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Departman departman;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Job jobDesc;

    @ManyToOne
    @JsonIgnoreProperties("")
    private JobStatus status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Qualification profQualification;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Contract contractType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public Employee lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelNum1() {
        return telNum1;
    }

    public Employee telNum1(String telNum1) {
        this.telNum1 = telNum1;
        return this;
    }

    public void setTelNum1(String telNum1) {
        this.telNum1 = telNum1;
    }

    public String getTelNum2() {
        return telNum2;
    }

    public Employee telNum2(String telNum2) {
        this.telNum2 = telNum2;
        return this;
    }

    public void setTelNum2(String telNum2) {
        this.telNum2 = telNum2;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Employee startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Employee endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPictureEmpl() {
        return pictureEmpl;
    }

    public Employee pictureEmpl(String pictureEmpl) {
        this.pictureEmpl = pictureEmpl;
        return this;
    }

    public void setPictureEmpl(String pictureEmpl) {
        this.pictureEmpl = pictureEmpl;
    }

    public String getNotes() {
        return notes;
    }

    public Employee notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachments() {
        return attachments;
    }

    public Employee attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public PhonePrivilage getPhPrivilage() {
        return phPrivilage;
    }

    public Employee phPrivilage(PhonePrivilage phonePrivilage) {
        this.phPrivilage = phonePrivilage;
        return this;
    }

    public void setPhPrivilage(PhonePrivilage phonePrivilage) {
        this.phPrivilage = phonePrivilage;
    }

    public EmployeeGroup getGroup() {
        return group;
    }

    public Employee group(EmployeeGroup employeeGroup) {
        this.group = employeeGroup;
        return this;
    }

    public void setGroup(EmployeeGroup employeeGroup) {
        this.group = employeeGroup;
    }

    public Sector getSector() {
        return sector;
    }

    public Employee sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Departman getDepartman() {
        return departman;
    }

    public Employee departman(Departman departman) {
        this.departman = departman;
        return this;
    }

    public void setDepartman(Departman departman) {
        this.departman = departman;
    }

    public Job getJobDesc() {
        return jobDesc;
    }

    public Employee jobDesc(Job job) {
        this.jobDesc = job;
        return this;
    }

    public void setJobDesc(Job job) {
        this.jobDesc = job;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Employee status(JobStatus jobStatus) {
        this.status = jobStatus;
        return this;
    }

    public void setStatus(JobStatus jobStatus) {
        this.status = jobStatus;
    }

    public Qualification getProfQualification() {
        return profQualification;
    }

    public Employee profQualification(Qualification qualification) {
        this.profQualification = qualification;
        return this;
    }

    public void setProfQualification(Qualification qualification) {
        this.profQualification = qualification;
    }

    public Contract getContractType() {
        return contractType;
    }

    public Employee contractType(Contract contract) {
        this.contractType = contract;
        return this;
    }

    public void setContractType(Contract contract) {
        this.contractType = contract;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", telNum1='" + getTelNum1() + "'" +
            ", telNum2='" + getTelNum2() + "'" +
            ", email='" + getEmail() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", pictureEmpl='" + getPictureEmpl() + "'" +
            ", notes='" + getNotes() + "'" +
            ", attachments='" + getAttachments() + "'" +
            "}";
    }
}
