package com.example.myplants.feature_main.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.usecase.PlantUseCase
import com.example.myplants.feature_main.domain.util.FetchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val plantUseCase: PlantUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TasksListState())
    val state: State<TasksListState> = _state

    private var getTasksJob: Job? = null

    private var deletedPlant: Plant? = null

    init {
        getPlantsAsTask(FetchType.Upcoming)
    }

    fun onEvent(event: TasksListEvent) {
        when (event) {
            is TasksListEvent.Fetch -> onFetch(event.fetchType)
            is TasksListEvent.MarkWatered -> onMarkWatered(event.taskId)
            is TasksListEvent.Delete -> onDelete(event.plantId)
            TasksListEvent.Restore -> onRestore()
        }
    }

    private fun onFetch(fetchType: FetchType) {
        if (state.value.fetchType::class == fetchType::class) {
            return
        }
        getPlantsAsTask(fetchType)
    }

    private fun onMarkWatered(taskId: Long) {
        viewModelScope.launch {
            val task = plantUseCase.getTask(taskId)
            if (task != null) {
                plantUseCase.completeTask(task)
            }
        }
    }

    private fun onDelete(plantId: Long) {
        viewModelScope.launch {
            val plant = plantUseCase.getPlant(plantId)
            if (plant != null) {
                plantUseCase.deletePlant(plant)
                deletedPlant = plant
            }
        }
    }

    private fun onRestore() {
        viewModelScope.launch {
            plantUseCase.savePlant(deletedPlant ?: return@launch)
            deletedPlant = null
        }
    }

    private fun getPlantsAsTask(fetchType: FetchType) {
        getTasksJob?.cancel()
        getTasksJob = plantUseCase.getAll(fetchType)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    todoList = tasks,
                    fetchType = fetchType
                )
            }
            .launchIn(viewModelScope)
    }
}