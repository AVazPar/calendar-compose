package com.avazpar.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun MonthRow(
    month: Int,
    year: Int,
    locale: Locale = Locale.getDefault(),
    goLeft: () -> Unit,
    goRight: () -> Unit,
    isFirstMonth: Boolean,
    isLastMonth: Boolean
) {
    val monthName = Month.of(month).getDisplayName(TextStyle.FULL, locale)

    Row {
        Icon(
            modifier = Modifier
                .padding(Spacing.xSmall)
                .clickable(enabled = !isFirstMonth, onClick = goLeft),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            tint = if (isFirstMonth) CalendarTheme.colors.unavailableText else CalendarTheme.colors.availableText
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "$monthName $year",
            textAlign = TextAlign.Center,
            color = CalendarTheme.colors.availableText
        )
        Icon(
            modifier = Modifier
                .padding(Spacing.xSmall)
                .clickable(enabled = !isLastMonth, onClick = goRight),
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "",
            tint = if (isLastMonth) CalendarTheme.colors.unavailableText else CalendarTheme.colors.availableText
        )
    }
}