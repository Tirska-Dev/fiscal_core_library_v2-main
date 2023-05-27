package me.ugreek85.utils

import me.ugreek85.document.common.IDocumentHeader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DocumentDateTimeUtil {

    fun setDateTime(documentHeader: IDocumentHeader, dateTime: LocalDateTime){
        documentHeader.setDate(DateTimeFormatter.ofPattern("ddMMyyyy").format(dateTime.toLocalDate()))
        documentHeader.setTime(DateTimeFormatter.ofPattern("HHmmss").format(dateTime.toLocalTime()))
    }

    fun getDateTime(documentHeader: IDocumentHeader): LocalDateTime{

        if(documentHeader.getDate().isNotEmpty() && documentHeader.getTime().isNotEmpty())
        {
            return LocalDateTime.parse("${documentHeader.getDate()}${documentHeader.getTime()}", DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"))
        }

        throw IllegalStateException("field Date or Time is not filled")
    }
}
