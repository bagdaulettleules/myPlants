package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.TaskWithPlant
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.domain.util.FetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.util.Date

class GetAll(
    private val repository: TaskLocalRepository
) {
    operator fun invoke(fetchType: FetchType): Flow<List<TaskWithPlant>> {
        return repository.getAll().map { tasks ->
            when (fetchType) {
                FetchType.History -> {
                    tasks.sortedByDescending { it.task.updateTs }
                }

                FetchType.Missed -> {
                    val currentDate = Date.from(Instant.now())
                    tasks.filter { !it.task.isDone && it.task.dueDateTs < currentDate }
                        .sortedByDescending { it.task.dueDateTs }
                }

                FetchType.Upcoming -> {
                    val currentDate = Date.from(Instant.now())
                    tasks.filter { !it.task.isDone && it.task.dueDateTs >= currentDate }
                        .sortedBy { it.task.dueDateTs }
                }
            }
        }
    }
}