package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.receipt.IPaymentDetails
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order
import org.simpleframework.xml.Root

@Root(name = "ROW")
@Order(elements = [/*"TAXNUM",*/"NAME","ACQUIREPN","ACQUIRENM","ACQUIRETRANSID","POSTRANSDATE","POSTRANSNUM","DEVICEID","EPZDETAILS","AUTHCD"/*,"SUM","COMMISSION"*/])
class XmlPaymentDetails : IPaymentDetails {

    @field:Element(name="NAME", required = false)
    private var mPaymentSystemName: String? = null

    @field:Element(name="ACQUIRENM", required = false)
    private var mPaymentProvider: String? = null

    @field:Element(name="ACQUIRETRANSID", required = false)
    private var mTransactionId: String? = null

    @field:Element(name="POSTRANSDATE", required = false)
    private var mTransactionDateTime: String? = null

    @field:Element(name="POSTRANSNUM", required = false)
    private var mReceiptNumber: String? = null

    @field:Element(name="DEVICEID", required = false)
    private var mDeviceId: String? = null

    @field:Element(name="EPZDETAILS", required = false)
    private var mPersonalAccountNumber: String? = null

    @field:Element(name="AUTHCD", required = false)
    private var mAuthCode: String? = null

    override fun getPaymentSystemName(): String? {
        return mPaymentSystemName
    }

    override fun setPaymentSystemName(paymentSystemName: String?) {
        mPaymentSystemName = paymentSystemName
    }

    override fun getPaymentProvider(): String? {
        return mPaymentProvider
    }

    override fun setPaymentProvider(paymentProvider: String?) {
        mPaymentProvider = paymentProvider
    }

    override fun getTransactionId(): String? {
        return mTransactionId
    }

    override fun setTransactionId(transactionId: String?) {
        mTransactionId = transactionId
    }

    override fun getTransactionDateTime(): String? {
        return mTransactionDateTime
    }

    override fun setTransactionDateTime(transactionDateTime: String?) {
        mTransactionDateTime = transactionDateTime
    }

    override fun getReceiptNumber(): String? {
        return mReceiptNumber
    }

    override fun setReceiptNumber(receiptNumber: String?) {
        mReceiptNumber = receiptNumber
    }

    override fun getDeviceId(): String? {
        return mDeviceId
    }

    override fun setDeviceId(deviceId: String?) {
        mDeviceId = deviceId
    }

    override fun getPersonalAccountNumber(): String? {
        return mPersonalAccountNumber
    }

    override fun setPersonalAccountNumber(personalAccountNumber: String?) {
        mPersonalAccountNumber = personalAccountNumber
    }

    override fun getAuthCode(): String? {
        return mAuthCode
    }

    override fun setAuthCode(authCode: String?) {
        mAuthCode = authCode
    }

    override fun toString(): String {
        return "XmlPaymentDetails(mPaymentSystemName=$mPaymentSystemName, mPaymentProvider=$mPaymentProvider, mTransactionId=$mTransactionId, mTransactionDateTime=$mTransactionDateTime, mReceiptNumber=$mReceiptNumber, mDeviceId=$mDeviceId, mPersonalAccountNumber=$mPersonalAccountNumber, mAuthCode=$mAuthCode)"
    }
}
