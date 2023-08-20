package com.example.myplants.feature_main.domain.usecase.task

data class TaskUseCase(
    val getAllTasks: GetAllTasks,
    val getTask: GetTask,
    val nextSchedule: NextSchedule,
    val saveSchedule: SaveSchedule
)