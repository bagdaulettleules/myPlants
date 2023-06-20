package com.example.myplants.feature_main.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myplants.feature_main.data.CustomTypeConverters
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.model.Plant

@Database(
    entities = [Plant::class, Task::class],
    version = 1
)
@TypeConverters(CustomTypeConverters::class)
abstract class PlantDatabase : RoomDatabase() {

    abstract val plantDao: PlantDao

    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "plants_db"
    }
}