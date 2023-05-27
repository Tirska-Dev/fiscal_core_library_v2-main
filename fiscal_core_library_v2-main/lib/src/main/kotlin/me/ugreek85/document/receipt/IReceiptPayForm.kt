package me.ugreek85.document.receipt

import me.ugreek85.document.common.IDocumentPayForm
import java.math.BigDecimal

interface IReceiptPayForm : IDocumentPayForm {

    fun setSum(sum: BigDecimal?)
    fun getSum(): BigDecimal?

    fun setProvidedSum(providedSum: BigDecimal?)
    fun getProvidedSum(): BigDecimal?

    fun setSumOfRemain(sumOfRemain: BigDecimal?)
    fun getSumOfRemain(): BigDecimal?

    fun getPaymentDetails(): IPaymentDetails?
    fun setPaymentDetails(paymentDetails: IPaymentDetails?)
}
