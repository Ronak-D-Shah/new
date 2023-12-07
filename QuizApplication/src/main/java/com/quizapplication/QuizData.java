package com.quizapplication;

public class QuizData {
    private String date;
    private int score;
    private String type;

    public QuizData(String dt, int scr, String tp){
        date = dt;
        score = scr;
        type = tp;
    }

    public String getDate(){
        return date;
    }
    public int getScore(){
        return score;
    }
    public String getType(){
        return type;
    }
}
