package me.ugreek85.common

import java.math.BigDecimal

interface ITaxRate {

    fun setLetter(letter: String?)
    fun getLetter(): String?

    fun setType(type: Int)
    fun getType(): Int

    fun setPercent(percent: BigDecimal)
    fun getPercent(): BigDecimal

    fun setName(name: String)
    fun getName(): String

    fun setSign(sign: Boolean?)
    fun getSign(): Boolean?
}
