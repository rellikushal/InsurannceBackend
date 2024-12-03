package com.insurance.www.model;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import lombok.Data;

@Entity
@Table
// @Data
public class VNotificationForEmail 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String customerid;
	

    private String oldemail;
	private String email;
	private String status = "request";
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getOldemail() {
        return oldemail;
    }

    public void setOldemail(String oldemail) {
        this.oldemail = oldemail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date = LocalDateTime.now();

    public VNotificationForEmail(long id, String customerid, String oldemail, String email, String status,
            LocalDateTime date) {
        this.id = id;
        this.customerid = customerid;
        this.oldemail = oldemail;
        this.email = email;
        this.status = status;
        this.date = date;
    }

    public VNotificationForEmail()
	{
		super();
	}
}
