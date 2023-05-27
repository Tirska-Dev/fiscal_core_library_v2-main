package me.ugreek85.document.report.impl

import me.ugreek85.document.report.IReportCashTotal
import java.math.BigDecimal
import kotlin.properties.Delegates

class ReportCashTotalImpl : IReportCashTotal {

    private var mSum by Delegates.notNull<BigDecimal>()
    private var mNumberOfReceipts by Delegates.notNull<Int>()
    private var mSumOfCommission: BigDecimal? = null

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

    override fun setSumOfCommission(sumOfCommission: BigDecimal?) {
        mSumOfCommission = sumOfCommission
    }

    override fun getSumOfCommission(): BigDecimal? {
        return mSumOfCommission
    }

}
