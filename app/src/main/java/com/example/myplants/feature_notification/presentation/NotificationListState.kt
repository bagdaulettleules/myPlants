package com.example.myplants.feature_notification.presentation

import com.example.myplants.feature_notification.domain.model.Notification
import com.example.myplants.feature_notification.domain.util.NotificationFetchType

data class NotificationListState(
    val fetchType: NotificationFetchType = NotificationFetchType.All,
    val categorizedItems: Map<String, List<Notification>> = emptyMap()
) {
    val isEmpty: Boolean
        get() = categorizedItems.isEmpty()
}
