package com.example.myplants.feature_main.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.usecase.PlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val plantUseCase: PlantUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = mutableStateOf(
        TaskDetailState()
    )
    val viewState: State<TaskDetailState> = _viewState

    private var currentTaskId: Long = -1
    private var currentPlantId: Long = -1

    init {
        savedStateHandle.get<Long>("taskId")?.let { id ->
            currentTaskId = id
            if (id > 0) {
                viewModelScope.launch {
                    plantUseCase.getTask(id)?.let {
                        _viewState.value = viewState.value.copy(
                            isDone = it.isDone
                        )
                        currentPlantId = it.plantId
                    }

                    plantUseCase.getPlant(currentPlantId)?.let {
                        _viewState.value = viewState.value.copy(
                            name = it.name,
                            description = it.description,
                            imageUrl = it.image,
                            frequency = "${it.waterDays.size} times/week",
                            hour = it.waterTime,
                            amount = it.waterAmount,
                            size = it.size
                        )
                    }

                }
            }
        }
    }

    fun onEditPlant(): Long {
        return currentPlantId
    }

    fun onMarkWatered() {
        _viewState.value = viewState.value.copy(
            isDone = true
        )
        viewModelScope.launch {
            plantUseCase.getTask(currentTaskId)?.let {
                plantUseCase.completeTask(it)
            }
        }
    }
}