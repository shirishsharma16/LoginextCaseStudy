package com.loginext.casestudy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loginext.casestudy.bean.Country;
import com.loginext.casestudy.bean.CurrentBooking;
import com.loginext.casestudy.bean.CurrentBookingList;
import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.bean.DriverData;
import com.loginext.casestudy.bean.DriverDataList;
import com.loginext.casestudy.bean.ErrorRes;
import com.loginext.casestudy.bean.RideDetails;
import com.loginext.casestudy.dao.DriverDataDao;
import com.loginext.casestudy.service.CurrentBookingService;
import com.loginext.casestudy.service.CustomerDataService;
import com.loginext.casestudy.service.RideBookingService;
@Controller
public class RideBookingController {

	@Autowired
	DriverDataDao driverDataDao;

	@Autowired
	CustomerDataService customerDataService;

	@Autowired
	RideBookingService rideBookingService;

	@Autowired
	CurrentBookingService currentBookingService;


	/**
	 * Method returns all the available drivers
	 * @return
	 */
	@RequestMapping(value = "/availabledrivers", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<DriverDataList> getAllAvailableDrivers()
	{

		List<DriverData> availableDriverList;
		DriverDataList driverDataList = new DriverDataList();
		try {
			availableDriverList = driverDataDao.getAvailableDrivers();

			driverDataList.setDriverDataList(availableDriverList);
			return  ResponseEntity.ok(driverDataList);
		} catch (Exception e) {
			ErrorRes errorRes = new ErrorRes();
			errorRes.setErroCode(1005);
			errorRes.setErrorMessage(e.getMessage());
			driverDataList.setErrorRes(errorRes);
			return new ResponseEntity<DriverDataList>(driverDataList, HttpStatus.INTERNAL_SERVER_ERROR );
		}

	}

	/**
	 * this method would register a customer. One needs to enter the current lat long with unique name
	 * @param customerData
	 * @return error obj in case of error else json object with order number 
	 */
	@RequestMapping(value = "/bookride", method = RequestMethod.POST)
	public ResponseEntity<RideDetails> bookRide(@RequestBody CustomerData customerData)
	{
		try {
			RideDetails rideDetails = rideBookingService.bookRide(customerData);
			return ResponseEntity.ok(rideDetails);
		} catch (Exception e){
			RideDetails data = new RideDetails();
			ErrorRes errorRes = new ErrorRes();
			errorRes.setErroCode(1005);
			errorRes.setErrorMessage(e.getMessage());
			data.setErrorRes(errorRes);

			return new ResponseEntity<RideDetails>(data, HttpStatus.INTERNAL_SERVER_ERROR );
		}

	}

	/**
	 * Returns list of all the drivers with booking and availability 
	 * @return
	 */
	@RequestMapping(value = "/currentbookings", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<CurrentBookingList> getCurrentBookings()
	{

		List<CurrentBooking> currentBookings;
		CurrentBookingList currentBookingList = new CurrentBookingList();
		try {
			currentBookings = currentBookingService.getAllBookings();

			currentBookingList.setCurrentBookingList(currentBookings);
			return  ResponseEntity.ok(currentBookingList);
		} catch (Exception e) {
			ErrorRes errorRes = new ErrorRes();
			errorRes.setErroCode(1005);
			errorRes.setErrorMessage(e.getMessage());
			currentBookingList.setErrorRes(errorRes);
			return new ResponseEntity<CurrentBookingList>(currentBookingList, HttpStatus.INTERNAL_SERVER_ERROR );
		}

	}


	@RequestMapping(value = "/resetdata", method = RequestMethod.POST)
	public ResponseEntity<String> resetData()
	{
		try {
			currentBookingService.clearAllData();;
			return  ResponseEntity.ok("Success");
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}
	




}
