package com.quizapplication;

import java.sql.*;

public class MyConnection {
    public Connection connection;
    public PreparedStatement statement;

    public MyConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String dbUrl = "jdbc:mysql://localhost:3306/quizDB";
        String user = "root";
        String password = "Mysql@28";
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(dbUrl, user, password);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String query){
        try {
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void runQuery(String query){
        System.out.println(query);
        try {
            statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
