package com.example.myplants.feature_notification.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myplants.feature_main.presentation.list.components.UnderLinedText
import com.example.myplants.feature_notification.domain.util.NotificationFetchType

@Composable
fun NotificationFetchTypeSection(
    modifier: Modifier = Modifier,
    fetchType: NotificationFetchType = NotificationFetchType.All,
    onFetchTypeChange: (NotificationFetchType) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        UnderLinedText(
            text = "All Notifications",
            isSelected = fetchType is NotificationFetchType.All,
            onSelect = {
                onFetchTypeChange(NotificationFetchType.All)
            }
        )

        Spacer(modifier = Modifier.width(24.dp))

        UnderLinedText(
            text = "Forgot to water",
            isSelected = fetchType is NotificationFetchType.Forgot,
            onSelect = {
                onFetchTypeChange(NotificationFetchType.Forgot)
            }
        )

        Spacer(modifier = Modifier.width(24.dp))

        UnderLinedText(
            text = "History",
            isSelected = fetchType is NotificationFetchType.Upcoming,
            onSelect = {
                onFetchTypeChange(NotificationFetchType.Upcoming)
            }
        )

    }
}