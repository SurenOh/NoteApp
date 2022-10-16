package com.example.noteapp.util

import java.text.SimpleDateFormat

object DateUtil {

    val defaultDateFormat = SimpleDateFormat("dd.MM.yyyy")

    // It's not user friendly to use this time format( hh:mm ), you can't distinguish 08:00 from 20:00, so I use HH:mm
    val todayDateFormat = SimpleDateFormat("HH:mm")
}
