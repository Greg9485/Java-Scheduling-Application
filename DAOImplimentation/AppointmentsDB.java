/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Appointment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.Alert;

/**
 *
 * @author greg9485
 */
public class AppointmentsDB {
    //CREATE STATEMENT
    /**
     * creates appointment in database. 
     * 
     * @throws SQLException if sql statement is invalid
     */
    public static void CreateAppointment() throws SQLException{
    //Connect to DataBase
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO appointments(Appointment_Name) VALUES(?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ResultSet rs = ps.getResultSet();
        
        String appointmentsName;
        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }
    
    
//READ(SELECT) STATEMENT
    /**
    * Selects appointment from database and adds to appointments in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void SelectAppointment() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentName = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Date startDate = rs.getDate("Start");          
            Time startTime = rs.getTime("Start");          
            Date endDate = rs.getDate("End");          
            Time endTime = rs.getTime("End");          
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            LocalDateTime start = LocalDateTime.of(startDate.toLocalDate(), startTime.toLocalTime());
            LocalDateTime end = LocalDateTime.of(endDate.toLocalDate(), endTime.toLocalTime());
            ZoneId myZoneId = ZoneId.systemDefault(); 
           
            //obtain ZonedDateTime version of LocalDateTime - not converting date back to local date
            ZonedDateTime startZDT = ZonedDateTime.of(start, ZoneId.systemDefault());
            ZonedDateTime endZDT = ZonedDateTime.of(end, ZoneId.systemDefault());

            DateTimeFormatter looksGood = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            Appointment.addAppointment(new Appointment(appointmentID, customerID, appointmentName, description, location, contactID, type, userID, looksGood.format(startZDT), looksGood.format(endZDT)));   
        }
    } 
    
    /**
    * Finds appointments and checks date/time against user's local data/time. 
    * @throws SQLException if sql statement is invalid
    */
    public static void appointmentAlert() throws SQLException{
        Connection conn4 = DBConnection.startConnection();        

        String selectPreparedStatement2 = "SELECT * FROM appointments";     //SELECT Statement
        DBQuery.setPreparedStatement(conn4, selectPreparedStatement2); //Create Statement Object

        PreparedStatement statement2 = DBQuery.getPreparedStatement(); //Get Statement Refrence

        statement2.execute(selectPreparedStatement2);                 //EXECUTE Statement - returns boolean - should be TRUE here
        ResultSet rs4 = statement2.getResultSet();            //Get ResultSet (in this case it gets all info from users TABLE

        while(rs4.next()){
            int apptID = rs4.getInt("Appointment_ID");

            String startDateTime = rs4.getString("Start");
            ZonedDateTime d = ZonedDateTime.parse(startDateTime);
        }        
    }
    
//UPDATE STATEMENT 
    /**
    * updates appointment in database - not functioning and not used in application. 
    * @throws SQLException if sql statement is invalid
    */  
    public static void UpdateAppointment() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE appointments SET Title = ? WHERE Title = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String appointmentName, newAppointment, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }

//DELETE STATEMENT
    /**
    * deletes appointment from database - not functioning and not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void DeleteAppointment() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM appointments WHERE Title = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String appointmentName;
        ps.execute();   
    }      
}
