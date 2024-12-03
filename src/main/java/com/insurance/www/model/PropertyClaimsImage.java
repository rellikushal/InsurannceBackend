package com.insurance.www.model;


import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insurance.www.model.ImageStatus;
import com.insurance.www.model.PropertyClaimEntity;
import jakarta.persistence.*;
// import lombok.Data;

// @Data
@Entity
public class PropertyClaimsImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String apiPath;

    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private ImageStatus imageStatus;

    @ManyToOne
    @JoinColumn(name = "propertyClaimEntity_id")
    @JsonBackReference
    private PropertyClaimEntity propertyClaimEntity;



    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}



    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getApiPath() {
        return apiPath;
    }
    
    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
    
    public ImageStatus getImageStatus() {
        return imageStatus;
    }
    
    public void setImageStatus(ImageStatus imageStatus) {
        this.imageStatus = imageStatus;
    }
    
    public PropertyClaimEntity getPropertyClaimEntity() {
        return propertyClaimEntity;
    }
    
    public void setPropertyClaimEntity(PropertyClaimEntity propertyClaimEntity) {
        this.propertyClaimEntity = propertyClaimEntity;
    }


    
}
