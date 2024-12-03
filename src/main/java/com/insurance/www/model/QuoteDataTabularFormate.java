package com.insurance.www.model;

public class QuoteDataTabularFormate 
{
    private String marketValue;
	
	private String squareFeet;
	
	private String pincode;
	
	private String year;
	
	private String premium;

    public QuoteDataTabularFormate(String marketValue, String squareFeet, String pincode, String year, String premium) {
        this.marketValue = marketValue;
        this.squareFeet = squareFeet;
        this.pincode = pincode;
        this.year = year;
        this.premium = premium;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    
}
