package com.example.myplants.feature_main.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myplants.feature_main.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Transaction
    @Query("select * from task")
    fun getAll(): Flow<List<Task>>

    @Query("select * from task where id = :id")
    suspend fun get(id: Long?): Task?

    @Query("select * from task where plantId = :plantId and not isDone")
    suspend fun getNext(plantId: Long?): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(task: Task): Long

    @Delete
    suspend fun delete(task: Task)
}