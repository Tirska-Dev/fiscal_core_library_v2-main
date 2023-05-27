package me.ugreek85.document.report.impl

import me.ugreek85.document.common.IDocumentTaxRate
import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.report.IReportPayForm
import me.ugreek85.document.report.IReportTotal
import me.ugreek85.utils.DocumentCopyUtil
import java.math.BigDecimal
import kotlin.properties.Delegates

class ReportTotalImpl(
) : IReportTotal {

    private var mSum by Delegates.notNull<BigDecimal>()
    private var mNumberOfReceipts by Delegates.notNull<Int>()
    private var mPawnSumIssued: BigDecimal? = null
    private var mPawnSumReceived: BigDecimal? = null
    private var mPayForms: List<IReportPayForm>? = null
    private var mTaxRates: List<IDocumentTaxRate>? = null

    override fun setSum(sum: BigDecimal) {
        mSum = sum
    }

    override fun getSum(): BigDecimal {
        return mSum
    }

    override fun setNumberOfReceipts(numberOfReceipts: Int) {
        mNumberOfReceipts = numberOfReceipts
    }

    override fun getNumberOfReceipts(): Int {
        return mNumberOfReceipts
    }

    override fun getPawnSumIssued(): BigDecimal? {
        return mPawnSumIssued
    }

    override fun setPawnSumIssued(pawnSumIssued: BigDecimal?) {
        mPawnSumIssued = pawnSumIssued
    }

    override fun getPawnSumReceived(): BigDecimal? {
        return mPawnSumReceived
    }

    override fun setPawnSumReceived(pawnSumReceived: BigDecimal?) {
        mPawnSumReceived = pawnSumReceived
    }

    override fun setPayForms(payForms: List<IReportPayForm>?) {

        mPayForms = DocumentCopyUtil.copyList(IReportPayForm::class.java, payForms) {
            ReportDocumentPayFormImpl()
        }
    }

    override fun getPayForms(): List<IReportPayForm>? {
        return mPayForms
    }

    override fun setTaxRates(taxRates: List<IDocumentTaxRate>?) {
        mTaxRates = DocumentCopyUtil.copyList(IDocumentTaxRate::class.java, taxRates) {
            DocumentTaxRateImpl()
        }
    }

    override fun getTaxRates(): List<IDocumentTaxRate>? {
        return mTaxRates
    }
}
