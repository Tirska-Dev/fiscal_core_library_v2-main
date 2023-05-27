package me.ugreek85.view.xml.impl.transforms


import me.ugreek85.document.receipt.PawnMoneyDirection
import org.simpleframework.xml.transform.Transform

class PawnMoneyDirectionTransform : Transform<PawnMoneyDirection> {
    override fun read(value: String): PawnMoneyDirection {
        return PawnMoneyDirection.byInternalValue(value)
    }

    override fun write(value: PawnMoneyDirection): String {
        return value.direction.toString()
    }
}