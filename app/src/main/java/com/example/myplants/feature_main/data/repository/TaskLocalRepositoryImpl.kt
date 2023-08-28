package com.example.myplants.feature_main.data.repository

import com.example.myplants.feature_main.data.datasource.TaskDao
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class TaskLocalRepositoryImpl(
    private val dao: TaskDao
) : TaskLocalRepository {

    override fun getAll(): Flow<List<Task>> {
        return dao.getAll()
    }

    override suspend fun get(id: Long?): Task? {
        return dao.get(id)
    }

    override suspend fun getNext(plantId: Long?): Task? {
        return dao.getNext(plantId)
    }

    override suspend fun save(task: Task): Long {
        return dao.upsert(task)
    }

    override suspend fun delete(task: Task) {
        dao.delete(task)
    }
}