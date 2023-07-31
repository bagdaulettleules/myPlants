package com.example.myplants.feature_main.domain.repository

import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    fun getAll(): Flow<List<Task>>

    suspend fun get(id: Long): Schedule?

    suspend fun getAsTask(id: Long): Task?

    suspend fun save(schedule: Schedule): Long

    suspend fun delete(schedule: Schedule)
}