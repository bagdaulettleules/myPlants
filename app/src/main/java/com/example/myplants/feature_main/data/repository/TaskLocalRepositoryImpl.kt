package com.example.myplants.feature_main.data.repository

import com.example.myplants.feature_main.data.datasource.TaskDao
import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class TaskLocalRepositoryImpl(
    private val dao: TaskDao
) : TaskLocalRepository {

    override fun getAll(): Flow<List<Task>> {
        return dao.getAll()
    }

    override suspend fun get(id: Long): Schedule? {
        return dao.get(id)
    }

    override suspend fun getAsTask(id: Long): Task? {
        return dao.getAsTask(id)
    }

    override suspend fun save(schedule: Schedule): Long {
        return dao.upsert(schedule)
    }

    override suspend fun delete(schedule: Schedule) {
        dao.delete(schedule)
    }
}