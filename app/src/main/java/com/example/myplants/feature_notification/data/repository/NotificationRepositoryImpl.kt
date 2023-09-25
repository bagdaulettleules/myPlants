package com.example.myplants.feature_notification.data.repository

import com.example.myplants.feature_notification.data.datasource.NotificationDao
import com.example.myplants.feature_notification.domain.model.Notification
import com.example.myplants.feature_notification.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val dao: NotificationDao
) : NotificationRepository {
    override fun getAll(): Flow<List<Notification>> {
        return dao.getAll()
    }

    override suspend fun get(id: Long?): Notification? {
        return dao.get(id)
    }

    override suspend fun save(notification: Notification): Long {
        return dao.upsert(notification)
    }

    override suspend fun delete(notification: Notification) {
        dao.delete(notification)
    }
}