package me.ugreek85.common

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.*


class BigDecimalTest {

    @Test
    fun isPrecisionStored() {

        var value = BigDecimal.valueOf(9.8).setScale(2)
        assertEquals(value.scale(), 2)

        value = value.setScale(3)
        assertEquals(value.scale(), 3)
    }
}
