/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Contact;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 *
 * @author greg9485
 */
public class ContactsDB {
    //CREATE STATEMENT
    /**
    * creates contact in database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void CreateContact() throws SQLException{
    //Connect to DataBase
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO contacts(Contact_Name)"/*, Create_Date, Created_By, Last_Updated_By)*/ + "VALUES(?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ResultSet rs = ps.getResultSet();
        
        String contactName;

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }
    
//READ(SELECT) STATEMENT
    /**
    * selects contact from database. 
    * @throws SQLException if sql statement is invalid
    */
    public static void SelectContact() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM contacts";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
         
            Contact.addContacts(contactName);
        } 
    } 
    
//UPDATE STATEMENT
    /**
    * updates contact in database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void UpdateContact() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE contacts SET Contact_Name = ? WHERE Contact_Name = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String contactName, newContact, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement

    }

//DELETE STATEMENT
    /**
    * Deletes contact from database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void DeleteContact() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM contacts WHERE Contact_Name = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String contactName;

        ps.execute();   
    }  
    
    /**
     * 
     * 
     * @throws SQLException if sql statement is invalid 
     */
    
      public static void PopulateContactAppointments() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "SELECT * FROM appointments WHERE Contact_ID = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();     
        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
        }
        
      }    
    
}
