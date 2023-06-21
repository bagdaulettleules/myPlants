package com.example.myplants.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.util.Date

@Entity(
    tableName = "task",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("plant_id")
    ]
)
data class Task(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "week_day")
    val dayOfWeek: DayOfWeek,
    @ColumnInfo(name = "due_date_ts")
    val dueDateTs: Date,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    @ColumnInfo(name = "update_ts")
    val updateTs: Date,
    @PrimaryKey
    val id: Long? = null
)