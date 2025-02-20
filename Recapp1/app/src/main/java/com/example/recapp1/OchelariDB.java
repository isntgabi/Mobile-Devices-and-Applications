package com.example.recapp1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ochelari.class}, version = 1, exportSchema = false)
public abstract class OchelariDB extends RoomDatabase {
    private static final String dbName = "ochelari.db";
    private static OchelariDB instance;

    public static OchelariDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context, OchelariDB.class, dbName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract OchelariDAO getOchelariDAO();
}
