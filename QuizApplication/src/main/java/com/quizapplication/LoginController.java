package com.quizapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public static String name;
    public static String email;
    public static String password;
    public static int id;
    Stage stage;

    @FXML
    Button loginButton;
    @FXML
    Button signUpButton;

    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordField;

    ResultSet resultSet;

    public void initialize(){
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                if(checkCredentials(emailField.getText(), passwordField.getText()) == 1){
                    stage.setScene(new Scene(new AdminPage()));
                    stage.show();
                }
                else if(checkCredentials(emailField.getText(), passwordField.getText()) == 2){
                    stage.setScene(new Scene(new OptionsPage()));
                    stage.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Incorrect Username or password");
                    alert.show();
                }
            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.stage.setScene(new Scene(new SignUpPage(true)));
            }
        });
    }

    public int checkCredentials(String eml, String pwd){

        if(eml.isEmpty() || pwd.isEmpty())
            return 0;

        ResultSet resultSet = null;

        try {
            String query = "SELECT password FROM app_admin WHERE email = '" + eml + "';";
            resultSet = Main.myConnection.getResult(query);
            while (resultSet.next())
                if(Objects.equals(resultSet.getString(1), pwd))
                    return 1;

            query = "SELECT * FROM student where email = '" + eml + "';";
            resultSet = Main.myConnection.getResult(query);

            if(resultSet.next())
                if(Objects.equals(pwd, resultSet.getString(5))){
                    LoginController.id = resultSet.getInt(1);
                    LoginController.email = eml;
                    LoginController.name = resultSet.getString(3) + " " + resultSet.getString(4);
                    System.out.println(name);
                    LoginController.password = pwd;
                    return 2;
                }
            return 0;


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
