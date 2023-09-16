package com.example.myplants.feature_main.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShortSummarySection(
    modifier: Modifier,
    size: String,
    waterAmount: String,
    frequency: String
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = RoundedCornerShape(8.dp),
        border = null
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Size",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = size,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                )

            }

            Divider(
                modifier = Modifier
                    .height(32.dp)
                    .width(1.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surface
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Water",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = waterAmount,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                )

            }

            Divider(
                modifier = Modifier
                    .height(32.dp)
                    .width(1.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surface
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Frequency",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = frequency,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }

        }
    }

}