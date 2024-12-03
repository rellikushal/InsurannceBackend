package com.insurance.www.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table

public class Customer 
{
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerid;
	private String fullName;
	private String address;
	private String state;
	private String mobile;
	private String email;
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customer(String customerid, String fullName, String address, String state, String mobile, String email) {
		this.customerid = customerid;
		this.fullName = fullName;
		this.address = address;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
	}

	public Customer()
	{
		super();
	}
}
