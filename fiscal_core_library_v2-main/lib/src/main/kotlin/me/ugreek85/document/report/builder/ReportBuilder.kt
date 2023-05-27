package me.ugreek85.document.report.builder

import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.models.ReportBuilderModel
import me.ugreek85.document.report.impl.ReportCashTotalImpl
import me.ugreek85.document.report.impl.ReportDocumentImpl
import me.ugreek85.document.report.impl.ReportDocumentPayFormImpl
import me.ugreek85.document.report.impl.ReportTotalImpl
import java.math.BigDecimal

class ReportBuilder(
    _uid: String,
    _tin: String,
    _ipn: String? = null,
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
    _fiscalNum: String? = null
) : ReportBuilderModel(
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
    _fiscalNum
) {

    override var mDocument: ReportDocumentImpl = ReportDocumentImpl()

    private val mTotalsBySale: ReportTotalImpl = ReportTotalImpl()
    private val mTotalsByReturn: ReportTotalImpl = ReportTotalImpl()
    private val mTotalsByCash: ReportCashTotalImpl = ReportCashTotalImpl()

    private val mSalePayForms = mutableListOf<ReportDocumentPayFormImpl>()
    private val mSaleTaxRates = mutableListOf<DocumentTaxRateImpl>()

    private val mReturnPayForms = mutableListOf<ReportDocumentPayFormImpl>()
    private val mReturnTaxRates = mutableListOf<DocumentTaxRateImpl>()

    private val totalsType = mapOf(
        "Sale" to mTotalsBySale,
        "Return" to mTotalsByReturn
    )

    private val payFormsType = mapOf(
        "Sale" to mSalePayForms,
        "Return" to mReturnPayForms
    )

    private val taxRatesType = mapOf(
        "Sale" to mSaleTaxRates,
        "Return" to mReturnTaxRates
    )


    init {
        val header = super.createHeader()
        mDocument.setDocumentHeader(header)
    }

    private fun setTotalsSaleOrReturn(type: String, data: ReportTotalImpl) {
        if (type == "Sale") mDocument.setTotalsBySale(data)
        else mDocument.setTotalsByReturn(data)
    }

    fun addTotalSumsBySaleOrReturn(
        type: String,
        sum: BigDecimal,
        ordersNum: Int,
        pawnSumIssued: BigDecimal? = null,
        pawnSumReceived: BigDecimal? = null
    ) {
        totalsType[type]?.let {
            it.setSum(sum)
            it.setNumberOfReceipts(ordersNum)
            it.setPawnSumIssued(pawnSumIssued)
            it.setPawnSumReceived(pawnSumReceived)
            setTotalsSaleOrReturn(type, it)
        }
    }

    fun addTotalSumsByCash(
        sum: BigDecimal,
        ordersNum: Int,
        sumOfCommission: BigDecimal? = null
    ) {
        mTotalsByCash.setSum(sum)
        mTotalsByCash.setNumberOfReceipts(ordersNum)
        mTotalsByCash.setSumOfCommission(sumOfCommission)
        mDocument.setTotalsByCash(mTotalsByCash)
    }

    fun addPayRow(
        type: String,
        row: Int,
        name: String,
        sum: BigDecimal,
        code: String
    ) {
        val payRow = super.createPayRow(row, name, sum, code)
        payFormsType[type]?.let { list ->
            list.add(payRow)
            totalsType[type]!!.setPayForms(list)
            setTotalsSaleOrReturn(type, totalsType[type]!!)
        }
    }

    fun addTaxRow(
        type: String,
        row: Int,
        payRowType: Int,
        name: String,
        letter: String? = null,
        percent: BigDecimal,
        sign: Boolean? = null,
        turnoverSum: BigDecimal,
        sourceSum: BigDecimal? = null,
        sum: BigDecimal
    ) {
        val taxRate = super.createTaxRateRow(row, letter, name, percent, sign, payRowType, sum, sourceSum, turnoverSum)
        taxRatesType[type]?.let { list ->
            list.add(taxRate)
            totalsType[type]!!.setTaxRates(list)
            setTotalsSaleOrReturn(type, totalsType[type]!!)
        }
    }
}