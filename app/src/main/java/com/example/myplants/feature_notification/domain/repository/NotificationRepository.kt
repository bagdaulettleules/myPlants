package com.example.myplants.feature_notification.domain.repository

import com.example.myplants.feature_notification.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    fun getAll(): Flow<List<Notification>>

    suspend fun get(id: Long?): Notification?

    suspend fun save(notification: Notification): Long

    suspend fun delete(notification: Notification)
}