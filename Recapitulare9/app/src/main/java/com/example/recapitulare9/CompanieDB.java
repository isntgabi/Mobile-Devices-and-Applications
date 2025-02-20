package com.example.recapitulare9;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({Converters.class})
@Database(entities = {Companie.class}, version = 1, exportSchema = false)
public abstract class CompanieDB extends RoomDatabase {
    private static final String dbName = "companii.db";
    private static CompanieDB instance;

    public static CompanieDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, CompanieDB.class, dbName).allowMainThreadQueries().fallbackToDestructiveMigrationOnDowngrade().build();
        }
        return instance;
    }

    public abstract CompanieDAO getCompanieDAO();
}
