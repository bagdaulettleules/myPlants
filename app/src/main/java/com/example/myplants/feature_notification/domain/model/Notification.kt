package com.example.myplants.feature_notification.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myplants.feature_main.domain.model.Plant

@Entity(
    tableName = "notification",
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
data class Notification(
    val plantId: Long,
    val updateTs: Long,
    val type: NotificationType,
    val subject: String = "",
    val message: String = "",
    val isRead: Boolean = false,
    @PrimaryKey
    val id: Long? = null
)
