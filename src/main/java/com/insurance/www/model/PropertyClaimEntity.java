package com.insurance.www.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

// import java.time.LocalDate;


// import lombok.Data;

import java.util.List;
// @Data
@Entity
public class PropertyClaimEntity {


    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyId;

    private String customerId;

    private String holderName;

    private String email;

    private String mobileNumber;

    private String address;

    private String propertyValue;

    private String dateOfIncident;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(columnDefinition="TEXT")
    private String causeOfLoss;

    private String repairEstimates;

    @Column(columnDefinition="TEXT")
    private String additionalDetails;

    @Column(columnDefinition="TEXT")
    private String witnessStatement;

    @OneToMany(mappedBy = "propertyClaimEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference

    private List<PropertyClaimsImage> images;


    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPolicyId() {
        return policyId;
    }
    
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPropertyValue() {
        return propertyValue;
    }
    
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    
    public String getDateOfIncident() {
        return dateOfIncident;
    }
    
    public void setDateOfIncident(String dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCauseOfLoss() {
        return causeOfLoss;
    }
    
    public void setCauseOfLoss(String causeOfLoss) {
        this.causeOfLoss = causeOfLoss;
    }
    
    public String getRepairEstimates() {
        return repairEstimates;
    }
    
    public void setRepairEstimates(String repairEstimates) {
        this.repairEstimates = repairEstimates;
    }
    
    public String getAdditionalDetails() {
        return additionalDetails;
    }
    
    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
    
    public String getWitnessStatement() {
        return witnessStatement;
    }
    
    public void setWitnessStatement(String witnessStatement) {
        this.witnessStatement = witnessStatement;
    }
    
    public List<PropertyClaimsImage> getImages() {
        return images;
    }
    
    public void setImages(List<PropertyClaimsImage> images) {
        this.images = images;
    }
    
}
