package com.example.myplants.feature_main.presentation.edit

data class SingleSelectFieldState(
    val text: String = "",
    val isDialogShown: Boolean = false,
    val options: List<String> = emptyList(),
    val selected: Int = -1
)
