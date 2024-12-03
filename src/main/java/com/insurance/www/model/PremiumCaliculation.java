package com.insurance.www.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class PremiumCaliculation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private double p1; // half of the percent marketValue
	@Column
	private double p2; // buildingAgeIntial Values 
	@Column
	private double p3;
	@Column
	private double p4;
	@Column
	private double p5;
	@Column
	private double p6;
	@Column
	private double p7; // security check initial values
	@Column
	private double p8;
	
	@Column
	private Boolean state = false;
	
	@Column
	private String marKetValue;
	@Column 
	private String buildingAge;
	@Column 
	private String securityCheck;
	
	
	
	public String getMarKetValue() {
		return marKetValue;
	}
	public void setMarKetValue(String marKetValue) {
		this.marKetValue = marKetValue;
	}
	public String getBuildingAge() {
		return buildingAge;
	}
	public void setBuildingAge(String buildingAge) {
		this.buildingAge = buildingAge;
	}
	public String getSecurityCheck() {
		return securityCheck;
	}
	public void setSecurityCheck(String securityCheck) {
		this.securityCheck = securityCheck;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getP1() {
		return p1;
	}
	public void setP1(double p1) {
		this.p1 = p1;
	}
	public double getP2() {
		return p2;
	}
	public void setP2(double p2) {
		this.p2 = p2;
	}
	public double getP3() {
		return p3;
	}
	public void setP3(double p3) {
		this.p3 = p3;
	}
	public double getP4() {
		return p4;
	}
	public void setP4(double p4) {
		this.p4 = p4;
	}
	public double getP5() {
		return p5;
	}
	public void setP5(double p5) {
		this.p5 = p5;
	}
	public double getP6() {
		return p6;
	}
	public void setP6(double p6) {
		this.p6 = p6;
	}
	public double getP7() {
		return p7;
	}
	public void setP7(double p7) {
		this.p7 = p7;
	}
	public double getP8() {
		return p8;
	}
	public void setP8(double p8) {
		this.p8 = p8;
	}
	
	
	public boolean isState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	public PremiumCaliculation(long id, double p1, double p2, double p3, double p4, double p5, double p6, double p7,
			double p8, String marKetValue, String buildingAge, String securityCheck,Boolean state) {
		super();
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.p6 = p6;
		this.p7 = p7;
		this.p8 = p8;
		this.state = state;
		this.marKetValue = marKetValue;
		this.buildingAge = buildingAge;
		this.securityCheck = securityCheck;
	}
	
	
	public PremiumCaliculation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}