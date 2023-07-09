package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.model.TaskWithPlant
import com.example.myplants.feature_main.domain.util.FetchType

data class TasksListState(
    val taskList: List<TaskWithPlant> = emptyList(),
    val fetchType: FetchType = FetchType.Upcoming
)
