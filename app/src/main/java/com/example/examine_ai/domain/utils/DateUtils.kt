package com.example.examine_ai.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun dateFormatter(date: Date): String {
    val pattern = "dd/MM/yyyy"
    val dateFormatada = date
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(dateFormatada)
}