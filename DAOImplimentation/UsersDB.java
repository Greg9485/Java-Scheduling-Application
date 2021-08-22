/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplimentation;

import Utils.DBConnection;
import Utils.DBQuery;
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
public class UsersDB {
//CREATE STATEMENT
    /**
         * Create user in database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */
    public static void CreateUser() throws SQLException{
    //Connect to DataBase
        Connection conn = DBConnection.startConnection(); 
        String insertStatement = "INSERT INTO users(User_Name) VALUES(?)";
        
        DBQuery.setPreparedStatement(conn, insertStatement);
        
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ResultSet rs = ps.getResultSet();
        
        
        String userName;
        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }
    
//READ(SELECT) STATEMENT
    /**
         * Select user in database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */
    public static void SelectUser() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM users";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
        }
    } 
    
//UPDATE STATEMENT  
    /**
         * Update's user in database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */
    public static void UpdateUser() throws SQLException{
        Connection conn = DBConnection.startConnection(); 
        String updateStatement = "UPDATE users SET User_Name = ? WHERE User_Name = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String userName, newUser, createdBy; //-> NEED TO INITIALIZE FROM OTHER CLASSES
        ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
    }

//DELETE STATEMENT
        /**
         * Delete's user from database - not used in application. 
         * @throws SQLException if sql statement is invalid
         */    
    public static void DeleteUser() throws SQLException{
        
        Connection conn = DBConnection.startConnection(); 
        String deleteStatement = "DELETE FROM users WHERE User_Name = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        String userName;
        ps.execute();   
    }  
}
