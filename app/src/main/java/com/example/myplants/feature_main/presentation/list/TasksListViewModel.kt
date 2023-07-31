package com.example.myplants.feature_main.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Schedule
import com.example.myplants.feature_main.domain.usecase.plant.PlantUseCase
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCase
import com.example.myplants.feature_main.domain.util.FetchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val plantUseCase: PlantUseCase,
    private val taskUseCase: TaskUseCase
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
            is TasksListEvent.MarkCompleted -> onMarkWatered(event.schedule)
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

    private fun onMarkWatered(schedule: Schedule) {
        viewModelScope.launch {
            taskUseCase.saveSchedule(
                schedule.copy(
                    isDone = true,
                    updateTs = System.currentTimeMillis()
                )
            )

            plantUseCase.getPlant(schedule.plantId)?.let {
                taskUseCase.nextSchedule(it)
            }
        }
    }

    private fun onDelete(plant: Plant) {
        viewModelScope.launch {
            plantUseCase.deletePlant(plant)
            deletedPlant = plant
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
        getTasksJob = taskUseCase.getAllTasks(fetchType)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    taskList = tasks,
                    fetchType = fetchType
                )
            }
            .launchIn(viewModelScope)
    }
}