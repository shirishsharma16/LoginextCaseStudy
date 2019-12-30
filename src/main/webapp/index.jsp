<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#map {
	height: 370px;
	width: 100%;
}
</style>
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/loginext.js" />"></script>

</head>
<body>
	<h3>Loginext Case Study</h3>
	<div id="map"></div>





	<table style="width: 100%">
		<tr >
			<td style="width: 50%">
				<table style="width: 50%">
					<tr>
						<td>My Location</td>
						<td>My Lat : <input type="text" id="lat" readonly> </br>My
							Lng: <input type="text" id="lng" readonly></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Customer Name:</td>
						<td><input type="text" name="customerName" id="customerName"></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<button onclick="getAllAvailableDrivers()" id="btnNewBooking">New
								Booking</button>
							<button onclick="bookCab()" id="btnBookCab">BookCab</button>
						</td>
					</tr>
				</table> <br>


				<div id="currentBookings"></div>
				<button onclick="resetData()" id="btnReset">Reset Database</button>
			</td>
			<td align="left">
				<h3>Instructions</h3>
				<ul>
					<li>Green Color marker is your current location. It has been hard coded to Loginext's office location. It is a drag able marker, So you can now adjust your current location on map</li>
					<li>Red markers are drivers around you.</li>
					<li>Info window on driver marker (red marker) shows their name and availability status.</li>
					<li>Use button "BookCab" to find the nearest driver around you.</li>
					<li>Once a nearest driver has been booked it will no longer be show on maps. You can check the driver status in the grid below.</li>
					<li>To make a new booking click "New Booking" button. It will show all the remaining drivers.</li>
					<li>Button <span style="color: red">"Reset Database"</span> will reset all the data in database i.e. It will delete all the bookings, all customer info and update the driver booking status to AVAILABLE. </li>
					<li>Table above "Reset Database" button shows the current driver/booking status. And will get updated on every new booking.</li>
					
				</ul>
			</td>
		</tr>
	</table>


	<script>
		function initMap() {
			// do any things related to map initilization here. I ave done it in onload method.
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=<your_googlemap_api_key>&callback=initMap">
		
	</script>
</body>
</html>