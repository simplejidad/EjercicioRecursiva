package com.example.recursivasantiagogil.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results_table")
public class Result {

    @PrimaryKey
    private int id;
    private String name;
    private String email;
    private int result;

    public Result(int id, String name, String email, int result) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.result = result;
    }

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
