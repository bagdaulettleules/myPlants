package com.example.myplants.feature_main.presentation.detail

data class TaskDetailState(
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val frequency: String = "",
    val hour: Int = 0,
    val amount: String = "",
    val size: String = "",
    val isDone: Boolean = false
)
