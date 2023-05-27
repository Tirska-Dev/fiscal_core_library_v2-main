package me.ugreek85.utils

import me.ugreek85.view.xml.impl.types.Decimal
import java.nio.charset.Charset
import java.util.*

object CheckNumberUtil {

    fun getCheckNumber(crc32: Int): Int {
        var crc32 = crc32
        if (crc32 == 0) crc32 = 1
        var checkNumber = Integer.toUnsignedString(crc32)
        if (checkNumber.length >= 4) {
            checkNumber = checkNumber.substring(checkNumber.length - 4)
        }

        val value = checkNumber.toInt()
        return value
    }

    fun createCheckNumber(
        offlineSeed: String,
        dateTime: Date,
        localDocumentNumber: Long,
        registrarId: Long,
        cashDeskLocalNumber: Long,
        totalSum: Double?,
        prevDocHash: String?
    ): Int {

        var line = java.lang.String.join(
            ",",
            offlineSeed,
            DateTimeUtil.getStringDate(dateTime),
            DateTimeUtil.getStringTime(dateTime),
            localDocumentNumber.toString(),
            registrarId.toString(),
            cashDeskLocalNumber.toString()
        )

        if (totalSum != null) {
            val num = Decimal(2)
            num.setValue(totalSum)
            line = java.lang.String.join(",", line, num.toString())
        }
        if (prevDocHash != null && prevDocHash.isNotEmpty()) line = java.lang.String.join(",", line, prevDocHash)

        val crc32 = Crc32.get(line.toByteArray(Charset.forName("windows-1251")))
        return getCheckNumber(crc32)
    }
}