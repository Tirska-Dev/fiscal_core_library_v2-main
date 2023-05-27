package me.ugreek85.document.common.impl

import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.IDocument
import me.ugreek85.document.common.IDocumentHeader
import me.ugreek85.utils.CopyObjectUtil

abstract class BaseDocumentImpl() : IDocument {

    private lateinit var mDocumentHeader: IDocumentHeader

    override fun getDocumentHeader(): IDocumentHeader {
        return mDocumentHeader
    }

    override fun setDocumentHeader(documentHeader: IDocumentHeader) {
        mDocumentHeader = DocumentHeaderImpl()
        CopyObjectUtil.copyByInterface(IDocumentHeader::class.java, documentHeader, mDocumentHeader)
    }

    override fun isOfflineDocument(): Boolean {
        return mDocumentHeader.isOffline() ?: false
    }
}
