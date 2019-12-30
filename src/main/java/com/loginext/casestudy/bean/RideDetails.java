package com.loginext.casestudy.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RideDetails {

	private String driverName;
	private BigDecimal driverLat;
	private BigDecimal driverLng;
	
	private BigDecimal customerLat;
	private BigDecimal customerLng;
	private ErrorRes errorRes;
	
	public ErrorRes getErrorRes() {
		return errorRes;
	}
	public void setErrorRes(ErrorRes errorRes) {
		this.errorRes = errorRes;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public BigDecimal getDriverLat() {
		return driverLat;
	}
	public void setDriverLat(BigDecimal driverLat) {
		this.driverLat = driverLat;
	}
	public BigDecimal getDriverLng() {
		return driverLng;
	}
	public void setDriverLng(BigDecimal driverLng) {
		this.driverLng = driverLng;
	}
	public BigDecimal getCustomerLat() {
		return customerLat;
	}
	public void setCustomerLat(BigDecimal customerLat) {
		this.customerLat = customerLat;
	}
	public BigDecimal getCustomerLng() {
		return customerLng;
	}
	public void setCustomerLng(BigDecimal customerLng) {
		this.customerLng = customerLng;
	}
	
	
}
