package com.example.myplants.feature_main.domain.repository

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.model.TaskWithPlant
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    fun getAll(): Flow<List<TaskWithPlant>>

    suspend fun get(id: Long): Task?

    suspend fun save(task: Task): Long

    suspend fun delete(task: Task)
}