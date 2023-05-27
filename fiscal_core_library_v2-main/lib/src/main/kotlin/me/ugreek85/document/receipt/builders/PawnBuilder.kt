package me.ugreek85.document.receipt.builders

import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.models.ReceiptBuilderModel
import me.ugreek85.document.receipt.impl.ReceiptDocumentImpl
import me.ugreek85.document.receipt.impl.ReceiptDocumentPayFormImpl
import me.ugreek85.document.receipt.impl.ReceiptHeaderImpl
import me.ugreek85.document.receipt.impl.ReceiptProductImpl
import java.math.BigDecimal

class PawnBuilder(
    _uid: String,
    _tin: String,
    _ipn: String?,
    _companyName: String,
    _pointName: String,
    _pointAddress: String? = null,
    _date: String,
    _time: String,
    _docNum: Long,
    _registrarLocNum: Long,
    _registrarId: Long,
    _cashierName: String? = null,
    _verCode: Int,
    _comment: String? = null,
    _fiscalNum: String? = null,
    _vehicleRegistrationNumber: String? = null,
    _operationTypeName: String? = null,
    _canceled: Boolean? = null,
    _logoUrl: String? = null,
    _fiscalNumForCancel: String? = null,
    _fiscalNumForReturn: String? = null
) : ReceiptBuilderModel(
    InternalDocumentType.PAWN,
    _uid,
    _tin,
    _ipn,
    _companyName,
    _pointName,
    _pointAddress,
    _date,
    _time,
    _docNum,
    _registrarLocNum,
    _registrarId,
    _cashierName,
    _verCode,
    _comment,
    _fiscalNum,
    _vehicleRegistrationNumber,
    _operationTypeName,
    _canceled,
    _logoUrl,
    _fiscalNumForCancel,
    _fiscalNumForReturn
) {

    override var mDocument: ReceiptDocumentImpl = ReceiptDocumentImpl()
    private val mPayForms = mutableListOf<ReceiptDocumentPayFormImpl>()
    private val mTaxRates = mutableListOf<DocumentTaxRateImpl>()
    private val mProducts = mutableListOf<ReceiptProductImpl>()

    init {
        val header = createHeader()
        mDocument.setDocumentHeader(header)
    }

    fun addTotals(
        sum: BigDecimal? = null,
        discountSum: BigDecimal? = null,
        roundingSum: BigDecimal? = null,
        sumWithoutRounding: BigDecimal? = null,
        commissionSum: BigDecimal? = null,
        pawnSumIssued: BigDecimal? = null,
        pawnSumReceived: BigDecimal? = null
    ) {
        val totals = super.createTotal(sum, commissionSum, discountSum, roundingSum, pawnSumIssued, pawnSumReceived,
            sumWithoutRounding)
        mDocument.setTotal(totals)
    }

    fun addPayRow(
        row: Int,
        name: String,
        sum: BigDecimal,
        providedSum: BigDecimal? = null,
        sumOfRemain: BigDecimal? = null,
        code: String
    ) {
        val payRow = super.createPayRow(row, name, sum, providedSum, sumOfRemain, code)
        mPayForms.add(payRow)
        mDocument.setPayForms(mPayForms)
    }

    fun addTaxRow(
        row: Int,
        type: Int,
        name: String,
        letter: String? = null,
        percent: BigDecimal,
        sign: Boolean? = null,
        turnoverSum: BigDecimal,
        sourceSum: BigDecimal? = null,
        sum: BigDecimal,
    ) {
        val taxRow = super.createTaxRateRow(row, letter, name, percent, sign, type, sum, sourceSum, turnoverSum)
        mTaxRates.add(taxRow)
        mDocument.setTaxRates(mTaxRates)
    }

    fun addProdRow(
        row: Int,
        name: String,
        description: String? = null,
        unitCode: String? = null,
        unitName: String? = null,
        amount: BigDecimal? = null,
        price: BigDecimal? = null,
        taxRateLetter: String? = null,
        cost: BigDecimal? = null,
        discountSum: BigDecimal? = null,
        code: String? = null,
        barCode: String? = null,
        comment: String? = null,
        nationalCode: String? = null,
        serviceCode: String? = null
    ) {
        val prodRow = super.createProductRow(row, name, code, comment, amount, barCode, cost, description,
            discountSum, nationalCode, price, serviceCode, taxRateLetter, unitCode, unitName)
        mProducts.add(prodRow)
        mDocument.setProducts(mProducts)
    }

    override fun createHeader(): ReceiptHeaderImpl {
        val recHeader = super.createHeader()
        recHeader.setInternalDocumentSubType(InternalDocumentSubType.SALE)
        return recHeader
    }
}