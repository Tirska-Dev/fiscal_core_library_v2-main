package me.ugreek85.view.xml.impl.transforms

import me.ugreek85.document.common.InternalDocumentSubType
import org.simpleframework.xml.transform.Transform

class InternalDocumentSubTypeTransform : Transform<InternalDocumentSubType> {
    override fun read(value: String): InternalDocumentSubType {
        return InternalDocumentSubType.byInternalValue(value)
    }

    override fun write(value: InternalDocumentSubType): String {
        return value.value
    }
}