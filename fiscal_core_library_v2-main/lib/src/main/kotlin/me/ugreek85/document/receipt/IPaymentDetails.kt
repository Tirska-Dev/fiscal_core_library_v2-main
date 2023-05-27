package me.ugreek85.document.receipt

interface IPaymentDetails {

    fun getPaymentSystemName(): String?
    fun setPaymentSystemName(paymentSystemName: String?)

    fun getPaymentProvider(): String?
    fun setPaymentProvider(paymentProvider: String?)

    fun getTransactionId(): String?
    fun setTransactionId(transactionId: String?)

    fun getTransactionDateTime(): String?
    fun setTransactionDateTime(transactionDateTime: String?)

    fun getReceiptNumber(): String?
    fun setReceiptNumber(receiptNumber: String?)

    fun getDeviceId(): String?
    fun setDeviceId(deviceId: String?)

    fun getPersonalAccountNumber(): String?
    fun setPersonalAccountNumber(personalAccountNumber: String?)

    fun getAuthCode(): String?
    fun setAuthCode(authCode: String?)
}