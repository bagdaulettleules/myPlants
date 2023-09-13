package com.example.myplants.feature_main.domain.usecase

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskRepository

class GetTask(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Long): Task? {
        return taskRepository.get(id)
    }
}