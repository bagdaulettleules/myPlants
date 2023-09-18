package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.util.FetchType

sealed class TasksListEvent {

    data class Fetch(val fetchType: FetchType) : TasksListEvent()

    data class MarkWatered(val taskId: Long) : TasksListEvent()

    data class Delete(val plantId: Long) : TasksListEvent()

    object ConfirmDelete : TasksListEvent()

    object DismissDelete : TasksListEvent()
}
