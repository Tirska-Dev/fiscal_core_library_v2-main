package me.ugreek85.view.xml.impl.document

import me.ugreek85.document.common.IDocumentHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order


@Order(elements = ["UID","TIN","IPN","ORGNM","POINTNM","POINTADDR","ORDERDATE","ORDERTIME","ORDERNUM","CASHDESKNUM","CASHREGISTERNUM","CASHIER","VER","ORDERTAXNUM","OFFLINE","PREVDOCHASH","REVOKED","TESTING"])
open class XmlDocumentHeader : IDocumentHeader {

    @field:Element(name="UID")
    private var mUid: String = ""

    @field:Element(name="TIN")
    private var mSellerId: String = ""

    @field:Element(name="IPN", required = false)
    private var mVatNumber: String? = null

    @field:Element(name="ORGNM")
    private var mCompanyName: String = ""

    @field:Element(name="POINTNM")
    private var mPointName: String = ""

    @field:Element(name="POINTADDR", required = false)
    private var mPointAddress: String? = null

    @field:Element(name="ORDERDATE")
    private var mDate: String = ""

    @field:Element(name="ORDERTIME")
    private var mTime: String = ""

    @field:Element(name="ORDERNUM")
    private var mDocumentNumber: Long = 0L

    @field:Element(name="CASHDESKNUM")
    private var mRegistrarLocalNumber: Long = 0L

    @field:Element(name="CASHREGISTERNUM")
    private var mRegistrarId: Long = 0L

    @field:Element(name="CASHIER", required = false)
    private var mCashierName: String? = null

    @field:Element(name="VER")
    private var mVersion: Int = 0

    @field:Element(name="ORDERTAXNUM", required = false)
    private var mFiscalNumber: String? = null

    @field:Element(name="REVOKED", required = false)
    private var mRevoked: Boolean? = null

    @field:Element(name="OFFLINE", required = false)
    private var mOffline: Boolean? = null

    @field:Element(name="PREVDOCHASH", required = false)
    private var mPreviousDocumentHash: String? = null

    @field:Element(name="TESTING", required = false)
    private var mTest: Boolean? = null

    override fun getUid(): String {
        return mUid
    }

    override fun setUid(uid: String) {
        this.mUid = uid
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
