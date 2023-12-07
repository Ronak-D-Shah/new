package com.quizapplication;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class QuizController extends Pane {
    AllMcqsPage mcqs;

    ScrollPane quizArea;

    public QuizController(int type){
        Main.stage.setY(10);

        mcqs = new AllMcqsPage();
        setPrefSize(640, 600);
        String [] str = {"Name: " + LoginController.name, "ID: " + LoginController.id};
        Label [] info = new Label[2];
        for (int i = 0, x = 100 ; i < 2; i++, x += 300){
            info[i] = new Label(str[i]);
            info[i].setFont(new Font(20));
            info[i].setLayoutX(x);
            getChildren().add(info[i]);
        }

        quizArea = new ScrollPane();
        quizArea.setPrefSize(600, 560);

        getChildren().add(quizArea);

        quizArea.setContent(mcqs.createMcqs(type));
        quizArea.setLayoutX(20);
        quizArea.setLayoutY(40);

    }


}
