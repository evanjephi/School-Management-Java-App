package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

   /* public static String registerController1 = "main";
    public static String registerFormFile = "registerForm.fxml";
    public static String loginController2 = "loginController";
    public static String loginFormFile = "loginForm.fxml";
    public static String userAccountController3 = "userAccountController";
    public static String userAccountFile = "userAccount.fxml";

    */


    @Override
    public void start(Stage primaryStage) throws Exception{
/*
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.registerController1, Main.registerFormFile);
        mainContainer.loadScreen(Main.loginController2, Main.loginFormFile);
        mainContainer.loadScreen(Main.userAccountController3, Main.userAccountFile);

        mainContainer.setScreen(Main.registerController1);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();

 */

        Parent root = FXMLLoader.load(getClass().getResource("registerForm.fxml"));
        primaryStage.setTitle("Register Form");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
