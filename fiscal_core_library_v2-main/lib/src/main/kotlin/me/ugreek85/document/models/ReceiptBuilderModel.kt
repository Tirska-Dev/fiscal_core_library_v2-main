package me.ugreek85.document.models

import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.receipt.impl.*
import me.ugreek85.utils.CheckNumberUtil
import me.ugreek85.utils.DateTimeUtil
import java.math.BigDecimal
import java.util.*

open class ReceiptBuilderModel(
    private val type: InternalDocumentType,
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
    private val comment: String? = null,
    private val fiscalNum: String? = null,
    private val vehicleRegistrationNumber: String? = null,
    private val operationTypeName: String? = null,
    private val canceled: Boolean? = null,
    private val logoUrl: String? = null,
    private val fiscalNumForCancel: String? = null,
    private val fiscalNumForReturn: String? = null
) {

    protected open lateinit var mDocument: ReceiptDocumentImpl

    protected open fun createHeader() : ReceiptHeaderImpl {
        val recHeader = ReceiptHeaderImpl()
        recHeader.setInternalDocumentType(this.type)
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
        recHeader.setComment(this.comment)
        recHeader.setFiscalNumber(this.fiscalNum)
        recHeader.setVehicleRegistrationNumber(this.vehicleRegistrationNumber)
        recHeader.setOperationTypeName(this.operationTypeName)
        recHeader.setCanceled(this.canceled)
        recHeader.setLogoUrl(this.logoUrl)
        recHeader.setFiscalNumberForCancel(this.fiscalNumForCancel)
        recHeader.setFiscalNumberForReturn(this.fiscalNumForReturn)
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

    fun getDocument(): ReceiptDocumentImpl {
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

    protected open fun createTotal(
        sum: BigDecimal?,
        commissionSum: BigDecimal?,
        discountSum: BigDecimal?,
        roundingSum: BigDecimal?,
        pawnSumIssued: BigDecimal?,
        pawnSumReceived: BigDecimal?,
        sumWithoutRounding: BigDecimal?
    ) : ReceiptTotalImpl {
        val recTotal = ReceiptTotalImpl()
        recTotal.setSum(sum)
        recTotal.setCommissionSum(commissionSum)
        recTotal.setDiscountSum(discountSum)
        recTotal.setRoundingSum(roundingSum)
        recTotal.setPawnSumIssued(pawnSumIssued)
        recTotal.setPawnSumReceived(pawnSumReceived)
        recTotal.setSumWithoutRounding(sumWithoutRounding)
        return recTotal
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
        providedSum: BigDecimal?,
        sumOfRemain: BigDecimal?,
        code: String
    ) : ReceiptDocumentPayFormImpl {
        val payForm = ReceiptDocumentPayFormImpl()
        payForm.setRow(row)
        payForm.setName(name)
        payForm.setSum(sum)
        payForm.setProvidedSum(providedSum)
        payForm.setSumOfRemain(sumOfRemain)
        payForm.setCode(code)
        return payForm
    }

    protected open fun createProductRow(
        row: Int,
        name: String,
        code: String?,
        comment: String?,
        amount: BigDecimal?,
        barCode: String?,
        cost: BigDecimal?,
        description: String?,
        discountSum: BigDecimal?,
        nationalCode: String?,
        price: BigDecimal?,
        serviceCode: String?,
        taxRateLetter: String?,
        unitCode: String?,
        unitName: String?
    ) : ReceiptProductImpl {
        val payForm = ReceiptProductImpl()
        payForm.setRow(row)
        payForm.setName(name)
        payForm.setCode(code)
        payForm.setComment(comment)
        payForm.setAmount(amount)
        payForm.setBarCode(barCode)
        payForm.setCost(cost)
        payForm.setDescription(description)
        payForm.setDiscountSum(discountSum)
        payForm.setNationalCode(nationalCode)
        payForm.setPrice(price)
        payForm.setServiceCode(serviceCode)
        payForm.setTaxRateLetter(taxRateLetter)
        payForm.setUnitCode(unitCode)
        payForm.setUnitName(unitName)
        return payForm
    }
}
