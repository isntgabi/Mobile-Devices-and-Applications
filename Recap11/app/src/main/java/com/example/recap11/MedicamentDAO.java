package com.example.recap11;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicamentDAO {

    @Insert
    void insertMedicament(Medicament medicament);

    @Query("SELECT * FROM medicamente")
    List<Medicament> getMedicamente();
}
