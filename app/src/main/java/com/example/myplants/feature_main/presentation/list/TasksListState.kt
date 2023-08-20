package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.util.FetchType

data class TasksListState(
    val taskList: List<Task> = emptyList(),
    val fetchType: FetchType = FetchType.Upcoming
)
