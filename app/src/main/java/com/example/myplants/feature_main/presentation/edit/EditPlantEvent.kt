package com.example.myplants.feature_main.presentation.edit

import android.net.Uri
import androidx.compose.ui.focus.FocusState

sealed class EditPlantEvent {
    data class NameChanged(val value: String) : EditPlantEvent()
    data class NameFocusChanged(val focusState: FocusState) : EditPlantEvent()
    data class DescriptionChanged(val value: String) : EditPlantEvent()
    data class DescriptionFocusChanged(val focusState: FocusState) : EditPlantEvent()
    data class DatesSelected(val value: List<String>) : EditPlantEvent()
    data class DatesFocusChanged(val isFocused: Boolean) : EditPlantEvent()
    data class TimeSelected(val value: Int) : EditPlantEvent()
    data class TimeFocusChanged(val isFocused: Boolean) : EditPlantEvent()
    data class WaterAmountSelected(val value: Int) : EditPlantEvent()
    data class WaterAmountFocusChanged(val isFocused: Boolean) : EditPlantEvent()
    data class SizeSelected(val value: Int) : EditPlantEvent()
    data class SizeFocusChanged(val isFocused: Boolean) : EditPlantEvent()
    data class ImagePicked(val uri: Uri?) : EditPlantEvent()
    object PlantSaved : EditPlantEvent()
}
