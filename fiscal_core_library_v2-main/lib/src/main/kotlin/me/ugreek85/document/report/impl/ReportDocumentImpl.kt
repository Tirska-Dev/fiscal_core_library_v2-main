package me.ugreek85.document.report.impl

import me.ugreek85.document.common.impl.BaseDocumentImpl
import me.ugreek85.document.report.IReportCashTotal
import me.ugreek85.document.report.IReportDocument
import me.ugreek85.document.report.IReportTotal
import me.ugreek85.utils.DocumentCopyUtil
import org.json.JSONObject
import java.math.BigDecimal

class ReportDocumentImpl : BaseDocumentImpl(), IReportDocument
{
    private var mTotalsBySale: IReportTotal? = null
    private var mTotalsByReturn: IReportTotal? = null
    private var mServiceInputSum: BigDecimal? = null
    private var mServiceOutputSum: BigDecimal? = null
    private var mTotalsByCash: IReportCashTotal? = null

    override fun setTotalsBySale(totals: IReportTotal?) {
        mTotalsBySale = DocumentCopyUtil.copyObject(IReportTotal::class.java, totals) {
            ReportTotalImpl()
        }
    }

    override fun getTotalsBySale(): IReportTotal? {
        return mTotalsBySale
    }

    override fun setTotalsByReturn(totals: IReportTotal?) {
        mTotalsByReturn = DocumentCopyUtil.copyObject(IReportTotal::class.java, totals){
            ReportTotalImpl()
        }
    }

    override fun getTotalsByReturn(): IReportTotal? {
        return mTotalsByReturn
    }

    override fun setServiceInputSum(sum: BigDecimal?) {
        mServiceInputSum = sum
    }

    override fun getServiceInputSum(): BigDecimal? {
        return mServiceInputSum
    }

    override fun setServiceOutputSum(sum: BigDecimal?) {
        mServiceOutputSum = sum
    }

    override fun getServiceOutputSum(): BigDecimal? {
        return mServiceOutputSum
    }

    override fun setTotalsByCash(totals: IReportCashTotal?) {
        mTotalsByCash = DocumentCopyUtil.copyObject(IReportCashTotal::class.java, totals) {
            ReportCashTotalImpl()
        }
    }

    override fun getTotalsByCash(): IReportCashTotal? {
        return mTotalsByCash
    }

    private fun getTotals(total: IReportTotal, type: String): Map<String, BigDecimal> {
        val map = mutableMapOf<String, BigDecimal>()
        map["${type}.Sum"] = total.getSum()
        map["${type}.ReceiptsCount"] = BigDecimal.valueOf(total.getNumberOfReceipts().toLong())
        total.getPawnSumIssued()?.let {
            map["${type}.PawnSumIssued"] = it
        }
        total.getPawnSumReceived()?.let {
            map["${type}.PawnSumReceived"] = it
        }
        total.getTaxRates()?.forEach { taxRow ->
            val json = JSONObject()
            taxRow.getLetter()?.let {
                json.put("Letter", it)
            }
            json.put("Name", taxRow.getName())
            json.put("Type", taxRow.getType())
            json.put("Prc", taxRow.getPercent())
            taxRow.getSign()?.let {
                json.put("Sign", it)
            }
            map["${type}.Tax.Sum:$json"] = taxRow.getSum()
            map["${type}.Tax.Turnover:$json"] = taxRow.getTurnoverSum()
            taxRow.getSourceSum()?.let {
                map["${type}.Tax.SourceSum:$json"] = it
            }
        }
        total.getPayForms()?.forEach { payRow ->
            val json = JSONObject()
            json.put("Code", payRow.getCode())
            json.put("Name", payRow.getName())
            map["${type}.PayForm.Sum:$json"] = payRow.getSum()
        }
        return map
    }

    override fun getRegisters(): Map<String, BigDecimal>? {
        val map = mutableMapOf<String, BigDecimal>()
        mTotalsBySale?.let { total ->
            map.putAll(getTotals(total, "Sale"))
        }
        mTotalsByReturn?.let { total ->
            map.putAll(getTotals(total, "Return"))
        }
        mTotalsByCash?.let { total ->
            map["Cash.Sum"] = total.getSum()
            total.getSumOfCommission()?.let {
                map["Cash.CommissionSum"] = it
            }
            map["Cash.ReceiptsCount"] = BigDecimal.valueOf(total.getNumberOfReceipts().toLong())
        }
        mServiceInputSum?.let { total ->
            map["ServiceInput.Sum"] = total
        }
        mServiceOutputSum?.let { total ->
            map["ServiceOutput.Sum"] = total
        }
        if (map.isEmpty()) return null
        return map
    }
}
