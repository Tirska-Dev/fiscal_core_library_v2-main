package me.ugreek85.view

import me.ugreek85.document.common.IDocument

interface IDocumentSerializer<T> {
    fun serialize(document: IDocument, args: Map<String, Any>? = null): T
}
