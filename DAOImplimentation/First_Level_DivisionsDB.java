/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.City;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class First_Level_DivisionsDB {
    
//CREATE STATEMENT
    /**
     * Creates first level division in Database - not used in application. 
     * @throws SQLException if sql statement is invalid
     */

    public static void CreateFLD() throws SQLException{
    //Connect to DataBase
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO first_level_divisions(Divison) VALUES(?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ResultSet rs = ps.getResultSet();
        
        String divisionName;
        
        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }
    
      
//READ CITY NAMES STATEMENT
        /**
         * selects first level division in Database - not used in application. 
         * @throws SQLException if sql statement is invalid
         * @return resultSet
         */

    public static ResultSet PopulateCities() throws SQLException{           
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            String divisionName = rs.getString("Division");
        }
        
        return rs;

    }
    
    
//READ(SELECT) STATEMENT
        /**
         * Creates first level division in Database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */

    public static void SelectFLD() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            int countryID = rs.getInt("COUNTRY_ID");
        }
    } 
    
//UPDATE STATEMENT  
        /**
         * updates first level division in Database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */
    public static void UpdateFLD() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE first_level_divisions SET Division = ? WHERE Division = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String divisionName, newDivision, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES

        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }

//DELETE STATEMENT    
    /**
    * deletes first level division in Database - not used in application. 
    * @throws SQLException if sql statement is invalid
    */
    public static void DeleteFLD() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM first_level_divisions WHERE Division = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String countryName;
        
        ps.execute();   
    }  
}
