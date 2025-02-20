package com.example.recap17;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tara.class}, version = 1, exportSchema = false)
public abstract class TaraDB extends RoomDatabase {
    private static final String dbName = "tari.db";
    private static TaraDB instance;

    public static TaraDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, TaraDB.class,dbName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract TaraDAO getTaraDAO();
}
