package me.ugreek85.view.xml.impl.document

import me.ugreek85.document.common.IRow
import org.simpleframework.xml.Attribute

open class XmlBaseRow : IRow {

    @field:Attribute(name="ROWNUM")
    private var mRow: Int = 1

    override fun setRow(row: Int){
        mRow = row
    }

    override fun getRow() = mRow
}