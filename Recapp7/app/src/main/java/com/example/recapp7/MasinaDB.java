package com.example.recapp7;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Masina.class}, version = 1, exportSchema = false)
public abstract class MasinaDB extends RoomDatabase {
    private static final String name = "masini.db";
    private static MasinaDB instance;

    public static MasinaDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context,MasinaDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract MasinaDAO getMasinaDAO();
}
