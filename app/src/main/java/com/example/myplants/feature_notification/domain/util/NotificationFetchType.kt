package com.example.myplants.feature_notification.domain.util

sealed class NotificationFetchType {
    object All : NotificationFetchType()
    object Forgot : NotificationFetchType()
    object Upcoming : NotificationFetchType()
}
