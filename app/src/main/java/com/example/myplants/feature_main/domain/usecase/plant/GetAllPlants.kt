package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllPlants(
    private val repository: PlantLocalRepository
) {
    operator fun invoke(): Flow<List<Plant>> {
        return repository.getAll()
    }
}