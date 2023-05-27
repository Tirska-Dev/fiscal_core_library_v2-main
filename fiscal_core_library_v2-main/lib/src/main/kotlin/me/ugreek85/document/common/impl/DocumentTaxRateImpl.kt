package me.ugreek85.document.common.impl

import me.ugreek85.common.impl.TaxRateImpl
import me.ugreek85.document.common.IDocumentTaxRate
import java.math.BigDecimal
import kotlin.properties.Delegates

class DocumentTaxRateImpl : IDocumentTaxRate, TaxRateImpl() {

    private var mRow: Int = 1
    private var mTurnoverSum by Delegates.notNull<BigDecimal>()
    private var mSourceSum: BigDecimal? = null
    private var mSum by Delegates.notNull<BigDecimal>()

    override fun setRow(row: Int){
        mRow = row
    }

    override fun getRow() = mRow

    override fun setTurnoverSum(turnoverSum: BigDecimal) {
        mTurnoverSum = turnoverSum
    }

    override fun getTurnoverSum(): BigDecimal {
        return mTurnoverSum
    }

    override fun setSourceSum(sourceSum: BigDecimal?) {
        mSourceSum = sourceSum
    }

    override fun getSourceSum(): BigDecimal? {
        return mSourceSum
    }

    override fun setSum(sum: BigDecimal) {
        mSum = sum
    }

    override fun getSum(): BigDecimal {
        return mSum
    }
}
