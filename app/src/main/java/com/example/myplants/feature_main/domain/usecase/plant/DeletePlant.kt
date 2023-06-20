package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository

class DeletePlant(
    private val repository: PlantLocalRepository
) {
    suspend operator fun invoke(plant: Plant) {
        repository.delete(plant)
    }
}