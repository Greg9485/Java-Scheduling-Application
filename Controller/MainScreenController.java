/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greg.boydston.scheduling.application.Controller;

import DAOImplimentation.CustomersDB;
import Utils.DBConnection;
import Utils.DBQuery;
import greg.boydston.scheduling.application.Model.Address;
import greg.boydston.scheduling.application.Model.Appointment;
import static greg.boydston.scheduling.application.Model.Appointment.customerAppointmentList;
import static greg.boydston.scheduling.application.Model.Appointment.getAllCustomerAppointments;
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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg9485
 */
public class MainScreenController implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentsTableView;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, Integer> UserIDColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentCustomerIDColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTitleColumn;
    @FXML       
    private TableColumn<Appointment, String> StartDateTimeColumn;
    @FXML       
    private TableColumn<Appointment, String> EndDateTimeColumn;
    @FXML
    private Button AddButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button ModifyButton;
    @FXML
    private TableView<Customer> CustomersTableView;
    @FXML
    private TableColumn<Customer, Integer> CustIDColumn;
    @FXML
    private TableColumn<Customer, String> CustNameColumn;
    @FXML
    private TableColumn<Customer, String> AddressIDColumn;
    @FXML
    private TableColumn<Customer, Boolean> ActiveColumn;
    @FXML
    private Button ViewAppointmentsButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button AppointmentTypesButton;
    @FXML
    private Button ConsultantScheduleButton;
    @FXML
    private Button AppointmentCustomerButton;
    @FXML
    private Button SearchAppointmentHandler;
    @FXML
    private Button LogOffButton;
    @FXML
    private Button modifyCustomerButton;
    @FXML
    private TableColumn<Customer, String> Add1Column;
   /* @FXML
    private TableColumn<Customer, String> Add2Column;
    */@FXML
    private TableColumn<Address, City> StateProvinceColumn;
    @FXML
    private TableColumn<Address, Country> CountryColumn;
    @FXML
    private TableColumn<Customer, String> PhoneColumn;
    @FXML
    private TableColumn<Customer, String> ZipColumn;

  //Customer.addCustomer(new Customer(customerID, customerName, address, zip, phone, divisionID, active, countryID));

    @FXML
    /**
    * Loads add appointment screen
    */
    private void AddButtonHandler(ActionEvent event) throws IOException {
        
       Stage stage;
       Parent root;
       stage=(Stage) AddButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/AddAppointment1.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    /**
    * Deletes Appointment from application and database
    * Throws alert if no appointment is selected
    */
    private void DeleteButtonHandler(ActionEvent event) throws SQLException {                
        if(AppointmentsTableView.getSelectionModel().isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error!");
            alert.setContentText("You need to select an appointment before you can delete it!");
            alert.showAndWait();
       }else if(AppointmentsTableView.getSelectionModel().isEmpty() == false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DELETE CONFIRMATION!");
            alert.setContentText("Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
      
            if(result.isPresent() && result.get() == ButtonType.OK){
                Appointment selectedAppointment = (Appointment)AppointmentsTableView.getSelectionModel().getSelectedItem();
                Appointment.allAppointments.remove(selectedAppointment);
            
            
                Connection conn = DBConnection.startConnection(); 
                String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
                DBQuery.setPreparedStatement(conn, deleteStatement);
                PreparedStatement ps = DBQuery.getPreparedStatement();

                int appointmentID = selectedAppointment.getAppointmentID();
                ps.setInt(1, appointmentID);
                ps.execute();   
                
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("APPOINTMENT DELETED");
                alert1.setContentText("APPOINTMENT ID: " + selectedAppointment.getAppointmentID() + " With APPOINTMENT TYPE: " + selectedAppointment.getType() + "\nWAS SUCCESSFULLY DELETED");
                Optional<ButtonType> result1 = alert1.showAndWait();
            }
        }
    }

    @FXML
    /**
    * Loads Modify Appointment Screen - Sets selected appointment information in next screen
    * Throws Alert if no appointment is selected
    */
    private void ModifyButtonHAndler(ActionEvent event) throws IOException, SQLException {
        if(AppointmentsTableView.getSelectionModel().isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error!");
            alert.setContentText("You need to select an appointment before you can modify!");
            alert.showAndWait();
        }else{
            Stage stage;
            Parent root;
            stage=(Stage) ModifyButton.getScene().getWindow();

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/ModifyAppointment1.fxml"));

            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            ModifyAppointmentController controller = loader.getController();
            Appointment selectedAppointment = AppointmentsTableView.getSelectionModel().getSelectedItem();
            controller.setAppointment(selectedAppointment);
        }
    }

    @FXML
    /**
    * Loads Appointment View Screen
    */
    private void ViewAppointmentsButtonHandler(ActionEvent event) throws IOException {  
       Stage stage;
       Parent root;
       stage=(Stage) ViewAppointmentsButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/ViewAppointments1.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    /**
    * Loads Appointment Types Report Screen
    */
    private void AppointmentTypesButtonHAndler(ActionEvent event) throws IOException {
       Stage stage;
       Parent root;
       stage=(Stage) AppointmentTypesButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/AppointmentTypesReport1.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    /**
     * Loads consultant schedule report screen
     */
    private void ConsultantScheduleButtonHandler(ActionEvent event) throws IOException {    
       Stage stage;
       Parent root;
       stage=(Stage) ConsultantScheduleButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/ContactScheduleReport1.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    /**
    * Loads Customer Appointment Report Screen
    */
    private void AppointmentCustomerButtonHandler(ActionEvent event) throws IOException {
       Stage stage;
       Parent root;
       stage=(Stage) AppointmentCustomerButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/CustomerAppointmentReport1.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    /**
    * Logs user out of application
    * Throws Alert verifying user wants to log out
    */
    private void LogOffButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to log out?");
        alert.setTitle("Exit Alert!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage;
            Parent root;
            stage=(Stage) addCustomerButton.getScene().getWindow();

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Login.fxml"));

            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }       
    }
    
    @FXML
    /**
    * Loads Add Customer screen
    */
    private void addCustomerButtonHandler(ActionEvent event) throws IOException {
       Stage stage;
       Parent root;
       stage=(Stage) addCustomerButton.getScene().getWindow();

       FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/AddCustomer.fxml"));

       root = loader.load();
           
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
    @FXML
    /**
    * Deletes Selected Customer from application and database
    * Throws alert if no customer is selected
    * 
    */
    private void deleteCustomerButtonHandler(ActionEvent event) throws SQLException {
        if(CustomersTableView.getSelectionModel().isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error!");
            alert.setContentText("You need to select a customer before you can delete!");
            alert.showAndWait();
       }else if(CustomersTableView.getSelectionModel().isEmpty() == false){
           Customer selectedCustomer = CustomersTableView.getSelectionModel().getSelectedItem(); 
           
           
           //Check DB for Customer Appointments 
           Connection conn = DBConnection.startConnection(); 
            String selectStatement = "SELECT * FROM appointments WHERE Customer_ID = " + selectedCustomer.getCustID();
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();   //Execute Prepared Statement

            ResultSet rs = ps.getResultSet();
            
            
            //If appointments - throw error and advise to cancel appointments - else, ask if you really want to delete customer
            if(rs.next()){
            System.out.println(rs.next());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("DELETE CUSTOMER APPOINTMENTS");
                alert.setContentText("You must delete all appointments for this customer \nbefore you can delete the customer!");
                Optional<ButtonType> result = alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("DELETE CONFIRMATION!");
                alert.setContentText("Are you sure you want to delete this customer?");
                Optional<ButtonType> result = alert.showAndWait();
                //Delete Customer
                if(result.isPresent() && result.get() == ButtonType.OK){
                    Customer selectedCustomer1 = (Customer)CustomersTableView.getSelectionModel().getSelectedItem();
                    Customer.allCustomers.remove(selectedCustomer1);


                    Connection conn1 = DBConnection.startConnection(); 
                    String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
                    DBQuery.setPreparedStatement(conn1, deleteStatement);
                    PreparedStatement ps1 = DBQuery.getPreparedStatement();

                    int customerID = selectedCustomer1.getCustID();
                    ps1.setInt(1, customerID);
                    ps1.execute();   
                }
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("CUSTOMER DELETED");
                alert1.setContentText("CUSTOMER SUCCESSFULLY DELETED");
                Optional<ButtonType> result1 = alert1.showAndWait();
            }
        }
    }
    
    
        
    @FXML
    private void SearchAppointmentHandler(ActionEvent event) throws SQLException, IOException {   
     Stage stage;
            Parent root;
            stage=(Stage) addCustomerButton.getScene().getWindow();

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/SearchAppointments.fxml"));

            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    

    @FXML
    /**
    * Loads Modify Customer Screen
    * Throws alert if no customer is selected
    * 
    */
    private void modifyCustomerButtonHandler(ActionEvent event) throws IOException, SQLException {
        if(CustomersTableView.getSelectionModel().isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error!");
            alert.setContentText("You need to select a customer before you can modify!");
            alert.showAndWait();
        }else{
            Stage stage;
            Parent root;
            stage=(Stage) addCustomerButton.getScene().getWindow();

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/ModifyCustomer.fxml"));

            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            ModifyCustomerController controller = loader.getController();
            Customer selectedCustomer = CustomersTableView.getSelectionModel().getSelectedItem();
            controller.setCustomer(selectedCustomer);
        }
    }

    private static class stage {

        private static void setTitle(String scheduling_System) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void setScene(Scene scene) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void show() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public stage() {
        }
    }
    
    //Creating a Search Customers method
    
    public boolean searchCustomers(int customerID){
        for(Customer ourCust : Customer.getAllCustomers()){
            if(ourCust.getCustID() == customerID){
                return true;
            }
        }
        return false;
    }
    
    //Updating Customers Method
    
    public boolean updateCustomer(int id, Customer customer){
        int index = -1;
        
        for(Customer ourCust : Customer.getAllCustomers()){
            index++;
            if(ourCust.getCustID() == id){
                Customer.getAllCustomers().set(id, customer);
                return true;            
            }
        }
        return false;
    }
    
    //Remove Customers Method
    
    public boolean removeCustomer(int id){
        for(Customer ourCust : Customer.getAllCustomers()){
            if(ourCust.getCustID() == id){
                return Customer.getAllCustomers().remove(ourCust);
            }
        }
        return false;
    }
    
    
    /**
    * Initializes the controller class. 
    * 
    * POPULATES CUSTOMER TABLE WITH CUSTOMER INFO FROM DATABASE
    * Gets user ID from Database
    * @param url - initialize URL
    * @param rb - resourceBundle
    */
    public void initialize(URL url, ResourceBundle rb){
        CustomersTableView.setItems(Customer.getAllCustomers());
        
        CustIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        CustNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustName"));
        AddressIDColumn.setCellValueFactory(new PropertyValueFactory<>("AddressID"));
        ActiveColumn.setCellValueFactory(new PropertyValueFactory<>("Active"));
        
        Add1Column.setCellValueFactory(new PropertyValueFactory<>("Address"));
      //  Add2Column.setCellValueFactory(new PropertyValueFactory<>("Address"));
        StateProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("stateProvinceName"));
     
        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));;
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));;
        ZipColumn.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));;

        
        AppointmentsTableView.setItems(Appointment.getAllAppointments());

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        AppointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        StartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
         
        System.out.println("StartDateTimeColumn: " + StartDateTimeColumn.getCellValueFactory().toString());
        
        //Getting USER_ID from DB
        try {
            Connection conn3 = DBConnection.startConnection();        
            String selectPreparedStatement1 = "SELECT User_ID FROM users";     

            DBQuery.setPreparedStatement(conn3, selectPreparedStatement1); 

            PreparedStatement statement1 = DBQuery.getPreparedStatement(); 

            statement1.execute(selectPreparedStatement1);                 
            ResultSet rs3 = statement1.getResultSet();           

            while(rs3.next()){
                int userID = rs3.getInt("User_ID");
                Employee.addUsersID(userID);
          }
        } catch (SQLException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
