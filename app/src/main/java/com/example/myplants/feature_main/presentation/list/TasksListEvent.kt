package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.util.FetchType

sealed class TasksListEvent {

    data class Fetch(val fetchType: FetchType) : TasksListEvent()

    data class Water(val task: Task) : TasksListEvent()

    data class Delete(val plant: Plant) : TasksListEvent()

    object Restore : TasksListEvent()
}
