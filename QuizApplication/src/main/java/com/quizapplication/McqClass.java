package com.quizapplication;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;

public class McqClass extends VBox {
    Label statement;
    RadioButton [] options = new RadioButton[4];
    public ToggleGroup toggleGroup;

    String correctAnwser;

    public McqClass(String stmt, String [] opt, String ans){
        correctAnwser = ans;
        toggleGroup = new ToggleGroup();

        statement = new Label(stmt);
        statement.setFont(new Font("Times New Roman", 18));
        statement.setTextFill(Color.LIGHTCORAL);
        HBox hBox = new HBox();
        hBox.getChildren().add(statement);
        hBox.setPadding(new Insets(15, 0, 10, 0));
        getChildren().add(hBox);


        HBox [] hBoxes = new HBox[4];
        for (int i = 0; i < 4; i++){
            hBoxes[i] = new HBox();
            options[i] = new RadioButton(opt[i]);
            options[i].setToggleGroup(toggleGroup);
            hBoxes[i].getChildren().add(options[i]);
            hBoxes[i].setPadding(new Insets(5, 0, 5, 15));
            getChildren().add(hBoxes[i]);
        }
    }
    public String selectedAnswer(){
        if (toggleGroup.getSelectedToggle() != null) {
            String txt = ((RadioButton)toggleGroup.getSelectedToggle()).getText();
            System.out.println(txt);
            return txt;
        }
        return "";
    }
    public RadioButton answerButton(){
        if(Objects.equals(options[0].getText(), correctAnwser))
            return options[0];
        else if(Objects.equals(options[1].getText(), correctAnwser))
            return options[1];
        else if(Objects.equals(options[2].getText(), correctAnwser))
            return options[2];
        else
            return options[3];
    }
    public String getCorrectAnwser(){
        return correctAnwser;
    }

    public void disableOptions(){
        for(int i = 0; i < 4; i++)
            options[i].setDisable(true);
    }
}
