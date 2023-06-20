package com.example.myplants.feature_main.domain.usecase.task

class TaskUseCases(
    val getAll: GetAll,
    val getTask: GetTask,
    val saveTask: SaveTask,
    val deleteTask: DeleteTask
)