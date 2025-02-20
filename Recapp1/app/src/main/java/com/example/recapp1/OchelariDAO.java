package com.example.recapp1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OchelariDAO {

    @Insert
    void insertOchelari(Ochelari ochelari);

    @Query("SELECT * FROM ochelari WHERE id = :idd")
    Ochelari getOchelariID(Long idd);

    @Query("SELECT * FROM ochelari")
    List<Ochelari> getOchelari();
    @Update
    void updateOchelari(Ochelari ochelari);
}
