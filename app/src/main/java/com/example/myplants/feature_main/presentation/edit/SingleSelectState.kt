package com.example.myplants.feature_main.presentation.edit

data class SingleSelectState(
    val text: String = "",
    val isDialogShown: Boolean = false,
    val options: List<String> = emptyList(),
    val selected: Int? = null
)
