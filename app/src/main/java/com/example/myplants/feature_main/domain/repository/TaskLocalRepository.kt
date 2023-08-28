package com.example.myplants.feature_main.domain.repository

import com.example.myplants.feature_main.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    fun getAll(): Flow<List<Task>>

    suspend fun get(id: Long?): Task?

    suspend fun getNext(plantId: Long?): Task?

    suspend fun save(task: Task): Long

    suspend fun delete(task: Task)
}