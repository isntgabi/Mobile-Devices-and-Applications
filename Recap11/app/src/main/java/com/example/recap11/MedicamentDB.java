package com.example.recap11;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({com.example.recap11.TypeConverters.class})
@Database(entities = {Medicament.class}, version = 1, exportSchema = false)
public abstract class MedicamentDB extends RoomDatabase {
    private static final String dbName = "medicamente.db";
    private static MedicamentDB instance;

    public static MedicamentDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, MedicamentDB.class,dbName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract MedicamentDAO getMedicamentDAO();
}
