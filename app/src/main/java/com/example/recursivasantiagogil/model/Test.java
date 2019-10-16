package com.example.recursivasantiagogil.model;

public class Test {

    private String name;
    private String email;
    private int score;
    private int currentQuestion = 0;

    public static final int CORRECT_SCORE = 2;
    public static final int INCORRECT_SCORE = -1;
    public static final int INCOMPLETE_SCORE = -2;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void nextQuestion() {
        currentQuestion++;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void submitAnswer(Boolean isCorrect) {
        if (isCorrect != null){
            if (isCorrect)
                setScore(getScore() + CORRECT_SCORE);
            else
                setScore(getScore() + INCORRECT_SCORE);
        } else
             setScore(getScore() + INCOMPLETE_SCORE);

    }
}
