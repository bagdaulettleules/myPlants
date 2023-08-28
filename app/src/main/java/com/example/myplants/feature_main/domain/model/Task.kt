package com.example.myplants.feature_main.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "task",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["id"],
            childColumns = ["plantId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("plantId")
    ]
)
data class Task(
    val plantId: Long? = null,
    val dueDateTs: Long,
    val isDone: Boolean = false,
    val updateTs: Long? = null,
    @PrimaryKey
    val id: Long? = null
)