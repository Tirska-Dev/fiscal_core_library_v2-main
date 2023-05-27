package me.ugreek85.document.models

import me.ugreek85.document.common.impl.DocumentHeaderImpl
import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.receipt.impl.ReceiptDocumentPayFormImpl
import me.ugreek85.document.report.impl.ReportDocumentImpl
import me.ugreek85.document.report.impl.ReportDocumentPayFormImpl
import me.ugreek85.utils.CheckNumberUtil
import me.ugreek85.utils.DateTimeUtil
import java.math.BigDecimal

open class ReportBuilderModel(
    private val uid: String,
    private val tin: String,
    private val ipn: String? = null,
    private val companyName: String,
    private val pointName: String,
    private val pointAddress: String? = null,
    private val date: String,
    private val time: String,
    private val docNum: Long,
    private val registrarLocNum: Long,
    private val registrarId: Long,
    private val cashierName: String? = null,
    private val verCode: Int,
    private val fiscalNum: String? = null
) {

    protected open lateinit var mDocument: ReportDocumentImpl

    protected open fun createHeader() : DocumentHeaderImpl {
        val recHeader = DocumentHeaderImpl()
        recHeader.setUid(this.uid)
        recHeader.setSellerId(this.tin)
        recHeader.setVatNumber(this.ipn)
        recHeader.setCompanyName(this.companyName)
        recHeader.setPointName(this.pointName)
        recHeader.setPointAddress(this.pointAddress)
        recHeader.setDate(this.date)
        recHeader.setTime(this.time)
        recHeader.setDocumentNumber(this.docNum)
        recHeader.setRegistrarLocalNumber(this.registrarLocNum)
        recHeader.setRegistrarId(this.registrarId)
        recHeader.setCashierName(this.cashierName)
        recHeader.setVersion(this.verCode)
        recHeader.setFiscalNumber(this.fiscalNum)
        return recHeader
    }

    private fun getOfflineFiscalNum(
        offlineSessionId: String,
        nextOfflineLocalNumber: Int,
        offlineSeed: String,
        totalSum: Double?,
        prevDocHash: String?): String {
        val num = CheckNumberUtil.createCheckNumber(
            offlineSeed,
            DateTimeUtil.dateFromString(this.time + this.date),
            this.docNum,
            this.registrarId,
            this.registrarLocNum,
            totalSum,
            prevDocHash
        )
        return "${offlineSessionId}.${nextOfflineLocalNumber}.$num"
    }

    fun getDocument(): ReportDocumentImpl {
        return mDocument
    }

    fun setOffline(
        offlineSessionId: String,
        nextOfflineLocalNumber: Int,
        offlineSeed: String,
        totalSum: Double? = null,
        prevDocHash: String? = null) {
        val fiscNum = getOfflineFiscalNum(
            offlineSessionId,
            nextOfflineLocalNumber,
            offlineSeed,
            totalSum,
            prevDocHash
        )
        mDocument.getDocumentHeader().setOffline(true)
        mDocument.getDocumentHeader().setFiscalNumber(fiscNum)
        mDocument.getDocumentHeader().setPreviousDocumentHash(prevDocHash)
    }

    protected open fun createTaxRateRow(
        row: Int,
        letter: String?,
        name: String,
        percent: BigDecimal,
        sign: Boolean?,
        type: Int,
        sum: BigDecimal,
        sourceSum: BigDecimal?,
        turnoverSum: BigDecimal
    ) : DocumentTaxRateImpl {
        val taxRate = DocumentTaxRateImpl()
        taxRate.setRow(row)
        taxRate.setLetter(letter)
        taxRate.setName(name)
        taxRate.setPercent(percent)
        taxRate.setSign(sign)
        taxRate.setType(type)
        taxRate.setSum(sum)
        taxRate.setSourceSum(sourceSum)
        taxRate.setTurnoverSum(turnoverSum)
        return taxRate
    }

    protected open fun createPayRow(
        row: Int,
        name: String,
        sum: BigDecimal,
        code: String
    ) : ReportDocumentPayFormImpl {
        val payForm = ReportDocumentPayFormImpl()
        payForm.setRow(row)
        payForm.setName(name)
        payForm.setSum(sum)
        payForm.setCode(code)
        return payForm
    }
}
