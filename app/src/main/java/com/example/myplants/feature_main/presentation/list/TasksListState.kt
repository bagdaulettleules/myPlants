package com.example.myplants.feature_main.presentation.list

import com.example.myplants.feature_main.domain.model.Todo
import com.example.myplants.feature_main.domain.util.FetchType

data class TasksListState(
    val todoList: List<Todo> = emptyList(),
    val fetchType: FetchType = FetchType.Upcoming
) {
    val isEmptyPlants: Boolean
        get() = todoList.isEmpty()
}
