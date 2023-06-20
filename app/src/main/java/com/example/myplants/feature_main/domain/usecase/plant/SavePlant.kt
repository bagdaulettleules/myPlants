package com.example.myplants.feature_main.domain.usecase.plant

import com.example.myplants.feature_main.domain.model.InvalidPlantException
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository

class SavePlant(
    private val repository: PlantLocalRepository
) {
    suspend operator fun invoke(plant: Plant): Long {
        if (plant.name.isEmpty()) {
            throw InvalidPlantException("Empty name")
        }
        if (plant.waterDays.isEmpty()) {
            throw InvalidPlantException("Watering dates not set")
        }
        return repository.save(plant)
    }
}