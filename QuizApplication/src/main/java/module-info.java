module com.quizapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.quizapplication to javafx.fxml;
    exports com.quizapplication;
}