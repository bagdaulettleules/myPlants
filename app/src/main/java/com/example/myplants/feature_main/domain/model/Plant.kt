package com.example.myplants.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "plant")
data class Plant(
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val waterDays: List<DayOfWeek> = emptyList(),
    val waterTime: Int = 0,
    val waterAmount: String = "",
    val size: String = "",
    val updateTs: Long? = null,
    @PrimaryKey
    val id: Long? = null
) {
    companion object {
        val waterAmounts = listOf("250ml", "500ml", "750ml", "1000ml")
        val sizes = listOf("Small", "Medium", "Large", "Extra large")
    }
}

