package com.avazpar.calendar.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class DayColors(
    val text: Color,
    val background: Color,
    val dot: Color,
    val border: Color,
    val focus: Color
)

@Composable
internal fun getColors(
    hasSessions: Boolean,
    isCurrentDay: Boolean,
    isSelected: Boolean,
    isNotThisMonth: Boolean
): DayColors = DayColors(
    text = getTextColors(hasSessions = hasSessions, isSelected = isSelected, isNotThisMonth = isNotThisMonth),
    background = getBackgroundColors(hasSessions = hasSessions, isSelected = isSelected),
    dot = getDotColors(hasSessions = hasSessions, isSelected = isSelected),
    border = getBorderColors(isCurrentDay = isCurrentDay),
    focus = Color.Transparent
)

@Composable
private fun getTextColors(
    hasSessions: Boolean,
    isSelected: Boolean,
    isNotThisMonth: Boolean
): Color = when {
    hasSessions && isSelected -> CalendarTheme.colors.activeText
    hasSessions -> CalendarTheme.colors.availableText
    isNotThisMonth -> Color.Transparent
    else -> CalendarTheme.colors.unavailableText
}

@Composable
private fun getBackgroundColors(
    hasSessions: Boolean,
    isSelected: Boolean
): Color = when {
    hasSessions && isSelected -> CalendarTheme.colors.activeBackground
    hasSessions -> CalendarTheme.colors.availableBackground
    else -> Color.Transparent
}

@Composable
private fun getDotColors(
    hasSessions: Boolean,
    isSelected: Boolean
): Color = when {
    hasSessions && isSelected -> CalendarTheme.colors.activeDot
    hasSessions -> CalendarTheme.colors.availableDot
    else -> Color.Transparent
}

@Composable
private fun getBorderColors(
    isCurrentDay: Boolean
): Color = when {
    isCurrentDay -> CalendarTheme.colors.availableBorder
    else -> Color.Transparent
}
