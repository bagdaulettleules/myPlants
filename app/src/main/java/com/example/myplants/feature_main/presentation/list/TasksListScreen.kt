package com.example.myplants.feature_main.presentation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myplants.R
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.model.TaskWithPlant
import com.example.myplants.feature_main.presentation.list.components.EmptyListMessageBox
import com.example.myplants.feature_main.presentation.list.components.FetchTypeSection
import com.example.myplants.feature_main.presentation.list.components.TaskListItem
import com.example.myplants.feature_main.presentation.util.Screen
import com.example.myplants.ui.components.NavigationBar
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900
import java.time.DayOfWeek
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    navController: NavHostController = rememberNavController(),
    viewState: TasksListState,
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
                    onClick = { /*TODO*/ },
                    border = BorderStroke(0.dp, MaterialTheme.colorScheme.surfaceVariant),
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
                onNavigationClick = {}
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                    .height(20.dp),
                fetchType = viewState.fetchType,
                onFetchTypeChange = { fetchType ->
                    onEvent(TasksListEvent.Fetch(fetchType))
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (viewState.taskList.isEmpty()) {
                EmptyListMessageBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    onAddButtonClick = {
                        navController.navigate(Screen.EditPlant.route)
                    }
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 168.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
                ) {
                    items(viewState.taskList) { task ->
                        TaskListItem(
                            modifier = Modifier
                                .width(168.dp)
                                .height(256.dp),
                            task = task,
                            onItemClick = {},
                            onStatusIconClick = {}
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
        taskList = listOf(
            TaskWithPlant(tasksList[0], monsteraPlant),
            TaskWithPlant(tasksList[1], monsteraPlant),
            TaskWithPlant(tasksList[2], monsteraPlant),
        )
    )
    MyPlantsTheme {
        Surface {
            TasksListScreen(
                viewState = tasksListState,
                onEvent = {}
            )
        }
    }
}

val monsteraPlant = Plant(
    "Monstera",
    "Short description",
    null,
    emptyList(),
    12,
    50,
    Size.MEDIUM,
    null,
    1
)

val tasksList = listOf(
    Task(1, DayOfWeek.FRIDAY, Date(), true, null, 1),
    Task(1, DayOfWeek.SATURDAY, Date(), false, null, 2),
    Task(1, DayOfWeek.SUNDAY, Date(), false, null, 3)
)
