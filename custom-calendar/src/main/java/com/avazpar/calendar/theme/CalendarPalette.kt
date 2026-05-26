package com.avazpar.calendar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CalendarColors(
    val unavailableBorder: Color,
    val availableText: Color,
    val activeText: Color,
    val unavailableText: Color,
    val activeBackground: Color,
    val availableBackground: Color,
    val activeDot: Color,
    val availableDot: Color,
    val availableBorder: Color
)

val RedThemeColors = CalendarColors(
    unavailableBorder = Color(0xFFC44536),
    availableText = Color(0xFFE8F5E9),
    activeText = Color(0xFFF8EDEB),
    unavailableText = Color(0xFF5D1F1F),
    activeBackground = Color(0xFF2E1A1A),
    availableBackground = Color(0xFFC44536),
    activeDot = Color(0xFFF8EDEB),
    availableDot = Color(0xFFF8EDEB),
    availableBorder = Color(0xFF2E1A1A)
)

val DarkRedThemeColors = CalendarColors(
    unavailableBorder = Color(0xFFE56B6B),
    availableText = Color(0xFFE8F5E9),
    activeText = Color(0xFFF8EDEB),
    unavailableText = Color(0xFFF8A8A8),
    activeBackground = Color(0xFF5D1F1F),
    availableBackground = Color(0xFF8C2D2D),
    activeDot = Color(0xFFF8EDEB),
    availableDot = Color(0xFF2E1A1A),
    availableBorder = Color(0xFFF8DED5)
)

val BlueThemeColors = CalendarColors(
    unavailableBorder = Color(0xFF8AA4C7),
    availableText = Color(0xFF12385F),
    activeText = Color(0xFFF7FBFF),
    unavailableText = Color(0xFF6E85A0),
    activeBackground = Color(0xFF0F2742),
    availableBackground = Color(0xFF2F6FB2),
    activeDot = Color(0xFFF7FBFF),
    availableDot = Color(0xFFEAF3FF),
    availableBorder = Color(0xFF12385F)
)

val DarkBlueThemeColors = CalendarColors(
    unavailableBorder = Color(0xFF5E7FA5),
    availableText = Color(0xFFEAF3FF),
    activeText = Color(0xFFF7FBFF),
    unavailableText = Color(0xFF9EB9DA),
    activeBackground = Color(0xFF0F2742),
    availableBackground = Color(0xFF295F99),
    activeDot = Color(0xFFF7FBFF),
    availableDot = Color(0xFFD8E8FB),
    availableBorder = Color(0xFF8FC2FF)
)

val GreenThemeColors = CalendarColors(
    unavailableBorder = Color(0xFF5DA9E9),
    unavailableText = Color(0xFF1E3A5F),
    availableText = Color(0xFF2E7D32),
    activeText = Color(0xFFE8F5E9),
    activeBackground = Color(0xFF2E7D32),
    availableBackground = Color(0xFF4CAF50),
    activeDot = Color(0xFFF8EDEB),
    availableDot = Color(0xFFE8F5E9),
    availableBorder = Color(0xFF81C784)
)

val DarkGreenThemeColors = CalendarColors(
    unavailableBorder = Color(0xFF5DA9E9),
    unavailableText = Color(0xFFF8EDEB),
    activeText = Color(0xFFF8EDEB),
    activeDot = Color(0xFFE8F5E9),
    activeBackground = Color(0xFF2E7D32),
    availableText = Color(0xFFE8F5E9),
    availableBackground = Color(0xFF81C784),
    availableDot = Color(0xFFF8EDEB),
    availableBorder = Color(0xFF81C784)
)

enum class CalendarPalette {
    RED, BLUE, GREEN, CUSTOM
}
