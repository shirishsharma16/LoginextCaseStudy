package com.loginext.casestudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginext.casestudy.bean.CurrentBooking;
import com.loginext.casestudy.dao.CurrentBookingDao;
@Service
public class CurrentBookingServiceImpl implements CurrentBookingService{

	@Autowired
	CurrentBookingDao currentBookingDao;
	@Override
	public void createNewBooking(long driverId, String customerName,
			int orderNumber) throws Exception {
		currentBookingDao.createNewBooking(driverId, customerName, orderNumber);
		
	}

	@Override
	public List<CurrentBooking> getAllBookings() throws Exception {
		
		return currentBookingDao.getAllBookings();
	}

	@Override
	public void clearAllData() throws Exception {
		currentBookingDao.clearAllData();
		
	}

}
