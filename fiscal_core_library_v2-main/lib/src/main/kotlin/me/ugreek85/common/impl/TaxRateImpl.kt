package me.ugreek85.common.impl

import me.ugreek85.common.ITaxRate
import java.math.BigDecimal
import kotlin.properties.Delegates

open class TaxRateImpl : ITaxRate {

    private var mLetter: String? = null
    private var mType by Delegates.notNull<Int>()
    private var mPercent by Delegates.notNull<BigDecimal>()
    private lateinit var mName: String
    private var mSign: Boolean? = null

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
        mPercent = percent
    }

    override fun getPercent(): BigDecimal {
        return mPercent
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
