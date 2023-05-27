package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.common.IDocument
import me.ugreek85.document.common.IDocumentHeader
import me.ugreek85.document.common.IDocumentTaxRate
import me.ugreek85.document.receipt.*
import me.ugreek85.utils.CopyObjectUtil
import me.ugreek85.utils.DocumentCopyUtil

import me.ugreek85.view.xml.impl.document.XmlDocumentHeader
import me.ugreek85.view.xml.impl.document.XmlDocumentTaxRate
//import me.ugreek85.view.xml.impl.document.XmlDocumentTaxRate
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Order
import org.simpleframework.xml.Root
import java.math.BigDecimal
import java.util.ArrayList

@Root(name="CHECK", strict = false)
@Order(elements = ["CHECKHEAD", "CHECKTOTAL", "CHECKPAY", "CHECKTAX"/*, "CHECKPTKS"*/, "CHECKBODY"])
class XmlReceiptDocument: IReceiptDocument, IDocument {

    @field:Element(name="CHECKHEAD", type = XmlReceiptHeader::class)
    private lateinit var mDocumentHeader: IReceiptHeader

    @field:Element(name="CHECKTOTAL", required = false, type = XmlReceiptTotal::class)
    private var mTotal: IReceiptTotal? = null

    @field:ElementList(name = "CHECKPAY", required = false, type = XmlReceiptPayForm::class )
    var mPayForms: ArrayList<IReceiptPayForm>? = null

    @field:ElementList(name = "CHECKTAX", required = false, type = XmlDocumentTaxRate::class )
    var mTaxRates: ArrayList<IDocumentTaxRate>? = null

    @field:ElementList(name = "CHECKBODY", required = false, type = XmlReceiptProduct::class )
    var mProducts: ArrayList<IReceiptProduct>? = null


    override fun setTotal(total: IReceiptTotal?) {
        mTotal = DocumentCopyUtil.copyObject(IReceiptTotal::class.java, total){
            XmlReceiptTotal()
        } as XmlReceiptTotal

        val cl = XmlReceiptTotal::class.java
    }

    override fun getTotal(): IReceiptTotal? {
        return mTotal
    }

    override fun setPayForms(payForms: List<IReceiptPayForm>?) {

        mPayForms = DocumentCopyUtil.copyArrayList(IReceiptPayForm::class.java, payForms){
            XmlReceiptPayForm()
        }
    }

    override fun getPayForms(): List<IReceiptPayForm>? {
        return mPayForms
    }

    override fun setTaxRates(taxRates: List<IDocumentTaxRate>?) {

        mTaxRates = DocumentCopyUtil.copyArrayList(IDocumentTaxRate::class.java, taxRates){
            XmlDocumentTaxRate()
        }
    }

    override fun getTaxRates(): List<IDocumentTaxRate>? {
        return mTaxRates
    }

    override fun setProducts(products: List<IReceiptProduct>?) {

        mProducts = DocumentCopyUtil.copyArrayList(IReceiptProduct::class.java, products){
            XmlReceiptProduct()
        }
    }

    override fun getProducts(): List<IReceiptProduct>? {
        return mProducts
    }

    override fun getDocumentHeader(): IDocumentHeader {
        return mDocumentHeader
    }

    override fun setDocumentHeader(documentHeader: IDocumentHeader) {
        mDocumentHeader = XmlReceiptHeader()
        CopyObjectUtil.copyByInterface(IReceiptHeader::class.java, documentHeader as IReceiptHeader, mDocumentHeader)
    }
}
