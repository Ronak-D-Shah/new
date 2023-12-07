package com.quizapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public static MyConnection myConnection;
    @Override
    public void start(Stage stage) throws IOException {
        myConnection = new MyConnection();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}