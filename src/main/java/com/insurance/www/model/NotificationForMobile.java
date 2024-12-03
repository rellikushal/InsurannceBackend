package com.insurance.www.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class NotificationForMobile 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String customerId;
	private String mobileNo;
	private String status = "request";
	
	private LocalDateTime date = LocalDateTime.now();
	
//	private LocalDateTime notificatioDate = LocalDateTime.now();
	
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
//	public LocalDateTime getNotificatioDate() {
//		return notificatioDate;
//	}
//	public void setNotificatioDate(LocalDateTime notificatioDate) {
//		this.notificatioDate = notificatioDate;
//	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public NotificationForMobile(long id, String customerId, String mobileNo,String status) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.mobileNo = mobileNo;
		this.status = status;
	}
	public NotificationForMobile() {
		super();
		
	}
	
	

}