package me.ugreek85.document.receipt.impl

import me.ugreek85.document.common.impl.BaseRowImpl
import me.ugreek85.document.receipt.IExciseLabel
import me.ugreek85.document.receipt.IReceiptProduct
import me.ugreek85.document.receipt.PawnMoneyDirection
import me.ugreek85.utils.DocumentCopyUtil
import java.math.BigDecimal

class ReceiptProductImpl(
) : IReceiptProduct, BaseRowImpl() {

    private var mCode: String? = null
    private var mBarCode: String? = null
    private var mNationalCode: String? = null
    private var mServiceCode: String? = null
    private var mName: String? = null
    private var mDescription: String? = null
    private var mUnitCode: String? = null
    private var mUnitName: String? = null
    private var mAmount: BigDecimal? = null
    private var mPrice: BigDecimal? = null
    private var mCost: BigDecimal? = null
    private var mTaxRateLetters: String? = null
    private var mComment: String? = null
    private var mPawnMoneyDirection: PawnMoneyDirection? = null
    private var mDiscountSum: BigDecimal? = null
    private var mExciseLabels: List<IExciseLabel>? = null


    override fun setCode(code: String?) {
        mCode = code
    }

    override fun getCode(): String? {
        return mCode
    }

    override fun setBarCode(barCode: String?) {
        mBarCode = barCode
    }

    override fun getBarCode(): String? {
        return mBarCode
    }

    override fun setNationalCode(nationalCode: String?) {
        if(nationalCode != null)
        {
            mNationalCode = nationalCode
            mServiceCode = null
        }
    }

    override fun getNationalCode(): String? {
        return mNationalCode
    }

    override fun setServiceCode(serviceCode: String?) {
        if(serviceCode != null){
            mServiceCode = serviceCode
            mNationalCode = null
        }
    }

    override fun getServiceCode(): String? {
        return mServiceCode
    }

    override fun setName(name: String?) {
        mName = name
    }

    override fun getName(): String? {
        return mName
    }

    override fun setDescription(description: String?) {
        mDescription = description
    }

    override fun getDescription(): String? {
        return mDescription
    }

    override fun setUnitCode(unitCode: String?) {
        mUnitCode = unitCode
    }

    override fun getUnitCode(): String? {
        return mUnitCode
    }

    override fun setUnitName(unitName: String?) {
        mUnitName = unitName
    }

    override fun getUnitName(): String? {
        return mUnitName
    }

    override fun setAmount(amount: BigDecimal?) {
        mAmount = amount
    }

    override fun getAmount(): BigDecimal? {
        return mAmount
    }

    override fun setPrice(price: BigDecimal?) {
        mPrice = price
    }

    override fun getPrice(): BigDecimal? {
        return mPrice
    }

    override fun setTaxRateLetter(taxRateLetter: String?) {
        mTaxRateLetters = taxRateLetter
    }

    override fun getTaxRateLetter(): String? {
        return mTaxRateLetters
    }

    override fun setCost(cost: BigDecimal?) {
        mCost = cost
    }

    override fun getCost(): BigDecimal? {
        return mCost
    }

    override fun setComment(comment: String?) {
        mComment = comment
    }

    override fun getComment(): String? {
        return mComment
    }

    override fun getPawnMoneyDirection(): PawnMoneyDirection? {
        return mPawnMoneyDirection
    }

    override fun setPawnMoneyDirection(pawnMoneyDirection: PawnMoneyDirection?) {
        mPawnMoneyDirection = pawnMoneyDirection
    }

    override fun setDiscountSum(discountSum: BigDecimal?) {
        mDiscountSum = discountSum
    }

    override fun getDiscountSum(): BigDecimal? {
        return mDiscountSum
    }

    override fun setExciseLabels(exciseLabels: List<IExciseLabel>?) {
        mExciseLabels = DocumentCopyUtil.copyList(IExciseLabel::class.java, exciseLabels){
            ExciseLabelImpl()
        }
    }

    override fun getExciseLabels(): List<IExciseLabel>? {
        return mExciseLabels
    }
}
