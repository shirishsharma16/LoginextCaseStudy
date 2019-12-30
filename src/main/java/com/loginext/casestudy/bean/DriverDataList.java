package com.loginext.casestudy.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DriverDataList {

	List<DriverData> driverDataList;
	ErrorRes errorRes;
	public List<DriverData> getDriverDataList() {
		return driverDataList;
	}
	public void setDriverDataList(List<DriverData> driverDataList) {
		this.driverDataList = driverDataList;
	}
	public ErrorRes getErrorRes() {
		return errorRes;
	}
	public void setErrorRes(ErrorRes errorRes) {
		this.errorRes = errorRes;
	}
	
	
}
