package com.example.myplants.feature_main.domain.model

import java.time.DayOfWeek

data class Todo(
    val plantId: Long,
    val name: String,
    val description: String = "",
    val image: String = "",
    val waterDays: List<DayOfWeek>,
    val waterTime: Int = 0,
    val waterAmount: String = "",
    val size: String = "",
    val taskId: Long,
    val isDone: Boolean = false,
    val dueDateTs: Long
) {
    var dueDateText: String = ""
}
