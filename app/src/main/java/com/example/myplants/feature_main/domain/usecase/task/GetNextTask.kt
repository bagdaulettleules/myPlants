package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.presentation.util.getNext
import com.example.myplants.feature_main.presentation.util.toLong
import java.time.LocalDate
import java.time.ZoneId

class GetNextTask(
    private val repository: TaskLocalRepository
) {
    suspend operator fun invoke(plant: Plant): Task {
        return repository.getNext(plant.id) ?: generateNewTask(plant)
    }

    private fun generateNewTask(plant: Plant): Task {
        val localDate = LocalDate.now()
        val nextDayOfWeek = plant.waterDays
            .sorted()
            .firstOrNull { it >= localDate.dayOfWeek }
            ?: plant.waterDays[0]

        return Task(
            plantId = plant.id,
            dueDateTs = localDate.getNext(nextDayOfWeek).toLong(ZoneId.systemDefault()),
            updateTs = System.currentTimeMillis(),
            isDone = false
        )
    }
}