package com.example.myplants.feature_main.presentation.detail

import com.example.myplants.feature_main.domain.model.Todo

data class TaskDetailState(
    val pages: Int = 1,
    val currentPage: Int = 0,
    val todo: Todo? = null
)
