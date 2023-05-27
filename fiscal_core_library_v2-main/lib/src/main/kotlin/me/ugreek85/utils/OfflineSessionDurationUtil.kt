package me.ugreek85.utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object OfflineSessionDurationUtil {

    val MAX_MONTH_DURATION: Duration = Duration.ofHours(168)
    val MAX_SESSION_DURATION: Duration = Duration.ofHours(36)

    fun getMaxDurationForNextOfflineSession(currentSessionDurationInMinutes: Long, monthlySessionDurationInMinutes: Long): Long
    {
        return getMaxDurationForNextOfflineSession(
            Duration.ofMinutes(currentSessionDurationInMinutes),
            Duration.ofMinutes(monthlySessionDurationInMinutes)
        )
    }


    fun getMaxDurationForNextOfflineSession(currentSessionDuration: Duration, monthlySessionDuration: Duration): Long
    {
        val leftDurationOfMonth = MAX_MONTH_DURATION.minus(monthlySessionDuration)
        val leftDuration = leftDurationOfMonth.minus(currentSessionDuration)

        return Math.min(leftDuration.toMillis(), MAX_SESSION_DURATION.toMillis());
    }

    fun calculateDateTimeOfStartOfflineSession(currentSessionDurationInMinutes: Long) : Date {
        val now = LocalDateTime.now()
        val startDateTime = now.minusMinutes(currentSessionDurationInMinutes).minusMinutes(5)
        return Date(startDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
    }
}