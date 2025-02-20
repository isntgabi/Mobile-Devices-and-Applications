package com.example.recapp4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BursaDAO {

    @Insert
    void insertBursa(Bursa bursa);

    @Update
    void updateBursa(Bursa bursa);

    @Delete
    void deleteBursa(Bursa bursa);

    @Query("SELECT * FROM burse")
    List<Bursa> getBurse();

    @Query("SELECT * FROM burse WHERE tip = :tipul")
    List<Bursa> getBurseDupaTip(String tipul);

}
