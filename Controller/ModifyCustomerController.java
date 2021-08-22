/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Address;
import greg.boydston.scheduling.application.Model.Appointment;
import greg.boydston.scheduling.application.Model.City;
import greg.boydston.scheduling.application.Model.Country;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Model.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class ModifyCustomerController implements Initializable {

    @FXML
    private Label Comments;
    @FXML
    private Button SaveButton;
    @FXML
    private Button CloseButton;
    @FXML
    private ComboBox<String> CityChoice;
    @FXML
    private ComboBox<String> ActiveChoice;
    @FXML
    private TextField CustomerID;
     @FXML
    private TextField AddressID;
  /*  @FXML
    private TextField Address2;
    */@FXML
    private TextField CustomerName;
    @FXML
    private TextField Address1;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField ZipCode;
    @FXML
    private ComboBox<String> CountryChoice1;
    private Customer selectedCustomer;
    private Address selectedAddress;

    ObservableList<String> ActiveList = FXCollections.observableArrayList("Yes", "No");
    
 
    /**
     * Initializes the controller class.
     * Sets combo boxes
     * @param url - initialize url
     * @param rb - ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ActiveChoice.setItems(ActiveList);
        
        ObservableList<String> cities;
        ObservableList<String> countries;
        try {
            cities = City.getAllCities();
            CityChoice.setItems(cities);
            
            countries = Country.getTheCountries();
            CountryChoice1.setItems(countries);
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    /**
    * Throws alert if information is missing
    * Saves modifications and updates information in database and application
    */
    private void SaveButtonHandler(ActionEvent event) throws IOException, SQLException {
        if(CustomerName.getText().isBlank()){
            Comments.setText("Please enter the customer's name.");
        }else if(Address1.getText().isEmpty()){
            Comments.setText("Please enter a street address.");
        }else if(ZipCode.getText().isEmpty()){
            Comments.setText("Please enter a postal code.");
        }else if(PhoneNumber.getText().isEmpty()){
            Comments.setText("Please enter a phone number.");
        }else if(ActiveChoice.getSelectionModel().isEmpty()){
            Comments.setText("Please enter if customer is active or not.");
        }else{
            try{
                String customerName = CustomerName.getText();
                String address = Address1.getText();//+ "" + Address2.getText();
                String zip = ZipCode.getText();
                String phone = PhoneNumber.getText();
                String lastUpdatedBy = Employee.loggedInEmployee.toString();  
                int customerID = Integer.parseInt(CustomerID.getText());
                String StateProvince = CityChoice.getValue(); 

                Connection conn1 = DBConnection.startConnection(); 
                String divSelectStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + StateProvince +"'";
                DBQuery.setPreparedStatement(conn1, divSelectStatement);

                PreparedStatement ps1 = DBQuery.getPreparedStatement();
                ps1.execute();

                ResultSet rs1 = ps1.getResultSet();

                while(rs1.next()){
                    int divID = rs1.getInt("Division_ID");
                    System.out.println(divID);
                    Customer newCustomer = new Customer(Integer.parseInt(CustomerID.getText()), customerName, Address1.getText(), ZipCode.getText(), PhoneNumber.getText(), divID, ActiveChoice.getValue(), CountryChoice1.getValue(), StateProvince);


                    newCustomer.setCustID(selectedCustomer.getCustID());
                    newCustomer.setAddressID(selectedCustomer.getAddressID());
                    Customer.allCustomers.add(newCustomer);
                    Customer.allCustomers.remove(selectedCustomer);

                    Connection conn = DBConnection.startConnection(); 
                    String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";// + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                    DBQuery.setPreparedStatement(conn, updateStatement);
                    PreparedStatement ps = DBQuery.getPreparedStatement();

                    System.out.println("Next Connection done and SQL Statement processed");
      
                    ps.setString(1, customerName);
                    ps.setString(2, address);
                    ps.setString(3, zip);
                    ps.setString(4, phone);
                    ps.setString(5, lastUpdatedBy);
                    ps.setInt(6, divID);
                    ps.setInt(7, customerID);

                    ps.execute();   //Execute Prepared Statement //Execute Prepared Statement

                    if(ps.getUpdateCount() > 0){
                        System.out.println(ps.getUpdateCount() + " rows affected.");
                    }else{
                        System.out.println("No change!");
                    }
                }
                  
  
              Stage stage;
              Parent root;
              stage=(Stage) CloseButton.getScene().getWindow();

              FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

              root = loader.load();

              Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
            }
            catch (NumberFormatException e){
            }
        }
    }
    
   
    @FXML
    private void AddressIDHandler(ActionEvent event) {
        
    }

    @FXML
    /**
    * closes scene and loads main screen
    */
    private void CloseButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) CloseButton.getScene().getWindow();

        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/MainScreen1.fxml"));

        root = loader.load();
            
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void CityChoiceHandler(ActionEvent event) {
    }

    @FXML
    private void ActiveChoiceHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerIDHandler(ActionEvent event) {
    }

    @FXML
    private void Address2Handler(ActionEvent event) {
    }

    @FXML
    private void CustomerNameHandler(ActionEvent event) {
    }

    @FXML
    private void Address1Handler(ActionEvent event) {
    }


    @FXML
    private void PhoneNumberHandler(ActionEvent event) {
    }

    @FXML
    private void ZipCodeHandler(ActionEvent event) {
    }

    @FXML
    /**
    * Sets state/province choices depending on Country chosen
    */
    private void CountryChoiceHandler(ActionEvent event) throws SQLException {
    //Match Countries to Cities
        ObservableList<String> cities;   
        //Country CountryName = CountryChoice1.getValue();
        String CountryName = CountryChoice1.getValue();
        if(null != CountryName)switch (CountryName.toString()) {
            case "U.S":
                cities = City.getAllUSCities();
                CityChoice.setItems(cities);
                break;
            case "UK":
                cities = City.getAllUKCities();
                CityChoice.setItems(cities);
                break;
            case "Canada":
                cities = City.getAllCanadaCities();
                CityChoice.setItems(cities);
                break;
            default:
                break;
        }    
    }
    
    /**
     * sets customer information in applicable fields. 
     * 
     * @throws SQLException if sql statement is invalid
     * @param selectedCustomer pulls data from customer selected by user
     */
    public void setCustomer(Customer selectedCustomer) throws SQLException{
        this.selectedCustomer = selectedCustomer;
        CustomerID.setText(new Integer(selectedCustomer.getCustID()).toString());
        AddressID.setText(new Integer(selectedCustomer.getCustID()).toString());
        CustomerName.setText(selectedCustomer.getCustName());
        Address1.setText(selectedCustomer.getAddress());
        //Address2.setText(selectedCustomer.getAddress());
        PhoneNumber.setText(selectedCustomer.getPhoneNumber());
        ZipCode.setText(selectedCustomer.getZipCode());
        ActiveChoice.setValue(selectedCustomer.getActive());
        CountryChoice1.setValue(selectedCustomer.getCountryName());   
        CityChoice.setValue(selectedCustomer.getStateProvinceName());
    }   
}
