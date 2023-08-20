package com.example.myplants.feature_main.presentation.detail

import com.example.myplants.feature_main.domain.model.Schedule

sealed class TaskDetailEvent {
    data class PageSelected(val page: Int) : TaskDetailEvent()
    data class MarkWatered(val schedule: Schedule) : TaskDetailEvent()
}