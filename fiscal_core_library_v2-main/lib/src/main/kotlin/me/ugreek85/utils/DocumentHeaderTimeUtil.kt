package me.ugreek85.utils

import java.text.SimpleDateFormat
import java.util.*

object DocumentHeaderTimeUtil {

    private val dateFormat = SimpleDateFormat("ddMMyyyy")
    private val timeFormat = SimpleDateFormat("HHmmss")

    fun getDateString(date: Date): String
    {
        return dateFormat.format(date);
    }

    fun getTimeString(date: Date): String
    {
        return timeFormat.format(date)
    }
}