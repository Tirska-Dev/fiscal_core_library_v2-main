package me.ugreek85.view.xml.impl.types

import java.math.BigDecimal
import java.math.RoundingMode

open class Decimal(
    private var precision: Int,
    private var value: BigDecimal
) {

    private val ROUNDING_MODE = RoundingMode.HALF_UP
    constructor(precision: Int): this(precision, BigDecimal.ZERO)

    fun getValue() = value

    fun setValue(value: BigDecimal){
        this.value = value.setScale(precision, ROUNDING_MODE)
    }
    fun setValue(value: Double){
        this.value = BigDecimal.valueOf(value).setScale(precision, ROUNDING_MODE)
    }

    fun getFormattedValue(): String
    {
        return value.setScale(precision, ROUNDING_MODE).toString()
    }

    override fun toString(): String
    {
        return getFormattedValue()
    }
}
