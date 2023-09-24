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
                            val listState = viewModel.state
                            val dialogState = viewModel.dialogState

                            TasksListScreen(
                                navController = navController,
                                state = listState.value,
                                dialogState = dialogState.value,
                                onEvent = viewModel::onEvent
                            )
                        }

                        composable(
                            route = Screen.PlantDetail.route + "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(name = "taskId") {
                                    type = NavType.LongType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<TaskDetailViewModel>()
                            val viewState = viewModel.viewState.value
                            TaskDetailScreen(
                                navController = navController,
                                name = viewState.name,
                                description = viewState.description,
                                imageUrl = viewState.imageUrl,
                                size = viewState.size,
                                frequency = viewState.frequency,
                                amount = viewState.amount,
                                isDone = viewState.isDone,
                                onMarkWatered = viewModel::onMarkWatered,
                                onEdit = viewModel::onEditPlant
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
                            val nameState = viewModel.name
                            val descriptionState = viewModel.description
                            val sizeState = viewModel.size
                            val waterAmountState = viewModel.waterAmount
                            val waterDaysState = viewModel.waterDays
                            val imageState = viewModel.image

                            EditPlantScreen(
                                navController = navController,
                                nameState = nameState.value,
                                descriptionState = descriptionState.value,
                                sizeState = sizeState.value,
                                waterAmountState = waterAmountState.value,
                                waterDaysState = waterDaysState.value,
                                imageState = imageState.value,
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