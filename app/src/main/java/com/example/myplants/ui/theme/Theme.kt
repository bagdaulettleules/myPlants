package com.example.myplants.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = AccentA500,
    onPrimary = NeutralN100,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.White,
    secondary = AccentA100,
    onSecondary = NeutralN000,
    tertiary = AccentA600,
    background = NeutralN100,
    onBackground = NeutralN300,
    surface = OtherG100,
    onSurface = OtherG500,
    surfaceVariant = NeutralN500
)

@Composable
fun MyPlantsTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = LightColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}