/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import greg.boydston.scheduling.application.Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class ViewAppointmentsController implements Initializable {

    @FXML
    private TableView<Appointment> viewAppointmentsHandler;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    private TableColumn<Appointment, Integer> UserID;
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
    private RadioButton WeeklyAppointments;
    @FXML
    private RadioButton AllAppointments;
    @FXML
    private RadioButton MonthlyAppointments;
    @FXML
    private Button CloseButton;
  /* @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    */@FXML
    private TableColumn<Appointment, String> Type;
    
    
    private int plusMinusView = 1;

    /**
     * Initializes the controller class. 
     * 
     * 
     *
     * WeeklyAppointmentHandler Method- CONTAINS LAMBDA.  
     * Uses Lambda Expression to filter appointments by week
     * Lambda minimizes amount of code needed to populate data in tableview
     * 
     * Sets all appointments into TableView fields
     * 
     * Monthly Appointments Handler - Contains Lambda. 
     * 
     * Uses lambda expression to filter appointments by month
     * Lambda minimizes amount of code needed to populate data in TableView
     * 
     * @param url - Initialize URL
     * @param rb - ReourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AllAppointments.setSelected(true);
        if(AllAppointments.isSelected()){
            WeeklyAppointments.setSelected(false);
            MonthlyAppointments.setSelected(false);
            
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
    }    

    /**
    * Weekly Appointment Handler. 
    * CONTAINS LAMBDA.Uses Lambda Expression to filter appointments by week
    * Lambda minimizes amount of code needed to populate data in tableview
    * @param event - ActionEvent
    */
    @FXML
    public void WeeklyAppointmentsHandler(ActionEvent event) {   
        if(WeeklyAppointments.isSelected()){
            AllAppointments.setSelected(false);
            MonthlyAppointments.setSelected(false);
            plusMinusView = 1;
            
            LocalDate today = LocalDate.now();
            LocalDate thisWeek = today.plusWeeks(plusMinusView);
            DateTimeFormatter looksGood = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            //lambda expression to filter appts by week
            FilteredList<Appointment> weeklyAppointments = new FilteredList<>(Appointment.getAllAppointments());
            weeklyAppointments.setPredicate(week -> {
                LocalDate rowDate = LocalDate.parse(week.getStart(), looksGood);
                return rowDate.isAfter(today.minusDays(1)) && rowDate.isBefore(thisWeek);
            });
            viewAppointmentsHandler.setItems(weeklyAppointments);
        }
    }

    @FXML
    /**
    * Shows all appointments 
    */
    private void AllAppointmentsHandler(ActionEvent event) {
        if(AllAppointments.isSelected()){
            WeeklyAppointments.setSelected(false);
            MonthlyAppointments.setSelected(false);
            
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
    }

    
    /**
    * Monthly Appointments Handler - Contains Lambda. 
    * 
    * Uses lambda expression to filter appointments by month
    * Lambda minimizes amount of code needed to populate data in TableView
    */
    @FXML
    public void MonthlyAppointmentsHandler(ActionEvent event) {
        if(MonthlyAppointments.isSelected()){
            AllAppointments.setSelected(false);
            WeeklyAppointments.setSelected(false);
            plusMinusView = 1;
            
            LocalDate today = LocalDate.now();
            LocalDate thisMonth = today.plusMonths(plusMinusView);
            DateTimeFormatter looksGood = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            //lambda expression to filter appts by month
            FilteredList<Appointment> monthlyAppointments = new FilteredList<>(Appointment.getAllAppointments());
            monthlyAppointments.setPredicate(month -> {
                LocalDate rowDate = LocalDate.parse(month.getStart(), looksGood);
                return rowDate.isAfter(today.minusDays(1)) && rowDate.isBefore(thisMonth);
            });
            viewAppointmentsHandler.setItems(monthlyAppointments);
        }   
    }
    
  /*  
    @FXML
    private void nextButtonHandler(ActionEvent event) {
        plusMinusView++;
        System.out.println(plusMinusView);
    }
    
    @FXML
    private void previousButtonHandler(ActionEvent event) {
        plusMinusView--;
        System.out.println(plusMinusView);
    }
    */
    @FXML
    /**
    * Closes scene and opens main screen
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
}
    
