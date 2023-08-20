package com.example.myplants.feature_main.domain.usecase.plant

class PlantUseCase(
    val getPlants: GetAllPlants,
    val getPlant: GetPlant,
    val savePlant: SavePlant,
    val deletePlant: DeletePlant
)