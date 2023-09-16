package com.example.myplants.feature_main.domain.usecase

class PlantUseCase(
    val getAll: GetAll,
    val getPlant: GetPlant,
    val getTask: GetTask,
    val savePlant: SavePlant,
    val deletePlant: DeletePlant,
    val completeTask: CompleteTask
)