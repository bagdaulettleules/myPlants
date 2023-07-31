package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.util.FetchType

sealed class TasksListEvent {

    data class Fetch(val fetchType: FetchType) : TasksListEvent()

    data class MarkCompleted(val schedule: Schedule) : TasksListEvent()

    data class Delete(val plant: Plant) : TasksListEvent()

    object Restore : TasksListEvent()
}
