package com.example.recapitulare10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({Converters.class})
@Database(entities = {Carte.class}, version = 1, exportSchema = false)
public abstract class CarteDB extends RoomDatabase {
    private final static String dbName = "carti.db";
    private static CarteDB instance;

    public static CarteDB getInstance(Context context) {
           if(instance == null) {
               instance = Room.databaseBuilder(context, CarteDB.class, dbName).
                       allowMainThreadQueries().
                       fallbackToDestructiveMigration().
                       build();
           }

           return instance;
    }

    public abstract CarteDAO getCarteDAO();
}
