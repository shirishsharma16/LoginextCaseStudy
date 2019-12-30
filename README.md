# LoginextCaseStudy
java-google map based apis to book a nearest cab


Aplication Url:

http://localhost:9090/LoginextCaseStudy/index.jsp

Configuration Setup :

	1.Update port number for calling REST API
		Go to "LoginextCaseStudy\src\main\webapp\resources\theme1\js\loginext.js" 
		and update the "port" variable at the start of js to the actual port number on which your tomcat is running. 
		
	2. Import the LoginextDump.sql to setup database. And insert initial driver details.
		Location : "LoginextCaseStudy\LoginextDump.sql"
		I have used MySql database.
		
	3.Update JDBC properties 
		Go to "LoginextCaseStudy\src\main\java\database.properties"
		and update it with you schema/user/password etc. (make sure you are using the correct schema).
		
	4. Running: 
		Either import the project as maven project in eclipse. And run on server (I have used tomcat 8).
		OR
		maven clean install
		deploy to tomcat
		
		hit on browser "http://localhost:9090/LoginextCaseStudy/index.jsp" to run. Check further user specific instruntion on index.jsp page once the app is up and running.
