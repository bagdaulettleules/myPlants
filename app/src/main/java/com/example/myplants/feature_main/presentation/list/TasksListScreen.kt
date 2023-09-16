package com.example.myplants.feature_main.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myplants.R
import com.example.myplants.feature_main.domain.util.FetchType
import com.example.myplants.feature_main.presentation.list.components.EmptyListMessage
import com.example.myplants.feature_main.presentation.list.components.FetchTypeSection
import com.example.myplants.feature_main.presentation.list.components.TaskListItem
import com.example.myplants.feature_main.presentation.util.Screen
import com.example.myplants.ui.components.NavigationBar
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun TasksListScreen(
    navController: NavHostController = rememberNavController(),
    state: TasksListState,
    onEvent: (TasksListEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .paint(
                painter = painterResource(id = R.drawable.ic_background),
                alignment = Alignment.TopStart,
                contentScale = ContentScale.FillWidth
            ),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 80.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Let's Care \nMy Plants!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = NeutralN900,
                    maxLines = 2
                )

                OutlinedButton(
                    modifier = Modifier.size(40.dp),
                    onClick = {
                        navController.navigate(
                            Screen.NotificationsList.route
                        )
                    },
                    border = null,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(
                onNavigationClick = {
                    navController.navigate(it.route)
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Button(
                onClick = {
                    navController.navigate(Screen.EditPlant.route)
                },
                modifier = Modifier
                    .size(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                elevation = ButtonDefaults.buttonElevation(25.dp),
                contentPadding = PaddingValues(14.dp),
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(
                    top = paddingValues.calculateTopPadding() + 32.dp,
                    bottom = 0.dp,
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 20.dp,
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 20.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {
            FetchTypeSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fetchType = state.fetchType,
                onFetchTypeChange = { fetchType ->
                    onEvent(TasksListEvent.Fetch(fetchType))
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (state.isEmptyPlants) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 84.dp,
                            start = 64.dp,
                            end = 64.dp,
                            bottom = 0.dp
                        )
                ) {
                    EmptyListMessage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        header = stringResource(
                            if (state.fetchType !is FetchType.Missed)
                                R.string.no_plants_header
                            else R.string.no_missed_plants_header
                        ),
                        message =
                        stringResource(
                            if (state.fetchType !is FetchType.Missed)
                                R.string.no_plants_message
                            else R.string.no_missed_plants_message
                        ),
                        isButtonEnabled = state.fetchType !is FetchType.Missed,
                        onAddButtonClick = {
                            navController.navigate(Screen.EditPlant.route)
                        }
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.todoList) { todo ->
                        TaskListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(256.dp),
                            name = todo.name,
                            description = todo.description,
                            imageUrl = todo.image,
                            waterAmountText = todo.waterAmount,
                            isUpcoming = state.fetchType is FetchType.Upcoming,
                            isWatered = todo.isDone,
                            onItemClick = {
                                navController.navigate(
                                    route = Screen.PlantDetail.route +
                                            "?taskId=${todo.taskId}"
                                )
                            },
                            onStatusIconClick = {
                                onEvent(TasksListEvent.MarkWatered(todo.taskId))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PlantsListScreenPreview() {
    val tasksListState = TasksListState(
        todoList = listOf()
    )
    MyPlantsTheme {
        Surface {
            TasksListScreen(
                state = tasksListState,
                onEvent = {}
            )
        }
    }
}
