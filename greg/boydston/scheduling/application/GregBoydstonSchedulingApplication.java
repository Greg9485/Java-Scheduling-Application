

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author greg9485
 */
public class GregBoydstonSchedulingApplication extends Application{

    /**
     * Start Screen
     * 
     * Sets the stage for application - calls login form page
     * 
     *@param stage  sets the Stage to open applicaiton
     
     **/
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("BioChem Solutions, LLC. Sales Rep Scheduling System");
        stage.setScene(scene);
        stage.show();
    }
    
     /**
     * Launches program
     * 
     * @param args the command line arguments
     */
   public static void main(String[] args){
        
        //Connect to DataBase
     //   Connection conn = DBConnection.startConnection();      
          
        launch(args);
        
        //Disconnect from DataBase
       // DBConnection.closeConnection();
    }
    
}
