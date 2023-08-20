package com.example.myplants.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "plant")
data class Plant(
    val name: String,
    val description: String? = null,
    val image: String? = null,
    @ColumnInfo(name = "water_days")
    val waterDays: List<DayOfWeek>,
    @ColumnInfo(name = "water_time")
    val waterTime: Int,
    @ColumnInfo(name = "water_amount")
    val waterAmount: Int,
    val size: Size? = null,
    val updateTs: Long? = null,
    @PrimaryKey
    val id: Long? = null
)

