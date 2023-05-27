package me.ugreek85.document.receipt.impl

import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.common.impl.DocumentHeaderImpl
import me.ugreek85.document.receipt.IReceiptHeader

class ReceiptHeaderImpl : IReceiptHeader, DocumentHeaderImpl() {

    // output xml tag ("DOCTYPE")
    private var mInternalDocumentType: InternalDocumentType? = null

    // output xml tag ("DOCSUBTYPE")
    private var mInternalDocumentSubType: InternalDocumentSubType? = null

    // output xml tag ("ORDERRETNUM")
    private var mFiscalNumberForReturn: String? = null

    // output xml tag ("ORDERSTORNUM")
    private var mFiscalNumberForDelete: String? = null

    // output xml tag ("REVOKELASTONLINEDOC")
    private var mRevokedLastOnlineDocument: Boolean? = null

    // output xml tag ("LOGOURL")
    private var mLogoUrl: String? = null

    // output xml tag ("COMMENT")
    private var mComment: String? = null

    // output xml tag ("STORNED")
    private var mDeleted: Boolean? = null

    private var mOperationTypeName: String? = null

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
        mDeleted = canceled
    }

    override fun isCanceled(): Boolean? {
        return mDeleted
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
