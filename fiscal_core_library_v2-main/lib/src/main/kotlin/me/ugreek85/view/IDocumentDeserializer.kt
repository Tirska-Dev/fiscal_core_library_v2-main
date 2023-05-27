package me.ugreek85.view

import me.ugreek85.document.common.IDocument

interface IDocumentDeserializer<T> {
    fun deserialize(data: T): IDocument
}
