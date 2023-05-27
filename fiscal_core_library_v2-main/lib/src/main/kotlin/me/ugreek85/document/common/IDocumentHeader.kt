package me.ugreek85.document.common

import me.ugreek85.utils.DateTimeUtil
import me.ugreek85.utils.DocumentDateTimeUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


interface IDocumentHeader {

    fun getUid(): String
    fun setUid(uid: String)

    // TIN
    fun setSellerId(sellerId: String)
    fun getSellerId(): String

    // IPN
    fun setVatNumber(vatNumber: String?)
    fun getVatNumber(): String?

    fun setCompanyName(companyName: String)
    fun getCompanyName(): String

    fun setPointName(pointName: String)
    fun getPointName(): String

    fun setPointAddress(pointAddress: String?)
    fun getPointAddress(): String?

    fun setDate(date: String)
    fun getDate(): String

    fun setTime(time: String)
    fun getTime(): String

    fun setDocumentNumber(documentNumber: Long)
    fun getDocumentNumber(): Long

    fun setRegistrarLocalNumber(registrarLocalNumber: Long)
    fun getRegistrarLocalNumber(): Long

    fun setRegistrarId(registrarId: Long)
    fun getRegistrarId(): Long

    fun setCashierName(cashierName: String?)
    fun getCashierName(): String?

    fun setVersion(version: Int)
    fun getVersion(): Int

    fun setFiscalNumber(fiscalNumber: String?)
    fun getFiscalNumber(): String?

    fun setOffline(offline: Boolean?)
    fun isOffline(): Boolean?

    fun setPreviousDocumentHash(previousDocumentHash: String?)
    fun getPreviousDocumentHash(): String?

    fun setRevoked(revoked: Boolean?)
    fun isRevoked(): Boolean?

    fun setTest(test: Boolean?)
    fun isTest(): Boolean?

    fun setCurrentDateTime(){
        DocumentDateTimeUtil.setDateTime(this, LocalDateTime.now())
    }

    fun getDateTime(): LocalDateTime {
        return DocumentDateTimeUtil.getDateTime(this)
    }

    fun getFormattedDateTime(): String {

        return DateTimeUtil.getFormattedDateTime(getDateTime())
    }

    fun getCheckNumber(): String? {

        if(isOffline() == true)
        {
            val parts = getFiscalNumber()?.split(".")
            if(parts?.size == 3) return parts[2]
        }

        return null
    }

//    fun copyTo(target: IDocumentHeader){
//        target.setUid(getUid())
//        target.setSellerId(getSellerId())
//        target.setVatNumber(getVatNumber())
//        target.setCompanyName(getCompanyName())
//        target.setPointName(getPointName())
//        target.setPointAddress(getPointAddress())
//        target.setDate(getDate())
//        target.setTime(getTime())
//    }
}
