package com.loginext.casestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.bean.DriverData;
import com.loginext.casestudy.bean.RideDetails;
import com.loginext.casestudy.dao.CurrentBookingDao;
import com.loginext.casestudy.dao.DriverDataDao;

@Service
public class RideBookingServiceImpl implements RideBookingService {

	@Autowired
	DriverDataDao driverDataDao;

	@Autowired
	CurrentBookingService currentBookingService;
	
	@Autowired
	CustomerDataService customerDataService;

	@Override
	public RideDetails bookRide(CustomerData customerData) throws Exception{
		RideDetails rideDetails = null;
		//1. Find the nearest driver
		//2. Generate order number
		//3. Update the ordernumber with customer (insert customer details)
		//4. Update driver status
		//5. Save order against fetched (nearest) driver
		//6. return the required details 


		//1. Find the nearest driver
		DriverData nearestDriver = driverDataDao.getNearestDriver(customerData);

	
		//2. Generate order number (I have randomly generated order number. I know it is not full proof as it will be repeated some time
		// For the sake of POC keeping the logic simple)
		int ramdom_order = (int)(Math.random()*5000+1);
		customerData.setOrdreNumber(ramdom_order);
		
		customerDataService.saveCustomerData(customerData);
		

		if(nearestDriver != null){
		
			//4. Update driver status
			driverDataDao.updateDriverStatus(nearestDriver);
			
			//5. Save order against fetched (nearest) driver
			currentBookingService.createNewBooking(nearestDriver.getId(), customerData.getCustomerName(), customerData.getOrdreNumber());
		
			//6. return the required details
			rideDetails = new RideDetails();
			rideDetails.setCustomerLat(customerData.getCurrentLat());
			rideDetails.setCustomerLng(customerData.getCurrentLng());
			rideDetails.setDriverLat(nearestDriver.getCurrentLat());
			rideDetails.setDriverLng(nearestDriver.getCurrentLng());
			rideDetails.setDriverName(nearestDriver.getDriverName());
		}

		


		return rideDetails;
	}

}
