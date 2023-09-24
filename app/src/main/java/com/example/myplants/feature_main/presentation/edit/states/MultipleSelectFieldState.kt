package com.example.myplants.feature_main.presentation.edit.states

import com.example.myplants.feature_main.presentation.edit.SelectItem

data class MultipleSelectFieldState(
    val text: String = "",
    val isDialogShown: Boolean = false,
    val options: List<SelectItem> = emptyList()
)
