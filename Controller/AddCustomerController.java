/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import DAOImplimentation.CustomersDB;
import static DAOImplimentation.First_Level_DivisionsDB.PopulateCities;
import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Address;
import greg.boydston.scheduling.application.Model.City;
import greg.boydston.scheduling.application.Model.Country;
import greg.boydston.scheduling.application.Model.Customer;
import greg.boydston.scheduling.application.Controller.LoginController;
import greg.boydston.scheduling.application.Model.Employee;
import static greg.boydston.scheduling.application.Model.Employee.getEmployee;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
//import java.util.Date;
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
public class AddCustomerController implements Initializable {

    @FXML
    private Label Comments;
    @FXML
    private Button SaveButton;
    @FXML
    private Button CloseButton;
    @FXML 
    private ComboBox<String> CountryChoice;
    @FXML
    private ComboBox<String> StateProvinceChoice;
    @FXML
    private ComboBox<String> ActiveChoice;   
    @FXML
    private TextField CustomerID;
   /* @FXML
    private TextField Address2;
    */@FXML
    private TextField CustomerName;
    @FXML
    private TextField Address1;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField ZipCode;
  /*  @FXML
    private TextField AddressID;
    */@FXML
    private ComboBox<Integer> DivID;
    
    ObservableList<String> ActiveList = FXCollections.observableArrayList("Yes", "No");
    
    
    @FXML
     /**
     * Saves customer information. 
     * <p>Throws errors if: 
     * <ul>    
     *     <li> customer ID and/or User ID are not in database
     *     <li> Phone number and/or postal code is invalid 
     *     <li> Any required field is blank
     *  </ul>
     *  <p>- Pulls user input from fillable fields and pushes data to database
     */
    private void SaveButtonHandler(ActionEvent event) throws IOException, SQLException {      
        if(CustomerName.getText().isBlank()){
            Comments.setText("Please enter the new customer's name.");
        }else if(CountryChoice.getSelectionModel().isEmpty()){
             Comments.setText("Please choose a country.");
        }else if(StateProvinceChoice.getSelectionModel().isEmpty()){
            Comments.setText("Please choose a state/province.");
        }else if(Address1.getText().isEmpty()){
            Comments.setText("Please enter a street address for Address 1 field.");
        }/*else if(Address2.getText().isEmpty()){
            Comments.setText("Please enter a street address for Address 2 field.");
        }*/else if(ZipCode.getText().isEmpty()){
            Comments.setText("Please enter a postal code.");
        }else if(ZipCode.getText().length() != 5 ){
                Comments.setText("Please enter a valid postal code");
        }else if(PhoneNumber.getText().isEmpty()){
            Comments.setText("Please enter a phone number.");
        }else if(PhoneNumber.getText().length() < 10 || PhoneNumber.getText().length() > 15){
                Comments.setText("Please enter a valid phone number with area code.");
        }else if(ActiveChoice.getSelectionModel().isEmpty()){
            Comments.setText("Please enter if customer is active or not.");
        }else{
            try{            
                //Customer Info
                
                int CustID = Integer.parseInt(CustomerID.getText());
                String CustName = CustomerName.getText();
                String Add1 = Address1.getText();
                //String Add2 = Address2.getText();
                int AddID = 0;
                String StateProvince = StateProvinceChoice.getValue(); 
                //Country CountryName = CountryChoice.getValue();
                String CountryName = CountryChoice.getValue();
                String Zip = ZipCode.getText();
                String Number = PhoneNumber.getText();
                String Active = ActiveChoice.getValue();
            //    String cName = CountryName.getCountryName();
                String createdBy = Employee.loggedInEmployee.toString();
                String updatedBy = Employee.loggedInEmployee.toString();
                String address = Add1;// + " " + Add2;
                int CountryID = 0;
           
        //Find the Division ID and associate it with the City Name 
                Connection conn1 = DBConnection.startConnection(); 
                String divSelectStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + StateProvince +"'";
                DBQuery.setPreparedStatement(conn1, divSelectStatement);

                PreparedStatement ps1 = DBQuery.getPreparedStatement();
                ps1.execute();
                
                
                ResultSet rs1 = ps1.getResultSet();
                while(rs1.next()){
                    int divID = rs1.getInt("Division_ID");
                    Customer.addCustomer(new Customer(CustID, CustName, address, Zip, Number, divID, Active, CountryName, StateProvince));
     //Send Customer to DataBase
                    
                    Connection conn = DBConnection.startConnection(); 
                    String insertStatement = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Updated_By, Division_ID)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                    DBQuery.setPreparedStatement(conn, insertStatement);

                    PreparedStatement ps = DBQuery.getPreparedStatement();

                    ResultSet rs = ps.getResultSet();

                   //key-value mapping
                    ps.setInt(1, CustID);
                    ps.setString(2, CustName);
                    ps.setString(3, address);
                    ps.setString(4, Zip);
                    ps.setString(5, Number);
                    ps.setString(6, createdBy);
                    ps.setString(7, updatedBy);
                    ps.setInt(8, divID);

                    ps.execute();   //Execute Prepared Statement //Execute Prepared Statement
                }
  
                Stage stage;
                Parent root;
                stage=(Stage) SaveButton.getScene().getWindow();

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
    /**
    * Closes screen - loads main screen
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
    private void StateProvinceHandler(ActionEvent event) {
    }

    @FXML
    private void ActiveChoiceHandler(ActionEvent event) {        
    }

    @FXML
    private void CustomerIDHandler(ActionEvent event) {
    }
    /*
    @FXML
    private void AddressIDHandler(ActionEvent event) {
    }*/

    
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
    /**
    * Sets state/province values according to which country is chosen
    */
    private void CountryChoiceHandler(ActionEvent event) throws SQLException {
        ObservableList<String> cities;   
        String CountryName = CountryChoice.getValue();
        if(null != CountryName)switch (CountryName.toString()) {
            case "U.S":
                cities = City.getAllUSCities();
                StateProvinceChoice.setItems(cities);
                break;
            case "UK":
                cities = City.getAllUKCities();
                StateProvinceChoice.setItems(cities);
                break;
            case "Canada":
                cities = City.getAllCanadaCities();
                StateProvinceChoice.setItems(cities);
                break;
            default:
                break;
        }      
    }
    

    @FXML
    private void PhoneNumberHandler(ActionEvent event) {
    }

    @FXML
    private void ZipCodeHandler(ActionEvent event) {
    }
        
    private void setCities() throws SQLException{

    }
    
    

    @Override
     /**
     * Initializes the controller class.
     * 
     * 
     * Adds a customer to the table "customers" in the Database and Application's Customers TableView
     * 
     * <p>Includes the initialize method which sets the values for all combo boxes and the Customer ID
     * <p>Includes a close method which will bring the user back to the main screen without adding an appointment. 
     * <p>Close method throws an alert warning user that changes will not be saved
     * 
     * <p>Includes a save method which does the following: 
     *  <p><ul>
     * <p>Throws errors if:
     *     <li> customer ID and/or User ID are not in database
     *     <li> Phone number and/or postal code is invalid 
     *     <li> Any required field is blank
     *  </ul>
     *  <p>- Pulls user input from fillable fields and pushes data to database
     * 
     * @param url - Sets link for JavaDoc
     * @param rb -Initializes resource bundle
     */   
    public void initialize(URL url, ResourceBundle rb) {   
     /**
      * Sets combo boxes values
      * Sets Customer ID 
      */
       ActiveChoice.setItems(ActiveList);
      
      //Setting Value of State/Province ComboBox
        ObservableList<Employee> loggedInEmployees;
        ObservableList<String> cities;   
        ObservableList<Country> countries;
        ObservableList<String> country;
        ObservableList<Integer> cityID;
        
        try {
            
            //Pull CustomerID from Database
            Connection conn = DBConnection.startConnection(); 
            String selectStatement = "SELECT MAX(Customer_ID) FROM customers";
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();   //Execute Prepared Statement

            ResultSet rs = ps.getResultSet();

           while(rs.next()){
               //Set CustomerID Field to current MAX customerID + 1
                int customerID = rs.getInt("MAX(Customer_ID)") + 1;
            
                loggedInEmployees = Employee.getEmployee();

                country = Country.getTheCountries();
                CountryChoice.setItems(country);

                cities = City.getAllCities();
                cityID = City.getAllCityIDs();

                StateProvinceChoice.setItems(cities);  

    //Set CustomerID to Pull from Database
                CustomerID.setText(String.valueOf(customerID));
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}