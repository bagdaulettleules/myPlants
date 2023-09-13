package com.example.myplants.feature_main.presentation.edit

data class MultipleSelectFieldState(
    val text: String = "",
    val isDialogShown: Boolean = false,
    val options: List<SelectItem> = emptyList()
)
