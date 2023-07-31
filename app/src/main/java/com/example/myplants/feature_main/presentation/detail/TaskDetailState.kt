package com.example.myplants.feature_main.presentation.detail

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Schedule

data class TaskDetailState(
    val pages: Int = 0,
    val currentPage: Int = 0,
    val schedule: Schedule? = null,
    val plant: Plant? = null
)
