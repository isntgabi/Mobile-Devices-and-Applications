package com.example.recapp2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Examen.class}, version = 1, exportSchema = false)
public abstract class ExamenDB extends RoomDatabase {
    private static final String name = "examene.db";
    private static ExamenDB instance;

    public static ExamenDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context,ExamenDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ExamenDAO getExamenDAO();
}
