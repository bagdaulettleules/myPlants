package com.example.myplants.feature_main.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Transaction
    @Query(
        """ select 
                p.id as plantId, 
                p.name, p.description, 
                p.image, 
                p.waterDays, 
                p.waterTime, 
                p.waterAmount, 
                p.size,
                t.id as taskId, 
                t.isDone, 
                t.dueDateTs,
                '' as dueDateText
            from plant p 
            join task t on t.plantId = p.id """
    )
    fun getAll(): Flow<List<Todo>>

    @Query("select * from plant where id = :id")
    suspend fun get(id: Long?): Plant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(plant: Plant): Long

    @Delete
    suspend fun delete(plant: Plant)
}