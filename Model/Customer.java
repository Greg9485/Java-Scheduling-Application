/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import DAOImplimentation.CustomersDB;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg9485
 */
public class Customer {
    
    //Declaring Variables
    
    private int CustID;
    private String CustName;
    private String Active;
    private int AddressID;
    private String Address1;
    private String Address2;
    private String StateProvinceName;
    private String CountryName;
    private String ZipCode;
    private String PhoneNumber;
    private Country Country;
    private City StateProvince;
    private String Address;
    private int DivID;
    private int CountryID;
    
    
    
    public static int numCustID;
    public static int numAddID;

               

    
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    public static ObservableList<Integer> custIDs = FXCollections.observableArrayList();
    
    public static void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }
    
    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }
    
    public void deleteCustomers(Customer newCustomer){
        allCustomers.remove(newCustomer);
    }
    
    
    public static void addCustIDs(int newCustIDs){
        custIDs.add(newCustIDs);
    }
    
    public static ObservableList<Integer> getCustIDs(){
        return custIDs;
    }
    
    
    //Constructor

    public Customer(int CustID, String CustName, String Address, String ZipCode, String PhoneNumber, int DivID, String Active, String CountryName, String StateProvinceName){
        this.CustID = CustID; 
        this.CustName = CustName;
        this.Address1 = Address1;
        this.Address2 = Address2;
        this.CountryName = CountryName;
        this.StateProvinceName = StateProvinceName; 
        this.AddressID = ++numAddID;
        this.Address = Address;
        this.ZipCode = ZipCode;
        this.PhoneNumber = PhoneNumber;
        this.DivID = DivID;
        this.Active = Active;   
        this.CountryID = CountryID;
    }
    
    
    //Getters
  
 public int getCustID() {
        return CustID;
    }
    
    public String getCustName() {
        return CustName;
    }

    public String getActive() {
        return Active;
    }

    public int getAddressID() {
        return AddressID;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public City getStateProvince(){
        return StateProvince;
    }
    
    
    public String getStateProvinceName(){
        return StateProvinceName;
    }
    
    public String getCountryName(){
        return CountryName;
    }
   
    public Country getCountry(){
        return Country;
    }
   
    public String getZipCode() {
        return ZipCode;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    
    public String getAddress(){
        return Address;
    }
    
    public int getDivID(){
        return DivID;
    }
    
    public int getCountryID(){
        return CountryID;
    }
    
    //Setters
    
    public void setCustID(int CustID) throws SQLException {
        this.CustID = CustID;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }

    public void setAddressID(int AddressID) {
        this.AddressID = AddressID;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    public void setStateProvince(City stateProvince){
        this.StateProvince = stateProvince;
    }
    
    public void setCountry(Country Country){
        this.Country = Country;
    }
    
    public void setStateProvinceName(String stateProvinceName){
        this.StateProvinceName = stateProvinceName;
    }

    public void setCountryName(String countryName){
        this.CountryName = countryName;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    
    public void setAddress(String Address){
        this.Address = Address;
    }

    public void setDivID(int DivID){
        this.DivID = DivID;
    }
    
    public void setCountryID(int CountryID){
        this.DivID = DivID;
    }
}
