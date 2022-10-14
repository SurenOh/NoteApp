package com.example.noteapp.util

import java.text.SimpleDateFormat

object DateUtil {

    val defaultDateFormat = SimpleDateFormat("dd.MM.yyyy")

    // it's not user friendly use hh:mm
    val todayDateFormat = SimpleDateFormat("HH:mm")
}
