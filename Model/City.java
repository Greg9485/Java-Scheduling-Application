/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;


import Utils.DBConnection;
import Utils.DBQuery;
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
public class City {
    
    int divisionID;
    String divisionName;
    Date createDate;
    Time createTime;
    String createBy;
    LocalDateTime lastUpdate;
    String updatedBy;
    int countryID;
    
    public static ObservableList<City> allCityList = FXCollections.observableArrayList();
    public static ObservableList<String> associatedCities = FXCollections.observableArrayList();
    
   /* public City(int divisionID, String divisionName, Date createDate, Time createTime, String createBy, LocalDateTime lastUpdate, String updatedBy, int countryID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate; 
        this.updatedBy = updatedBy;
        this.countryID = countryID;
    }*/
    
    public City(int divisionID, String divisionName){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }
   
     //Setters & Getters
    public int getDivisionID(){
        return divisionID;
    }
    
    public void setDivisionID(int divID){
        this.divisionID = divID;
    }

    public String getDivisionName(){
        return divisionName;
    }
    
    public void setDivision(String div){
        this.divisionName = div;
    }    

    public String getCreateBy(){
        return createBy;
    }
    
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }    
    
    public int getCountryID(){
        return countryID;
    }
    
    public void setCountryID(int cID){
        this.countryID = cID;
    }
   
    
    //City observablelist
    public void addAllCityList(City newCity){
        allCityList.add(newCity);
    }
    
    public void deleteCityList(City newCity){
        allCityList.remove(newCity);
    }
    
    public ObservableList<City> getCityList(){
        return allCityList;
    }
    
    
    
   //City Names in ComboBoxes
    public void addAssociatedCities(String newCity){
        associatedCities.add(newCity);
    }
    
    public void deleteAssociatedCities(String newCity){
        associatedCities.remove(newCity);
    }
    
    public ObservableList<String> getAssociatedCities(){
        return associatedCities;
    }
    
    
    
    
    /*
   
  //Populate All Cities in Application       
    public static ObservableList<City> getAllCityList() throws SQLException {
        ObservableList<City> allCities=FXCollections.observableArrayList();    
        
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

            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            City cityResult= new City(divisionID, divisionName, createDate, createTime, createBy, lastUpdate, updatedBy, countryID);
            allCities.add(cityResult); 
            }
            
        DBConnection.closeConnection(); 
        System.out.println(allCities);
        return allCities;
        }
   
    
     public static ObservableList<City> getAllCanadaCityList() throws SQLException {
        ObservableList<City> allCanadaCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 3";
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

            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            City CanadaCityResult= new City(divisionID, divisionName, createDate, createTime, createBy, lastUpdate, updatedBy, countryID);
            allCanadaCities.add(CanadaCityResult);
                
            }
            
        DBConnection.closeConnection();   
        return allCanadaCities;
        }
    
     //Populate US Cities in Application    
    public static ObservableList<City> getAllUSCityList() throws SQLException {
        ObservableList<City> allUSCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 1";
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

            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            City USCityResult= new City(divisionID, divisionName, createDate, createTime, createBy, lastUpdate, updatedBy, countryID);
            allUSCities.add(USCityResult);
                
            }
            
        DBConnection.closeConnection();   
        return allUSCities;
        }
    */
    
    public static ObservableList<String> getAllCities() throws SQLException {
        ObservableList<String> allCities=FXCollections.observableArrayList();    
                
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
            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            String cityResult= divisionName;
            allCities.add(cityResult); 
            }
        DBConnection.closeConnection(); 
        return allCities;
        }
    
    
    public static ObservableList<Integer> getAllCityIDs() throws SQLException {
        ObservableList<Integer> allCities=FXCollections.observableArrayList();    
                
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

             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            allCities.add(divisionID);   
            }      
        DBConnection.closeConnection(); 
        return allCities;
        }
    
    
  //STRINGS
   
    
    
      public static ObservableList<String> getAllCanadaCities() throws SQLException {
        ObservableList<String> allCanadaCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 3";
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
            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            String CanadaCityResult= divisionName;
            allCanadaCities.add(CanadaCityResult); 
            }
        DBConnection.closeConnection();   
        return allCanadaCities;
        }

    
    
    
 
    
    
    public static ObservableList<String> getAllUSCities() throws SQLException {
        ObservableList<String> allUSCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 1";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();
             
        while(rs.next()){
          
            String divisionName = rs.getString("Division");
            /*int divisionID = rs.getInt("Division_ID");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            int countryID = rs.getInt("COUNTRY_ID");

            */
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            String USCityResult= divisionName;
            allUSCities.add(USCityResult);
                
            }
            
        DBConnection.closeConnection();   
        return allUSCities;
        }
    /*
  //Populate UK Cities in Application    
    public static ObservableList<City> getAllUKCityList() throws SQLException {
        ObservableList<City> allUKCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 2";
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

            
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            City UKCityResult= new City(divisionID, divisionName, createDate, createTime, createBy, lastUpdate, updatedBy, countryID);
            allUKCities.add(UKCityResult);
                
            }
            
        DBConnection.closeConnection();   
        return allUKCities;
        }
    */
      public static ObservableList<String> getAllUKCities() throws SQLException {
        ObservableList<String> allUKCities=FXCollections.observableArrayList();    
        
        Connection conn = DBConnection.startConnection(); 
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 2";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        ps.execute();   //Execute Prepared Statement

        ResultSet rs = ps.getResultSet();
             
        while(rs.next()){
        
            String divisionName = rs.getString("Division");
            /*int divisionID = rs.getInt("Division_ID");
            Date createDate = rs.getDate("Create_Date");          
            Time createTime = rs.getTime("Create_Date");          
            String createBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();    
            String updatedBy = rs.getString("Last_Updated_By"); 
            int countryID = rs.getInt("COUNTRY_ID");

            */
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            String UKCityResult= divisionName;
            allUKCities.add(UKCityResult);
            }
            
        DBConnection.closeConnection();   
        return allUKCities;
        }
    
    
    
              
    //Override Public String toString so it shows actual names from DB
    @Override
    public String toString(){
        return (divisionName);
    }
    
    public Integer toInteger(){
        return (divisionID);
    }
    
    
    
    /*
    public Integer toInt(){
        return(divisionID);
    }*/
    
    
    /*
    public int divisionID;
    public String division;
    public String createdBy;
    public int countryID;
    
    public City(int divID, String div, String createBy, int cID){
        this.divisionID = divID;
        this.division = div;
        this.createdBy = createBy;
        this.countryID = cID;
    }
    
    
    */
   
}
