package com.loginext.casestudy.bean;

import java.math.BigDecimal;

public class DriverData {

	private long id;
	private String driverName;
	private BigDecimal currentLat;
	private BigDecimal currentLng;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
	
	
	
}
