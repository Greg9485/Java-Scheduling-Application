/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Model.Reports;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class SearchAppointmentsController implements Initializable {

    @FXML
    private TableView<Appointment> viewAppointmentsHandler;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    @FXML
    private TableColumn<Appointment, Integer> CustomerID;
    @FXML
    private TableColumn<Appointment, String> Title;
    @FXML
    private TableColumn<Appointment, String> Description;
    @FXML
    private TableColumn<Appointment, String> Location;
    @FXML
    private TableColumn<Appointment, String> Contact;
    @FXML
    private TableColumn<Appointment, String> Start;
    @FXML
    private TableColumn<Appointment, String> End;
    @FXML
    private TableColumn<Appointment, String> Type;
    @FXML
    private Button SearchByMonthHandler;
    @FXML
    private Button SearchByWeekHandler;
    @FXML
    private Button ShowAllAppointmentsHandler;
    @FXML
    private Button CloseButton;
    
    @FXML
    private TextField SearchText;
    
    private int plusMinusView = 1;
  
//TODO
    /*
        1. UPDATE SEARCH BUTTONS TO PULL THE CUTSOMER APPOINTMENTS BY MONTH/WEEK/ALL APPOINTMENTS FROM THE CUSTOMER ID
            ENTERED IN THE SEARCH FIELD
        
    TIP:    SEE VIEWAPPOINTMENTSCONTROLLER, AND TURN THE RADIO BUTTONS INTO SEARCH FUNCTIONALITY
    
        WHERE WE'RE AT: 
                NEED TO PULL THE CUSTOMER ID FROM THE SEARCH FIELD
                    IF NUMBER ENTERED != AN ACTUAL CUSTOMER ID THROW AN ERROR AND ADVISE OF CURRENT CUSTOMER IDs
                    IF THE TEXT ENTERED != INT - THROW ERROR THAT USER MUST ENTER A VALID CUSTOMER ID AS INT
    
                NEED TO PULL ALL CUSOMTER DATA FROM THIS MONTH/WEEK FROM THE CUSTOMER ID ENTERED IN THE TEXT FIELD. 
                    ESSENTIALLY WE'RE CHANGING THE VIEWAPPOINTMENTS SCREEN INTO A SEARCH APPOINTMENTS SCREEN. 
    
                ALL APPOINTMENTS HAVE BEEN UPDATED TO THIS WEEK (8/8 + 5 DAYS) 
    */

    public void initialize(URL url, ResourceBundle rb) {
                    
            viewAppointmentsHandler.setItems(Appointment.getAllAppointments());

            AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            Title.setCellValueFactory(new PropertyValueFactory<>("title"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            Start.setCellValueFactory(new PropertyValueFactory<>("start"));
            End.setCellValueFactory(new PropertyValueFactory<>("end"));
            Location.setCellValueFactory(new PropertyValueFactory<>("location"));
            Description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contact")); 
            
    } 
    
    public static boolean isNum(String strNum){
        if(strNum == null){
            return false;
        }
      
        try{
          int searchAppointment = Integer.parseInt(strNum);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    
    @FXML
    public void SearchByWeekHandler(ActionEvent event) throws SQLException{
     String searchAppointment = SearchText.getText();
        for(Customer custSearched:Customer.getAllCustomers()){
            if(custSearched.getCustName().equals(searchAppointment)||Integer.toString(custSearched.getCustID()).equals(searchAppointment)){
                   System.out.println("first IF: " + custSearched.getCustName() + " " + custSearched.getCustID());
                   
                    int customerID = custSearched.getCustID();
          
                    Reports.getAllCustomerAppointments().clear();

                    Connection conn1 = DBConnection.startConnection();        
                    String selectPreparedStatement = "Select * from appointments where customer_ID = " + customerID + " and Week(start) = Week(sysdate());";     
                    DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
                    PreparedStatement statement = DBQuery.getPreparedStatement(); 

                    statement.execute(selectPreparedStatement);                 
                    ResultSet rs = statement.getResultSet();           

                    while(rs.next()){
                        int appointmentID = rs.getInt("Appointment_ID");
                        int contactID = rs.getInt("Contact_ID");
                        String title = rs.getString("Title");
                        String description = rs.getString("Description");
                        String location = rs.getString("Location");
                        String type = rs.getString("Type");
                        String start = rs.getString("Start");
                        String end = rs.getString("End");
                        int userID = rs.getInt("User_ID");
                        String aptID = Integer.toString(appointmentID);
                        String custID = Integer.toString(customerID);

                        Reports.addCustomerAppointmentList(new Appointment(appointmentID, customerID, title, description, location, contactID, type, userID, start, end));
                     }
                 viewAppointmentsHandler.setItems(Reports.getAllCustomerAppointments());
            }
        }
    }        
    
    @FXML
    public void SearchByMonthHandler(ActionEvent event) throws SQLException{
        String searchAppointment = SearchText.getText();
        for(Customer custSearched:Customer.getAllCustomers()){
            if(custSearched.getCustName().equals(searchAppointment)||Integer.toString(custSearched.getCustID()).equals(searchAppointment)){
                   System.out.println("first IF: " + custSearched.getCustName() + " " + custSearched.getCustID());
                   
                    int customerID = custSearched.getCustID();
          
                    Reports.getAllCustomerAppointments().clear();

                    Connection conn1 = DBConnection.startConnection();        
                    String selectPreparedStatement = "Select * from appointments where customer_ID = " + customerID + " and Month(start) = Month(sysdate());";     
                    DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
                    PreparedStatement statement = DBQuery.getPreparedStatement(); 

                    statement.execute(selectPreparedStatement);                 
                    ResultSet rs = statement.getResultSet();           

                    while(rs.next()){
                        int appointmentID = rs.getInt("Appointment_ID");
                        int contactID = rs.getInt("Contact_ID");
                        String title = rs.getString("Title");
                        String description = rs.getString("Description");
                        String location = rs.getString("Location");
                        String type = rs.getString("Type");
                        String start = rs.getString("Start");
                        String end = rs.getString("End");
                        int userID = rs.getInt("User_ID");
                        String aptID = Integer.toString(appointmentID);
                        String custID = Integer.toString(customerID);

                        Reports.addCustomerAppointmentList(new Appointment(appointmentID, customerID, title, description, location, contactID, type, userID, start, end));
                     }

                 viewAppointmentsHandler.setItems(Reports.getAllCustomerAppointments());
            }
        }
    }         
    
    @FXML
    public void ShowAllAppointmentsHandler(ActionEvent event) throws SQLException{
       String searchAppointment = SearchText.getText();
        for(Customer custSearched:Customer.getAllCustomers()){
            if(custSearched.getCustName().equals(searchAppointment)||Integer.toString(custSearched.getCustID()).equals(searchAppointment)){
                   System.out.println("first IF: " + custSearched.getCustName() + " " + custSearched.getCustID());
                   
                    int customerID = custSearched.getCustID();
          
                    Reports.getAllCustomerAppointments().clear();

                    Connection conn1 = DBConnection.startConnection();        
                    String selectPreparedStatement = "Select * from appointments where customer_ID = " + customerID;     
                    DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
                    PreparedStatement statement = DBQuery.getPreparedStatement(); 

                    statement.execute(selectPreparedStatement);                 
                    ResultSet rs = statement.getResultSet();           

                    while(rs.next()){
                        int appointmentID = rs.getInt("Appointment_ID");
                        int contactID = rs.getInt("Contact_ID");
                        String title = rs.getString("Title");
                        String description = rs.getString("Description");
                        String location = rs.getString("Location");
                        String type = rs.getString("Type");
                        String start = rs.getString("Start");
                        String end = rs.getString("End");
                        int userID = rs.getInt("User_ID");
                        String aptID = Integer.toString(appointmentID);
                        String custID = Integer.toString(customerID);

                        Reports.addCustomerAppointmentList(new Appointment(appointmentID, customerID, title, description, location, contactID, type, userID, start, end));
                     }

                 viewAppointmentsHandler.setItems(Reports.getAllCustomerAppointments());
            }else if(!custSearched.getCustName().equals(searchAppointment)&&!Integer.toString(custSearched.getCustID()).equals(searchAppointment)){
             Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid Customer ID as an Integer.");
             alert.setTitle("Invalid Customer ID!");
             Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    @FXML
    public void CloseButtonHandler(ActionEvent event) throws IOException{
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
    


