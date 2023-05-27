package me.ugreek85.document.receipt

import java.lang.IllegalArgumentException

enum class PawnMoneyDirection(val direction: Boolean) {

    TO_ClIENT(false),
    FROM_ClIENT(true);

    companion object{

        fun byInternalValue(value: String): PawnMoneyDirection
        {
            PawnMoneyDirection.values().forEach {
                if(it.direction == value.toBoolean()) return it
            }

            throw IllegalArgumentException("No enum with internal value $value")
        }
    }
}