package com.avazpar.calendar.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.avazpar.calendar.model.TimeState

class TimeColors(
    val text: Color,
    val background: Color? = null,
    val border: Color? = null,
    val focus: Color? = null
)

@Composable
internal fun getColors(
    state: TimeState,
): TimeColors = when (state) {
    is TimeState.Default -> TimeColors(
        text = CalendarTheme.colors.availableText,
        background = null,
        border = CalendarTheme.colors.unavailableBorder,
        focus = null
    )

    is TimeState.Active -> TimeColors(
        text = CalendarTheme.colors.activeText,
        background = CalendarTheme.colors.activeBackground,
        border = null,
        focus = null
    )
}