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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Model.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class AppointmentTypesReportController implements Initializable {

    @FXML
    private Button CloseButton;
    @FXML
    private RadioButton MarchRadio;
    @FXML
    private RadioButton OctoberRadio;
    @FXML
    private RadioButton SeptemberRadio;
    @FXML
    private RadioButton AugustRadio;
    @FXML
    private RadioButton JulyRadio;
    @FXML
    private RadioButton JuneRadio;
    @FXML
    private RadioButton MayRadio;
    @FXML
    private RadioButton AprilRadio;
    @FXML
    private RadioButton JanuaryRadio;
    @FXML
    private RadioButton FebruaryRadio;
    private TableColumn<String, String> AppointmentTypeColumn;
    private TableColumn<Integer, Integer> CountColumn;
    @FXML
    private RadioButton NovemberRadio;
    @FXML
    private RadioButton DecemberRadio;
    @FXML
    private ToggleGroup MonthView;

    
    
    
    int numApts = 0;
    @FXML
    private TextArea OutputArea;
    
    
    /**
     * Initializes the controller class.
     * @param url - Initialize URL
     * @param rb - ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void MarchRadioHandler(ActionEvent event) throws SQLException {  
        if(MarchRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
            Appointment.getAllAppointments();

            
         //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-03-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void OctoberRadioHandler(ActionEvent event) throws SQLException {
        if(OctoberRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
        
        
          //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-10-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           

        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
        if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void SeptemberRadioHandler(ActionEvent event) throws SQLException {
        if(SeptemberRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
        
        //Clear the list on apts
        Reports.getAllApts().clear();
          

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-09-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }  
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void AugustRadioHandler(ActionEvent event) throws SQLException {
        if(AugustRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }

        //Clear the list on apts
        Reports.getAllApts().clear();
          

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-08-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
        
        
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void JulyRadioHandler(ActionEvent event) throws SQLException {
        if(JulyRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
    //Clear the list on apts
        Reports.getAllApts().clear();
        
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-07-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void JuneRadioHandler(ActionEvent event) throws SQLException {
        if(JuneRadio.isSelected()){
            AprilRadio.setSelected(false);
            MayRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
     //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-06-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void MayRadioHandler(ActionEvent event) throws SQLException {
        if(MayRadio.isSelected()){
            AprilRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
   //Clear the list on apts
        Reports.getAllApts().clear();
        
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-05-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
     /**
     * Sets radio button and displays appointment types and number for selected month
     * Disables all other radio buttons
     */
    private void AprilRadioHandler(ActionEvent event) throws SQLException {
        if(AprilRadio.isSelected()){
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
  //Clear the list on apts
        Reports.getAllApts().clear();
        
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-04-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void JanuaryRadioHandler(ActionEvent event) throws SQLException {
         if(JanuaryRadio.isSelected()){
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            AprilRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
        }
        
   //Clear the list on apts
        Reports.getAllApts().clear();
        
        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-01-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{ 
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void FebruaryRadioHandler(ActionEvent event) throws SQLException {
      if(FebruaryRadio.isSelected()){
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            AprilRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
        }
        
       //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-02-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void NovemberRadioHandler(ActionEvent event) throws SQLException {
        if(NovemberRadio.isSelected()){
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
            DecemberRadio.setSelected(false);
            AprilRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
        }
        
   //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-11-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts);
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
        
    }

    @FXML
    /**
    * Sets radio button and displays appointment types and number for selected month
    * Disables all other radio buttons
    */
    private void DecemberRadioHandler(ActionEvent event) throws SQLException {
        if(DecemberRadio.isSelected()){
            MayRadio.setSelected(false);
            JuneRadio.setSelected(false);
            JulyRadio.setSelected(false);
            AugustRadio.setSelected(false);
            SeptemberRadio.setSelected(false);
            OctoberRadio.setSelected(false);
            MarchRadio.setSelected(false);
            FebruaryRadio.setSelected(false);
            NovemberRadio.setSelected(false);
            AprilRadio.setSelected(false);
            JanuaryRadio.setSelected(false);
        }
    
      //Clear the list on apts
        Reports.getAllApts().clear();
        

        Connection conn1 = DBConnection.startConnection();        
        String selectPreparedStatement = "Select Type,Count(*) FROM appointments WHERE Start LIKE '%-12-%' GROUP BY Type";    
        DBQuery.setPreparedStatement(conn1, selectPreparedStatement); 
        PreparedStatement statement = DBQuery.getPreparedStatement(); 

        statement.execute(selectPreparedStatement);                 
        ResultSet rs1 = statement.getResultSet();           


        while(rs1.next()){
            String aptType = rs1.getString("Type");
            System.out.println("Type: " + aptType);
            numApts = rs1.getInt("Count(*)");
            System.out.println("Count: " + numApts); 
            String newApt = "Appointment Type: " + aptType + " Total: " + numApts + " \n";
            Reports.addApt(newApt);
        }
        System.out.println(Reports.getAllApts());
           if(Reports.getAllApts().isEmpty()){
            OutputArea.setText("No appointments for this month.");
        }else{
            OutputArea.setText(Reports.getAllApts().toString());    
        }
    }
}
