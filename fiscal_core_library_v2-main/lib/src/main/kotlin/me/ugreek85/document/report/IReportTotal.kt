package me.ugreek85.document.report

import me.ugreek85.document.common.IDocumentTaxRate
import java.math.BigDecimal

interface IReportTotal {

    fun setSum(sum: BigDecimal)
    fun getSum(): BigDecimal

    fun setNumberOfReceipts(numberOfReceipts: Int)
    fun getNumberOfReceipts(): Int

    fun getPawnSumIssued(): BigDecimal?
    fun setPawnSumIssued(pawnSumIssued: BigDecimal?)

    fun getPawnSumReceived(): BigDecimal?
    fun setPawnSumReceived(pawnSumReceived: BigDecimal?)

    fun setPayForms(payForms: List<IReportPayForm>?)
    fun getPayForms(): List<IReportPayForm>?

    fun setTaxRates(taxRates: List<IDocumentTaxRate>?)
    fun getTaxRates(): List<IDocumentTaxRate>?

}
