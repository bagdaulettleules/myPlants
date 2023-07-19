package com.example.myplants.feature_main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                                viewState = viewModel.state.value,
                                onEvent = viewModel::onEvent
                            )
                        }

                        composable(
                            route = Screen.PlantDetail.route + "?plantId={plantId}",
                            arguments = listOf(
                                navArgument(name = "plantId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            Text(text = "Plant Detail")
                        }

                    }
                }
            }
        }
    }
}