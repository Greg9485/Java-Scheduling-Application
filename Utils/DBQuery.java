/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 *
 * Creates and returns statement object. 
 * 
 * 
 * @author greg9485
 */
public class DBQuery {
   
    /**
     * sets prepared statement reference
     */
    private static PreparedStatement statement; //Statement refrence
    
       /**
        * Create Statement Object
        * @throws SQLException if sql statement is invalid
        * @param conn Prepared statement for connection
        * @param sqlStatement sqlStatemnent used in connection
        */
     
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException{
       statement = conn.prepareStatement(sqlStatement);     
    }
    
        /**
     * Return Statement Object. 
     * @return statement object
     */
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }    
}
