package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository

class SaveTask(
    private val repository: TaskLocalRepository
) {
    suspend operator fun invoke(task: Task): Long {
        return repository.save(task)
    }
}