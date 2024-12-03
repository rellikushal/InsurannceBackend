package com.insurance.www.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import lombok.Data;

@Entity
@Table
// @Data
public class Vehicle 
{
	@Id
	private  Long id;
	private String vnumber;
	private String vname;
	private int vyear;
	public Vehicle(Long id, String vnumber, String vname, int vyear) {
		this.id = id;
		this.vnumber = vnumber;
		this.vname = vname;
		this.vyear = vyear;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVnumber() {
		return vnumber;
	}
	public void setVnumber(String vnumber) {
		this.vnumber = vnumber;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public int getVyear() {
		return vyear;
	}
	public void setVyear(int vyear) {
		this.vyear = vyear;
	}
	
	public Vehicle()
	{
		super();
	}
	
	
}
