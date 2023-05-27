package me.ugreek85.document.receipt.impl

import me.ugreek85.document.receipt.IReceiptTotal
import java.math.BigDecimal
import kotlin.properties.Delegates

class ReceiptTotalImpl : IReceiptTotal {

    private var mSum: BigDecimal? = null
    private var mDiscountSum: BigDecimal? = null
    private var mSumWithoutRounding: BigDecimal? = null
    private var mRoundingSum: BigDecimal? = null
    private var mCommissionSum: BigDecimal? = null
    private var mPawnSumIssued: BigDecimal? = null
    private var mPawnSumReceived: BigDecimal? = null

    override fun setSum(sum: BigDecimal?) {
        mSum = sum
    }

    override fun getSum(): BigDecimal? {
        return mSum
    }

    override fun setPawnSumIssued(pawnSumIssued: BigDecimal?) {
        mPawnSumIssued = pawnSumIssued;
    }

    override fun getPawnSumIssued(): BigDecimal? {
        return mPawnSumIssued
    }

    override fun setPawnSumReceived(pawnSumReceived: BigDecimal?) {
        mPawnSumReceived = pawnSumReceived
    }

    override fun getPawnSumReceived(): BigDecimal? {
        return mPawnSumReceived
    }

    override fun setDiscountSum(discountSum: BigDecimal?) {
        mDiscountSum = discountSum
    }

    override fun getDiscountSum(): BigDecimal? {
        return mDiscountSum
    }

    override fun setSumWithoutRounding(sumWithoutRounding: BigDecimal?) {
        mSumWithoutRounding = sumWithoutRounding
    }

    override fun getSumWithoutRounding(): BigDecimal? {
        return mSumWithoutRounding
    }

    override fun setRoundingSum(roundingSum: BigDecimal?) {
        mRoundingSum = roundingSum
    }

    override fun getRoundingSum(): BigDecimal? {
        return mRoundingSum
    }

    override fun setCommissionSum(commissionSum: BigDecimal?) {
        mCommissionSum = commissionSum
    }

    override fun getCommissionSum(): BigDecimal? {
        return mCommissionSum
    }
}
