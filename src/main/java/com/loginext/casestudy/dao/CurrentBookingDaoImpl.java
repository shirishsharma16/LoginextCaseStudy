package com.loginext.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loginext.casestudy.bean.CurrentBooking;
import com.loginext.casestudy.bean.DriverData;
import com.loginext.casestudy.sqldata.SqlQuery;

@Component
public class CurrentBookingDaoImpl implements CurrentBookingDao{

	@Autowired
	private DataSource dataSource;

	@Override
	public void createNewBooking(long driverId,String customerName,int orderNumber) throws Exception {

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.createBooking);

			ps.setLong(1, driverId);
			ps.setString(2, customerName);
			ps.setLong(3, orderNumber);

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

	@Override
	public List<CurrentBooking> getAllBookings() throws Exception {

		List<CurrentBooking> allCurrentBookings = new ArrayList<CurrentBooking>();


		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.getAllCurrentBookings);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				CurrentBooking currentBooking = new CurrentBooking();

				currentBooking.setCustomerName(rs.getString("customer_name"));
				currentBooking.setDriverName(rs.getString("driver_name"));
				currentBooking.setDriverStatus(rs.getString("status"));
				allCurrentBookings.add(currentBooking);
			}
			rs.close();
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
		return allCurrentBookings;
	
	}

	@Override
	public void clearAllData() throws Exception {

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps_CurrentBookings = conn.prepareStatement(SqlQuery.clearCurrentBookingsData);
			PreparedStatement ps_CustomerData = conn.prepareStatement(SqlQuery.clearCustomerData);
			PreparedStatement ps_DriverAvailability = conn.prepareStatement(SqlQuery.updateAllDriverAvailablity);
			
			

			ps_CurrentBookings.executeUpdate();
			ps_CustomerData.executeUpdate();
			ps_DriverAvailability.executeUpdate();
			
			
			ps_CurrentBookings.close();
			ps_DriverAvailability.close();
			ps_CustomerData.close();
			
			

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
