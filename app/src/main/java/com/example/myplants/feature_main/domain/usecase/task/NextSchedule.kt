package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.presentation.util.getNext
import com.example.myplants.feature_main.presentation.util.toLong
import java.time.LocalDate
import java.time.ZoneId

class NextSchedule(
    private val repository: TaskLocalRepository
) {
    suspend operator fun invoke(plant: Plant): Long? {
        val localDate = LocalDate.now()
        val nextDayOfWeek = plant.waterDays
            .sorted()
            .firstOrNull { it >= localDate.dayOfWeek }
            ?: plant.waterDays[0]

        plant.id?.let {
            return repository.save(
                Schedule(
                    plantId = plant.id,
                    dueDateTs = localDate.getNext(nextDayOfWeek).toLong(ZoneId.systemDefault()),
                    updateTs = System.currentTimeMillis(),
                    isDone = false
                )
            )
        }
        return null
    }
}