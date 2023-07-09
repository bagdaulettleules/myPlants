package com.example.myplants.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun BottomDivider(
    onClick: () -> Unit
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth(0.33F)
            .clip(RoundedCornerShape(100.dp))
            .clickable(onClick = onClick),
        color = NeutralN900,
        thickness = 5.dp
    )
}