package com.quizapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpPage extends Pane {
    TextField [] signUpFields = new TextField[4];
    ResultSet resultSet;


    public SignUpPage(boolean student){
        setPrefSize(400, 600);

        Label[] signUpLabels = new Label[4];
        String [] str = {"First Name","Last Name", "Email", "Password"};
        for(int i = 0, y = 30; i < 4; i++, y += 120){
            signUpFields[i] = new TextField();
            signUpFields[i].setLayoutY(y + 50);
            signUpFields[i].setLayoutX(25);
            signUpFields[i].setPrefSize(350, 50);
            signUpFields[i].setFont(new Font(24));
            signUpLabels[i] = new Label(str[i]);
            signUpLabels[i].setLayoutY(y);
            signUpLabels[i].setLayoutX(25);
            signUpLabels[i].setPrefSize(250, 50);
            signUpLabels[i].setFont(new Font(24));
            getChildren().add(signUpLabels[i]);
            getChildren().add(signUpFields[i]);
        }

        String txt;
        if(student)
            txt = "Sign Up";
        else txt = "Add Student";
        Button signUp = new Button(txt);
        signUp.setPrefSize(125, 50);
        signUp.setLayoutX(50);
        signUp.setLayoutY(525);
        signUp.setFont(new Font("Calibri", 15));
        getChildren().add(signUp);

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!checkValidation()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Please Enter Valid values");
                    alert.show();
                    return;
                }
                String query = "INSERT INTO student (email, first_name, last_name, password) VALUES ('" + signUpFields[2].getText() + "','" + signUpFields[0].getText() + "','" + signUpFields[1].getText() + "','" + signUpFields[3].getText() + "');";
                System.out.println(query);
                Main.myConnection.runQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Congratulations!");
                try {
                    resultSet = Main.myConnection.getResult("SELECT MAX(student_id) FROM student;");
                    resultSet.next();
                    int id = resultSet.getInt(1);
                    String txt;
                    if(student)
                        txt = "You are Successfully Registered now, \nyour student id is '" + id + "'";
                    else txt = "Student added Successfully with id '" + id +"'";
                    alert.setContentText(txt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                alert.show();
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent dialogEvent) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
                        try {
                            Scene scene = null;
                            if(student)
                                scene = new Scene(fxmlLoader.load());
                            else scene = new Scene(new AdminPage());
                            Main.stage.setScene(scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
    private boolean checkValidation(){
        for(int i = 0; i < 4; i++){
            if(signUpFields[i].getText().isEmpty())
                return false;
        }
        if(!signUpFields[0].getText().matches("^[a-zA-Z\\s]+$"))
            return false;
        if(!signUpFields[1].getText().matches("^[a-zA-Z\\s]+$"))
            return false;
        if (!signUpFields[2].getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
            return false;
        return true;
    }
}
