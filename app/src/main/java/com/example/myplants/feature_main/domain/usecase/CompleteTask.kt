package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.PlantRepository
import com.example.myplants.feature_main.domain.repository.TaskRepository
import com.example.myplants.feature_main.domain.util.generateNewTask

class CompleteTask(
    private val taskRepository: TaskRepository,
    private val plantRepository: PlantRepository
) {

    suspend operator fun invoke(task: Task): Long? {
        taskRepository.save(
            task.copy(
                isDone = true,
                updateTs = System.currentTimeMillis()
            )
        )

        return plantRepository.get(task.plantId)?.let {
            taskRepository.save(it.generateNewTask(it.id!!, task.dueDateTs))
        }
    }
}