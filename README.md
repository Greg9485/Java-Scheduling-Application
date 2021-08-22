# Java-Scheduling-Application

[README.txt](https://github.com/Greg9485/Java-Scheduling-Application/files/7027456/README.txt)

README: 
	Title: BioChem Solutions, LLC Scheduling Application

	Author: Greg Boydston
	Contact Info: Gboydst@wgu.edu
	Application Version 879.0
	Date: 8/22/2021

	IDE Version: Apache Netbeans 11.0.8  
	JDK Version: Java SE 11.0.8
	JavaFX Version: JavaFX-SDK-11.0.2

How to Run Program: 
This project requires a MySQL Connector. I used mysql-connector-java-8.0.22.jar
Application Log In: 
	Username: test 
	Password: test 
	(username/password are both case sensitive)

Databse login information: 
JDBC URL: jdbc:mysql://wgudb.ucertify.com/WJ05euQ
MySql Driver: com.mysql.jdbc.Driver	
Username: U05euQ
Password: 53688483050


Purpose: 	

Log-in form with the following capabilities:
	•  accepts a user ID and password and provides an appropriate error message
	•  determines the user’s location (i.e., ZoneId) and displays it in a label on the log-in form
	•  displays the log-in form in English or French based on the user’s computer language setting to translate all the text, labels, buttons, and errors on the form
	•  automatically translates error control messages into English or French based on the user’s computer language setting

Provides the following customer record functionalities:
•  Customer records and appointments can be added, updated, and deleted.
	-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.
•  When adding and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and phone number.
	-  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are collected using separate combo boxes.
	-  When updating a customer, the customer data autopopulates in the form.
•  Country and first-level division data is prepopulated in separate combo boxes or lists in the user interface for the user to choose. The first-level list should be filtered by the user’s selection of a country.
•  All of the original customer information is displayed on the update form.
	-  Customer_ID must be disabled.
•  All of the fields can be updated except for Customer_ID.
•  Customer data is displayed using a TableView, including first-level division data. A list of all the customers and their information may be viewed in a TableView, and updates of the data can be performed in text fields on the form.
•  When a customer record is deleted, a custom message should display in the user interface.
 

Add scheduling functionalities to the GUI-based application by doing the following:
	a.  Enables the user to add, update, and delete appointments. Includes the following functionalities:
		•  A contact name is assigned to an appointment using a drop-down menu or combo box.
		•  A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.
		•  The Appointment_ID is auto-generated and disabled throughout the application.
		•  When adding and updating an appointment, text fields are used to record the following data: Appointment_ID, title, description, location, contact, type, start date and time, end date and time, Customer_ID, and User_ID.
		•  All of the original appointment information is displayed on the update form in local time zone.
		•  All of the appointment fields can be updated except Appointment_ID, which must be disabled.
 	b.  Enables the user to view appointment schedules by month and week using a TableView and allows the user to choose between these two options using radio buttons for filtering appointments. Includes each of the following requirements as columns:
		•  Appointment_ID
		•  Title
		•  Description
		•  Location
		•  Contact
		•  Type
		•  Start Date and Time
		•  End Date and Time
		•  Customer_ID
 	c. Enables the user to adjust appointment times. While the appointment times are stored in Coordinated Universal Time (UTC), they are automatically and consistently updated according to the local time zone set on the user’s computer wherever appointments are displayed in the application.
 	d. Implements input validation and logical error checks to prevent each of the following changes when adding or updating information; display a custom message specific for each error check in the user interface:
		•  scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends
		•  scheduling overlapping appointments for customers
		•  entering an incorrect username and password
 	e. Provides an alert when there is an appointment within 15 minutes of the user’s log-in. A custom message is displayed in the user interface and include the appointment ID, date, and time. If the user does not have any appointments within 15 minutes of logging in, a custom message is displayed in the user interface indicating there are no upcoming appointments.
 	f. Generates accurate information in each of the following reports and will display the reports in the user interface:
 		•  the total number of customer appointments by type and month
		•  a schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID
		•  Filter all Customer appointments by Customer_ID and displays the type of appointment, appointmet ID, Date/Time, Contact, and total number of appointments this customer has
 
Two different lambda expressions included in project (See Login Close Alert and ViewAppointments Week/Month filters).
Provides the ability to track user activity by recording all user log-in attempts, dates, and time stamps and whether each attempt was successful in a file named login_activity.txt. Append each new record to the existing file, and save to the root folder of the application.
