package com.example.myplants.feature_main.domain.usecase.task

data class TaskUseCase(
    val get: GetTask,
    val getNext: GetNextTask,
    val save: SaveTask
)