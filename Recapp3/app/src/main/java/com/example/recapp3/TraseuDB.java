package com.example.recapp3;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Traseu.class}, version = 1, exportSchema = false)
public abstract class TraseuDB extends RoomDatabase {
    private static final String name = "trasee.db";
    private static TraseuDB instance;

    public static TraseuDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context, TraseuDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract TraseuDAO getTraseuDAO();
}
