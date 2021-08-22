/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Appointment {
    private int appointmentID;
    private int customerID;
    private String title;
    private String description;
    private String location;
    private int contact;
    private String type;
    private int userID;
    //private void Date;
    private String start;
    private String end;
    /*  private DateTimeFormatter start;
    private DateTimeFormatter end;    
 */
    public static int numApptID;
    
   
    //All Appointment List
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    
    public static void addAppointment(Appointment newAppointment){
        allAppointments.add(newAppointment);
    }
    
    public static ObservableList<Appointment> getAllAppointments(){
        return allAppointments;
    }
    
    public static void deleteAppoinments(Appointment newAppointment){
        allAppointments.remove(newAppointment);
    }
    
    //Customer Appointment List
    public static ObservableList<Appointment> customerAppointmentList = FXCollections.observableArrayList();
    
    public static void addCustomerAppointmentList(Appointment newAppointment){
        customerAppointmentList.add(newAppointment);
    }
    
    public static ObservableList<Appointment> getAllCustomerAppointments(){
        return customerAppointmentList;
    }
    
    public static void deleteCustomerAppoinments(Appointment newAppointment){
        customerAppointmentList.remove(newAppointment);
    }
    
    
    //Contact Appointment List
    public static ObservableList<Appointment> contactAppointmentList = FXCollections.observableArrayList();
    
    public static void addContactAppointmentList(Appointment newAppointment){
        contactAppointmentList.add(newAppointment);
    }
    
    public static ObservableList<Appointment> getAllContactAppointments(){
        return contactAppointmentList;
    }
    
    public static void deleteContactAppoinments(Appointment newAppointment){
        contactAppointmentList.remove(newAppointment);
    }
    
    
    
    //Constructor
    
    //public Appointment(int appointmentID, int customerID, String title, String description, String location, int contact, String type, int userID, DateTimeFormatter start, DateTimeFormatter end){
    public Appointment(int appointmentID, int customerID, String title, String description, String location, int contact, String type, int userID, String start, String end){    
        this.appointmentID = ++numApptID; 
        this.customerID = customerID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.userID = userID;
        this.start = start;
        this.end = end;
   }
    
    //Getters and Setters
    
    public int getAppointmentID(){
        return appointmentID;
    }
    
    public void setAppointmentID(int appointmentID){
        this.appointmentID = appointmentID;
    }
    
       public int getCustomerID(){
        return customerID;
    }
    
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
     public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
     public String getLocation(){
        return location;
    }
    
    public void setLocation(String location){
        this.location = location;
    }

    public int getContact(){
        return contact;
    }
    
    public void setContact(int contact){
        this.contact = contact;
    }

    public String getType(){
        return type;
    }
     
    public void setType(String type){
        this.type = type;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID){
        this.userID = userID;
    }

    
    public String getStart(){
        return start;
    }
    
    public void setStart(String start){
        this.start = start;
    }
    
    public String getEnd(){
        return end;
    }
    
    public void setEnd(String end){
        this.end = end;
    }
}
