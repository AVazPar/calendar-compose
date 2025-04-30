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
        text = CalendarColors.AvailableText,
        background = null,
        border = CalendarColors.UnavailableBorder,
        focus = null
    )

    is TimeState.Active -> TimeColors(
        text = CalendarColors.ActiveText,
        background = CalendarColors.ActiveBackground,
        border = null,
        focus = null
    )
}