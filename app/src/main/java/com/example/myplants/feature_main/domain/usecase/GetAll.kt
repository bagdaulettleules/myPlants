package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Todo
import com.example.myplants.feature_main.domain.repository.PlantRepository
import com.example.myplants.feature_main.domain.util.FetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

class GetAll(
    private val plantRepository: PlantRepository
) {
    private val currentDate: LocalDate =
        Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault())
            .toLocalDate()

    operator fun invoke(fetchType: FetchType): Flow<List<Todo>> {
        return plantRepository.getAll().map { items ->
            when (fetchType) {
                FetchType.History -> {
                    items.sortedByDescending { it.taskId }
                        .map {
                            it.apply {
                                dueDateText = getDueDateText(it.dueDateTs)
                            }
                        }
                }

                FetchType.Missed -> {
                    items.filter { !it.isDone && it.dueDateTs < System.currentTimeMillis() }
                        .sortedBy { it.dueDateTs }
                        .map {
                            it.apply {
                                dueDateText = getDueDateText(it.dueDateTs)
                            }
                        }
                }

                FetchType.Upcoming -> {
                    items.filter { !it.isDone && it.dueDateTs >= System.currentTimeMillis() }
                        .sortedBy { it.dueDateTs }
                        .map {
                            it.apply {
                                dueDateText = getDueDateText(it.dueDateTs)
                            }
                        }
                }
            }
        }
    }

    private fun getDueDateText(dueDateTs: Long): String {
        val dueDate = Instant.ofEpochMilli(dueDateTs).atZone(ZoneId.systemDefault())
            .toLocalDate()
        val days = Period.between(currentDate, dueDate).days
        return when {
            days == -1 -> "Yesterday"
            days == 0 -> "Today"
            days == 1 -> "Tomorrow"
            days in -2 downTo -7 -> "Last ${dueDate.dayOfWeek.name.lowercase()}"
            days in 2..7 -> "Next ${dueDate.dayOfWeek.name.lowercase()}"
            days < -7 -> "Week before last"
            else -> "Later next week"
        }
    }
}