package com.example.myplants.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    iconResource: Int,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.background,
    disabledColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    disabledIconColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    paddingValues: PaddingValues = PaddingValues(12.dp),
    elevation: ButtonElevation? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = iconColor,
            disabledContainerColor = disabledColor,
            disabledContentColor = disabledIconColor
        ),
        elevation = elevation,
        contentPadding = paddingValues,
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(iconResource),
            contentDescription = null,
            tint = iconColor
        )
    }
}