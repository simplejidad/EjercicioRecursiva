package com.example.recursivasantiagogil.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "questions_table")
public class Question {

    @PrimaryKey
    private int id;

    private String question;
    private boolean singleAnswer;

    public Question(int id, String question, boolean singleAnswer) {
        this.id = id;
        this.question = question;
        this.singleAnswer = singleAnswer;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(boolean singleAnswer) {
        this.singleAnswer = singleAnswer;
    }
}
