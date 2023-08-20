package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.InvalidPlantException
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository

class SavePlant(
    private val repository: PlantLocalRepository
) {
    @Throws(InvalidPlantException::class)
    suspend operator fun invoke(plant: Plant): Long {
        if (plant.name.isBlank()) {
            throw InvalidPlantException("Empty name")
        }
        if (plant.waterAmount <= 0) {
            throw InvalidPlantException("Water amount not set")
        }
        if (plant.size == null) {
            throw InvalidPlantException("Size not set")
        }
        if (plant.waterDays.isEmpty()) {
            throw InvalidPlantException("Watering dates not set")
        }
        return repository.save(plant)
    }
}