package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController implements Initializable, controlledScreen {

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }


    @FXML
    private javafx.scene.control.TextField username;
    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPW;
    @FXML
    private javafx.scene.control.TextField streetNo;
    @FXML
    private javafx.scene.control.TextField streetName;
    @FXML
    private javafx.scene.control.TextField postalCode;
    @FXML
    private javafx.scene.control.TextField city;
    @FXML
    private javafx.scene.control.Label invalidUN;

    @FXML
    private javafx.scene.control.Label emailInvalid;
    @FXML
    private javafx.scene.control.Label passInvalid;
    @FXML
    private javafx.scene.control.Label cpwInvalid;
    @FXML
    private javafx.scene.control.Label stNumInvalid;
    @FXML
    private javafx.scene.control.Label nameStInvalid;
    @FXML
    private javafx.scene.control.Label pcInvalid;
    @FXML
    private Label cityInvalid;


    public void registerButtonClicked(ActionEvent actionEvent) {

        boolean valid = true;
        Helper.CreateTable(Helper.ConnectToDatabase());
        Alert alert = new Alert(Alert.AlertType.NONE);

        String uName = username.getText();
        if(Helper.validateUsername(uName) == false){

            invalidUN.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            invalidUN.setVisible(false);
            System.out.println("The username is valid");
        }

        String uEmail = email.getText();
        if(Helper.validateEmail(uEmail) == false){

            emailInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            emailInvalid.setVisible(false);
            System.out.println("Email is valid");
        }

        String pw = password.getText();
        if(Helper.validatePassword(pw) == false){

            passInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            passInvalid.setVisible(false);
            System.out.println("password is valid");
        }

        String conPW = confirmPW.getText();
        if(Helper.validateConfirm(pw, conPW) == false){

            cpwInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            cpwInvalid.setVisible(false);
            System.out.println("Confirm password is valid");
        }

        String stNumber = streetNo.getText();
        if(Helper.validateStNumber(stNumber) == false){

            stNumInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            stNumInvalid.setVisible(false);
            System.out.println("Street number is valid");
        }

        String stName = streetName.getText();
        if(Helper.validateStName(stName) == false){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            nameStInvalid.setVisible(true);


            valid = false;

        }else{
            nameStInvalid.setVisible(false);
            System.out.println("Street name is valid");
        }

        String pc = postalCode.getText();
        if(Helper.validatePostalCode(pc) == false){

            pcInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            pcInvalid.setVisible(false);
            System.out.println("Postal code is valid");
        }

        String cityName = city.getText();
        if(Helper.validateCity(cityName) == false){

            cityInvalid.setVisible(true);

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
            valid = false;

        }else{
            cityInvalid.setVisible(false);
            System.out.println("City name is valid");
        }




        if(valid == true) {
            Helper.InsertRecord(Helper.ConnectToDatabase(), uName, uEmail, pw, conPW, stNumber, stName, pc, cityName);
            loginForm();
            final Node source = (Node)actionEvent.getSource();
            final Stage stage = (Stage)source.getScene().getWindow();
            stage.close();

        }

    }

    public void loginButtonClicked(ActionEvent actionEvent) {

        loginForm();
        final Node source = (Node)actionEvent.getSource();
        final Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public void exitButtonClicked(ActionEvent actionEvent) {

        final Node source = (Node)actionEvent.getSource();
        final Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public void loginForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
            Stage loginForm = new Stage();
            loginForm.setTitle("Login Form");
            loginForm.setScene(new Scene(root, 1000, 500));
            loginForm.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
