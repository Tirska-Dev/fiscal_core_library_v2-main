package me.ugreek85.document.common

import me.ugreek85.utils.DocumentNameUtil
import me.ugreek85.utils.DocumentTypeUtil
import java.math.BigDecimal

interface IDocument {

    fun getDocumentHeader(): IDocumentHeader
    fun setDocumentHeader(documentHeader: IDocumentHeader)

    fun getRegisters(): Map<String, BigDecimal>? = null

    fun getDocumentName(): String {
        return DocumentNameUtil.getDocumentName(getDocumentType(), getDocumentHeader())
    }

    fun getDocumentType(): DocumentType {
        return DocumentTypeUtil.detectDocumentTypeByHeader(getDocumentHeader())
    }

    fun isOfflineDocument(): Boolean{
        return getDocumentHeader().isOffline() ?: false
    }
}
