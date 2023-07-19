package com.example.myplants.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.util.Date

@Entity(tableName = "plant")
data class Plant(
    val name: String,
    val description: String?,
    val images: List<String> = emptyList(),
    @ColumnInfo(name = "water_days")
    val waterDays: List<DayOfWeek>,
    @ColumnInfo(name = "water_time")
    val waterTime: Int,
    @ColumnInfo(name = "water_amount")
    val waterAmount: Int,
    val size: Size,
    val updateTs: Date? = null,
    @PrimaryKey
    val id: Long? = null
)

