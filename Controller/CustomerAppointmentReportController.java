/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import Utils.DBConnection;
import Utils.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class CustomerAppointmentReportController implements Initializable {

    @FXML
    private Button CloseButton;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    @FXML
    private TableColumn<Appointment, String> AppointmentType;
    @FXML
    private Label TotalAppointments;
    @FXML
    private TableView<Appointment> CustAptTableView;
    @FXML
    private TableColumn<Appointment, String> Start;
    @FXML
    private TableColumn<Appointment, String> End;
    @FXML
    private ComboBox<Integer> CustomerChoice;

    /**
     * Initializes the controller class.
     * 
     *
     * Populate Customer IDs
     *
     * @param url - Initialize URL
     * @param rb - ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            CustomerChoice.setItems(Reports.getAllCustomerIDs());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAppointmentReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    /**
    * Close screen and open main screen
    */
    private void CloseButtonHandler(ActionEvent event) throws IOException {  
        Stage stage;
        Parent root;
        stage=(Stage) CloseButton.getScene().getWindow();

        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

        root = loader.load();
            
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    /**
    * Pulls all appointments for selected customer and populates in tableview
    */
    private void CustomerChoice(ActionEvent event) throws SQLException {        
        int customerID = CustomerChoice.getValue();
          
        Reports.getAllCustomerAppointments().clear();
        
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select * FROM appointments WHERE Customer_ID = " + customerID;    
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
        
        CustAptTableView.setItems(Reports.getAllCustomerAppointments());

        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        Start.setCellValueFactory(new PropertyValueFactory<>("start"));
        End.setCellValueFactory(new PropertyValueFactory<>("end"));
        AppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        int totalCustAppts = (Reports.getAllCustomerAppointments().size() + 1);
        if(totalCustAppts == 1){
            TotalAppointments.setText("Total Appointments for Customer: " + 0);
        }else{
            TotalAppointments.setText("Total Appointments for Customer: " + totalCustAppts);
        }
        
    }       
}

