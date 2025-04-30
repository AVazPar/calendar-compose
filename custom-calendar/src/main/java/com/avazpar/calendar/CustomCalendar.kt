package com.avazpar.calendar

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.avazpar.calendar.model.CalendarData
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale


private const val MAX_SPOTS_FOR_DAYS_IN_MONTH = 42
private const val DAYS_OF_WEEK = 7

@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier,
    today: LocalDate = LocalDate.now(),
    data: CalendarData,
    selected: LocalDate,
    onClick: (LocalDate) -> Unit = {},
) {
    val calendarSessions = getCalendarSessions(data.availableSessions)
    val keys = calendarSessions.keys
    val pagerState = rememberPagerState(
        initialPage = calendarSessions.keys.indexOfFirst { it.first == today.monthValue && it.second == today.year },
        pageCount = { calendarSessions.size }
    )
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Spacing.normal)
        ) {
            MonthRow(
                month = keys.elementAt(page).first,
                year = keys.elementAt(page).second,
                goLeft = {
                    handleScrolling(
                        coroutineScope = coroutineScope,
                        scrollCheck = { pagerState.currentPage == 0 },
                        scrollAction = { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    )
                },
                goRight = {
                    handleScrolling(
                        coroutineScope = coroutineScope,
                        scrollCheck = { pagerState.currentPage == calendarSessions.size - 1 },
                        scrollAction = { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    )
                },
                isFirstMonth = page == 0,
                isLastMonth = pagerState.pageCount == page + 1
            )
            WeekDayRow()

            calendarSessions[keys.elementAt(page)]?.let { monthSessions ->
                if (monthSessions.isNotEmpty()) {
                    DayPicker(
                        availableSessions = monthSessions,
                        today = today,
                        selected = selected,
                        onClick = onClick
                    )
                } else {
                    EmptyMonth(
                        today = today,
                        monthValue = keys.elementAt(page).first,
                        year = keys.elementAt(page).second
                    )
                }
            }
        }
    }
}

private fun handleScrolling(
    coroutineScope: CoroutineScope,
    scrollCheck: () -> Boolean,
    scrollAction: suspend () -> Unit
) = coroutineScope.launch {
    if (scrollCheck()) {
        return@launch
    } else {
        scrollAction()
    }
}

private fun calendarLogic(
    month: Int,
    year: Int,
    locale: Locale = Locale.getDefault()
): List<List<LocalDate>> {
    val date = LocalDate.of(year, month, 1)
    val dayOfWeek = date.dayOfWeek
    val firstDayOfWeek: DayOfWeek = WeekFields.of(locale).firstDayOfWeek

    return List(MAX_SPOTS_FOR_DAYS_IN_MONTH) { position ->
        val daysOfDifference = firstDayOfWeek.ordinal - dayOfWeek.ordinal
        val adjustment = if (daysOfDifference > 0) DayOfWeek.entries.size else 0

        val adjustedDate = date.plusDays((daysOfDifference - adjustment + position).toLong())
        LocalDate.of(adjustedDate.year, adjustedDate.month.value, adjustedDate.dayOfMonth)
    }.chunked(DAYS_OF_WEEK)
}

private fun getCalendarSessions(availableSessions: List<LocalDate>): Map<Pair<Int, Int>, List<LocalDate>> {
    val minDate = availableSessions.minOrNull()
    val maxDate = availableSessions.maxOrNull()

    val allMonthsYears = minDate?.let { startDate ->
        maxDate?.let { endDate ->
            generateSequence(startDate) { it.withDayOfMonth(1).plusMonths(1) }
                .takeWhile { it <= endDate }
                .map { Pair(it.monthValue, it.year) }
                .toList()
        }
    } ?: emptyList()

    val groupedSessions = allMonthsYears
        .associateWith { monthYear ->
            availableSessions.filter { it.monthValue == monthYear.first && it.year == monthYear.second }
        }

    return groupedSessions
}

@Composable
private fun DayPicker(
    availableSessions: List<LocalDate>,
    today: LocalDate,
    selected: LocalDate,
    onClick: (LocalDate) -> Unit,
) {

    val monthData = calendarLogic(
        month = availableSessions.first().monthValue,
        year = availableSessions.first().year
    )

    LazyColumn {
        items(count = monthData.size) { indexWeek ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(count = monthData[indexWeek].size) { indexDay ->
                    Day(
                        isNotThisMonth = monthData[indexWeek][indexDay].monthValue != availableSessions.first().monthValue,
                        isSelected = monthData[indexWeek][indexDay] == selected,
                        isCurrentDay = monthData[indexWeek][indexDay] == LocalDate.of(
                            today.year,
                            today.month.value,
                            today.dayOfMonth
                        ),
                        hasSessions = availableSessions.contains(monthData[indexWeek][indexDay]),
                        day = monthData[indexWeek][indexDay].dayOfMonth,
                        onDayClicked = { onClick.invoke(monthData[indexWeek][indexDay]) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyMonth(
    today: LocalDate,
    monthValue: Int,
    year: Int
) {

    val monthData = calendarLogic(
        month = monthValue,
        year = year
    )

    LazyColumn {
        items(count = monthData.size) { indexWeek ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(count = monthData[indexWeek].size) { indexDay ->
                    Day(
                        isNotThisMonth = monthData[indexWeek][indexDay].monthValue != monthValue,
                        isSelected = false,
                        isCurrentDay = monthData[indexWeek][indexDay] == LocalDate.of(
                            today.year,
                            today.month.value,
                            today.dayOfMonth
                        ),
                        hasSessions = false,
                        day = monthData[indexWeek][indexDay].dayOfMonth,
                    )
                }
            }
        }
    }
}

internal class CalendarPreview {
    @Preview(name = "Light Mode")
    @Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun CalendarComponent() {
        CalendarTheme(darkTheme = isSystemInDarkTheme()) {
            CustomCalendar(
                today = LocalDate.of(2024, 2, 17),
                selected = LocalDate.of(2024, 2, 17),
                data = CalendarData(
                    availableSessions = listOf(
                        LocalDate.of(2024, 2, 17),
                        LocalDate.of(2024, 2, 21),
                        LocalDate.of(2024, 2, 28),
                        LocalDate.of(2024, 6, 17),
                        LocalDate.of(2024, 6, 21),
                        LocalDate.of(2024, 6, 28),
                        LocalDate.of(2025, 1, 17),
                        LocalDate.of(2025, 1, 21),
                        LocalDate.of(2025, 1, 28)
                    )
                )
            )
        }
    }
}