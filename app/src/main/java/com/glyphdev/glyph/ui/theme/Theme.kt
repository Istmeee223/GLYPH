package com.glyphdev.glyph.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0x00FF00),
    onPrimary = Color(0x000000),
    primaryContainer = Color(0x1A1A1A),
    onPrimaryContainer = Color(0x00FF00),
    secondary = Color(0x00FFFF),
    onSecondary = Color(0x000000),
    secondaryContainer = Color(0x1A2D2D),
    onSecondaryContainer = Color(0x00FFFF),
    tertiary = Color(0xFF00FF),
    onTertiary = Color(0x000000),
    tertiaryContainer = Color(0x2D1A2D),
    onTertiaryContainer = Color(0xFF00FF),
    error = Color(0xFF1744),
    onError = Color(0xFFFFFF),
    errorContainer = Color(0x331744),
    onErrorContainer = Color(0xFF1744),
    background = Color(0x000000),
    onBackground = Color(0xFFFFFF),
    surface = Color(0x111111),
    onSurface = Color(0xFFFFFF),
    surfaceVariant = Color(0x2D2D2D),
    onSurfaceVariant = Color(0x999999),
    outline = Color(0x404040),
    outlineVariant = Color(0x2D2D2D),
    scrim = Color(0x000000),
    inverseSurface = Color(0xFFFFFF),
    inverseOnSurface = Color(0x000000),
    inversePrimary = Color(0x00FF00),
)

@Composable
fun GlyphTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = GlyphTypography,
        content = content
    )
}
