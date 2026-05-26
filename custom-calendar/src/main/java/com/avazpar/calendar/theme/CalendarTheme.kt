package com.avazpar.calendar.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Composable
fun CalendarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    palette: CalendarPalette = CalendarPalette.BLUE,
    customColors: CalendarColors? = null,
    content: @Composable () -> Unit
) {
    val calendarColors = when {
        customColors != null -> customColors
        palette == CalendarPalette.RED -> getRedPalette(darkTheme = darkTheme)
        palette == CalendarPalette.BLUE -> getBluePalette(darkTheme = darkTheme)
        palette == CalendarPalette.GREEN -> getGreenPalette(darkTheme = darkTheme)
        else -> getBluePalette(darkTheme = darkTheme)
    }

    CompositionLocalProvider(LocalCalendarColors provides calendarColors) {
        MaterialTheme(
            colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
            content = content
        )
    }
}

private fun getRedPalette(darkTheme: Boolean) =  if (darkTheme) DarkRedThemeColors else RedThemeColors
private fun getBluePalette(darkTheme: Boolean) =  if (darkTheme) DarkBlueThemeColors else BlueThemeColors
private fun getGreenPalette(darkTheme: Boolean) =  if (darkTheme) DarkGreenThemeColors else GreenThemeColors


object CalendarTheme {
    val colors: CalendarColors
        @Composable
        get() = LocalCalendarColors.current
}

private val LocalCalendarColors = staticCompositionLocalOf { BlueThemeColors }

object Spacing {
    val normal = 16.dp
    val intermediate = 12.dp
    val small = 8.dp
    val xSmall = 4.dp
    val xxSmall = 2.dp
}