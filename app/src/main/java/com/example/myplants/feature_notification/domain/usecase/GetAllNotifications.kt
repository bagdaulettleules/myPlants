package com.example.myplants.feature_notification.domain.usecase

import com.example.myplants.feature_notification.domain.model.Notification
import com.example.myplants.feature_notification.domain.model.NotificationType
import com.example.myplants.feature_notification.domain.repository.NotificationRepository
import com.example.myplants.feature_notification.domain.util.NotificationFetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

class GetAllNotifications(
    private val repository: NotificationRepository
) {

    private val currentDate: LocalDate =
        Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault())
            .toLocalDate()

    operator fun invoke(fetchType: NotificationFetchType): Flow<Map<Int, List<Notification>>> {
        return repository.getAll().map { items ->
            when (fetchType) {
                NotificationFetchType.All -> items
                    .groupBy { getPeriod(it.updateTs) }
                    .toSortedMap(compareByDescending { it })

                NotificationFetchType.Forgot -> items
                    .filter { it.type == NotificationType.FORGOT }
                    .groupBy { getPeriod(it.updateTs) }
                    .toSortedMap(compareByDescending { it })

                NotificationFetchType.Upcoming -> items
                    .filter { it.type == NotificationType.UPCOMING }
                    .groupBy { getPeriod(it.updateTs) }
                    .toSortedMap(compareByDescending { it })
            }
        }
    }

    private fun getPeriod(dueDateTs: Long): Int {
        val dueDate = Instant.ofEpochMilli(dueDateTs).atZone(ZoneId.systemDefault())
            .toLocalDate()
        return Period.between(currentDate, dueDate).days
    }
}