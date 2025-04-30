package com.avazpar.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.avazpar.calendar.theme.CalendarColors
import com.avazpar.calendar.theme.Spacing
import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Locale


@Composable
internal fun WeekDayRow(locale: Locale = Locale.getDefault()) {
    val weekDays = DateFormatSymbols.getInstance(locale).shortWeekdays
    val firstDay = Calendar.getInstance(locale).firstDayOfWeek
    val daysOfWeek = with(weekDays.toList()) {
        this.subList(firstDay, weekDays.size) + this.subList(1, firstDay)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.intermediate),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { weekDay ->
            Text(
                text = weekDay.take(2).capitalize(locale),
                color = CalendarColors.ui900
            )
        }
    }
}

