/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import Utils.DBConnection;
import Utils.DBQuery;
import static greg.boydston.scheduling.application.Model.Customer.numAddID;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Reports {
    //int numApts;
    String aptType;
    
    public Reports(String aptType){
        this.aptType = aptType;
    }
    
    
    public static ObservableList<String> aptTypes = FXCollections.observableArrayList();
    
    public static void addApt(String newAptType){
        aptTypes.add(newAptType);
    }
    
    public static ObservableList<String> getAllApts(){
        return aptTypes;
    }
    
    public void deleteApt(String newAptType){
        aptTypes.remove(newAptType);
    }


    
    
    public String getAptType(){
        return aptType;
    }
      
    public void setAptType(String aptType){
        this.aptType = aptType;

    }
    
    
    
    //CONTACT APPOINTMENT LIST FOR CONTACTAPPT REPORT
    public static ObservableList<Appointment> contactAppointmentList = FXCollections.observableArrayList();
    
    public static void addContactAppointmentList(Appointment newAppointment){
        contactAppointmentList.add(newAppointment);
    }
    
    public static ObservableList<Appointment> getAllContactAppointments(){
        return contactAppointmentList;
    }
    
    public static void deleteContactAppoinments(Appointment newAppointment){
        contactAppointmentList.remove(newAppointment);
    }
    
    

    
    //CUSTOMER APPOINTMENT LIST FOR CUSTAPPT REPORT
    public static ObservableList<Appointment> customerAppointmentList = FXCollections.observableArrayList();
    
    public static void addCustomerAppointmentList(Appointment newAppointment){
        customerAppointmentList.add(newAppointment);
    }
    
    public static ObservableList<Appointment> getAllCustomerAppointments(){
        return customerAppointmentList;
    }
    
    public static void deleteCustomerAppoinments(Appointment newAppointment){
        customerAppointmentList.remove(newAppointment);
    }
    
    public static ObservableList<Integer> customerIDList = FXCollections.observableArrayList();
    
    public static void addCustomerIDList(Integer newCustID){
        customerIDList.add(newCustID);
    }
    
    public static ObservableList<Integer> getCustomerIDs(){
        return customerIDList;
    }
    
    public static void deleteCustomerIDs(Integer newCustID){
        customerIDList.remove(newCustID);
    }
    
    //MUST SELECT * UNIQUE CUSTOMER_IDS FROM CUSTOMERS TO POPULATE ALL CUSTOMER IDS
    //THEN MUST FIND ALL APPOINTMENTS ASSOCIATED WITH THE CHOSEN CUSTOMER ID, POPULATE THE APPTID, TYPE, START/END DATES, AND PRODUCE A TOTAL # OF APPOINTMENTS FOR THAT 
    //CUSTOMER
    
    
    
    /**
     *CUSTOMER ID FOR CUSTOMERAPPTS REPORT. 
     * 
     *THE POINT OF THE CUSTOMER APPOINTMENT REPORT IS TO ENABLE THE USER TO CHOOSE A CUSTOMER ID FROM A LIST OF ALL CUSTOMERS, 
     * WHICH WILL THEN SHOW ALL APPOINTMENTS ASSOCIATED W/ THAT CUSTOMER, INCLUDING APPT ID, APPT TYPE, START AND END DATES/TIMES
     * AND WILL DISPLAY THE TOTAL NUMBER OF APPOINTMNETS. 
     * @throws SQLException should sql statement not be valid
     * @return all customer IDs pulled from all customers in database
     */    
    public static ObservableList<Integer> getAllCustomerIDs() throws SQLException {
        ObservableList<Integer> allCustomerIDs=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM customers";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();
    
    
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            allCustomerIDs.add(customerID);
            }
            
        DBConnection.closeConnection();   
        return allCustomerIDs;
    }
    
    
    /*
    
    public Reports(int numApts, String aptType){
        this.numApts = numApts;
        this.aptType = aptType;
        
    }
    
    public int getNumApts(){
        return numApts;
    }
  
    public void setNumApts(int numApts) {
        this.numApts = numApts;
    }
    

     @Override
    public String toString(){
        return (aptType);
    }
    
    public Integer toInteger(){
        return (numApts);
    }
    
    
    public static ObservableList<String> getAllTypes() throws SQLException {
        ObservableList<String> allTypes=FXCollections.observableArrayList();    
                
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();
             
        while(rs.next()){
            String type = rs.getString("Type");
            
            allTypes.add(type); 
            }
        DBConnection.closeConnection(); 
        return allTypes;
        }
    */ 
}
