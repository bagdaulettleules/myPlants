package com.example.myplants.feature_main.presentation.edit.states

data class TextFieldState(
    val text: String = "",
    val hint: String = "",
    val label: String = "",
    val isHintVisible: Boolean = false
)
