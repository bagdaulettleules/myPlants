package com.example.myplants.feature_main.presentation.add

data class SingleSelectState(
    val isDialogShown: Boolean = false,
    val options: List<String> = emptyList(),
    val selected: Int? = null
)
