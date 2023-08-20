package com.example.myplants.feature_main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myplants.feature_main.presentation.detail.TaskDetailScreen
import com.example.myplants.feature_main.presentation.detail.TaskDetailViewModel
import com.example.myplants.feature_main.presentation.edit.EditPlantScreen
import com.example.myplants.feature_main.presentation.edit.EditPlantViewModel
import com.example.myplants.feature_main.presentation.list.TasksListScreen
import com.example.myplants.feature_main.presentation.list.TasksListViewModel
import com.example.myplants.feature_main.presentation.util.Screen
import com.example.myplants.ui.theme.MyPlantsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPlantsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TasksList.route
                    ) {

                        composable(route = Screen.TasksList.route) {
                            val viewModel = hiltViewModel<TasksListViewModel>()
                            TasksListScreen(
                                navController = navController,
                                state = viewModel.state.value,
                                onEvent = viewModel::onEvent
                            )
                        }

                        composable(
                            route = Screen.PlantDetail.route + "?tasksList={tasksList}&initialIdx={initialIdx}",
                            arguments = listOf(
                                navArgument(name = "tasksList") {
                                    type = NavType.LongArrayType
                                    defaultValue = emptyArray<Long>()
                                },
                                navArgument(name = "initialIdx") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<TaskDetailViewModel>()
                            val viewState = viewModel.viewState.value
                            TaskDetailScreen(
                                navController = navController,
                                pages = viewState.pages,
                                initialPage = viewState.pages,
                                task = viewState.task,
                                onEvent = viewModel::onEvent
                            )
                        }

                        composable(
                            route = Screen.EditPlant.route + "?plantId={plantId}",
                            arguments = listOf(
                                navArgument(name = "plantId") {
                                    type = NavType.LongType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<EditPlantViewModel>()
                            EditPlantScreen(
                                navController = navController,
                                nameState = viewModel.name.value,
                                descriptionState = viewModel.description.value,
                                sizeState = viewModel.size.value,
                                waterAmountState = viewModel.waterAmount.value,
                                waterDaysState = viewModel.waterDays.value,
                                onEvent = viewModel::onEvent,
                                eventFlow = viewModel.eventFlow
                            )
                        }

                    }
                }
            }
        }
    }
}