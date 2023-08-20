package com.example.myplants.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val colorScheme = MaterialTheme.colorScheme
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val enabledColors = if (isPressed) Pair(
        colorScheme.primary,
        colorScheme.onPrimary
    ) else Pair(colorScheme.primaryContainer, colorScheme.onPrimaryContainer)

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors(
            containerColor = enabledColors.first,
            contentColor = enabledColors.second,
            disabledContainerColor = colorScheme.secondaryContainer,
            disabledContentColor = colorScheme.onSecondaryContainer
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 8.dp,
            end = 12.dp,
            bottom = 8.dp
        ),
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = text
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 8.dp,
            end = 12.dp,
            bottom = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}