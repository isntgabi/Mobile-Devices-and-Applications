package com.example.recap17;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaraDAO {

    @Insert
    void insertTara(Tara tara);

    @Query("SELECT * FROM tari")
    List<Tara> getTari();
}
