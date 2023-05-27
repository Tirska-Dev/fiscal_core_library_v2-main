package me.ugreek85.view.xml.impl.transforms

import me.ugreek85.view.xml.impl.types.Decimal
import org.simpleframework.xml.transform.Transform
import java.math.BigDecimal

abstract class BaseDecimalTransform<T : Decimal>(
    private val factory : () -> T
) : Transform<T> {

    override fun read(value: String): T {
        return factory().apply { setValue(BigDecimal(value)) }
    }

    override fun write(value: T): String {
        return value.getFormattedValue()
    }
}