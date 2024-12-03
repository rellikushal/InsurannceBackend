package com.insurance.www.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class NotificationForEmail {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String customerId;
	private String email;
	private String status = "request";
	
	private LocalDateTime date = LocalDateTime.now();
//	private LocalDateTime notifiactionDate = date;
	
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
//	public LocalDateTime getNotifiactionDate() {
//		return notifiactionDate;
//	}
//	public void setNotifiactionDate(LocalDateTime notifiactionDate) {
//		this.notifiactionDate = notifiactionDate;
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
	public NotificationForEmail(long id, String customerId, String email, String status) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.email = email;
		this.status = status;
	}
	public NotificationForEmail() {
		super();
	
	}
	
	
	
}
