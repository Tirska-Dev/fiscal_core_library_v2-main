package me.ugreek85.document.common.impl

import me.ugreek85.document.common.IDocumentHeader

open class DocumentHeaderImpl : IDocumentHeader {

    // output xml tag ("UID")
    private var mUid: String = ""

    // output xml tag ("TIN")
    private var mSellerId: String = ""

    // output xml tag ("IPN")
    private var mVatNumber: String? = null

    // output xml tag ("ORGNM")
    private var mCompanyName: String = ""

    // output xml tag ("POINTNM")
    private var mPointName: String = ""

    // output xml tag ("POINTADDR")
    private var mPointAddress: String? = null

    // output xml tag ("ORDERDATE")
    private var mDate: String = ""

    // output xml tag ("ORDERTIME")
    private var mTime: String = ""

    // output xml tag ("ORDERNUM")
    private var mDocumentNumber: Long = 0L

    // output xml tag ("CASHDESKNUM")
    private var mRegistrarLocalNumber: Long = 0L

    // output xml tag ("CASHREGISTERNUM")
    private var mRegistrarId: Long = 0L

    // output xml tag ("CASHIER")
    private var mCashierName: String? = null

    // output xml tag ("VER")
    private var mVersion: Int = 0

    // output xml tag ("ORDERTAXNUM")
    private var mFiscalNumber: String? = null

    // output xml tag ("REVOKED")
    private var mRevoked: Boolean? = null

    // output xml tag ("OFFLINE")
    private var mOffline: Boolean? = null

    // output xml tag ("PREVDOCHASH")
    private var mPreviousDocumentHash: String? = null

    // output xml tag ("TEST")
    private var mTest: Boolean? = null

    override fun getUid(): String {
        return mUid
    }

    override fun setUid(uid: String) {
        mUid = uid
    }

    override fun setSellerId(sellerId: String) {
        mSellerId = sellerId
    }

    override fun getSellerId(): String {
        return mSellerId
    }

    override fun setVatNumber(vatNumber: String?) {
        mVatNumber = vatNumber
    }

    override fun getVatNumber(): String? {
        return mVatNumber
    }

    override fun setCompanyName(companyName: String) {
        mCompanyName = companyName
    }

    override fun getCompanyName(): String {
        return mCompanyName
    }

    override fun setPointName(pointName: String) {
        mPointName = pointName
    }

    override fun getPointName(): String {
        return mPointName
    }

    override fun setPointAddress(pointAddress: String?) {
        mPointAddress = pointAddress
    }

    override fun getPointAddress(): String? {
        return mPointAddress
    }


    override fun setDate(date: String) {
        mDate = date
    }

    override fun getDate(): String {
        return mDate
    }

    override fun setTime(time: String) {
        mTime = time
    }

    override fun getTime(): String {
        return mTime
    }

    override fun setDocumentNumber(documentNumber: Long) {
        mDocumentNumber = documentNumber
    }

    override fun getDocumentNumber(): Long {
        return mDocumentNumber
    }

    override fun setRegistrarLocalNumber(registrarLocalNumber: Long) {
        mRegistrarLocalNumber = registrarLocalNumber
    }

    override fun getRegistrarLocalNumber(): Long {
        return mRegistrarLocalNumber
    }

    override fun setRegistrarId(registrarId: Long) {
        mRegistrarId = registrarId
    }

    override fun getRegistrarId(): Long {
        return mRegistrarId
    }

    override fun setCashierName(cashierName: String?) {
        mCashierName = cashierName
    }

    override fun getCashierName(): String? {
        return mCashierName
    }


    override fun setVersion(version: Int) {
        mVersion = version
    }

    override fun getVersion(): Int {
        return mVersion
    }

    override fun setFiscalNumber(fiscalNumber: String?) {
        mFiscalNumber = fiscalNumber
    }

    override fun getFiscalNumber(): String? {
        return mFiscalNumber
    }

    override fun setRevoked(revoked: Boolean?) {
        mRevoked = revoked
    }

    override fun isRevoked(): Boolean? {
        return mRevoked
    }

    override fun setTest(test: Boolean?) {
        mTest = test
    }

    override fun isTest(): Boolean? {
        return mTest
    }

    override fun setOffline(offline: Boolean?) {
        mOffline = offline
    }

    override fun isOffline(): Boolean? {
        return mOffline
    }

    override fun setPreviousDocumentHash(previousDocumentHash: String?) {
        mPreviousDocumentHash = previousDocumentHash
    }

    override fun getPreviousDocumentHash(): String? {
        return mPreviousDocumentHash
    }
}
