package me.ugreek85.view.xml.impl.report

import me.ugreek85.document.common.IDocumentHeader
import me.ugreek85.document.report.IReportCashTotal
import me.ugreek85.document.report.IReportDocument
import me.ugreek85.document.report.IReportTotal
import me.ugreek85.utils.CopyObjectUtil
import me.ugreek85.utils.DocumentCopyUtil
import me.ugreek85.view.xml.impl.document.XmlDocumentHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order
import org.simpleframework.xml.Root
import java.math.BigDecimal

@Root(name="ZREP")
@Order(elements = ["ZREPHEAD", "ZREPREALIZ", "ZREPRETURN", "ZREPCASH"/*, "ZREPVAL", "ZREPFUEL"*/, "ZREPBODY"])
class XmlReportDocument : IReportDocument {

    @field:Element(name="ZREPHEAD", type = XmlDocumentHeader::class)
    private lateinit var mDocumentHeader: IDocumentHeader

    @field:Element(name="ZREPREALIZ", required = false, type = XmlReportTotal::class)
    private var mTotalsBySale: IReportTotal? = null

    @field:Element(name="ZREPRETURN", required = false, type = XmlReportTotal::class)
    private var mTotalsByReturn: IReportTotal? = null

    @field:Element(name="ZREPCASH", required = false, type = XmlReportCashTotal::class)
    private var mTotalsByCash: IReportCashTotal? = null

    @field:Element(name="ZREPBODY", required = false)
    private var mReportBody:XmlReportBody? = null

    override fun setTotalsBySale(totals: IReportTotal?) {

        mTotalsBySale = DocumentCopyUtil.copyObject2(IReportTotal::class.java, totals){
            XmlReportTotal()
        }
    }

    override fun getTotalsBySale(): IReportTotal? {
        return mTotalsBySale
    }

    override fun setTotalsByReturn(totals: IReportTotal?) {
        mTotalsByReturn = DocumentCopyUtil.copyObject2(IReportTotal::class.java, totals){
            XmlReportTotal()
        }
    }

    override fun getTotalsByReturn(): IReportTotal? {
        return mTotalsByReturn
    }

    override fun setServiceInputSum(sum: BigDecimal?) {
        if(mReportBody == null) mReportBody = XmlReportBody()
        mReportBody?.setServiceInputSum(sum)
    }

    override fun getServiceInputSum(): BigDecimal? {
        return mReportBody?.getServiceInputSum()
    }

    override fun setServiceOutputSum(sum: BigDecimal?) {
        if(mReportBody == null) mReportBody = XmlReportBody()
        mReportBody?.setServiceOutputSum(sum)
    }

    override fun getServiceOutputSum(): BigDecimal? {
        return mReportBody?.getServiceOutputSum()
    }

    override fun setTotalsByCash(totals: IReportCashTotal?) {
        mTotalsByCash = DocumentCopyUtil.copyObject2(IReportCashTotal::class.java, totals){
            XmlReportCashTotal()
        }
    }

    override fun getTotalsByCash(): IReportCashTotal? {
        return mTotalsByCash
    }

    override fun getDocumentHeader(): IDocumentHeader {
        return mDocumentHeader
    }

    override fun setDocumentHeader(documentHeader: IDocumentHeader) {
        mDocumentHeader = XmlDocumentHeader()
        CopyObjectUtil.copyByInterface(IDocumentHeader::class.java, documentHeader, mDocumentHeader)
    }
}
