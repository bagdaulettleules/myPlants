package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.domain.util.FetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTasks(
    private val repository: TaskLocalRepository
) {
    operator fun invoke(fetchType: FetchType): Flow<List<Task>> {
        return repository.getAll().map { tasks ->
            when (fetchType) {
                FetchType.History -> {
                    tasks.sortedByDescending { it.schedule.updateTs }
                }

                FetchType.Missed -> {
                    tasks.filter { !it.schedule.isDone && it.schedule.dueDateTs < System.currentTimeMillis() }
                        .sortedByDescending { it.schedule.dueDateTs }
                }

                FetchType.Upcoming -> {
                    tasks.filter { !it.schedule.isDone && it.schedule.dueDateTs >= System.currentTimeMillis() }
                        .sortedBy { it.schedule.dueDateTs }
                }
            }
        }
    }
}