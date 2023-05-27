package me.ugreek85.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtil {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
    private val dateTimeFormat = SimpleDateFormat("HHmmssddMMyyyy")
    private val dateFormat = SimpleDateFormat("ddMMyyyy")
    private val timeFormat = SimpleDateFormat("HHmmss")

    fun getFormattedDateTime(dateTime: LocalDateTime): String {

        return formatter.format(dateTime)
    }

    fun getStringDate(date: Date) : String
    {
        return dateFormat.format(date);
    }

    fun getStringTime(date: Date) : String
    {
        return timeFormat.format(date);
    }

    fun dateFromString(dateTime: String): Date
    {
        return dateTimeFormat.parse(dateTime)
    }
}
