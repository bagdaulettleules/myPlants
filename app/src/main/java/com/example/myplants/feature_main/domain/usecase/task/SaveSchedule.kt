package com.example.myplants.feature_main.domain.usecase.task

import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository

class SaveSchedule(
    private val taskRepository: TaskLocalRepository
) {
    suspend operator fun invoke(schedule: Schedule): Long {
        return taskRepository.save(schedule)
    }
}