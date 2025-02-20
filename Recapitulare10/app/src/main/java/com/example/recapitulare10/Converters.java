package com.example.recapitulare10;

import androidx.room.TypeConverter;


import java.util.Date;

public
class Converters {

    @TypeConverter
    public Long dateToTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public Date fromTimeStamp(Long id) {
        return id == null ? null : new Date(id);
    }
}
