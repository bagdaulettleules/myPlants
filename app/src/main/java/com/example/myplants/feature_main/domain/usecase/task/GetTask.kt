package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository

class GetTask(
    private val repository: TaskLocalRepository
) {
    suspend operator fun invoke(id: Long): Task? {
        return repository.getAsTask(id)
    }
}