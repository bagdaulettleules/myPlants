package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantRepository

class DeletePlant(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant) {
        plantRepository.delete(plant)
    }
}