/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author greg9485
 */
/**
     * sets JDBC URL Parts
     * sets JDBC URL
     * sets Refrence to mySql driver and connection interface
     * sets Database username and password
     */
public class DBConnection {
    //JDBC URL PARTS
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ05euQ";
    
    //JDBC Url
    private static final String jdbcUrl = protocol + vendorName + ipAddress;       
    
    //Refrence to MySQL Driver and Connecton Interface
    private static final String mySqlJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    //DataBase Username + Password
    private static final String userName = "U05euQ";
    private static final String password = "53688483050";
    
    
    //Method to Start Connection to DataBase
    /**
    * starts connection to database
    * @return conn - connection to database 
    */
    public static Connection startConnection(){
        try {
            Class.forName(mySqlJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    //Method to Close Connection to Database
    /**
    * closes connection to database
    */
    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
