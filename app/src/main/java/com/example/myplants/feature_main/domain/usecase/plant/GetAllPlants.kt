package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.Todo
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository
import com.example.myplants.feature_main.domain.util.FetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllPlants(
    private val repository: PlantLocalRepository
) {
    operator fun invoke(fetchType: FetchType): Flow<List<Todo>> {
        return repository.getAll().map { todos ->
            when (fetchType) {
                FetchType.History -> {
                    todos.sortedByDescending { it.task.dueDateTs }
                }

                FetchType.Missed -> {
                    todos.filter { !it.task.isDone && it.task.dueDateTs < System.currentTimeMillis() }
                        .sortedByDescending { it.task.dueDateTs }
                }

                FetchType.Upcoming -> {
                    todos.filter { !it.task.isDone && it.task.dueDateTs >= System.currentTimeMillis() }
                        .sortedBy { it.task.dueDateTs }
                }
            }
        }
    }
}