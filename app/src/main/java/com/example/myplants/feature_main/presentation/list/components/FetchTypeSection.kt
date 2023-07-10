package com.example.myplants.feature_main.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myplants.feature_main.domain.util.FetchType

@Composable
fun FetchTypeSection(
    modifier: Modifier = Modifier,
    fetchType: FetchType = FetchType.Upcoming,
    onFetchTypeChange: (FetchType) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        UnderLinedText(
            text = "Upcoming",
            isSelected = fetchType is FetchType.Upcoming,
            onSelect = {
                onFetchTypeChange(FetchType.Upcoming)
            }
        )

        Spacer(modifier = Modifier.width(24.dp))

        UnderLinedText(
            text = "Forgot to water",
            isSelected = fetchType is FetchType.Missed,
            onSelect = {
                onFetchTypeChange(FetchType.Missed)
            }
        )

        Spacer(modifier = Modifier.width(24.dp))
        
        UnderLinedText(
            text = "History",
            isSelected = fetchType is FetchType.History,
            onSelect = {
                onFetchTypeChange(FetchType.History)
            }
        )

    }
}