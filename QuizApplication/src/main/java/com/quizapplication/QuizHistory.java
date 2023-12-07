package com.quizapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class QuizHistory extends Pane {
    TableView<QuizData> history;

    public QuizHistory(){
        history = new TableView<>();
    }
    public void getHistoryTable(){

        TableColumn<QuizData, String> dateColumn = new TableColumn<>("Quiz Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<QuizData, String>("date"));

        TableColumn<QuizData, String> scoreColumn = new TableColumn<>("Score / 10");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<QuizData, String>("score"));

        TableColumn<QuizData, String> typeColumn = new TableColumn<>("Quiz Topic");
        typeColumn.setCellValueFactory(new PropertyValueFactory<QuizData, String>("type"));

        history.getColumns().addAll(dateColumn, scoreColumn, typeColumn);

        ObservableList<QuizData> quizData = FXCollections.observableArrayList();
        String query = "SELECT quiz_date, score, quiz_type FROM quiz WHERE student_id = '" + LoginController.id + "';";
        System.out.println(query);
        ResultSet resultSet = Main.myConnection.getResult(query);
        try {
            while (resultSet.next()) {
                quizData.add(new QuizData(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3)));
            }
            history.setItems(quizData);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(history));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


}
