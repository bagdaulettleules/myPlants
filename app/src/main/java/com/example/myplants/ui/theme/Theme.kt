package com.example.myplants.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = AccentA600,
    onPrimary = Color.White,
    primaryContainer = AccentA500,
    onPrimaryContainer = Color.White,
    secondary = AccentA100,
    onSecondary = Color.White,
    secondaryContainer = AccentA100,
    onSecondaryContainer = Color.White,
    tertiary = AccentA600,
    background = NeutralN100,
    onBackground = NeutralN300,
    surface = NeutralN100,
    onSurface = NeutralN300,
    surfaceVariant = NeutralN100,
    onSurfaceVariant = NeutralN500
)

@Composable
fun MyPlantsTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}