package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Asset.
 */
@Entity
@Table(name = "asset")
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "picture")
    private String picture;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "date_of_obligation")
    private LocalDate dateOfObligation;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "market_price")
    private Double marketPrice;

    @Column(name = "scrap_price")
    private Double scrapPrice;

    @Column(name = "purchased_date")
    private LocalDate purchasedDate;

    @Column(name = "in_service_date")
    private LocalDate inServiceDate;

    @Column(name = "warrenty")
    private LocalDate warrenty;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "attachments")
    private String attachments;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Type type;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Location location;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AssetStatus status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AssetCondition condition;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Employee asset;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Asset description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Asset manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public Asset brand(String brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public Asset model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPicture() {
        return picture;
    }

    public Asset picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Asset serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDateOfObligation() {
        return dateOfObligation;
    }

    public Asset dateOfObligation(LocalDate dateOfObligation) {
        this.dateOfObligation = dateOfObligation;
        return this;
    }

    public void setDateOfObligation(LocalDate dateOfObligation) {
        this.dateOfObligation = dateOfObligation;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public Asset purchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public Asset marketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
        return this;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getScrapPrice() {
        return scrapPrice;
    }

    public Asset scrapPrice(Double scrapPrice) {
        this.scrapPrice = scrapPrice;
        return this;
    }

    public void setScrapPrice(Double scrapPrice) {
        this.scrapPrice = scrapPrice;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public Asset purchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
        return this;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public LocalDate getInServiceDate() {
        return inServiceDate;
    }

    public Asset inServiceDate(LocalDate inServiceDate) {
        this.inServiceDate = inServiceDate;
        return this;
    }

    public void setInServiceDate(LocalDate inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public LocalDate getWarrenty() {
        return warrenty;
    }

    public Asset warrenty(LocalDate warrenty) {
        this.warrenty = warrenty;
        return this;
    }

    public void setWarrenty(LocalDate warrenty) {
        this.warrenty = warrenty;
    }

    public String getNotes() {
        return notes;
    }

    public Asset notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachments() {
        return attachments;
    }

    public Asset attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Type getType() {
        return type;
    }

    public Asset type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public Asset location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public Asset status(AssetStatus assetStatus) {
        this.status = assetStatus;
        return this;
    }

    public void setStatus(AssetStatus assetStatus) {
        this.status = assetStatus;
    }

    public AssetCondition getCondition() {
        return condition;
    }

    public Asset condition(AssetCondition assetCondition) {
        this.condition = assetCondition;
        return this;
    }

    public void setCondition(AssetCondition assetCondition) {
        this.condition = assetCondition;
    }

    public Employee getAsset() {
        return asset;
    }

    public Asset asset(Employee employee) {
        this.asset = employee;
        return this;
    }

    public void setAsset(Employee employee) {
        this.asset = employee;
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
        Asset asset = (Asset) o;
        if (asset.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asset.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Asset{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", manufacturer='" + getManufacturer() + "'" +
            ", brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", picture='" + getPicture() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", dateOfObligation='" + getDateOfObligation() + "'" +
            ", purchasePrice=" + getPurchasePrice() +
            ", marketPrice=" + getMarketPrice() +
            ", scrapPrice=" + getScrapPrice() +
            ", purchasedDate='" + getPurchasedDate() + "'" +
            ", inServiceDate='" + getInServiceDate() + "'" +
            ", warrenty='" + getWarrenty() + "'" +
            ", notes='" + getNotes() + "'" +
            ", attachments='" + getAttachments() + "'" +
            "}";
    }
}
