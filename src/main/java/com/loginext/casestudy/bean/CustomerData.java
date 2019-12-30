package com.loginext.casestudy.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class CustomerData {
	private String customerName;
	private BigDecimal currentLat;
	private BigDecimal currentLng;
	private int ordreNumber;
	
	private ErrorRes error;
	
	
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getCurrentLat() {
		return currentLat;
	}
	public void setCurrentLat(BigDecimal currentLat) {
		this.currentLat = currentLat;
	}
	public BigDecimal getCurrentLng() {
		return currentLng;
	}
	public void setCurrentLng(BigDecimal currentLng) {
		this.currentLng = currentLng;
	}
	public int getOrdreNumber() {
		return ordreNumber;
	}
	public void setOrdreNumber(int ordreNumber) {
		this.ordreNumber = ordreNumber;
	}
	
	
	
	
}
