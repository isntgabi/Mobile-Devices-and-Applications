package com.example.recapp5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDB extends RoomDatabase {
    private static final String name = "studenti.db";
    private static StudentDB instance;

    public static StudentDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context,StudentDB.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract StudentDAO getStudentDAO();
}
