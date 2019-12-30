package com.loginext.casestudy.dao;

import java.util.List;

import com.loginext.casestudy.bean.CurrentBooking;

public interface CurrentBookingDao {

	public void createNewBooking(long driverId,String customerName,int orderNumber) throws Exception;
	public List<CurrentBooking> getAllBookings() throws Exception;
	public void clearAllData() throws Exception;
}
