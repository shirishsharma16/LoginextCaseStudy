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

import com.loginext.casestudy.bean.CustomerData;
import com.loginext.casestudy.bean.DriverData;
import com.loginext.casestudy.sqldata.SqlQuery;

@Component
public class DriverDataDaoImpl implements DriverDataDao{

	@Autowired
	private DataSource dataSource;


	@Override
	public List<DriverData> getAvailableDrivers() throws SQLException  {
		List<DriverData> allAvailableDriverList = new ArrayList<DriverData>();


		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.getAllAvailableDrivers);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				DriverData driverData = new DriverData();

				driverData.setId(rs.getLong("id"));
				driverData.setDriverName(rs.getString("driver_name"));
				driverData.setCurrentLat(rs.getBigDecimal("current_lat"));
				driverData.setCurrentLng(rs.getBigDecimal("current_lng"));
				driverData.setStatus(rs.getString("status"));
				allAvailableDriverList.add(driverData);
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
		return allAvailableDriverList;
	}


	@Override
	public DriverData getNearestDriver(CustomerData customerData) throws Exception{




		Connection conn = null;
		DriverData driverData = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.getNearestDriver);
			ps.setBigDecimal(1, customerData.getCurrentLat());
			ps.setBigDecimal(2, customerData.getCurrentLng());
			ps.setBigDecimal(3, customerData.getCurrentLat());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				driverData = new DriverData();

				driverData.setId(rs.getLong("id"));
				driverData.setDriverName(rs.getString("driver_name"));
				driverData.setCurrentLat(rs.getBigDecimal("current_lat"));
				driverData.setCurrentLng(rs.getBigDecimal("current_lng"));
				driverData.setStatus(rs.getString("status"));

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
		return driverData;


	}

	@Override
	public void updateDriverStatus(DriverData driverData) throws Exception {
		int ramdom_order;	
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQuery.updateDriverStatus);
			ramdom_order = (int)(Math.random()*5000+1);
			ps.setLong(1, driverData.getId());

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
