package com.example.myplants.feature_main.data.repository

import com.example.myplants.feature_main.data.datasource.PlantDao
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Todo
import com.example.myplants.feature_main.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class PlantRepositoryImpl(
    private val dao: PlantDao
) : PlantRepository {

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll()
    }

    override suspend fun get(id: Long?): Plant? {
        return dao.get(id)
    }

    override suspend fun save(plant: Plant): Long {
        return dao.upsert(plant)
    }

    override suspend fun delete(plant: Plant) {
        dao.delete(plant)
    }
}