package com.quizapplication;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminPage extends VBox {
    Stage delStage;
    Button [] optionButtons = new Button[3];

    public AdminPage(){
        initializeOptionsButtons();
        initializeLogoutButton();
    }

    private void initializeOptionsButtons(){
        setSpacing(10);
        setPadding(new Insets(20, 15, 20, 15));
        String [] opt = {"Add Student", "Delete Student", "Add Quiz"};
        for(int i = 0; i < 3; i++){
            optionButtons[i] = new Button(opt[i]);
            optionButtons[i].setFont(new Font(24));
            optionButtons[i].setPrefSize(250, 50);
            getChildren().add(optionButtons[i]);
        }
        optionButtons[0].setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.stage.setScene(new Scene(new SignUpPage(false)));
            }
        });
        optionButtons[1].setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                delStage = new Stage();
                delStage.setScene(new Scene(deleteBox()));
                delStage.show();
            }
        });
        optionButtons[2].setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                delStage = new Stage();
                delStage.setScene(new Scene(addQuizPage()));
                delStage.show();
            }
        });

    }

    private void initializeLogoutButton(){
        Button logout = new Button("LOGOUT");
        logout.setFont(new Font(26));
        logout.setPrefSize(250, 50);
        getChildren().add(logout);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load());
                    Main.stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public VBox deleteBox(){
        VBox vBox = new VBox();

        Label idLabel = new Label("Student ID");
        idLabel.setFont(new Font(20));

        TextField idBox = new TextField();
        idBox.setFont(new Font(20));

        Label name = new Label("");
        name.setFont(new Font(20));

        Label email = new Label("");
        email.setFont(new Font(20));

        Button delete = new Button("DELETE STUDENT");
        delete.setFont(new Font(28));
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!name.getText().isEmpty()){
                    String query = "DELETE FROM student WHERE student_id = " + Integer.parseInt(idBox.getText()) + ";";
                    Main.myConnection.runQuery(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success!");
                    alert.setContentText("Student with ID '" + idBox.getText() + "' deleted");
                    alert.show();
                    alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent dialogEvent) {
                            delStage.close();
                        }
                    });
                }
            }
        });

        idBox.textProperty().addListener((observable, oldValue, newValue) -> {;
            Platform.runLater(()->{
                if(!Objects.equals(oldValue, newValue)){
                    if(idBox.getText().isEmpty()) {
                            name.setText("");
                            email.setText("");
                    }
                    else {
                        String query = "SELECT first_name, last_name, email FROM student WHERE student_id = " + idBox.getText() + ";";
                        ResultSet resultSet = Main.myConnection.getResult(query);
                        try {
                            int i = 0;
                            while (resultSet.next()){
                                name.setText(resultSet.getString(1) + " " + resultSet.getString(2));
                                email.setText(resultSet.getString(3));
                                i++;
                            }
                            if(i == 0){
                                name.setText("");
                                email.setText("");
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        });

        vBox.getChildren().addAll(idLabel, idBox, name, email, delete);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(20, 15, 20,15));
        return vBox;
    }

    public VBox addQuizPage(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20,15,20,15));
        vBox.setSpacing(20);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Sports", "Movies", "Animals");
        comboBox.setValue("Sports");

        Label question = new Label("Write Question:");
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 150);
        VBox vBox1 = new VBox(question, textArea);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(3);
        Label optLabel = new Label("Options");
        vBox2.getChildren().add(optLabel);
        TextField [] options = new TextField[4];
        for(int i = 0; i < 4; i++){
            options[i] = new TextField();
            vBox2.getChildren().add(options[i]);
        }

        Label ans = new Label("Right Answer: ");
        ComboBox<Integer> answer = new ComboBox<>();
        answer.setValue(0);
        answer.getItems().addAll(0, 1, 2, 3);
        HBox hBox = new HBox(ans, answer);

        Button add = new Button("Add Question");
        add.setFont(new Font(15));
        add.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int type = 0;
                if(Objects.equals(comboBox.getValue(), "Movies"))
                    type = 1;
                else if(Objects.equals(comboBox.getValue(), "Animals"))
                    type = 2;
                String query = "INSERT INTO quiz_questions (statement, option1, option2, option3, option4, answer, quiz_type) VALUES " +
                        "('" + textArea.getText() +"', '" + options[0].getText() + "', '" + options[1].getText() + "', '" + options[2].getText() + "', '" + options[3].getText() + "', '"+ options[answer.getValue()].getText() +"',"+type+" );";
                Main.myConnection.runQuery(query);
                System.out.println(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success!");
                alert.setContentText("Question added successfuly to " + comboBox.getValue() + " Portion");
                alert.show();
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent dialogEvent) {
                        delStage.close();
                    }
                });
            }
        });

        vBox.getChildren().addAll(comboBox, vBox1, vBox2, hBox, add);

        return vBox;
    }
}
