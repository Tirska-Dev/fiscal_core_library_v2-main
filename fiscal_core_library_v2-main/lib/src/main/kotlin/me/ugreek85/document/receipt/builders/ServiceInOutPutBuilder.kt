package me.ugreek85.document.receipt.builders

import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.document.models.ReceiptBuilderModel
import me.ugreek85.document.receipt.impl.ReceiptDocumentImpl
import me.ugreek85.document.receipt.impl.ReceiptHeaderImpl
import java.math.BigDecimal

class ServiceInOutPutBuilder(
    private val subType: InternalDocumentSubType,
    _uid: String,
    _tin: String,
    _ipn: String?,
    _companyName: String,
    _pointName: String,
    _pointAddress: String?,
    _date: String,
    _time: String,
    _docNum: Long,
    _registrarLocNum: Long,
    _registrarId: Long,
    _cashierName: String?,
    _verCode: Int,
    _comment: String? = null,
    _fiscalNum: String? = null,
    _vehicleRegistrationNumber: String? = null,
    _operationTypeName: String? = null,
    _canceled: Boolean? = null,
    _logoUrl: String? = null,
    _fiscalNumForCancel: String? = null,
    _fiscalNumForReturn: String? = null
) : ReceiptBuilderModel(
    InternalDocumentType.SALE,
    _uid,
    _tin,
    _ipn,
    _companyName,
    _pointName,
    _pointAddress,
    _date,
    _time,
    _docNum,
    _registrarLocNum,
    _registrarId,
    _cashierName,
    _verCode,
    _comment,
    _fiscalNum,
    _vehicleRegistrationNumber,
    _operationTypeName,
    _canceled,
    _logoUrl,
    _fiscalNumForCancel,
    _fiscalNumForReturn
) {

    override var mDocument: ReceiptDocumentImpl = ReceiptDocumentImpl()

    init {
        val header = createHeader()
        mDocument.setDocumentHeader(header)
    }

    fun addTotals(
        sum: BigDecimal? = null,
        discountSum: BigDecimal? = null,
        roundingSum: BigDecimal? = null,
        sumWithoutRounding: BigDecimal? = null,
        commissionSum: BigDecimal? = null
    ) {
        val totals = super.createTotal(sum, commissionSum, discountSum, roundingSum, null,
            null, sumWithoutRounding)
        mDocument.setTotal(totals)
    }

    override fun createHeader(): ReceiptHeaderImpl {
        val recHeader = super.createHeader()
        recHeader.setInternalDocumentSubType(this.subType)
        return recHeader
    }
}