package com.example.myplants.feature_main.data.repository

import com.example.myplants.feature_main.data.datasource.PlantDao
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository
import kotlinx.coroutines.flow.Flow

class PlantLocalRepositoryImpl(
    private val dao: PlantDao
) : PlantLocalRepository {

    override fun getAll(): Flow<List<Plant>> {
        return dao.getAll()
    }

    override suspend fun get(id: Long): Plant? {
        return dao.get(id)
    }

    override suspend fun save(plant: Plant): Long {
        return dao.upsert(plant)
    }

    override suspend fun delete(plant: Plant) {
        dao.delete(plant)
    }
}