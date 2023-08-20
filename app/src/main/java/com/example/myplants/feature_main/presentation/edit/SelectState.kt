package com.example.myplants.feature_main.presentation.edit

data class SelectState(
    val text: String = "",
    val isDialogShown: Boolean = false,
    val options: List<SelectItem> = emptyList()
)
