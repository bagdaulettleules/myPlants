package com.example.myplants.feature_main.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class Task(
    @Embedded
    val schedule: Schedule,
    @Relation(
        entity = Plant::class,
        parentColumn = "plant_id",
        entityColumn = "id"
    )
    val plant: Plant
)
