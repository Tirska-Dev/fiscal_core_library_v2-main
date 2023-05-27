package me.ugreek85.document.common.impl

import me.ugreek85.document.common.IRow

open class BaseRowImpl : IRow {

    private var mRow: Int = 1

    override fun setRow(row: Int){
        mRow = row
    }

    override fun getRow() = mRow
}