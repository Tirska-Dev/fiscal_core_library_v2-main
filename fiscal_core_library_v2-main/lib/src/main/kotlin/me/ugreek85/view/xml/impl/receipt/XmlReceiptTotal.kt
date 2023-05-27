package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.receipt.IReceiptTotal
import me.ugreek85.view.xml.impl.types.Decimal2
import me.ugreek85.view.xml.impl.utils.DecimalUtil
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order
import java.math.BigDecimal

@Order(elements = ["SUM","PWNSUMISSUED","PWNSUMRECEIVED","RNDSUM","NORNDSUM"/*,"NOTAXSUM"*/,"COMMISSION"/*,"USAGETYPE","SUBCHECK","DISCOUNTTYPE","DISCOUNTPERCENT"*/,"DISCOUNTSUM"])
class XmlReceiptTotal : IReceiptTotal {

    @field:Element(name="SUM")
    private var mSum: Decimal2? = null

    @field:Element(name="PWNSUMISSUED", required = false)
    var mPawnSumIssued: Decimal2? = null

    @field:Element(name="PWNSUMRECEIVED", required = false)
    var mPawnSumReceived: Decimal2? = null

    @field:Element(name="RNDSUM", required = false)
    private var mRoundingSum: Decimal2? = null

    @field:Element(name="NORNDSUM", required = false)
    private var mSumWithoutRounding: Decimal2? = null

    @field:Element(name = "COMMISSION", required = false)
    var mCommissionSum: Decimal2? = null

    @field:Element(name="DISCOUNTSUM", required = false)
    private var mDiscountSum: Decimal2? = null


    override fun setSum(sum: BigDecimal?){
        mSum = DecimalUtil.create(sum)
    }

    override fun getSum(): BigDecimal? {
        return DecimalUtil.getValue(mSum)
    }

    override fun setPawnSumIssued(pawnSumIssued: BigDecimal?) {
        mPawnSumIssued = DecimalUtil.create(pawnSumIssued)
    }

    override fun getPawnSumIssued(): BigDecimal? {
        return DecimalUtil.getValue(mPawnSumIssued)
    }

    override fun setPawnSumReceived(pawnSumReceived: BigDecimal?) {
        mPawnSumReceived = DecimalUtil.create(pawnSumReceived)
    }

    override fun getPawnSumReceived(): BigDecimal? {
        return DecimalUtil.getValue(mPawnSumReceived)
    }

    override fun setDiscountSum(discountSum: BigDecimal?) {
        mDiscountSum = DecimalUtil.create(discountSum)
    }

    override fun getDiscountSum(): BigDecimal? {
        return DecimalUtil.getValue(mDiscountSum)
    }

    override fun setSumWithoutRounding(sumWithoutRounding: BigDecimal?) {
        mSumWithoutRounding = DecimalUtil.create(sumWithoutRounding)
    }

    override fun getSumWithoutRounding(): BigDecimal? {
        return DecimalUtil.getValue(mSumWithoutRounding)
    }

    override fun setRoundingSum(roundingSum: BigDecimal?) {
        mRoundingSum = DecimalUtil.create(roundingSum)
    }

    override fun getRoundingSum(): BigDecimal? {
        return DecimalUtil.getValue(mRoundingSum)
    }

    override fun setCommissionSum(commissionSum: BigDecimal?) {
        mCommissionSum = DecimalUtil.create(commissionSum)
    }

    override fun getCommissionSum(): BigDecimal? {
        return DecimalUtil.getValue(mCommissionSum)
    }
}
