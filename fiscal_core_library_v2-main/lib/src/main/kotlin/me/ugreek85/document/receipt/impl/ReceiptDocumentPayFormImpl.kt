package me.ugreek85.document.receipt.impl

import me.ugreek85.document.common.impl.DocumentPayFormImpl
import me.ugreek85.document.receipt.IPaymentDetails
import me.ugreek85.document.receipt.IReceiptPayForm
import me.ugreek85.document.report.impl.ReportDocumentPayFormImpl
import me.ugreek85.utils.DocumentCopyUtil
import java.math.BigDecimal

class ReceiptDocumentPayFormImpl(
) : IReceiptPayForm, DocumentPayFormImpl(){

    private var mSum: BigDecimal? = null
    private var mProvidedSum: BigDecimal? = null
    private var mSumOfRemain: BigDecimal? = null
    private var mPaymentDetails: IPaymentDetails? = null

    override fun setSum(sum: BigDecimal?) {
        mSum = sum
    }

    override fun getSum(): BigDecimal? {
        return mSum
    }

    override fun setProvidedSum(providedSum: BigDecimal?) {
        mProvidedSum = providedSum
    }

    override fun getProvidedSum(): BigDecimal? {
        return mProvidedSum
    }

    override fun setSumOfRemain(sumOfRemain: BigDecimal?) {
        mSumOfRemain = sumOfRemain
    }

    override fun getSumOfRemain(): BigDecimal? {
        return mSumOfRemain
    }

    override fun getPaymentDetails(): IPaymentDetails? {
        return mPaymentDetails
    }

    override fun setPaymentDetails(paymentDetails: IPaymentDetails?) {
        mPaymentDetails = DocumentCopyUtil.copyObject(IPaymentDetails::class.java, paymentDetails){
            PaymentDetailsImpl()
        }
    }
}
