/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import Utils.DBConnection;
import Utils.DBQuery;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Country {
    
    int countryID;
    String countryName;
    String createdBy;
    Date createDate; 
    Time createTime;
    String createBy;
    LocalDateTime lastUpdate;
    String updatedBy;

    
    public static ObservableList<Country> associatedCountries = FXCollections.observableArrayList();
    public static ObservableList<String> countries = FXCollections.observableArrayList();
    
    public Country(int cID, String cName, Date createDate, Time createTime, String createBy, LocalDateTime lastUpdate, String updateBy){
        this.countryID = cID;
        this.countryName = cName;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createdBy = createBy;
        this.lastUpdate = lastUpdate;
        this.updatedBy = updateBy;
    }
    
    
         //Setters & Getters
    public int getCID(){
        return countryID;
    }
    
    public void setCID(int countryID){
        this.countryID = countryID;
    }

    public String getCountryName(){
        return countryName;
    }
    
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }    

    public String getCreateBy(){
        return createBy;
    }
    
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }    


    
    public void addAssociatedCountries(Country newCountry){
        associatedCountries.add(newCountry);
    }
    
    public void deleteAssociatedCountries(Country newCountry){
        associatedCountries.remove(newCountry);
    }
    
    public ObservableList<Country> getAssociatedCCountries(){
        return associatedCountries;
    }
    
    
    public ObservableList<String> getCountries(){
        return countries;
    }
       
 //Populate Countries in Application
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> allCountries=FXCollections.observableArrayList();    
        
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
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            
            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            Country countryResult= new Country(countryID, countryName, createDate, createTime, createdBy, lastUpdate, updatedBy);
            allCountries.add(countryResult);
            countries.add(countryName);
            }
            
        DBConnection.closeConnection();   
        return allCountries;
        }
              
    
    public static ObservableList<String> getTheCountries() throws SQLException {
        ObservableList<String> allCountries=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();
             
        while(rs.next()){
          //  int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            /*Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            
            */
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            String countryResult= countryName;
            allCountries.add(countryResult);
            countries.add(countryName);
            }
            
        DBConnection.closeConnection();   
        return allCountries;
        }
              
    
    
    
    //Override Public String toString so it shows actual names from DB
    @Override
    public String toString(){
        return (countryName);
    }
}
