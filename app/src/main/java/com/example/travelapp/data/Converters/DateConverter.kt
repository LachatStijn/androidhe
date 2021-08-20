package com.example.travelapp.data.Converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToLong(data: Date): Long {
        return data.time
    }

    @TypeConverter
    fun longToDate(data: Long): Date{
        return Date(data)
    }
}