package com.example.myplants.feature_main.domain.repository

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface PlantLocalRepository {

    fun getAll(): Flow<List<Todo>>

    suspend fun get(id: Long?): Plant?

    suspend fun save(plant: Plant): Long

    suspend fun delete(plant: Plant)
}