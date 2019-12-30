package com.loginext.casestudy.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CurrentBooking {

	private String driverName;
	private String customerName;
	private String driverStatus;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}
	
	
}
