package me.ugreek85.document.common

import java.lang.IllegalArgumentException


enum class InternalDocumentSubType(val value: String) {

    /**
     * Касовий чек (реалізація)
     */
    SALE("0"),

    /**
     * Видатковий чек (повернення)
     */
    RETURN("1"),

    /**
     * Чек операції «службове внесення»/«отримання авансу»
     */
    SERVICE_DEPOSIT("2"),

    /**
     * Чек операції «отримання підкріплення»
     */
    ADDITIONAL_DEPOSIT("3"),

    /**
     * Чек операції «службова видача»/«інкасація»
     */
    SERVICE_ISSUE("4"),

    /**
     * Чек сторнування попереднього чека
     */
    CANCEL("5");

    companion object{

        fun byInternalValue(value: String): InternalDocumentSubType
        {
            values().forEach {
                if(it.value == value) return it
            }

            throw IllegalArgumentException("No enum with internal value $value")
        }
    }
}