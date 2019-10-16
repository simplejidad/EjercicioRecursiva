package com.example.recursivasantiagogil.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROM questions_table")
    List<Question> getAllQuestions();

    @Query("SELECT * FROM questions_table WHERE id ==:questionId")
    Question getQuestionById(int questionId);

}
