package com.example.nammahomestay.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val AppColorScheme = darkColorScheme(
    primary = EmeraldGreen,
    onPrimary = Black,
    secondary = SoftGreen,
    onSecondary = Black,
    tertiary = GoldYellow,
    background = Black,
    onBackground = White,
    surface = CardBlack,
    onSurface = White,
    surfaceVariant = SurfaceBlack,
    onSurfaceVariant = GrayText
)

@Composable
fun NammaHomeStayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            content()
        }
    }
}