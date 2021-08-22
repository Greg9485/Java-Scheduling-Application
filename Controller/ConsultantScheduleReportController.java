/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;


import Utils.DBConnection;
import Utils.DBQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.Contact;
import greg.boydston.scheduling.application.Model.Reports;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class ConsultantScheduleReportController implements Initializable {

    @FXML
    private TableView<Appointment> consultantScheduleTableView;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    @FXML
    private TableColumn<Appointment, Integer> CustomerID;
    @FXML
    private TableColumn<Appointment, String> Title;
    @FXML
    private TableColumn<Appointment, String> Description;
    @FXML
    private TableColumn<Appointment, String> AppointmentType;
    @FXML
    private TableColumn<Appointment, String> Start;
    @FXML
    private TableColumn<Appointment, String> End;
    @FXML
    private Button CloseButton;
    @FXML
    private ComboBox<String> ContactCombo;

    /**
     * Initializes the controller class.
     *
     * Sets combobox 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList contacts = Contact.getContacts();
        ContactCombo.setItems(contacts);
        
    }    

    @FXML
    /**
     * Closes screen and loads main screen
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
    * Associates Contact name with ID
    * Pulls all appointments for contact and displays in TableView
    */
    private void ContactComboHandler(ActionEvent event) {
       int contactID = 1;
        switch (ContactCombo.getValue()) {
            case "Anika Costa":
                Reports.getAllContactAppointments().clear();
                contactID = 1;
                break;
            case "Daniel Garcia":
                Reports.getAllContactAppointments().clear();
                contactID = 2;
                break;
            case "Li Lee":
                Reports.getAllContactAppointments().clear();
                contactID = 3;
                break;
            default:
                Reports.getAllContactAppointments().clear();
                break;
        }

        try{
            Connection conn = DBConnection.startConnection();
            String deleteStatement = "SELECT * FROM appointments WHERE Contact_ID = ?";
            DBQuery.setPreparedStatement(conn, deleteStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            
            ps.setInt(1, contactID);
            
            ps.execute();
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                int customerID = rs.getInt("Customer_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int userID = rs.getInt("User_ID");
                String aptID = Integer.toString(appointmentID);
                String custID = Integer.toString(customerID);
                System.out.println(start.toString());
                
                Reports.addContactAppointmentList(new Appointment(appointmentID, customerID, title, description, location, contactID, type, userID, start, end));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultantScheduleReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        consultantScheduleTableView.setItems(Reports.getAllContactAppointments());

        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Start.setCellValueFactory(new PropertyValueFactory<>("start"));
        End.setCellValueFactory(new PropertyValueFactory<>("end"));
        AppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
      } 
}
