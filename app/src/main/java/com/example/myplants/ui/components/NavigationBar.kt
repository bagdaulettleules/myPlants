package com.example.myplants.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myplants.R
import com.example.myplants.feature_main.presentation.util.Screen

@Composable
fun NavigationBar(
    isExpanded: Boolean = false,
    currentScreen: Screen = Screen.TasksList,
    onNavigationClick: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 16.dp,
                spotColor = MaterialTheme.colorScheme.background,
                ambientColor = MaterialTheme.colorScheme.background
            )
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = 0.dp,
                end = 0.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        if (isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavigationItem(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_home),
                    text = "Home",
                    isSelected = currentScreen is Screen.TasksList,
                    onClick = { onNavigationClick(Screen.TasksList) }
                )
                NavigationItem(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_edit),
                    text = "Edit plants",
                    isSelected = currentScreen is Screen.PlantsList,
                    onClick = { onNavigationClick(Screen.PlantsList) }
                )
                CircleButton(
                    modifier = Modifier.size(48.dp),
                    icon = painterResource(id = R.drawable.ic_add),
                    onClick = { onNavigationClick(Screen.EditPlant) }
                )
                NavigationItem(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_notification),
                    text = "Notification",
                    isSelected = currentScreen is Screen.NotificationsList,
                    onClick = { onNavigationClick(Screen.NotificationsList) }
                )
                NavigationItem(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_profile),
                    text = "Profile",
                    isSelected = currentScreen is Screen.Profile,
                    onClick = { onNavigationClick(Screen.Profile) }
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        BottomDivider(
            onClick = {}
        )
    }
}