package com.example.myplants.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "schedule",
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
data class Schedule(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "due_date_ts")
    val dueDateTs: Long,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    @ColumnInfo(name = "update_ts")
    val updateTs: Long? = null,
    @PrimaryKey
    val id: Long? = null
)