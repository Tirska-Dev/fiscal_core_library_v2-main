package me.ugreek85.utils

import java.util.*

class RegisterRecord(
    fiscalDocNumber: String,
    key: String,
    value: Double
)
{
    var id: Long = 0
    var time: Date = Date()
    var fiscalDocNumber: String = fiscalDocNumber
    var storned: Boolean = false
    var key: String = key
    var value: Double = value
}