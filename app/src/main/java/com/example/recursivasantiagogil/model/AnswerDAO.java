package com.example.recursivasantiagogil.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnswerDAO {

    @Insert
    void insert(Answer answer);

    @Update
    void update(Answer answer);

    @Delete
    void delete(Answer answer);

    @Query("SELECT * FROM answers_table")
    List<Answer> getAllAnswers();

    @Query("SELECT * FROM answers_table WHERE question_id==:questionId")
    List<Answer> getAnswersForQuestion(int questionId);

}
