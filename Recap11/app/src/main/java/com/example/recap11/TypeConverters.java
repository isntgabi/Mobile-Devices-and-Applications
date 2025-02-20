package com.example.recap11;

import androidx.room.TypeConverter;

import java.util.Date;

public class TypeConverters {

    @TypeConverter
    public Long timeFromStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public Date dateToTimeStamp(Long date) {
        return date == null ? null : new Date(date);
    }
}
