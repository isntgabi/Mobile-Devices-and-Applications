package com.example.recap16;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrasDAO {

    @Insert
    void insertOras(Oras oras);

    @Query("SELECT * FROM orase")
    List<Oras> getOrase();
}
