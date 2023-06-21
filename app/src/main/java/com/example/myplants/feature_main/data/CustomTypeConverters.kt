package com.example.myplants.feature_main.data

import androidx.room.TypeConverter
import com.example.myplants.feature_main.domain.model.Size
import java.time.DayOfWeek
import java.util.Date

class CustomTypeConverters {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDayOfWeekList(dayOfWeekList: List<DayOfWeek>): String {
        return dayOfWeekList.joinToString(",")
    }

    @TypeConverter
    fun toDayOfWeekList(value: String): List<DayOfWeek> {
        return value.split(",").map { DayOfWeek.valueOf(it) }
    }

    @TypeConverter
    fun fromSize(size: Size): String {
        return size.name
    }

    @TypeConverter
    fun toSize(name: String): Size {
        return enumValueOf(name)
    }
}