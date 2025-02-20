package com.example.recapp6;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BonDAO {

    @Insert
    void insertBon(Bon bon);

    @Delete
    void deleteBon(Bon bon);

    @Update
    void updateBon(Bon bon);

    @Query("SELECT * FROM bonuri")
    List<Bon> getBonuri();

    @Query("SELECT * FROM bonuri WHERE plata = :p")
    List<Bon> getBonuriOnline(String p);
}
