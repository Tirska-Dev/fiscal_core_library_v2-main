package me.ugreek85.document.receipt.impl

import me.ugreek85.document.common.impl.BaseRowImpl
import me.ugreek85.document.receipt.IExciseLabel


class ExciseLabelImpl : IExciseLabel, BaseRowImpl() {

    private var mLabel: String = ""

    override fun getLabel(): String {
        return mLabel
    }

    override fun setLabel(label: String) {
        mLabel = label
    }
}