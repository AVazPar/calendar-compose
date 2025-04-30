package com.avazpar.calendar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing
import com.avazpar.calendar.theme.getColors
import com.avazpar.calendar.utils.borderCircle
import com.avazpar.calendar.utils.focusCircle
import com.avazpar.calendar.utils.orTransparent

@Composable
fun Day(
    modifier: Modifier = Modifier,
    day: Int,
    isSelected: Boolean = false,
    hasSessions: Boolean = false,
    isCurrentDay: Boolean = false,
    isNotThisMonth: Boolean = false,
    onDayClicked: () -> Unit = {}
) {
    val colors = getColors(hasSessions = hasSessions, isCurrentDay = isCurrentDay, isSelected = isSelected, isNotThisMonth = isNotThisMonth)
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    if (hasSessions)
                        onDayClicked()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = false,
                    radius = 0.dp
                )
            )
            .focusCircle(colors.focus.orTransparent())
            .borderCircle(colors.border.orTransparent())
            .background(
                color = colors.background.orTransparent(),
                shape = RoundedCornerShape(size = 20.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = Spacing.xxSmall),
            textAlign = TextAlign.Center,
            text = day.toString(),
            color = colors.text
        )
        Spacer(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp)
                .size(4.dp)
                .background(
                    color = colors.dot.orTransparent(),
                    shape = CircleShape
                )
        )
    }
}

internal class DayPreview {
    @Preview(name = "Light Mode")
    @Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun ShowDayUnavailableSelected() {
        CalendarTheme(darkTheme = isSystemInDarkTheme()) {
            Row(
                horizontalArrangement = spacedBy(8.dp)
            ) {
                //Unavailable
                Day(day = 22, hasSessions = false, isCurrentDay = false, isSelected = false)
                //Unavailable current day
                Day(day = 22, hasSessions = false, isCurrentDay = true, isSelected = false)
                //Available
                Day(day = 22, hasSessions = true, isCurrentDay = false, isSelected = false)
                //Available Selected
                Day(day = 22, hasSessions = true, isCurrentDay = true, isSelected = false)
                //Active
                Day(day = 22, hasSessions = true, isCurrentDay = false, isSelected = true)
            }
        }
    }
}
