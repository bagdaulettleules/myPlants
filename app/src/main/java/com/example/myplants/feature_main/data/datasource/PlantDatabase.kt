package com.example.myplants.feature_main.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myplants.feature_main.data.CustomTypeConverters
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_notification.data.datasource.NotificationDao
import com.example.myplants.feature_notification.domain.model.Notification

@Database(
    entities = [Plant::class, Task::class, Notification::class],
    version = 1
)
@TypeConverters(CustomTypeConverters::class)
abstract class PlantDatabase : RoomDatabase() {

    abstract val plantDao: PlantDao

    abstract val taskDao: TaskDao

    abstract val notificationDao: NotificationDao

    companion object {
        const val DATABASE_NAME = "plants_db"
    }
}