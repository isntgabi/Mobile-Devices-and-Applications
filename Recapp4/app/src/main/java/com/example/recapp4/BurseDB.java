package com.example.recapp4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bursa.class}, version=1, exportSchema = false)
public abstract class BurseDB extends RoomDatabase {
    private static final String name = "burse.db";
    private static BurseDB instance;

    public static BurseDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context, BurseDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract BursaDAO getBursaDAO();
}
