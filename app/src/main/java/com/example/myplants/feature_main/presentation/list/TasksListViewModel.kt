package com.example.myplants.feature_main.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.usecase.plant.PlantUseCases
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCases
import com.example.myplants.feature_main.domain.util.FetchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val plantUseCases: PlantUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(TasksListState())
    val state: State<TasksListState> = _state

    private var getPlantsJob: Job? = null

    private var deletedPlant: Plant? = null

    init {
        getPlantsAsTask(FetchType.Upcoming)
    }

    fun onEvent(event: TasksListEvent) {
        when (event) {
            is TasksListEvent.Fetch -> onFetch(event.fetchType)
            is TasksListEvent.Water -> onWater(event.task)
            is TasksListEvent.Delete -> onDelete(event.plant)
            TasksListEvent.Restore -> onRestore()
        }
    }

    private fun onFetch(fetchType: FetchType) {
        if (state.value.fetchType::class == fetchType::class) {
            return
        }
        getPlantsAsTask(fetchType)
    }

    private fun onWater(task: Task) {

    }

    private fun onDelete(plant: Plant) {
        viewModelScope.launch {
            plantUseCases.deletePlant(plant)
            deletedPlant = plant
        }
    }

    private fun onRestore() {
        viewModelScope.launch {
            plantUseCases.savePlant(deletedPlant ?: return@launch)
            deletedPlant = null
        }
    }

    private fun getPlantsAsTask(fetchType: FetchType) {
        getPlantsJob?.cancel()
        getPlantsJob = taskUseCases.getAll(fetchType)
            .onEach { plantsAsTasks ->
                _state.value = state.value.copy(
                    taskList = plantsAsTasks,
                    fetchType = fetchType
                )
            }
            .launchIn(viewModelScope)
    }
}