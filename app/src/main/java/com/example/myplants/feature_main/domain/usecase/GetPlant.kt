package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantRepository

class GetPlant(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(id: Long): Plant? {
        return plantRepository.get(id)
    }
}