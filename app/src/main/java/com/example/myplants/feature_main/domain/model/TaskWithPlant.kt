package com.example.myplants.feature_main.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithPlant(
    @Embedded
    val task: Task,
    @Relation(
        entity = Plant::class,
        parentColumn = "plant_id",
        entityColumn = "id"
    )
    val plant: Plant
)
