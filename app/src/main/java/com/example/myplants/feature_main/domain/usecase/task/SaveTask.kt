package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository

class SaveTask(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(task: Task): Long {
        return taskLocalRepository.save(task)
    }
}