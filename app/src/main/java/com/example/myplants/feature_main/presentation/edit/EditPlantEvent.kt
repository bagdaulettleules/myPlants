package com.example.myplants.feature_main.presentation.edit

import androidx.compose.ui.focus.FocusState

sealed class EditPlantEvent {
    data class NameChanged(val value: String) : EditPlantEvent()
    data class NameFocusChanged(val focusState: FocusState) : EditPlantEvent()
    data class DescriptionChanged(val value: String) : EditPlantEvent()
    data class DescriptionFocusChanged(val focusState: FocusState) : EditPlantEvent()
    data class DatesSelected(val value: List<String>) : EditPlantEvent()
    data class DatesFocusChanged(val isFocused: Boolean = false) : EditPlantEvent()
    data class TimeSelected(val value: Int) : EditPlantEvent()
    data class TimeFocusChanged(val isFocused: Boolean = false) : EditPlantEvent()
    data class WaterAmountSelected(val value: Int) : EditPlantEvent()
    data class WaterAmountFocusChanged(val isFocused: Boolean = false) : EditPlantEvent()
    data class SizeSelected(val value: Int) : EditPlantEvent()
    data class SizeFocusChanged(val isFocused: Boolean = false) : EditPlantEvent()
    object PlantSaved : EditPlantEvent()
}
