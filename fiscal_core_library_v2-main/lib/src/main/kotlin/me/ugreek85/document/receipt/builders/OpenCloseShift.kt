package me.ugreek85.document.receipt.builders

import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.models.ReceiptBuilderModel
import me.ugreek85.document.receipt.impl.ReceiptDocumentImpl

class OpenCloseShift(
    _type: InternalDocumentType,
    _uid: String,
    _tin: String,
    _ipn: String? = null,
    _companyName: String,
    _pointName: String,
    _pointAddress: String? = null,
    _date: String,
    _time: String,
    _docNum: Long,
    _registrarLocNum: Long,
    _registrarId: Long,
    _cashierName: String? = null,
    _verCode: Int,
    _fiscalNum: String? = null
) : ReceiptBuilderModel(
    _type,
    _uid,
    _tin,
    ipn = _ipn,
    _companyName,
    _pointName,
    pointAddress = _pointAddress,
    _date,
    _time,
    _docNum,
    _registrarLocNum,
    _registrarId,
    cashierName = _cashierName,
    _verCode,
    fiscalNum = _fiscalNum
) {

    override var mDocument: ReceiptDocumentImpl = ReceiptDocumentImpl()

    init {
        val header = super.createHeader()
        mDocument.setDocumentHeader(header)
    }
}