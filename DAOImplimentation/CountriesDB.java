/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Controller.AddCustomerController;
import greg.boydston.scheduling.application.Model.Country;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author greg9485
 */
public class CountriesDB {

//CREATE STATEMEN
    /**
    * creates country in database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void CreateCountry() throws SQLException{
    //Connect to DataBase
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO countries(Country)"/*, Create_Date, Created_By, Last_Updated_By)*/ + "VALUES(?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ResultSet rs = ps.getResultSet();
        
        String countryName;
   
        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }
    
//READ(SELECT) STATEMENT
    /**
    * selects country from database. 
    * @throws SQLException if sql statement is invalid
    */
    public static void SelectCountry() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 

        }
    } 
    
//UPDATE STATEMENT
    /**
    * updates country in database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void UpdateCountry() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE countries SET Country = ?, Created_By = ? WHERE Country = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String countryName, newCountry, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }

//DELETE STATEMENT
    /**
    * deletes country from database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void DeleteCountry() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM countries WHERE Country = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String countryName;
        
        ps.execute();   
    }   
}
