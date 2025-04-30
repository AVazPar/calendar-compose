package com.avazpar.calendar

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avazpar.calendar.model.TimeState
import com.avazpar.calendar.theme.CalendarTheme
import com.avazpar.calendar.theme.Spacing

private const val MAX_ITEMS_EXPAND = 3
private val TIME_PICKER_WIDTH = 333.dp
private val MAX_TIME_PICKER_HEIGHT = 212.dp

@Composable
fun TimePicker(
        modifier: Modifier = Modifier,
        selected: String,
        onClick: (String) -> Unit = {},
        list: List<String> = listOf()
) {
    val state = rememberLazyListState()
    val rows = list.chunked(MAX_ITEMS_EXPAND)

    LazyColumn(
            state = state,
            modifier = modifier
                    .width(TIME_PICKER_WIDTH)
                    .heightIn(max = MAX_TIME_PICKER_HEIGHT),
            verticalArrangement = Arrangement.spacedBy(space = Spacing.small),
            contentPadding = PaddingValues(top = Spacing.normal, bottom = Spacing.normal)
    ) {
        items(rows.size) { row ->
            LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = Spacing.small)
            ) {
                items(rows[row]){ item ->
                    Time(
                            modifier = Modifier.wrapContentWidth(),
                            time = item,
                            state = if (item == selected) {
                                TimeState.Active
                            } else {
                                TimeState.Default
                            },
                            onTimeClicked = { onClick.invoke(item) }
                    )
                }
            }
        }
    }
}

internal class TimePickerPreview {
    @Preview(name = "Light Mode")
    @Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun ShowTimePickerComponent() {
        CalendarTheme(darkTheme = isSystemInDarkTheme()) {
            TimePicker(
                    selected = "14:45pm",
                    list = listOf(
                            "14:45pm", "16:45pm", "18:45pm", "20:45pm", "22:45pm",
                    )
            )
        }
    }
}
