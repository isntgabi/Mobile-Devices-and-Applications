package com.example.recapp6;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bon.class}, version=1, exportSchema = false)
public abstract class BonDB extends RoomDatabase {
    private static final String name = "bonuri.db";
    private static BonDB instance;

    public static BonDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,BonDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract BonDAO getBonDAO();
}
