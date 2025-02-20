package com.example.recapp7;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MasinaDAO {

    @Insert
    void insertMasina(Masina masina);

    @Delete
    void deleteMasina(Masina masina);

    @Update
    void updateMasina(Masina masina);

    @Query("SELECT * FROM masini")
    List<Masina> getMasini();
}
