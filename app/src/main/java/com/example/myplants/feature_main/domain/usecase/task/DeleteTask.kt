package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository

class DeleteTask(
    private val repository: TaskLocalRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.delete(task)
    }
}