package com.example.myplants.feature_main.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class Todo(
    @Embedded
    val plant: Plant,
    @Relation(
        entity = Task::class,
        parentColumn = "id",
        entityColumn = "plantId"
    )
    val task: Task
)
