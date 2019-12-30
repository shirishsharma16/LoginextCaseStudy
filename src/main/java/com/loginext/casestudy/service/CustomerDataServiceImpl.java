package com.loginext.casestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.dao.CustomerDataDao;

@Service
public class CustomerDataServiceImpl implements CustomerDataService{

	@Autowired
	private CustomerDataDao customerDataDao;
	
	@Override
	public void saveCustomerData(CustomerData customerData) throws Exception {
		 customerDataDao.saveCustomerData(customerData);
	}

}
