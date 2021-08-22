/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import DAOImplimentation.AppointmentsDB;
import DAOImplimentation.ContactsDB;
import DAOImplimentation.CustomersDB;
import static DAOImplimentation.CustomersDB.SelectCustomerIDs;
import Utils.DBConnection;
import Utils.DBQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Model.Employee;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class. 
 * 
 * @author greg9485
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField PasswordText;
    @FXML
    private TextField UsernameText;
    @FXML
    private Button CloseButton;
    @FXML
    private Button SignInButton;
    @FXML
    private Label RegionLabel;
    @FXML
    private Label LanguageLabel;
    @FXML
    private Label UsernameLabel;
    @FXML
    private Label PasswordLabel;
    

    /**
     * Initializes the controller class.
     * CloseButtonHandler Method CONTAINS LAMBDA.  
     * Throws alert to verify user wants to close program (Checks user's language and throws alert in correct language) 
     * Lambda expressions is used to throw alert, which reduces code needed to produce alert and closes program if user clicks "OK" 
     * 
     * @param url - initialize url
     * @param rb ResourceBundle   
     */
    
    @Override
     //     * Checks user's language and sets labels to English or French
    public void initialize(URL url, ResourceBundle rb) {
        //LOCALIZATION - FINDING REGION AND LANGUAGE
        ZoneId myZoneId = ZoneId.systemDefault();
        rb = ResourceBundle.getBundle("ResourceBundles/rb_fr", Locale.getDefault());
        RegionLabel.setText(myZoneId.toString());
        if(Locale.getDefault().getLanguage().equals("en")){
            LanguageLabel.setText("English");
        }else if(Locale.getDefault().getLanguage().equals("fr")){
            LanguageLabel.setText("French");
            CloseButton.setText(rb.getString("Close"));
            SignInButton.setText(rb.getString("Sign") + " " + rb.getString("In"));
            UsernameText.setPromptText(rb.getString("Enter") + " " + rb.getString("Username"));
            PasswordText.setPromptText(rb.getString("Enter") + " " + rb.getString("Password"));
            UsernameLabel.setText(rb.getString("Username") + ":");
            PasswordLabel.setText(rb.getString("Password") + ":");
      
        }else{
            LanguageLabel.setText(Locale.getDefault().getLanguage());
        }
  }    

    @FXML
    private void PasswordTextHandler(ActionEvent event) {
    }

    @FXML
    private void UsernameTextHandler(ActionEvent event) {
    }

    /**
    * Close Button Handler - CONTAINS LAMBDA.  
    * Throws alert to verify user wants to close program (Checks user's language and throws alert in correct language) 
    * Lambda expressions is used to throw alert, which reduces code needed to produce alert and closes program if user clicks "OK"
    */
    @FXML
    private void CloseButtonHandler(ActionEvent event) {
        if(Locale.getDefault().getLanguage().equals("fr")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer le programme?");
                alert.setTitle("Alerte de sortie!");
                alert.showAndWait().ifPresent((close ->{
                    if(close == ButtonType.OK){
                        Platform.exit();
                    }
                })); 
        }else{
        //lmabda for alert for closing application
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the program?");
                alert.setTitle("Exit Alert!");
                alert.showAndWait().ifPresent((close ->{
                    if(close == ButtonType.OK){
                        Platform.exit();
                    }
                })); 
        }  
    }
    
    /**
    * Looks for any appointments within 15 minutes of user's local time - advises if there are any appointments in 15 minutes or not
    */    
    private void appointmentAlert(){
        //Looking for Appointments within 15 minutes 
        try{
            Connection conn4 = DBConnection.startConnection();        
            String selectPreparedStatement2 = "SELECT Appointment_ID, Type, Start FROM appointments";     //SELECT Statement

            DBQuery.setPreparedStatement(conn4, selectPreparedStatement2); //Create Statement Object

            PreparedStatement statement2 = DBQuery.getPreparedStatement(); //Get Statement Refrence

            statement2.execute(selectPreparedStatement2);                 //EXECUTE Statement - returns boolean - should be TRUE here
            ResultSet rs4 = statement2.getResultSet();            //Get ResultSet (in this case it gets all info from users TABLE

            System.out.println("Start while(rs4.next()) loop");
            while(rs4.next()){
                //Turn the appointment date/time to a string, split the date and time, pull the time from the string and
                //convert it to a localTime
                int apptID = rs4.getInt("Appointment_ID");
                String startDateTime = rs4.getString("Start");
                //Just sets the time's format and removes "T" in startDateTime
                DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                //Splits the date and time at the empty space between them
                //Saves date and time as a String Array
                String splitter = "[ ]";
                String[] splitDateTime = startDateTime.split(splitter);
                
                //Create individual strings for date and time
                String date = splitDateTime[0];
                String time = splitDateTime[1];
                
                //Setting Strings of Date and Time to LocalDate and LocalTime then to LocalDateTime
                //Showing in UTC from the Database - need to convert to user's local time
                LocalTime apptTime = LocalTime.parse(time, dateTime);
                LocalDate apptDate = LocalDate.parse(date);
                LocalDateTime apptStartLDT = LocalDateTime.of(apptDate, apptTime); //All in UTC from DB
                
                //setting local ZoneId and Database ZoneId of UTC
                ZoneId dbZoneId = ZoneId.of("UTC");
                ZoneId myZoneId = ZoneId.systemDefault();
                
                //Creates db's ZDT
                ZonedDateTime dbZDT = ZonedDateTime.of(apptStartLDT, dbZoneId);
                //Create's user's local ZDT - i dont think i need this here
                ZonedDateTime myZDT = ZonedDateTime.ofInstant(dbZDT.toInstant(), myZoneId);
                //Converts dbZDT to User's Local ZDT
                dbZDT = ZonedDateTime.ofInstant(dbZDT.toInstant(), myZoneId);
                
                //Shows current Time(HH:MM:SS:msssss)
                LocalTime timeNow = LocalTime.now();
                
                //Parsing the hour and minute in user's local time
                DateTimeFormatter justTimeHour = DateTimeFormatter.ofPattern("HH");
                DateTimeFormatter justTimeMinute = DateTimeFormatter.ofPattern("mm");
                int localHour = Integer.parseInt(justTimeHour.format(dbZDT));
                int localMinute = Integer.parseInt(justTimeMinute.format(dbZDT));
                
                LocalDate dateNow = LocalDate.now();
                LocalTime localStartTime = LocalTime.of(localHour, localMinute);
                
                //Check the difference between local start time for appt and user's local time now
                long timeDifference = ChronoUnit.MINUTES.between(localStartTime, timeNow);
                
                //Timedifference is showing up in negative time, and is 1 minute less than actual time
                long timeToMeeting = (timeDifference + -1) * -1;
                        
                
                //Alert for upcoming meeting or advises there are no upcoming meetings
                if(timeToMeeting <= 15 && timeToMeeting > 0 && apptDate.equals(dateNow)){
                    System.out.println("Start if statement");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upcoming Meeting");
                    alert.setContentText("You have an appointment starting in " + timeToMeeting + " minutes! \nAppointment ID: " +
                            apptID + "\nAppointment Date: " + date + "\nAppointment Time: " + localStartTime);
                    alert.showAndWait();
                }//If we made it to the last element of the resultset and the appointment date is today but 
                //it's more than 15 minutes from now, or the meeting time has passed, 
                //throw an alert that nomeetings are upcoming 
                else if(rs4.isLast() && (timeToMeeting > 15 || timeToMeeting < 0) && (apptDate.equals(dateNow))){                    
                    System.out.println("Start Else Statement Today but not 15 minutes");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Upcoming Meetings");
                    alert.setContentText("You have no appointments that start within 15 mintues"); 
                    alert.showAndWait();            
                }//if we made it to the last element of the resultset and the date is not today then give alert that
                //there are no meetings within 15 minutes. 
                else if(rs4.isLast() && (apptDate.equals(dateNow) == false)){                    
                    System.out.println("Start Else Statement not today");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Upcoming Meetings");
                    alert.setContentText("You have no appointments that start within 15 mintues"); 
                    alert.showAndWait();            
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Break out of loop and End Appointment Alert");
    }

    @FXML
    /**
    * Determines if username/password are valid - if not alert is thrown, if so user is able to log in - main screen loads
    * Checks if there is a logger file - if not one is created and login attempt is recorded - if logger file exists login attempt is added to logger file
    */
    private void SignInButtonHandler(ActionEvent event) throws Exception, IOException {      
        //Determines if there are any Rows in the Table, and scrolls through the columns of the rows collecting all of the information
        Connection conn = DBConnection.startConnection();        
        String selectPreparedStatement = "SELECT * FROM users";     //SELECT Statement
        DBQuery.setPreparedStatement(conn, selectPreparedStatement); //Create Statement Object
        PreparedStatement statement = DBQuery.getPreparedStatement(); //Get Statement Refrence
    
        statement.execute(selectPreparedStatement);                 //EXECUTE Statement - returns boolean - should be TRUE here
        ResultSet rs = statement.getResultSet();            //Get ResultSet (in this case it gets all info from users TABLE

           
        while(rs.next()){
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            int userID = rs.getInt("User_ID");

            String userPass = userName + " " + password;
            Employee.addUserPass(userPass);
        }
    
        String usernameTxt = UsernameText.getText();
        String passwordTxt = PasswordText.getText();
        String loginInfo = usernameTxt + " " + passwordTxt;
        
        
//Pull the user info into a txt file
        //Filename and item Variables - 
        //if needed to change file path - Write File Path Here when naming fileName
        String fileName = "login_activity.txt", item;
        String countFile = "count_file.txt", count;
        
        int numAttempts = 0;
        //Create File Object for Reading
        File file = new File(fileName);
        File countFileReader = new File(countFile);

        //Create FileWrite Object - needed to append to an existing file
        FileWriter fwriter = new FileWriter(fileName, true);
        FileWriter countFileWriter = new FileWriter(countFile, true);
                
        //Create and Open File
        PrintWriter outputFile = new PrintWriter(fwriter);
        PrintWriter countOutputFile = new PrintWriter(countFileWriter);
        
        //Check if file exists - if not, create the file
        if(!file.exists()){
            String loginAttempts = "Login attempt #: 1 || Username: " + usernameTxt + " || Log In Date: " +  LocalDate.now() + " || Log In TimeStamp: " + LocalTime.now(); 

            item = loginAttempts;
            outputFile.println(loginAttempts);

            if(Employee.getUserPass().contains(loginInfo)){
            //Set the Username for logged in user
               Employee thisUser = new Employee(UsernameText.getText(), PasswordText.getText());
               Employee.loggedInEmployee.add(thisUser);

               item = loginAttempts + "Login successfull\n";
               count = "1";
               
               outputFile.append(item);
               countOutputFile.append(count);

               //Pull Customers from database so they're populated in TableView on Main Screen
               CustomersDB.SelectCustomer();
               ContactsDB.SelectContact();
               AppointmentsDB.SelectAppointment();

               //Any Appointments in 15 minutes?          
               appointmentAlert();
               System.out.println("Appointment Alert 1!");

               Stage stage;
               Parent root;
               stage=(Stage) SignInButton.getScene().getWindow();

               FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

               root = loader.load();

               Scene scene = new Scene(root);
               stage.setScene(scene);
               stage.show();       
            }else{
                item = loginAttempts + "|| Login unsuccessfull\n";
                count = "1";
                outputFile.append(item);
                countOutputFile.append(count);
                if(Locale.getDefault().getLanguage().equals("fr")){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez saisir un nom d'utilisateur et un mot de passe valides.");
                    alert.setTitle("Les informations d'identification invalides!");
                    Optional<ButtonType> result = alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid username and password.");
                    alert.setTitle("Invalid Credentials!");
                    Optional<ButtonType> result = alert.showAndWait();
                }   
            }
        //The File Does Exist
        }else{               
            Scanner inputFile = new Scanner(file);
            Scanner countInputFile = new Scanner(countFileReader);
            int num = (int) countFileReader.length();
            
        //Read All Lines from File            
            while(inputFile.hasNext()){
                item = inputFile.nextLine();
                numAttempts = num + 1;
            }
            
            if(numAttempts == 0){
                ++numAttempts;
            }
            
            
            String loginAttempts = "Login attempt #: " + numAttempts + " || Username: " + 
                    usernameTxt + " || Log In Date: " +  LocalDate.now() + " || Log In TimeStamp: " + LocalTime.now(); 
            outputFile.append(loginAttempts);

            if(Employee.getUserPass().contains(loginInfo)){
            //Set the Username for logged in user
               Employee thisUser = new Employee(UsernameText.getText(), PasswordText.getText());
               Employee.loggedInEmployee.add(thisUser);

               item = " || Login successfull\n";
               outputFile.append(item);
               count = "1";
               countOutputFile.append(count);


               //Pull Customers from database so they're populated in TableView on Main Screen
               CustomersDB.SelectCustomer();
               ContactsDB.SelectContact();
               AppointmentsDB.SelectAppointment();

               //Any Appointments in 15 minutes?
               appointmentAlert();
               System.out.println("Appointment Alert 2!");
               
               Stage stage;
               Parent root;
               stage=(Stage) SignInButton.getScene().getWindow();

               FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

               root = loader.load();

               Scene scene = new Scene(root);
               stage.setScene(scene);
               stage.show();       
            }else{
                item = " || Login unsuccessfull\n";
                outputFile.append(item);
                count = "1";
                countOutputFile.append(count);
                
                if(Locale.getDefault().getLanguage().equals("fr")){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez saisir un nom d'utilisateur et un mot de passe valides.");
                    alert.setTitle("Les informations d'identification invalides!");
                    Optional<ButtonType> result = alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid username and password.");
                    alert.setTitle("Invalid Credentials!");
                    Optional<ButtonType> result = alert.showAndWait();
                }   
            }
        }
        //Close File
    outputFile.close();
    countOutputFile.close();
    }
}
    
