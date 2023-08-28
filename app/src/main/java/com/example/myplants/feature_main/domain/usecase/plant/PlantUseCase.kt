package com.example.myplants.feature_main.domain.usecase.plant

class PlantUseCase(
    val getAll: GetAllPlants,
    val get: GetPlant,
    val save: SavePlant,
    val delete: DeletePlant
)