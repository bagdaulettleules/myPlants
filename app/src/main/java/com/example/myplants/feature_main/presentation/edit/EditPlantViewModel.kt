package com.example.myplants.feature_main.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.InvalidPlantException
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_main.domain.usecase.plant.PlantUseCase
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCase
import com.example.myplants.feature_main.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class EditPlantViewModel @Inject constructor(
    private val plantUseCase: PlantUseCase,
    private val taskUseCase: TaskUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val waterAmountList = listOf(250, 500, 750, 1000)
    private val daysOfWeek = DayOfWeek.values().map { SelectItem(it.name, false) }

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
        SingleSelectState(
            isDialogShown = false,
            options = Size.values().map { it.name }
        )
    )
    val size: State<SingleSelectState> = _size

    private val _waterAmount = mutableStateOf(
        SingleSelectState(
            isDialogShown = false,
            options = waterAmountList.map { "${it}ml" }
        )
    )
    val waterAmount: State<SingleSelectState> = _waterAmount

    private val _waterDays = mutableStateOf(
        SelectState(
            isDialogShown = false,
            options = daysOfWeek
        )
    )
    val waterDays: State<SelectState> = _waterDays

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow

    private var currentPlantId: Long? = null

    init {
        savedStateHandle.get<Long>("plantId")?.let { id ->
            if (id > 0) {
                viewModelScope.launch {
                    plantUseCase.get(id)?.also { plant ->
                        currentPlantId = plant.id
                        nameChanged(plant.name)
                        descriptionChanged(plant.description)
                        sizeSelected(plant.size?.ordinal)
                        waterAmountSelected(waterAmountList.indexOf(plant.waterAmount))
                        datesSelected(plant.waterDays.map { it.name })
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

    private fun sizeSelected(value: Int?) {
        _size.value = size.value.copy(
            selected = value
        )
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

    private fun waterAmountSelected(value: Int?) {
        _waterAmount.value = waterAmount.value.copy(
            selected = value
        )
    }

    private fun datesFocusChanged(focused: Boolean) {
        _waterDays.value = waterDays.value.copy(
            isDialogShown = focused
        )
    }

    private fun datesSelected(value: List<String>) {
        _waterDays.value = waterDays.value.copy(
            options = daysOfWeek.map {
                SelectItem(
                    title = it.title,
                    isSelected = value.contains(it.title)
                )
            }
        )
    }


    private fun savePlant() {
        viewModelScope.launch {
            try {
                val plant = Plant(
                    name = name.value.text,
                    description = description.value.text,
                    image = "",
                    waterDays = waterDays.value.options
                        .filter { it.isSelected }
                        .map { DayOfWeek.valueOf(it.title) },
                    size = size.value.selected?.let { Size.values()[it] },
                    waterTime = 0,
                    waterAmount = waterAmount.value.selected?.let { waterAmountList[it] } ?: 0,
                    id = currentPlantId
                )

                plantUseCase.save(plant)
                taskUseCase.save(taskUseCase.getNext(plant))

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