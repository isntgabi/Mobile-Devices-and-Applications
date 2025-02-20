package com.example.recapp3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TraseuDAO {
    @Insert
    void insertTraseu(Traseu traseu);

    @Query("SELECT * FROM trasee")
    List<Traseu> getTrasee();

    @Update
    void updateTraseu(Traseu traseu);

    @Delete
    void deleteTraseu(Traseu traseu);

}
