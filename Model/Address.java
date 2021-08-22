/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import static greg.boydston.scheduling.application.Model.Customer.allCustomers;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Address {
    
    int addressID;
    int divID;
    int countryID;
    int customerID;
    String add1;
    //String add2;
    String zip;
    City stateProvince; 
    Country countryName;
    
    /*
    Date createDate;
    Time createTime;
    String createdBy;
    String lastUpdatedBy;
    LocalDateTime lastUpdate;
*/
    
    public static ObservableList<Address> associatedAddresses = FXCollections.observableArrayList();
       
    public static ObservableList<Address> getAddresses(){
        return associatedAddresses;
    }
    
    public static void addAddress(Address newAddress){
        associatedAddresses.add(newAddress);
    }
    
    public void deleteAddresses(Address newAddress){
        associatedAddresses.remove(newAddress);
    }
    
    
    public Address(int addressID, int divID, int countryID, int customerID, String add1, /*String add2,*/ City stateProvince, String zip, Country countryName){
                this.addressID = addressID;
                this.divID = divID;
                this.countryID = countryID;
                this.customerID = addressID;
                this.add1 = add1;
                //this.add2 = add2;
                this.stateProvince = stateProvince;
                this.zip = zip; 
                this.countryName = countryName;
              
                
                /*this.countryName = countryName;
                this.lastUpdatedBy = lastUpdatedBy;
                this.createDate = createDate;
                this.createTime = createTime;
                this.createdBy = createdBy;
                this.lastUpdate = lastUpdate;*/
    }
    
    public int getAddressID(){
        return addressID;
    }
    
    public void setAddressID(int addressID){
        this.addressID = addressID;
    }

    public int getDivID(){
        return divID;
    }
    
    public void setdivID(int divID){
        this.divID = divID;
    }

    public int getCountryID(){
        return countryID;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }

    public String getAdd1(){
        return add1;
    }
    
    public void setAdd1(String add1){
        this.add1 = add1;
    }
/*
    public String getAdd2(){
        return add2;
    }
    
    public void setAdd2(String add2){
        this.add2 = add2;
    }   
*/
    public String getZip(){
        return zip;
    }
    
    public void setZip(String zip){
        this.zip = zip;
    }   

  
    public City getStateProvince(){
        return stateProvince;
    }
    
    public void setStateProvince(City stateProvince){
        this.stateProvince = stateProvince;
    }
        
    public Country getCountryName(){
        return countryName;
    }
    
    public void setCountryName(Country countryName){
        this.countryName = countryName;
    }
  
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    
    
        @Override
    public String toString(){
        return (add1);
    }
 
    
}
