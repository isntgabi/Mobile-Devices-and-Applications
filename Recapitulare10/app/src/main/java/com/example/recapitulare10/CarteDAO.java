package com.example.recapitulare10;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarteDAO {

    @Insert
    void insert(Carte carte);

    @Query("SELECT * FROM carti")
    List<Carte> getCarti();
}
