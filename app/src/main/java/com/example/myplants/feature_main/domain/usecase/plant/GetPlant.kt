package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository

class GetPlant(
    private val repository: PlantLocalRepository
) {
    suspend operator fun invoke(id: Long): Plant? {
        return repository.get(id)
    }
}