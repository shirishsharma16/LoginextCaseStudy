
var markers = [];
var map = null;


var host = "localhost";
var port = "9090";

var urlAvailableDrivers = "/LoginextCaseStudy/availabledrivers";
var urlBookRide = "/LoginextCaseStudy/bookride";
var urlCurrentBookings = "/LoginextCaseStudy/currentbookings";
var urlResetData = "/LoginextCaseStudy/resetdata";
window.onload = function (){

	// Display a map on the page
	var mylat = 19.111539;
	var mylng = 72.909064;
	var myCurrentPosition = new google.maps.LatLng(mylat, mylng); //taking a dummy position but we can take the current position of user  or we can even fir Bounds to accumulate all the driver locations


	//if map object has not been initilized. (might have already been called from onload method)
	if(map == null) {
		map = new google.maps.Map(document.getElementById('map'), {
			zoom: 13,
			center: myCurrentPosition
		});
	}
	//set the label on up with initial location
	document.getElementById('lat').value = mylat;
	document.getElementById('lng').value = mylng;


	// adding a dragable marker with green color to show my location
	var myMarker = new google.maps.Marker({
		position: myCurrentPosition, 
		map: map, 
		draggable:true,
		icon:'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
	});
	var myInfowindow = new google.maps.InfoWindow({
		content: "This is my location. Drag to exact pickup location"
	});
	myInfowindow.open(map,myMarker);


	// adding a listener to update the my current location. whenever dragged.
	google.maps.event.addListener(
			myMarker,
			'drag',
			function() {

				$('#lat').val(myMarker.position.lat());
				$('#lng').val(myMarker.position.lng());
			}
	);

	getAllAvailableDrivers();
	
}

var getAllAvailableDrivers =  function (){

	// remove all existing markers.As we will be populating new markers/drivers who are available 
	removeAllMArker();

	$.ajax({
		type: "GET",
		url: "http://" + host + ":"+ port + urlAvailableDrivers,
		contentType: "application/json; charset=utf-8",


		success:function (successResponse,textStatus,jqXHR) {


			var i=0;


			// iterating over the response to populate driver locations 
			$.each(successResponse.driverDataList, function (key, val) {

				addMarker(val.currentLat,val.currentLng,val.driverName,map,"Available");

			});

		},
		error: function (errorResponse1) {
			console.log(errorResponse1);
			alert("failed to get driver details");
		}
	});

	//enable BookCab button and disable New Booking button
	$('#btnNewBooking').attr("disabled",true);
	$('#btnBookCab').attr("disabled",false);
	
	populateBookingsTable();
}

var bookCab = function(){


	var customerObject = {
			customerName:'', 
			currentLat:null,
			currentLng:null
	};


	customerObject.customerName = $('#customerName').val();
	customerObject.currentLat = $('#lat').val();
	customerObject.currentLng = $('#lng').val();

	if (!(customerObject.customerName).trim()) {
		alert ("Please enter customer name");
	}
	else {
		$.ajax({
			type: "POST",
			url: "http://" + host + ":"+ port + urlBookRide,
			data: JSON.stringify(customerObject),
			contentType: "application/json; charset=utf-8",


			success:function (successResponse,textStatus,jqXHR) {

				if(successResponse== ""){
					alert("No drivers are available at the moment. Plese click Reset Data to free all drivers.");
				}
				//remove all the driver markers from the map
				removeAllMArker();




				var additionalInfo = "Booking confirmed";
				//add the nearest driver marker on map
				addMarker(successResponse.driverLat,successResponse.driverLng,successResponse.driverName,map,additionalInfo);

				//enable New Booking  button and disable BookCab button
				$('#btnNewBooking').attr("disabled",false);
				$('#btnBookCab').attr("disabled",true);
				
				populateBookingsTable();
			},
			error: function (errorResponse1) {
				alert("Failed to book the ride details.");
			}
		});

	
		
		
	}
	
}
var removeAllMArker = function () {

	for (var i = 0; i < markers.length; i++) {
		//setting map as null will remove the marker from the map
		markers[i].setMap(null);
	}
	markers = [];
}

var addMarker = function(lat,lng,driverName,map,additionalInfo){
	var pos = new google.maps.LatLng(lat, lng);
	var marker = new google.maps.Marker({
		position: pos,
		map: map,
		animation: google.maps.Animation.DROP,
		title: "Driver info"
	});



	var infowindow = new google.maps.InfoWindow({
		content: additionalInfo + ":"+ driverName
	});
	infowindow.open(map,marker);

	// push all the markers into an array that we will delete later to remove markers from map
	markers.push(marker);
}

var populateBookingsTable = function() {
	$.ajax({
		type: "GET",
		
		url: "http://" + host + ":"+ port + urlCurrentBookings,
		contentType: "application/json; charset=utf-8",


		success:function (successResponse,textStatus,jqXHR) {
			$('#currentBookings').empty();
			var html = '<table border="1"><tr><th>Driver</th><th>Customer</th><th>Status</th></tr>';
			// iterating over the response to populate table
			$.each(successResponse.currentBookingList, function (key, val) {
				html += '<tr>';
				html += '<td>' + val.driverName + '</td>';
				if(val.customerName == undefined){
					html += '<td> - </td>';
				} else {
					html += '<td>' + val.customerName + '</td>';
				}
				html += '<td>' + val.driverStatus + '</td>';
				html += "</tr>";
			});
			html += '</table>';

			$(html).appendTo('#currentBookings');

		},
		error: function (errorResponse1) {
			console.log(errorResponse1);
			alert("failed to get current bookings - driver details");
		}
	});
}

var resetData = function(){
	
	$.ajax({
		type: "POST",
		url: "http://" + host + ":"+ port + urlResetData,
		contentType: "application/json; charset=utf-8",

		success:function (successResponse,textStatus,jqXHR) {
			getAllAvailableDrivers();
			populateBookingsTable();
			//enable BookCab button and disable New Booking button
			$('#btnNewBooking').attr("disabled",true);
			$('#btnBookCab').attr("disabled",false);
				alert("Data reset complete ! All drivers are available Now");
		},
		error: function (errorResponse1) {
			alert("Failed reset data.");
		}
	});
}
