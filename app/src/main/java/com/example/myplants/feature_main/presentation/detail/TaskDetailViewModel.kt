package com.example.myplants.feature_main.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var taskIds: LongArray? = null

    private val _viewState = mutableStateOf(
        TaskDetailState()
    )
    val viewState: State<TaskDetailState> = _viewState


    init {
        savedStateHandle.get<LongArray>("tasksList")?.let {
            taskIds = it

            _viewState.value = viewState.value.copy(
                pages = it.size
            )
        }

        savedStateHandle.get<Int>("initialIdx")?.let {
            onPageSelected(it)
        }
    }

    fun onEvent(event: TaskDetailEvent) {
        when (event) {
            is TaskDetailEvent.MarkWatered -> onMarkWatered(event.task)
            is TaskDetailEvent.PageSelected -> onPageSelected(event.page)
        }
    }

    private fun onMarkWatered(task: Task) {
        viewModelScope.launch {
            taskUseCase.save(
                task.copy(
                    isDone = true,
                    updateTs = System.currentTimeMillis()
                )
            )
        }
    }

    private fun onPageSelected(page: Int) {
        _viewState.value = viewState.value.copy(
            currentPage = page
        )
    }
}