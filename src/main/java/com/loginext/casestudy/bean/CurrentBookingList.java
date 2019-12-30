package com.loginext.casestudy.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CurrentBookingList {

	List<CurrentBooking> currentBookingList;
	ErrorRes errorRes;
	public List<CurrentBooking> getCurrentBookingList() {
		return currentBookingList;
	}
	public void setCurrentBookingList(List<CurrentBooking> currentBookingList) {
		this.currentBookingList = currentBookingList;
	}
	public ErrorRes getErrorRes() {
		return errorRes;
	}
	public void setErrorRes(ErrorRes errorRes) {
		this.errorRes = errorRes;
	}
	
	
}
