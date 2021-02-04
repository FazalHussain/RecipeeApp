package com.example.recipeapp.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightThemeColor = lightColors(
        primary = Blue600,
        primaryVariant = Blue400,
        onPrimary = Black2,
        secondary = Color.White,
        secondaryVariant = Teal300,
        onSecondary = Color.Black,
        error = RedErrorDark,
        onError = RedErrorLight,
        background = Grey1,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Black2
)


private val darkThemeColor = lightColors(
        primary = Blue700,
        primaryVariant = Color.White,
        onPrimary = Color.White,
        secondary = Color.White,
        secondaryVariant = Black1,
        onSecondary = Color.White,
        error = RedErrorLight,
        background = Color.Black,
        onBackground = Color.White,
        surface = Black1,
        onSurface = Color.White
)

@Composable
fun AppTheme(
        darkTheme: Boolean,
        content: @Composable () -> Unit
) {
    MaterialTheme(
            colors = if (darkTheme) darkThemeColor else lightThemeColor
    ) {
        content()
    }
}