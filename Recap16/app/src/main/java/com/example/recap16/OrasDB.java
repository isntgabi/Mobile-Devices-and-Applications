package com.example.recap16;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Oras.class}, version = 1, exportSchema = false)
public abstract class OrasDB extends RoomDatabase {
    private static final String dbName = "orase.db";
    private static OrasDB instance;

    public static OrasDB getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, OrasDB.class, dbName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    public abstract OrasDAO getOrasDAO();

}
