/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;


import DAOImplimentation.AppointmentsDB;
import DAOImplimentation.ContactsDB;
import DAOImplimentation.CustomersDB;
import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.Contact;
import greg.boydston.scheduling.application.Model.Country;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Model.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static java.time.ZoneOffset.UTC;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private Label CommunicationsLabel;
    @FXML
    private TextField AppointmentIDText;
    @FXML
    private TextField UserIDText;
    @FXML
    private TextField CustomerIDText;
    @FXML
    private TextField TitleText;
    @FXML
    private TextField DescriptionText;
    @FXML
    private TextField LocationText;
    @FXML
    private TextField TypeText;
    @FXML
    private Button SaveButton;
    @FXML
    private Button CloseButton;
    @FXML
    private ChoiceBox<String> StartHour;
    /*@FXML
    private ChoiceBox<String> EndAmPm;
    @FXML
    private ChoiceBox<String> StartAmPm;
    */@FXML
    private ChoiceBox<String> EndMinute;
    @FXML
    private ChoiceBox<String> EndHour;
    @FXML
    private ChoiceBox<String> StartMinute;
    @FXML
    private DatePicker StartDateField;
    @FXML
    private ComboBox<String> ContactChoiceID;
    @FXML
    private DatePicker EndDateField;
  
  
    ObservableList<String> hours = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    ObservableList<String> minutes = FXCollections.observableArrayList("00", "15", "30", "45");
  
    
    /**
     * Adds an appointment to the table "appointments" in the Database and Application's Appointments TableView. 
     * 
     * <p>Includes the initialize method which sets the values for all combo boxes and the Appointment ID</p>
     * <p>Includes a close method which will bring the user back to the main screen without adding an appointment </p>
     * <p>Close method throws an alert warning user that changes will not be saved</p>
     * 
     * <p>Includes a save method which does the following:
     * <p>Throws errors if:  
     * <ul>
     *     <li> Customer ID and/or User ID are not in database
     *     <li> Start date/time is after End date/time
     *     <li> Any required field is blank
     *     <li> A customer is double booked 
     *     <li> Appointment time is outside operating business hours
     *  </ul>
     *  <p> Pulls user input from fillable fields and pushes data to database</p>
     *  <p> Converts dates and times from local user time to UTC time before sending to database</p>
     * 
     * 
     * @param url - Sets link for JavaDoc
     * @param rb -Initializes resource bundle
     */
    @Override
    /**
    * Sets contents of combo boxes
    * Sets Appointment ID 
    */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StartHour.setItems(hours);
        EndHour.setItems(hours);
        StartMinute.setItems(minutes);
        EndMinute.setItems(minutes);
        
                
        ObservableList<String> contact;
        ObservableList<Employee> loggedInEmployees;
        CommunicationsLabel.setText("");
        try{
          //Pull AppointmnentID from Database
            Connection conn = DBConnection.startConnection(); 
            String selectStatement = "SELECT MAX(Appointment_ID) FROM appointments";
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();   //Execute Prepared Statement

            ResultSet rs = ps.getResultSet();

           while(rs.next()){
               //Set AppointmentID Field to current MAX AppointmentID + 1
                int appointmentID = rs.getInt("MAX(Appointment_ID)") + 1;
            
                loggedInEmployees = Employee.getEmployee();

                
    //Set AppointmentID to Pull from Database
                AppointmentIDText.setText(String.valueOf(appointmentID));
                
                
                contact = Contact.getContacts();
                ContactChoiceID.setItems(contact);
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void AppointmentIDTextHandler(ActionEvent event) {
    }

    @FXML
    private void UserIDTextHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerIDTextHandler(ActionEvent event) {
    }

    @FXML
    private void TitleTextHandler(ActionEvent event) {
    }

    @FXML
    private void DescriptionTextHandler(ActionEvent event) {
    }

    @FXML
    private void LocationTextHandler(ActionEvent event) {
    }

    @FXML
    private void TypeTextHandler(ActionEvent event) {
    }


    @FXML
    /**
    * Saves Appointment in application and database
    * Converts appointment start/end date/time from User's local time to UTC and saves in Database
    * <p><ul>
    *     <p>Throws errors if: 
    *     <li> Customer ID and/or User ID are not in database
    *     <li> Start date/time is after End date/time
    *     <li> Any required field is blank
    *     <li> A customer is double booked 
    *     <li> Appointment time is outside operating business hours
    *  </ul>
    * 
    */
    private void SaveButtonHandler(ActionEvent event) throws IOException, SQLException {  
//Pulling Customer IDS from DATABASE
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "SELECT Customer_ID FROM customers";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 
    
        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           
           
        while(rs1.next()){
            int customerID = rs1.getInt("Customer_ID");
            Customer.addCustIDs(customerID);
        }
        
        int appID = Integer.parseInt(AppointmentIDText.getText());
        int custID = Integer.parseInt(CustomerIDText.getText());
        int userID = Integer.parseInt(UserIDText.getText());
        String appTitle = TitleText.getText();
        String appDescription = DescriptionText.getText();
        String appLocation = LocationText.getText();
        String appContact = ContactChoiceID.getValue();
        String appType = TypeText.getText();
        String createdBy = Employee.loggedInEmployee.toString();
        String updatedBy = Employee.loggedInEmployee.toString();
        
//Time
        LocalDate startDate = StartDateField.getValue();
        LocalDate endDate = EndDateField.getValue();
        LocalTime startTime = LocalTime.of(Integer.parseInt(StartHour.getValue()), Integer.parseInt(StartMinute.getValue()));
        LocalTime endTime = LocalTime.of(Integer.parseInt(EndHour.getValue()), Integer.parseInt(EndMinute.getValue()));
        LocalDateTime startLDT = LocalDateTime.of(startDate, startTime);
        LocalDateTime endLDT = LocalDateTime.of(endDate, endTime);
        //obtain ZonedDateTime version of LocalDateTime
        ZonedDateTime startZDT = ZonedDateTime.of(startLDT, ZoneId.systemDefault());
        ZonedDateTime endZDT = ZonedDateTime.of(endLDT, ZoneId.systemDefault());


        //obtain the UTC ZonedDateTime of the ZonedDateTime version of LocalDateTime
        ZonedDateTime startUTC = startZDT.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime endUTC = endZDT.withZoneSameInstant(ZoneOffset.UTC);
        LocalDate today = LocalDate.now();

        //Format so "T" is not there
        DateTimeFormatter looksGood = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long timeDifference = ChronoUnit.MINUTES.between(startTime, endTime);
        
        //Setting Eastern Time to Check Opening/Closing Times
        DateTimeFormatter openingTimes = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime opening = LocalTime.of(8, 0);
        LocalTime closing = LocalTime.of(22, 0);
        LocalDateTime openingBusinessHours = LocalDateTime.of(startDate, opening);
        LocalDateTime closingBusinessHours = LocalDateTime.of(endDate, closing);
        ZoneId eastern = ZoneId.of("US/Eastern");
        ZonedDateTime easternOpening = ZonedDateTime.of(openingBusinessHours, eastern);
        ZonedDateTime easternClosing = ZonedDateTime.of(closingBusinessHours, eastern);
        
        ZonedDateTime apptOpening = ZonedDateTime.of(startLDT, eastern);
        ZonedDateTime apptClosing = ZonedDateTime.of(endLDT, eastern);
        ZonedDateTime custUTCAptStart = ZonedDateTime.of(startLDT, UTC);
        ZonedDateTime custUTCAptEnd = ZonedDateTime.of(endLDT, UTC);
        
        
      //WRITING CODE TO CHECK IF THE CUSTOMER HAS ANY OTHER APPOINTMENTS FOR THIS TIME
      //PULL APPOINTMENT ID, START TIME, END TIME WHERE CUST_ID == THIS CUSTOMER_ID
      //IF CUST_ID HAS 0 APPOINTMENTS - BREAK
      //IF THIS APPOINTMENT IS BETWEEN APPOINTMENT ID 1 START AND END TIME - THROW ERROR
      //IF THIS APPOINTMENT IS NOT BETWEEN APPOINTMENT ID 1 START AND END TIME, ITERATE THROUGH APPOINTMENTS FOR 
       //THIS CUSTOMER - IF APPOINTMENT IS NOT BETWEEN APPOINTMENT START AND END TIMES, SCHEDULE APPOINTMENT
       
        Connection conn3 = DBConnection.startConnection();        
        String selectPreparedStatement2 = "SELECT Appointment_ID, Start, End FROM appointments Where Customer_ID = " + custID;    
        DBQuery.setPreparedStatement(conn3, selectPreparedStatement2); 
        PreparedStatement statement2 = DBQuery.getPreparedStatement(); 
    
        statement2.execute(selectPreparedStatement2);                 
        ResultSet rs3 = statement2.getResultSet();           
   
        Boolean notDoubleBooked = true;
        while(rs3.next()){
            String aptStart = rs3.getString("Start");
            String aptEnd = rs3.getString("End");
            //Need to convert Strings to LocalDateTime/ZonedDateTimes? then compare with appointment start/end
            //see above for if/elses and implement them - then part 1 is done
            
            DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            //Splits the date and time at the empty space between them
            //Saves date and time as a String Array
            String splitter = "[ ]";
            String[] splitStartDateTime = aptStart.split(splitter);
            String[] splitEndDateTime = aptEnd.split(splitter);

            
            //Create individual strings for date and time
            String startDateString = splitStartDateTime[0];
            String startTimeString = splitStartDateTime[1];
            String endDateString = splitEndDateTime[0];
            String endTimeString = splitEndDateTime[1];
            

            //Setting Strings of Date and Time to LocalDate and LocalTime then to LocalDateTime
            //Showing in UTC from the Database - need to convert to user's local time
            LocalTime apptStartTime = LocalTime.parse(startTimeString, dateTime);
            LocalDate apptStartDate = LocalDate.parse(startDateString);
            LocalDateTime apptStartLDT = LocalDateTime.of(apptStartDate, apptStartTime); //All in UTC from DB

                        
            LocalTime apptEndTime = LocalTime.parse(endTimeString, dateTime);
            LocalDate apptEndDate = LocalDate.parse(endDateString);
            LocalDateTime apptEndLDT = LocalDateTime.of(apptEndDate, apptEndTime); //All in UTC from DB
            
            //setting local ZoneId and Database ZoneId of UTC
            ZoneId dbZoneId = ZoneId.of("UTC");
            ZoneId myZoneId = ZoneId.systemDefault();

            //Creates db's ZDT
            ZonedDateTime dbZDTStart = ZonedDateTime.of(apptStartLDT, dbZoneId);
            ZonedDateTime dbZDTEnd = ZonedDateTime.of(apptEndLDT, dbZoneId);
            //Create's user's local ZDT - i dont think i need this here
            ZonedDateTime myZDTStart = ZonedDateTime.ofInstant(dbZDTStart.toInstant(), myZoneId);
            ZonedDateTime myZDTEnd = ZonedDateTime.ofInstant(dbZDTEnd.toInstant(), myZoneId);
            //Converts dbZDT to User's Local ZDT
            dbZDTStart = ZonedDateTime.ofInstant(dbZDTStart.toInstant(), myZoneId);
            dbZDTEnd = ZonedDateTime.ofInstant(dbZDTEnd.toInstant(), myZoneId);

            if(dbZDTStart.isAfter(startZDT) && dbZDTStart.isBefore(endZDT)){
               notDoubleBooked = false;
            }else if(dbZDTEnd.isBefore(endZDT) && dbZDTEnd.isAfter(startZDT)){
                notDoubleBooked = false;
            }
        }
        
    //THROWING ALERTS
        if(UserIDText.getText().isBlank()){
            CommunicationsLabel.setText("Please enter a valid User ID.");
        }else if(!Employee.getUsersID().contains(Integer.parseInt(UserIDText.getText()))){
            CommunicationsLabel.setText("Please enter a valid User ID.");
        }else if(CustomerIDText.getText().isBlank()){
            CommunicationsLabel.setText("Please enter a valid Customer ID.");
        }else if(!Customer.getCustIDs().contains(Integer.parseInt(CustomerIDText.getText()))){
            CommunicationsLabel.setText("Please enter a valid Customer ID.");
        }else if(TitleText.getText().isEmpty()){
            CommunicationsLabel.setText("Please enter a title for the appointment.");
        }else if(DescriptionText.getText().isEmpty()){
            CommunicationsLabel.setText("Please enter a description for the appointment.");
        }else if(LocationText.getText().isEmpty()){
            CommunicationsLabel.setText("Please enter a location for the appointment.");
        }else if(ContactChoiceID.getSelectionModel().isEmpty()){
            CommunicationsLabel.setText("Please choose a contact.");
        }else if(TypeText.getText().isEmpty()){
            CommunicationsLabel.setText("Please enter the type of appointment.");
        }else if(StartDateField.getValue() == null){
            CommunicationsLabel.setText("Please enter a start date.");
        }else if(StartHour.getValue().isEmpty() || StartMinute.getValue().isEmpty()){
            CommunicationsLabel.setText("Please enter a valid start time for the appointment.");
        }else if(EndDateField.getValue() == null){
            CommunicationsLabel.setText("Please enter a start date.");
        }else if(EndHour.getValue().isEmpty() || EndMinute.getValue().isEmpty()){
            CommunicationsLabel.setText("Please enter a valid end time for the appointment.");
        }else if(StartDateField.getValue().isAfter(EndDateField.getValue())){
            CommunicationsLabel.setText("Start Date cannot be after End Date.");
        }else if((timeDifference <= 0) && (StartDateField.getValue().equals(EndDateField.getValue()))){
            CommunicationsLabel.setText("Start Time must be before End Time.");
        }else if(startDate.isBefore(today)){
            CommunicationsLabel.setText("Start Date cannot be before today.");
        }else if(easternOpening.isAfter(apptOpening)){ 
            CommunicationsLabel.setText("Start time must be after 8:00am EST.");
        }else if(easternClosing.isBefore(apptClosing)){
            CommunicationsLabel.setText("End time must be before 10:00pm EST.");
        }else if(notDoubleBooked == false){
            CommunicationsLabel.setText("Cusomter double booked!");
        }else{
            try{        
            //SENDING DATES/TIMES TO DATABASE IN UTC - WORKING!
               String startDateTimeDB = startUTC.toLocalDate().toString() + " " + startUTC.toLocalTime().toString();
               String endDateTimeDB = endUTC.toLocalDate().toString() + " " + endUTC.toLocalTime().toString();

            //Pull ContactID from ContactDB
                Connection conn = DBConnection.startConnection(); 
                String contactIDSelectStatement = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + appContact +"'";
                DBQuery.setPreparedStatement(conn, contactIDSelectStatement);

                PreparedStatement ps1 = DBQuery.getPreparedStatement();
                ps1.execute();

                ResultSet rs = ps1.getResultSet();
                while(rs.next()){
                    int contactID = rs.getInt("Contact_ID");

     //Add Appointment to Appointments in Application
                    Appointment.addAppointment(new Appointment(appID, custID, appTitle, appDescription, appLocation, contactID, appType, userID, looksGood.format(startZDT), looksGood.format(endZDT)));

     //Add Appointment to Database

                    Connection conn2 = DBConnection.startConnection(); 
                    String insertStatement = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, Start, End," /*Create_Date,*/ +" Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    DBQuery.setPreparedStatement(conn, insertStatement);

                    PreparedStatement ps = DBQuery.getPreparedStatement();

                    ResultSet rs2 = ps.getResultSet();

                   //key-value mapping
                    ps.setInt(1, appID);
                    ps.setString(2, appTitle);
                    ps.setString(3, appDescription);
                    ps.setString(4, appLocation);
                    ps.setString(5, appType);
                    ps.setString(6, startDateTimeDB);
                    ps.setString(7, endDateTimeDB);
                    ps.setString(8, createdBy);
                    ps.setString(9, updatedBy);
                    ps.setInt(10, custID);
                    ps.setInt(11, userID);
                    ps.setInt(12, contactID);

                    ps.execute();
            }   

                Stage stage;
                Parent root;
                stage=(Stage) SaveButton.getScene().getWindow();

                FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

                root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (SQLException ex) {
                Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    /**
    * Closes screen and loads main screen 
    * Throws alert to verify user wants to close this page
    */
    private void CloseButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to close? Changes won't be saved.");
        alert.setTitle("Close Alert!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage;
            Parent root;
            stage=(Stage) CloseButton.getScene().getWindow();

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void StartDateFieldHandler(ActionEvent event) {
    }   

    @FXML
    private void EndDateFieldHandler(ActionEvent event) {
    }
}
