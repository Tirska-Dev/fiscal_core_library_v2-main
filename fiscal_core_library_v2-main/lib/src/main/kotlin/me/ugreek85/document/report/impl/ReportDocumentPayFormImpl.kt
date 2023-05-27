package me.ugreek85.document.report.impl

import me.ugreek85.document.report.IReportPayForm
import me.ugreek85.document.common.impl.DocumentPayFormImpl
import java.math.BigDecimal
import kotlin.properties.Delegates

open class ReportDocumentPayFormImpl : IReportPayForm, DocumentPayFormImpl() {

    private var mSum by Delegates.notNull<BigDecimal>()

    override fun setSum(sum: BigDecimal) {
        mSum = sum
    }

    override fun getSum(): BigDecimal {
        return mSum
    }
}
