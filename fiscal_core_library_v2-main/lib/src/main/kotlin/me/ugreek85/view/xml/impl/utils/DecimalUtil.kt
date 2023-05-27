package me.ugreek85.view.xml.impl.utils

import me.ugreek85.view.xml.impl.types.Decimal
import me.ugreek85.view.xml.impl.types.Decimal2
import java.math.BigDecimal

object DecimalUtil {

    fun getDoubleValue(decimal: Decimal?): Double?
    {
        if(decimal == null) return null
        return decimal.getValue().toDouble()
    }

    fun getValue(decimal: Decimal?): BigDecimal?
    {
        if(decimal == null) return null
        return decimal.getValue()
    }

    inline fun <reified T:Decimal> create(value: BigDecimal?): T?
    {
        if(value == null) return null
        return T::class.java.newInstance().apply { setValue(value) }
    }
}
