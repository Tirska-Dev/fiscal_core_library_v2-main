package me.ugreek85.document.receipt

import me.ugreek85.document.common.IRow

interface IExciseLabel : IRow {

    fun getLabel(): String
    fun setLabel(label: String)
}