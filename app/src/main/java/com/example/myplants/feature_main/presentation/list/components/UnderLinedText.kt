package com.example.myplants.feature_main.presentation.list.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun UnderLinedText(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onBackground,
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.clickable {
                onSelect()
            },
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = color
        )

        if (isSelected) {
            Divider(
                modifier = Modifier
                    .width(40.dp)
                    .clip(RoundedCornerShape(1.dp)),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}