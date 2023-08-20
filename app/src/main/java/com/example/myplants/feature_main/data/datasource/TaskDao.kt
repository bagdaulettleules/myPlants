package com.example.myplants.feature_main.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Transaction
    @Query("select * from schedule order by update_ts desc")
    fun getAll(): Flow<List<Task>>

    @Query("select * from schedule where id = :id")
    suspend fun get(id: Long): Schedule?

    @Query("select * from schedule where id = :id")
    suspend fun getAsTask(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(schedule: Schedule): Long

    @Delete
    suspend fun delete(schedule: Schedule)
}