package com.avazpar.calendar.model

sealed class TimeState {
    data object Default : TimeState()
    data object Active : TimeState()
}