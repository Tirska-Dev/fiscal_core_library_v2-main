package me.ugreek85.document.report

import me.ugreek85.document.common.IDocumentPayForm
import java.math.BigDecimal

interface IReportPayForm : IDocumentPayForm {

    fun setSum(sum: BigDecimal)
    fun getSum(): BigDecimal
}
