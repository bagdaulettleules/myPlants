package com.example.myplants.feature_main.presentation.add

data class TextFieldState(
    val value: String = "",
    val hint: String = "",
    val label: String = ""
) {
    val isHintVisible: Boolean
        get() = value.isEmpty()
}
