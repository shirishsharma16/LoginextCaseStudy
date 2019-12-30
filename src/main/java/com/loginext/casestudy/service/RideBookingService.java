package com.loginext.casestudy.service;

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.bean.RideDetails;

public interface RideBookingService {

	public RideDetails bookRide(CustomerData customerData) throws Exception;
}
