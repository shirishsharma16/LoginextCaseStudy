package com.loginext.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.sqldata.SqlQuery;

@Component
public class CustomerDataDaoImpl implements CustomerDataDao{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void saveCustomerData(CustomerData customerData) throws Exception {
		int ramdom_order;	
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.saveCustomer);
			
			ps.setInt(1, customerData.getOrdreNumber());
			ps.setString(2, customerData.getCustomerName());
			ps.setBigDecimal(3, customerData.getCurrentLat());
			ps.setBigDecimal(4, customerData.getCurrentLng());
			ps.setInt(5, customerData.getOrdreNumber());
			
			ps.executeUpdate();
		
			
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {		
					throw e;
				}
			}
		}
		
		
	}

}
