package com.example.recursivasantiagogil.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ResultDAO {

    @Insert
    void insert(Result result);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);

    @Query("SELECT * FROM results_table")
    List<Result> getAllResults();

}
