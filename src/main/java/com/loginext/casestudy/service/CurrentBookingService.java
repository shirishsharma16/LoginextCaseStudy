package com.loginext.casestudy.service;

import java.util.List;

import com.loginext.casestudy.bean.CurrentBooking;

public interface CurrentBookingService {

	public void createNewBooking(long driverId,String customerName,int orderNumber) throws Exception;
	public List<CurrentBooking> getAllBookings() throws Exception;
	public void clearAllData() throws Exception;
}
