package me.ugreek85.view.xml.impl.document

import me.ugreek85.document.common.IDocumentPayForm
import me.ugreek85.view.xml.impl.types.Decimal2
import org.simpleframework.xml.Element
import java.math.BigDecimal

open class XmlDocumentPayForm : IDocumentPayForm, XmlBaseRow() {

    @field:Element(name="PAYFORMCD")
    private var mCode: String = ""

    @field:Element(name="PAYFORMNM")
    private var mName: String = ""

    override fun setCode(code: String) {
        mCode = code
    }

    override fun getCode(): String {
        return mCode
    }

    override fun setName(name: String) {
        mName = name
    }

    override fun getName(): String {
        return mName
    }
}
