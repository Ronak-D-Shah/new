package com.quizapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class OptionsPage extends Pane {

    Button infoButton;

    Button[] portionButtons = new Button[3];

    Button history;

    public OptionsPage(){
        setPrefSize(300, 500);
        initializeInfoButton();
        initializePortionButtons();
        initializeHistoryButton();
    }

    public void initializeInfoButton(){
        infoButton = new Button("Profile");
        infoButton.setPrefSize(50, 50);
        infoButton.setLayoutX(225);
        infoButton.setLayoutY(25);
        infoButton.setFont(new Font(12));

        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.stage.setScene(new Scene(infoBox()));
            }
        });
        getChildren().add(infoButton);
    }

    public void initializePortionButtons(){
        String [] opt = {"SPORTS", "MOVIES", "ANIMALS"};
        for(int i = 0, y = 100; i < 3; i++, y += 100){
            portionButtons[i] = new Button(opt[i]);
            portionButtons[i].setPrefSize(250, 50);
            portionButtons[i].setFont(new Font(25));
            portionButtons[i].setLayoutX(25);
            portionButtons[i].setLayoutY(y);
            portionButtons[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            portionButtons[i].setTextFill(Color.WHITE);
            portionButtons[i].setCursor(Cursor.HAND);
            int finalI = i;
            portionButtons[i].setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Main.stage.setScene(new Scene(new QuizController(finalI)));
                }
            });
            getChildren().add(portionButtons[i]);
        }
    }

    public void initializeHistoryButton(){
        history = new Button("VIEW HISTORY");
        history.setLayoutX(25);
        history.setLayoutY(420);
        history.setPrefSize(250,50);
        history.setFont(new Font(25));
        history.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        history.setCursor(Cursor.HAND);
        history.setTextFill(Color.WHITE);

        history.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new QuizHistory().getHistoryTable();
            }
        });

        getChildren().add(history);
    }

    private VBox infoBox(){
        VBox vBox = new VBox();

        HBox [] hBoxes = new HBox[4];
        Label [] title = new Label[4];
        Label [] value = new Label[4];
        String [] tstr = {"Name: ", "ID: ", "Email: ", "Password: "};
        String [] vstr = {LoginController.name, "" + LoginController.id, LoginController.email, LoginController.password};
        for(int i = 0; i < 4; i++){
            hBoxes[i] = new HBox();
            title[i] = new Label(tstr[i]);
            title[i].setFont(new Font(25));
            hBoxes[i].getChildren().add(title[i]);
            value[i] = new Label(vstr[i]);
            value[i].setFont(new Font(25));
            value[i].setTextFill(Color.GRAY);
            hBoxes[i].setPadding(new Insets(5, 10, 5, 10));
            hBoxes[i].getChildren().add(value[i]);
            vBox.getChildren().add(hBoxes[i]);
        }
        vBox.setSpacing(20);

        Button logout = new Button("LOG OUT");
        logout.setFont(new Font(20));
        logout.setCursor(Cursor.HAND);
        Button goBack = new Button("Go Back");
        goBack.setCursor(Cursor.HAND);
        goBack.setFont(new Font("Calibri", 20));
        HBox hBox = new HBox(logout, goBack);
        hBox.setPadding(new Insets(10, 20, 20, 10));
        hBox.setSpacing(20);
        vBox.getChildren().add(hBox);
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load());
                    Main.stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    Scene scene = new Scene(new OptionsPage());
                    Main.stage.setScene(scene);
            }
        });

        return vBox;
    }
}
