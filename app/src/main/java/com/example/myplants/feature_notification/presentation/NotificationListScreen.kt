package com.example.myplants.feature_notification.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myplants.R
import com.example.myplants.feature_notification.presentation.components.NotificationFetchTypeSection
import com.example.myplants.ui.components.CircleButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun NotificationListScreen(
    navController: NavController = rememberNavController(),
    listState: NotificationListState
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .paint(
                painter = painterResource(id = R.drawable.ic_background),
                alignment = Alignment.TopStart,
                contentScale = ContentScale.FillWidth
            ),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleButton(
                        iconResource = R.drawable.ic_chevron_left,
                        modifier = Modifier
                            .size(36.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        iconColor = NeutralN900,
                        onClick = {
                            navController.navigateUp()
                        },
                        paddingValues = PaddingValues(8.dp)
                    )

                    Text(
                        text = "Notifications",
                        style = MaterialTheme.typography.bodyLarge,
                        color = NeutralN900
                    )

                }

                NotificationFetchTypeSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    fetchType = listState.fetchType,
                    onFetchTypeChange = {

                    }
                )
            }

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        val pv = paddingValues

    }

}

@Preview
@Composable
fun PlantsListScreenPreview() {
    val listState = NotificationListState()
    MyPlantsTheme {
        Surface {
            NotificationListScreen(
                listState = listState
            )
        }
    }
}