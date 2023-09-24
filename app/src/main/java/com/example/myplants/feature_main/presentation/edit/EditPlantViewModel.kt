package com.example.myplants.feature_main.presentation.edit

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.InvalidPlantException
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_main.domain.usecase.PlantUseCase
import com.example.myplants.feature_main.presentation.edit.states.ImageViewState
import com.example.myplants.feature_main.presentation.edit.states.MultipleSelectFieldState
import com.example.myplants.feature_main.presentation.edit.states.SingleSelectFieldState
import com.example.myplants.feature_main.presentation.edit.states.TextFieldState
import com.example.myplants.feature_main.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class EditPlantViewModel @Inject constructor(
    private val plantUseCase: PlantUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val amounts = Plant.waterAmounts
    private val daysOfWeek = DayOfWeek.values().map { SelectItem(it.name, false) }
    private val sizes = Plant.sizes

    private val _name = mutableStateOf(
        TextFieldState(
            hint = "Enter plant name"
        )
    )
    val name: State<TextFieldState> = _name

    private val _description = mutableStateOf(
        TextFieldState(
            hint = "Enter short description for this plant"
        )
    )
    val description: State<TextFieldState> = _description

    private val _size = mutableStateOf(
        SingleSelectFieldState(
            isDialogShown = false,
            options = sizes
        )
    )
    val size: State<SingleSelectFieldState> = _size

    private val _waterAmount = mutableStateOf(
        SingleSelectFieldState(
            isDialogShown = false,
            options = amounts
        )
    )
    val waterAmount: State<SingleSelectFieldState> = _waterAmount

    private val _waterDays = mutableStateOf(
        MultipleSelectFieldState(
            isDialogShown = false,
            options = daysOfWeek
        )
    )
    val waterDays: State<MultipleSelectFieldState> = _waterDays

    private val _imageState = mutableStateOf(
        ImageViewState(
            hint = "Add image"
        )
    )
    val image: State<ImageViewState> = _imageState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow

    private var currentPlantId: Long? = null

    init {
        savedStateHandle.get<Long>("plantId")?.let { id ->
            if (id > 0) {
                viewModelScope.launch {
                    plantUseCase.getPlant(id)?.also { plant ->
                        currentPlantId = plant.id
                        nameChanged(plant.name)
                        descriptionChanged(plant.description)
                        sizeSelected(sizes.indexOf(plant.size))
                        waterAmountSelected(amounts.indexOf(plant.waterAmount))
                        datesSelected(plant.waterDays.map { it.name })
                        imageSelected(Uri.parse(plant.image))
                    }
                }
            }
        }
    }

    fun onEvent(event: EditPlantEvent) {
        when (event) {
            is EditPlantEvent.NameChanged -> nameChanged(event.value)
            is EditPlantEvent.NameFocusChanged -> nameFocusChanged(event.focusState)
            is EditPlantEvent.DescriptionChanged -> descriptionChanged(event.value)
            is EditPlantEvent.DescriptionFocusChanged -> descriptionFocusChanged(event.focusState)
            is EditPlantEvent.SizeFocusChanged -> sizeFocusChanged(event.isFocused)
            is EditPlantEvent.SizeSelected -> sizeSelected(event.value)
            is EditPlantEvent.WaterAmountFocusChanged -> waterAmountFocusChanged(event.isFocused)
            is EditPlantEvent.WaterAmountSelected -> waterAmountSelected(event.value)
            is EditPlantEvent.DatesFocusChanged -> datesFocusChanged(event.isFocused)
            is EditPlantEvent.DatesSelected -> datesSelected(event.value)
            is EditPlantEvent.TimeFocusChanged -> {}
            is EditPlantEvent.TimeSelected -> {}
            is EditPlantEvent.ImagePicked -> imageSelected(event.uri)
            EditPlantEvent.PlantSaved -> savePlant()
        }
    }

    private fun nameChanged(value: String) {
        _name.value = name.value.copy(
            text = value
        )
    }

    private fun nameFocusChanged(focusState: FocusState) {
        _name.value = name.value.copy(
            isHintVisible = !focusState.isFocused && name.value.text.isBlank()
        )
    }

    private fun descriptionChanged(value: String) {
        _description.value = description.value.copy(
            text = value
        )
    }

    private fun descriptionFocusChanged(focusState: FocusState) {
        _description.value = description.value.copy(
            isHintVisible = !focusState.isFocused && description.value.text.isBlank()
        )
    }

    private fun sizeSelected(value: Int) {
        if (value >= 0) {
            _size.value = size.value.copy(
                text = Size.values()[value].text,
                selected = value
            )
        }
    }

    private fun sizeFocusChanged(focused: Boolean) {
        _size.value = size.value.copy(
            isDialogShown = focused
        )
    }

    private fun waterAmountFocusChanged(focused: Boolean) {
        _waterAmount.value = waterAmount.value.copy(
            isDialogShown = focused
        )
    }

    private fun waterAmountSelected(value: Int) {
        if (value >= 0) {
            _waterAmount.value = waterAmount.value.copy(
                text = amounts[value],
                selected = value
            )
        }
    }

    private fun datesFocusChanged(focused: Boolean) {
        _waterDays.value = waterDays.value.copy(
            isDialogShown = focused
        )
    }

    private fun datesSelected(value: List<String>) {
        _waterDays.value = waterDays.value.copy(
            text = "${value.size} times/week",
            options = daysOfWeek.map {
                SelectItem(
                    title = it.title,
                    isSelected = value.contains(it.title)
                )
            }
        )
    }

    private fun imageSelected(uri: Uri?) {
        _imageState.value = image.value.copy(
            hint = if (uri == null) "Add image" else "Change image",
            uri = uri
        )
    }

    private fun savePlant() {
        viewModelScope.launch {
            try {
                val plant = Plant(
                    name = name.value.text,
                    description = description.value.text,
                    image = image.value.uri.toString(),
                    waterDays = waterDays.value.options
                        .filter { it.isSelected }
                        .map { DayOfWeek.valueOf(it.title) },
                    size = sizes[size.value.selected],
                    waterTime = 0,
                    waterAmount = amounts[waterAmount.value.selected],
                    id = currentPlantId
                )

                plantUseCase.savePlant(plant)

                _eventFlow.emit(UiEvent.SuccessfullyCompleted)
            } catch (ex: InvalidPlantException) {
                _eventFlow.emit(
                    UiEvent.ErrorOccurred(
                        message = ex.message ?: "Plant couldn't be saved"
                    )
                )
            }
        }
    }


}