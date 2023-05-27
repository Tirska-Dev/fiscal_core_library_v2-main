package me.ugreek85.utils

import java.util.*

fun Double.roundString(precision: Int): String {
    return String.format(Locale.US, "%.${precision}f", this);
}

fun Double.roundDouble(precision: Int): Double {
    return roundString(precision).toDouble()
}