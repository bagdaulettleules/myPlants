package com.example.myplants.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "plant")
data class Plant(
    val name: String,
    val description: String = "",
    val image: String = "",
    val waterDays: List<DayOfWeek> = emptyList(),
    val waterTime: Int? = null,
    val waterAmount: Int = 0,
    val size: Size? = null,
    val updateTs: Long? = null,
    @PrimaryKey
    val id: Long? = null
)

