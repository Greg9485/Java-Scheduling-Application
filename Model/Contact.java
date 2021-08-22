/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import static greg.boydston.scheduling.application.Model.Customer.numAddID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Contact {
    
    private static int ContactID;
    private static String ContactName;
    private static String Email;
    
    
    
    public static ObservableList<String> contacts = FXCollections.observableArrayList();
    
    public static void addContacts(String newContact){
        contacts.add(newContact);
    }
    
    public static ObservableList<String> getContacts(){
        return contacts;
    }
    
    
    //Constructor

    public Contact(int ContactD, String ContactName, String Email){
        this.ContactID = ContactID; 
        this.ContactName = ContactName;
        this.Email = Email;
    }
    
    public int getContactID(){
        return ContactID;
    }
    
    public void setContactID(int contactID){
        this.ContactID = contactID;
    }
    
    
    public String getContactName(){
        return ContactName;
    }
    
    public void setContactName(String contactName){
        this.ContactName = contactName;
    }    

    public String getEmail(){
        return Email;
    }
    
    public void setEmail(String email){
        this.Email = email;    
    }
    
}
