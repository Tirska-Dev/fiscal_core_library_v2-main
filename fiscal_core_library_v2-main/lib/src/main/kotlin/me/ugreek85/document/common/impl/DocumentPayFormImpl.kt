package me.ugreek85.document.common.impl

import me.ugreek85.document.common.IDocumentPayForm
import me.ugreek85.document.common.impl.BaseRowImpl

open class DocumentPayFormImpl : IDocumentPayForm, BaseRowImpl() {

    private lateinit var mCode: String
    private lateinit var mName: String

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