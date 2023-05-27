package me.ugreek85.utils

import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.IDocumentHeader

object DocumentNameUtil {

    fun getDocumentName(documentType: DocumentType, documentHeader: IDocumentHeader): String
    {
        if(documentType == DocumentType.BeginOffline || documentType == DocumentType.FinishOffline) return documentType.value

        var offlinePrefixName = ""
        documentHeader.isOffline()?.let {
            if(it) offlinePrefixName = "Offline"
        }
        return "$offlinePrefixName${documentType.value}"
    }
}