package com.quizapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Vector;

public class AllMcqsPage {
    public AllMcqsPage(){
    }

    VBox vBox;

    Vector<McqClass> mcq = new Vector<>();

    int score = 0;

    int type;

    public VBox createMcqs(int type){
        this.type = type;
        vBox = new VBox();

        ResultSet resultSet = fetchData("Select * from quiz_questions where quiz_type = " + type + ";");
        Vector<String> questions = new Vector<>();
        Vector<String[]> options = new Vector<>();
        Vector<String> answers = new Vector<>();
        try {
            int i = 0;
            while (resultSet.next()){
                questions.add(resultSet.getString(1));
                //questions[i] = resultSet.getString(1);
                options.add(new String[]{resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)});
                answers.add(resultSet.getString(6));
                mcq.add(new McqClass(questions.elementAt(i), options.elementAt(i), answers.elementAt(i))) ;
                vBox.getChildren().add(mcq.elementAt(i));
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < mcq.size(); i++) {

        }
        vBox.getChildren().add(buttons());

        return vBox;
    }

    public HBox buttons(){
        Button back = new Button("GO BACK");
        back.setFont(new Font(24));
        back.setCursor(Cursor.HAND);

        Button submit = new Button("SUBMIT QUIZ");
        submit.setFont(new Font(24));
        submit.setCursor(Cursor.HAND);

        HBox hBox = new HBox(back, submit);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(30, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.stage.setScene(new Scene(new OptionsPage()));
            }
        });

        submit.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                calculateResult(hBox);
            }
        });

        return hBox;
    }

    public void calculateResult(HBox hBx){
        for(int i = 0; i < mcq.size(); i++){
            mcq.elementAt(i).disableOptions();
            if(Objects.equals(mcq.elementAt(i).selectedAnswer(), mcq.elementAt(i).getCorrectAnwser())) {
                score++;
            }
            else{
                mcq.elementAt(i).answerButton().setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            }
        }
        addQuizToHistory();
        vBox.getChildren().remove(hBx);

        Button goBack = new Button("GO BACK");
        goBack.setFont(new Font(24));
        goBack.setTextFill(Color.WHITE);
        goBack.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        goBack.setCursor(Cursor.HAND);

        goBack.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.stage.setScene(new Scene(new OptionsPage()));
            }
        });

        HBox hBox = new HBox(goBack);
        hBox.setPadding(new Insets(30, 0, 20, 0));
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);

    }

    public void addQuizToHistory(){
        String type = switch (this.type) {
            case 0 -> "SPORTS";
            case 1 -> "MOVIES";
            case 2 -> "ANIMALS";
            default -> null;
        };
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        String query = "INSERT INTO quiz (quiz_date, student_id, score, quiz_type) VALUES ('" + localDate.toString() + "','" + LoginController.id + "', '" + score + "', '" + type + "');" ;
        Main.myConnection.runQuery(query);
    }

    public ResultSet fetchData(String query){
        ResultSet resultSet = null;
        try {
            resultSet = Main.myConnection.getResult(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
