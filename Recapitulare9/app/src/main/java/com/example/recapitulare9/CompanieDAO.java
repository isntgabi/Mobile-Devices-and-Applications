package com.example.recapitulare9;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompanieDAO {

    @Insert
    void insertCompanie(Companie companie);

    @Query("SELECT * FROM companii")
    List<Companie> getCompanii();
}
