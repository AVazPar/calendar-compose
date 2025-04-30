package com.avazpar.calendar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avazpar.calendar.model.TimeState
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing
import com.avazpar.calendar.theme.getColors
import com.avazpar.calendar.utils.borderRectangle
import com.avazpar.calendar.utils.focusRectangle
import com.avazpar.calendar.utils.orTransparent

@Composable
fun Time(
    modifier: Modifier = Modifier,
    time: String = "",
    state: TimeState = TimeState.Default,
    onTimeClicked: () -> Unit = {}
) {
    val colors = getColors(state)

    Box(
        modifier = modifier
             .clickable(
                 interactionSource = remember { MutableInteractionSource() },
                 indication = ripple(
                     bounded = false,
                     radius = 0.dp
                 ),
                 onClick = onTimeClicked
             )
            .defaultMinSize(100.dp)
            .focusRectangle(colors.focus.orTransparent())
            .borderRectangle(colors.border.orTransparent())
            .background(
                color = colors.background.orTransparent(),
                shape = RoundedCornerShape(size = 40.dp)
            )
            .padding(horizontal = Spacing.normal, vertical = Spacing.intermediate)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = time,
            color = colors.text,
            maxLines = 1
        )
    }
}

internal class TimePreview {
    @Preview(name = "Light Mode")
    @Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun ShowTimeCalendarComponent() {
        CalendarTheme(darkTheme = isSystemInDarkTheme()) {
            Row {
                Time(time = "6:45pm", state = TimeState.Default)
                Time(time = "6:45pm", state = TimeState.Default)
                Time(time = "6:45pm", state = TimeState.Default)
            }
        }
    }
}
