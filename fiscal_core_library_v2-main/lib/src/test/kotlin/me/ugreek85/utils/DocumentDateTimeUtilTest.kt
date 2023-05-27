package me.ugreek85.utils

import me.ugreek85.document.common.impl.DocumentHeaderImpl
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.*


internal class DocumentDateTimeUtilTest {

    @Test
    fun setDateTime() {

        val header = DocumentHeaderImpl()
        val dateTime = LocalDateTime.of(2022, Month.FEBRUARY, 8, 19, 31, 20)
        DocumentDateTimeUtil.setDateTime(header, dateTime)

        assertEquals("08022022", header.getDate())
        assertEquals("193120", header.getTime())
    }

    @Test
    fun getDateTime() {

        val header = DocumentHeaderImpl()
        header.setDate("08022022")
        header.setTime("193120")

        val dateTime = LocalDateTime.of(2022, Month.FEBRUARY, 8, 19, 31, 20)
        assertEquals(dateTime, DocumentDateTimeUtil.getDateTime(header))
    }
}
