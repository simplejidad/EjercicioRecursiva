package com.example.recursivasantiagogil.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity (tableName = "answers_table", primaryKeys = {"question_id", "option"}, foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "question_id"))
public class Answer {

    private int question_id;
    private char option;
    private String answer;
    private boolean isCorrect;


    public Answer() {
    }

    public Answer(int question_id, char option, String answer, boolean isCorrect) {
        this.question_id = question_id;
        this.option = option;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
