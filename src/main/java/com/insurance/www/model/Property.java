package com.insurance.www.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String pincode;
    private String ageOfProperty;
    private String structureValue;
    private String customerId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAgeOfProperty() {
        return ageOfProperty;
    }

    public void setAgeOfProperty(String ageOfProperty) {
        this.ageOfProperty = ageOfProperty;
    }

    public String getStructureValue() {
        return structureValue;
    }

    public void setStructureValue(String structureValue) {
        this.structureValue = structureValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
