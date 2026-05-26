package com.avazpar.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.avazpar.calendar.model.CalendarData
import com.avazpar.calendar.theme.CalendarPalette
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing
import java.time.LocalDate

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarTheme(
                palette = CalendarPalette.BLUE
            ) {
                val initialSelectedDate = availableSessions.first()
                var selectedDate by remember { mutableStateOf(initialSelectedDate) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(Spacing.normal),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomCalendar(
                            today = LocalDate.now(),
                            selected = selectedDate,
                            data = CalendarData(
                                availableSessions = availableSessions
                            ),
                            onClick = { selectedDate = it }
                        )
                    }
                }
            }
        }
    }

    private val availableSessions = listOf(
        LocalDate.of(2025, 2, 17),
        LocalDate.of(2025, 2, 21),
        LocalDate.of(2025, 2, 28),
        LocalDate.of(2025, 6, 17),
        LocalDate.of(2025, 6, 21),
        LocalDate.of(2025, 6, 28),
        LocalDate.of(2026, 1, 17),
        LocalDate.of(2026, 1, 21),
        LocalDate.of(2026, 1, 28)
    )
}
