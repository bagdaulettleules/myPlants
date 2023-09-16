package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Todo
import com.example.myplants.feature_main.domain.repository.PlantRepository
import com.example.myplants.feature_main.domain.util.FetchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAll(
    private val plantRepository: PlantRepository
) {
    operator fun invoke(fetchType: FetchType): Flow<List<Todo>> {
        return plantRepository.getAll().map { items ->
            when (fetchType) {
                FetchType.History -> {
                    items.sortedByDescending { it.dueDateTs }
                }

                FetchType.Missed -> {
                    items.filter { !it.isDone && it.dueDateTs < System.currentTimeMillis() }
                        .sortedBy { it.dueDateTs }
                }

                FetchType.Upcoming -> {
                    items.filter { !it.isDone && it.dueDateTs >= System.currentTimeMillis() }
                        .sortedBy { it.dueDateTs }
                }
            }
        }
    }
}