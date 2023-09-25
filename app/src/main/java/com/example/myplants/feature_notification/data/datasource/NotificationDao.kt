package com.example.myplants.feature_notification.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myplants.feature_notification.domain.model.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Transaction
    @Query("select * from notification")
    fun getAll(): Flow<List<Notification>>

    @Query("select * from notification where id = :id")
    suspend fun get(id: Long?): Notification?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(notification: Notification): Long

    @Delete
    suspend fun delete(notification: Notification)

}