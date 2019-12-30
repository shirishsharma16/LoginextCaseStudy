package com.loginext.casestudy.dao;

import java.util.List;

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.bean.DriverData;

public interface DriverDataDao {
	

	public List<DriverData> getAvailableDrivers() throws Exception;
	public DriverData getNearestDriver(CustomerData customerData) throws Exception;
	public void updateDriverStatus(DriverData driverData) throws Exception;

}
