package com.example.todo

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.ignoreDate() {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Calendar.ignoreTime() {
    set(Calendar.DAY_OF_MONTH, 0)
    set(Calendar.MONTH, 0)
    set(Calendar.YEAR, 0)
}

fun Calendar.setCurrentDate(year: Int, month: Int, day: Int) {
    set(Calendar.DAY_OF_MONTH, day)
    set(Calendar.MONTH, month)
    set(Calendar.YEAR, year)
}

fun Calendar.dateFormatOnly(): String {
    val dateFormat = SimpleDateFormat("yyyy/MMM/dd ", Locale.ENGLISH)
    return dateFormat.format(time)
}

fun Calendar.setCurrentTime(hour: Int, minute: Int) {
    set(Calendar.MINUTE, minute)
    set(Calendar.HOUR_OF_DAY, hour)
}

fun Calendar.timeFormatOnly(): String {
    val dateFormat = SimpleDateFormat("HH:mm a ", Locale.ENGLISH)
    return dateFormat.format(time)
}

