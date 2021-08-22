/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author greg9485
 */
public class Employee {
    private String userName;
    private String password;
    private int userID;
  //  private String createdBy;
  //  private String lastUpdatedBy;
    
    
    public static ObservableList<Integer> usersID = FXCollections.observableArrayList();
    
    
    
    public static void addUsersID(int newUsersID){
        usersID.add(newUsersID);
    }
    
    public static ObservableList<Integer> getUsersID(){
        return usersID;
    }

    
    public static ObservableList<String> userPass = FXCollections.observableArrayList();
    
    public static void addUserPass(String newUserPass){
        userPass.add(newUserPass);
    }

    public static ObservableList<String> getUserPass(){
        return userPass;
    }
    
    public static ObservableList<Employee> loggedInEmployee = FXCollections.observableArrayList();

    public static void addLoggedInEmployee(Employee newEmployee){
        loggedInEmployee.add(newEmployee);
    }

    public static ObservableList<Employee> getEmployee(){
        return loggedInEmployee;
    }

    //Constructor
    
    public Employee(String name, String pass){
        this.userName = name;
        this.password = pass;
    }
    
    //Getters and Setters
    
    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String name){
        this.userName = name;
    }
       
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String pass){
        this.password = pass;
    }
    
    @Override
    public String toString(){
        return (userName);
    }
    
    
}