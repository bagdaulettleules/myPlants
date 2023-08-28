package com.example.myplants.feature_main.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
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
            is TasksListEvent.MarkCompleted -> onMarkWatered(event.task)
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

    private fun onMarkWatered(task: Task) {
        viewModelScope.launch {
            taskUseCase.save(
                task.copy(
                    isDone = true,
                    updateTs = System.currentTimeMillis()
                )
            )

            plantUseCase.get(task.plantId)?.let {
                val newTask = taskUseCase.getNext(it)
                taskUseCase.save(newTask)
            }
        }
    }

    private fun onDelete(plant: Plant) {
        viewModelScope.launch {
            plantUseCase.delete(plant)
            deletedPlant = plant
        }
    }

    private fun onRestore() {
        viewModelScope.launch {
            plantUseCase.save(deletedPlant ?: return@launch)
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