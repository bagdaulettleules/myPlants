package com.example.myplants.feature_main.data

import androidx.room.TypeConverter
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_notification.domain.model.NotificationType
import java.time.DayOfWeek

class CustomTypeConverters {

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

    @TypeConverter
    fun fromListString(string: List<String>): String {
        return string.joinToString(",")
    }

    @TypeConverter
    fun toListString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromNotificationType(notificationType: NotificationType): String {
        return notificationType.name
    }

    @TypeConverter
    fun toNotificationType(name: String): NotificationType {
        return enumValueOf(name)
    }
}