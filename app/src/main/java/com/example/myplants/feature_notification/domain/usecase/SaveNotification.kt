package com.example.myplants.feature_notification.domain.usecase

import com.example.myplants.feature_notification.domain.model.Notification
import com.example.myplants.feature_notification.domain.repository.NotificationRepository

class SaveNotification(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notification: Notification): Long {
        return repository.save(notification)
    }
}