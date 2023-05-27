package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.receipt.IReceiptHeader
import me.ugreek85.view.xml.impl.document.XmlDocumentHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order

@Order(elements = ["DOCTYPE","DOCSUBTYPE","UID","TIN","IPN","ORGNM","POINTNM","POINTADDR","ORDERDATE","ORDERTIME","ORDERNUM","CASHDESKNUM","CASHREGISTERNUM","ORDERRETNUM","ORDERSTORNUM","OPERTYPENM","VEHICLERN",
    "REVOKELASTONLINEDOC","CASHIER","LOGOURL","COMMENT","VER","ORDERTAXNUM","OFFLINE","PREVDOCHASH","REVOKED","STORNED","TESTING"])
class XmlReceiptHeader : XmlDocumentHeader(), IReceiptHeader {

    @field:Element(name="DOCTYPE", required = false)
    private var mInternalDocumentType: InternalDocumentType? = null

    @field:Element(name="DOCSUBTYPE", required = false)
    private var mInternalDocumentSubType: InternalDocumentSubType? = null

    @field:Element(name="ORDERRETNUM", required = false)
    private var mFiscalNumberForReturn: String? = null

    @field:Element(name="ORDERSTORNUM", required = false)
    private var mFiscalNumberForDelete: String? = null

    @field:Element(name="REVOKELASTONLINEDOC", required = false)
    private var mRevokedLastOnlineDocument: Boolean? = null

    @field:Element(name="LOGOURL", required = false)
    private var mLogoUrl: String? = null

    @field:Element(name="COMMENT", required = false)
    private var mComment: String? = null

    @field:Element(name="STORNED", required = false)
    private var mCanceled: Boolean? = null

    @field:Element(name="OPERTYPENM", required = false)
    private var mOperationTypeName: String? = null

    @field:Element(name="VEHICLERN", required = false)
    private var mVehicleRegistrationNumber: String? = null


    override fun setInternalDocumentType(internalDocumentType: InternalDocumentType?) {
        mInternalDocumentType = internalDocumentType
    }

    override fun getInternalDocumentType(): InternalDocumentType? {
        return mInternalDocumentType
    }

    override fun setInternalDocumentSubType(internalDocumentSubType: InternalDocumentSubType?) {
        mInternalDocumentSubType = internalDocumentSubType
    }

    override fun getInternalDocumentSubType(): InternalDocumentSubType? {
        return mInternalDocumentSubType
    }


    override fun setFiscalNumberForReturn(fiscalNumberForReturn: String?) {
        mFiscalNumberForReturn = fiscalNumberForReturn
    }

    override fun getFiscalNumberForReturn(): String? {
        return mFiscalNumberForReturn
    }

    override fun setFiscalNumberForCancel(fiscalNumberForCancel: String?) {
        mFiscalNumberForDelete = fiscalNumberForCancel
    }

    override fun getFiscalNumberForCancel(): String? {
        return mFiscalNumberForDelete
    }

    override fun setRevokedLastOnlineDocument(revokeLastOnlineDocument: Boolean?) {
        mRevokedLastOnlineDocument = revokeLastOnlineDocument
    }

    override fun isRevokedLastOnlineDocument(): Boolean? {
        return mRevokedLastOnlineDocument
    }


    override fun setLogoUrl(logoUrl: String?) {
        mLogoUrl = logoUrl
    }

    override fun getLogoUrl(): String? {
        return mLogoUrl
    }

    override fun setComment(comment: String?) {
        mComment = comment
    }

    override fun getComment(): String? {
        return mComment
    }

    override fun setCanceled(canceled: Boolean?) {
        mCanceled = canceled
    }

    override fun isCanceled(): Boolean? {
        return mCanceled
    }

    override fun getOperationTypeName(): String? {
        return mOperationTypeName
    }

    override fun setOperationTypeName(operationTypeName: String?) {
        mOperationTypeName = operationTypeName
    }

    override fun getVehicleRegistrationNumber(): String? {
        return mVehicleRegistrationNumber
    }

    override fun setVehicleRegistrationNumber(vehicleRegistrationNumber: String?) {
        mVehicleRegistrationNumber = vehicleRegistrationNumber
    }
}
