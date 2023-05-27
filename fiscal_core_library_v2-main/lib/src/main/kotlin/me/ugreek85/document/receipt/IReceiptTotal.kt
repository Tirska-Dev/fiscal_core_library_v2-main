package me.ugreek85.document.receipt

import java.math.BigDecimal

interface IReceiptTotal {

    fun setSum(sum: BigDecimal?)
    fun getSum(): BigDecimal?

    fun setPawnSumIssued(pawnSumIssued: BigDecimal?)
    fun getPawnSumIssued(): BigDecimal?

    fun setPawnSumReceived(pawnSumReceived: BigDecimal?)
    fun getPawnSumReceived(): BigDecimal?

    fun setDiscountSum(discountSum: BigDecimal?)
    fun getDiscountSum(): BigDecimal?

    fun setSumWithoutRounding(sumWithoutRounding: BigDecimal?)
    fun getSumWithoutRounding(): BigDecimal?

    fun setRoundingSum(roundingSum: BigDecimal?)
    fun getRoundingSum(): BigDecimal?

    fun setCommissionSum(commissionSum: BigDecimal?)
    fun getCommissionSum(): BigDecimal?
}
