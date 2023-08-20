package com.example.myplants.feature_main.presentation.detail.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    currentPage: Int
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterHorizontally
        )
    ) {
        items(size) { index ->
            val animatedWidth =
                animateDpAsState(
                    targetValue = if (index == currentPage) {
                        20.dp
                    } else {
                        6.dp
                    }
                )
            val animatedColor = animateColorAsState(
                targetValue = if (index == currentPage) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            )
            Divider(
                modifier = Modifier
                    .width(animatedWidth.value)
                    .height(6.dp)
                    .clip(
                        shape = RoundedCornerShape(size = 100.dp)
                    ),
                thickness = 6.dp,
                color = animatedColor.value
            )
        }
    }
}