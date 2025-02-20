package com.example.recapp2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExamenDAO {

    @Insert
    void insertExamen(Examen examen);

    @Update
    void updateExamen(Examen examen);

    @Query("SELECT * FROM examene")
    List<Examen> getExamene();
}
