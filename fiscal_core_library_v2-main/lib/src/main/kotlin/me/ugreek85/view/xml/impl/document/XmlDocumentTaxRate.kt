package me.ugreek85.view.xml.impl.document

import me.ugreek85.document.common.IDocumentTaxRate
import me.ugreek85.view.xml.impl.types.Decimal2
import me.ugreek85.view.xml.impl.utils.DecimalUtil
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order
import org.simpleframework.xml.Root
import java.math.BigDecimal

@Root(name = "ROW")
@Order(elements = ["TYPE","NAME","LETTER","PRC","SIGN","TURNOVER"/*,"TURNOVERDISCOUNT"*/,"SOURCESUM","SUM"])
class XmlDocumentTaxRate : IDocumentTaxRate, XmlBaseRow() {

    @field:Element(name="TYPE")
    private var mType: Int = 0

    @field:Element(name="NAME")
    private var mName: String = ""

    @field:Element(name="LETTER", required = false)
    private var mLetter: String? = null

    @field:Element(name="PRC")
    private var mPercent: Decimal2 = Decimal2()

    @field:Element(name="SIGN", required = false)
    private var mSign: Boolean? = null

    @field:Element(name="TURNOVER")
    private var mTurnoverSum: Decimal2 = Decimal2()

    @field:Element(name="SOURCESUM", required = false)
    private var mSourceSum: Decimal2? = null

    @field:Element(name="SUM")
    private var mSum: Decimal2 = Decimal2()

    override fun setTurnoverSum(turnoverSum: BigDecimal) {
        mTurnoverSum.setValue(turnoverSum)
    }

    override fun getTurnoverSum(): BigDecimal {
        return mTurnoverSum.getValue()
    }

    override fun setSourceSum(sourceSum: BigDecimal?) {
        mSourceSum = DecimalUtil.create(sourceSum)
    }

    override fun getSourceSum(): BigDecimal? {
        return DecimalUtil.getValue(mSourceSum)
    }

    override fun setSum(sum: BigDecimal) {
        mSum.setValue(sum)
    }

    override fun getSum(): BigDecimal {
        return mSum.getValue()
    }

    override fun setLetter(letter: String?) {
        mLetter = letter
    }

    override fun getLetter(): String? {
        return mLetter
    }

    override fun setType(type: Int) {
        mType = type
    }

    override fun getType(): Int {
        return mType
    }

    override fun setPercent(percent: BigDecimal) {
        mPercent.setValue(percent)
    }

    override fun getPercent(): BigDecimal {
        return mPercent.getValue()
    }

    override fun setName(name: String) {
        mName = name
    }

    override fun getName(): String {
        return mName
    }

    override fun setSign(sign: Boolean?) {
        mSign = sign
    }

    override fun getSign(): Boolean? {
        return mSign
    }
}
