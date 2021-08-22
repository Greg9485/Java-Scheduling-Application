/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.City;
import greg.boydston.scheduling.application.Model.Country;
import greg.boydston.scheduling.application.Model.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import greg.boydston.scheduling.application.Controller.AddCustomerController;
import greg.boydston.scheduling.application.Controller.MainScreenController;
import greg.boydston.scheduling.application.Model.Appointment;


/**
 *
 * @author greg9485
 */
public class CustomersDB {
//CREATE STATEMENT
    /**
    * creates customer in database. 
    * @throws SQLException if sql statement is invalid
    */
    public static void CreateCustomer() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID)" + "VALUES(?, ?, ?, ?, ?, ?)";
        DBQuery.setPreparedStatement(conn, insertStatement);

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ResultSet rs = ps.getResultSet();
    }
    
    /**
    * selects customer id from database. 
    * @throws SQLException if sql statement is invalid
    */
    public static void getCustomerID() throws SQLException{
        Connection conn2 = DBConnection.startConnection(); 
        
        String customerIDSelectStatement = "SELECT MAX(Customer_ID) FROM customers";
        DBQuery.setPreparedStatement(conn2, customerIDSelectStatement);

        PreparedStatement ps2 = DBQuery.getPreparedStatement();
        ps2.execute();
        ResultSet rs2 = ps2.getResultSet();
        int CustID;


        //System.out.println(rs2.next());
        while(rs2.next()){
            CustID = rs2.getInt("MAX(Customer_ID)") + 1;
        }
    }
    
    
//READ(SELECT) STATEMENT
    /**
    * selects customer in database - adds to customer class. 
    * @throws SQLException if sql statement is invalid
    */
    public static void SelectCustomer() throws SQLException{  
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT countries.Country, first_level_divisions.Division, customers.* FROM first_level_divisions, customers, countries WHERE first_level_divisions.Division_ID = customers.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID ORDER BY customers.Customer_ID";
        DBQuery.setPreparedStatement(conn, selectStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            int divisionID = rs.getInt("Division_ID");
            int countryID = 0;
            String countryName = rs.getString("Country");
            String stateProvinceName = rs.getString("Division");
            String active = "No";
            Customer.addCustomer(new Customer(customerID, customerName, address, zip, phone, divisionID, active, countryName, stateProvinceName));
        }
    } 
    
    /**
    * selects highest number customer ID in database. 
    * @throws SQLException if sql statement is invalid
    * @return highest customer ID in database, then adds 1 to it
    */
    public static int SelectCustomerIDs() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT MAX(Customer_ID) FROM customers";
        DBQuery.setPreparedStatement(conn, selectStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        return rs.getInt("MAX(Customer_ID)") + 1;
        
    } 
    
//UPDATE STATEMENT
    /**
    * updates customer information in database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void UpdateCustomer() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE customers SET Customer_Name = ? WHERE Customer_Name = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String customerName, newCustomer, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }

//DELETE STATEMENT
   /**
    * deletes customer form database. 
    * @throws SQLException if sql statement is invalid
    */
    public static void DeleteCustomer() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM customers WHERE Customer_Name = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String customerName;

        ps.execute();   
    }      
}
