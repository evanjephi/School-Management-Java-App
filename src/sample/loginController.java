package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class loginController implements Initializable, controlledScreen {

    ScreensController myController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private javafx.scene.control.TextField usernameLogin;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private Label invalidUNL;
    @FXML
    private Label invalidPassL;


    public void loginButtonClicked(ActionEvent actionEvent) throws SQLException {

       try {
           if (usernameLogin.getText().isBlank() == false && passwordLogin.getText().isBlank() == false) {
               invalidUNL.setVisible(true);
               invalidPassL.setVisible(true);
           }
               // validateLogin();
               // System.out.println("is blank");
               if (Helper.validateLogin(Helper.ConnectToDatabase(),usernameLogin.getText(), passwordLogin.getText())) {

                  new loginSession(usernameLogin.getText());
                                     invalidUNL.setVisible(false);
                   invalidPassL.setVisible(false);
                    //Calling UserAccount Window
                   //myController.setScreen(Main.userAccountController3);

                   userAccount();
                   final Node source = (Node)actionEvent.getSource();
                   final Stage stage = (Stage)source.getScene().getWindow();
                   stage.close();
                  // Msg.setText("Username and password is correct");


               }

           else {
               invalidUNL.setVisible(true);
               invalidPassL.setVisible(true);
               //Msg.setText("Username and password is NOT correct");
           }
       }catch(SQLException e){

           //Msg.setText("Username and password is NOT correct");
           e.printStackTrace();
        }
    }




     public void userAccount() {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("userAccount.fxml"));
             Parent root = (Parent) loader.load();
             //Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
             Stage userAccount = new Stage();
             userAccount.setTitle("Home Account");
             userAccountController usController = loader.getController();
             usController.show();
             userAccount.setScene(new Scene(root, 1000, 500));
             userAccount.show();

         } catch (Exception e) {
             e.printStackTrace();
             e.getCause();
         }
     }


}

