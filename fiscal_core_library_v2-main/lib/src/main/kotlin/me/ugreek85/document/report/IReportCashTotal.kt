package me.ugreek85.document.report

import java.math.BigDecimal

interface IReportCashTotal {

    fun setSum(sum: BigDecimal)
    fun getSum(): BigDecimal

    fun setNumberOfReceipts(numberOfReceipts: Int)
    fun getNumberOfReceipts(): Int

    fun setSumOfCommission(sumOfCommission: BigDecimal?)
    fun getSumOfCommission(): BigDecimal?
}
