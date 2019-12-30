package com.loginext.casestudy.sqldata;

public class SqlQuery {

	public static final String getAllAvailableDrivers = "SELECT id,driver_name,current_lat,current_lng,status FROM driver_current_data WHERE status = 'AVAILABLE'";

	public static final String saveCustomer = "INSERT INTO customer_data (order_number,customer_name,current_lat,current_lng) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE order_number=? ";

	public static final String getNearestDriver = ""
			+ "SELECT id,driver_name,current_lat,current_lng,status, "
			+ "( 6371 * acos( cos( radians(?) ) * cos( radians( current_lat ) ) * cos( radians( current_lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( current_lat ) ) ) ) AS distance "
			+ "FROM driver_current_data WHERE status = 'AVAILABLE' ORDER BY distance LIMIT 1 ";

	public static final String createBooking = ""
			+ "INSERT INTO current_booking (driver_id,customer_name,order_number) VALUES (?,?,?)";
	public static final String updateDriverStatus = "UPDATE driver_current_data SET status = 'BUSY' WHERE id = ?";

	public static final String getAllCurrentBookings = ""
			+ "SELECT dca.driver_name, cb.customer_name,dca.status FROM driver_current_data AS dca "
			+ "LEFT JOIN current_booking AS cb ON cb.driver_id = dca.id order by  dca.driver_name ";

	public static final String clearCurrentBookingsData = "delete from current_booking";
	public static final String clearCustomerData = "delete from customer_data";
	public static final String updateAllDriverAvailablity = "update driver_current_data set status='AVAILABLE' where 1=1";
}
