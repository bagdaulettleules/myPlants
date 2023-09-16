package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.InvalidPlantException
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantRepository
import com.example.myplants.feature_main.domain.repository.TaskRepository
import com.example.myplants.feature_main.domain.util.generateNewTask
import com.example.myplants.feature_main.domain.util.getNext
import java.time.Instant
import java.time.ZoneId

class SavePlant(
    private val taskRepository: TaskRepository,
    private val plantRepository: PlantRepository
) {
    @Throws(InvalidPlantException::class)
    suspend operator fun invoke(plant: Plant): Long {
        if (plant.name.isBlank()) {
            throw InvalidPlantException("Empty name")
        }
        if (plant.waterAmount.isBlank()) {
            throw InvalidPlantException("Water amount not set")
        }
        if (plant.size.isBlank()) {
            throw InvalidPlantException("Size not set")
        }
        if (plant.waterDays.isEmpty()) {
            throw InvalidPlantException("Watering dates not set")
        }

        val plantId = plantRepository.save(plant)
        val task = taskRepository.getNext(plant.id)

        if (task != null) {
            val localDate =
                Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault())
                    .toLocalDate()
            val dayOfWeek = plant.waterDays.getNext(localDate.dayOfWeek)
            val dueDate = localDate.getNext(dayOfWeek).atTime(plant.waterTime, 0)
            return taskRepository.save(
                task.copy(
                    dueDateTs = dueDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                )
            )
        }

        return taskRepository.save(plant.generateNewTask(plantId, System.currentTimeMillis()))

    }
}