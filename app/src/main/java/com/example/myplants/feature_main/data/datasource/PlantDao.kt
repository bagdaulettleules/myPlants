package com.example.myplants.feature_main.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myplants.feature_main.domain.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Transaction
    @Query("select * from plant")
    fun getAll(): Flow<List<Plant>>

    @Query("select * from plant where id = :id")
    suspend fun get(id: Long): Plant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(plant: Plant): Long

    @Delete
    suspend fun delete(plant: Plant)
}