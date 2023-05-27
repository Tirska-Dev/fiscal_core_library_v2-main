package me.ugreek85.document.receipt.impl

import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.IDocumentHeader
import me.ugreek85.document.common.IDocumentTaxRate
import me.ugreek85.document.common.impl.BaseDocumentImpl
import me.ugreek85.document.common.impl.DocumentTaxRateImpl
import me.ugreek85.document.receipt.*
import me.ugreek85.utils.CopyObjectUtil
import me.ugreek85.utils.DocumentCopyUtil
import me.ugreek85.utils.DocumentTypeUtil
import org.json.JSONObject
import java.math.BigDecimal

class ReceiptDocumentImpl(
)
    : BaseDocumentImpl(), IReceiptDocument
{
    private lateinit var mHeader: IReceiptHeader
    private var mTotal: IReceiptTotal? = null
    private var mPayForms: List<IReceiptPayForm>? = null
    private var mTaxRates: List<IDocumentTaxRate>? = null
    private var mProducts: List<IReceiptProduct>? = null

    override fun setDocumentHeader(documentHeader: IDocumentHeader) {
        mHeader = ReceiptHeaderImpl()
        CopyObjectUtil.copyByInterface(IReceiptHeader::class.java, documentHeader as IReceiptHeader, mHeader)
    }

    override fun getDocumentHeader(): IDocumentHeader {
        return mHeader
    }

    override fun setTotal(total: IReceiptTotal?) {
        mTotal = DocumentCopyUtil.copyObject(IReceiptTotal::class.java, total){
            ReceiptTotalImpl()
        }
    }

    override fun getTotal(): IReceiptTotal? {
        return mTotal
    }

    override fun setPayForms(payForms: List<IReceiptPayForm>?) {
        mPayForms = DocumentCopyUtil.copyList(IReceiptPayForm::class.java, payForms){
            ReceiptDocumentPayFormImpl()
        }
    }

    override fun getPayForms(): List<IReceiptPayForm>? {
        return mPayForms
    }

    override fun setTaxRates(taxRates: List<IDocumentTaxRate>?) {
        mTaxRates = DocumentCopyUtil.copyList(IDocumentTaxRate::class.java, taxRates){
            DocumentTaxRateImpl()
        }
    }

    override fun getTaxRates(): List<IDocumentTaxRate>? {
        return mTaxRates
    }

    override fun setProducts(products: List<IReceiptProduct>?) {
        mProducts = DocumentCopyUtil.copyList(IReceiptProduct::class.java, products){
            ReceiptProductImpl()
        }
    }

    override fun getDocumentType(): DocumentType {
        return DocumentTypeUtil.detectDocumentTypeByHeader(getDocumentHeader())
    }

    private fun getTypeName(): String {

        return when (getDocumentType()){
            DocumentType.Sale -> "Sale"
            DocumentType.Pawn -> "Sale"
            DocumentType.Return -> "Return"
            DocumentType.CashWithdrawal-> "Cash"
            DocumentType.ServiceInput -> "ServiceInput"
            DocumentType.ServiceOutput -> "ServiceOutput"
            else -> "Other"
        }
    }

    override fun getProducts(): List<IReceiptProduct>? {
        return mProducts
    }

    override fun getRegisters(): Map<String, BigDecimal>? {
        val type = getTypeName()
        var map: Map<String, BigDecimal>? = null

        if (type != "Other") {
            map = mutableMapOf()
            mTotal?.let { total ->
                total.getSum()?.let {
                    map["$type.Sum"] = it
                }
                total.getDiscountSum()?.let {
                    map["$type.DiscountSum"] = it
                }
                if (getDocumentType() == DocumentType.Pawn) {
                    total.getPawnSumIssued()?.let {
                        map["$type.PawnSumIssued"] = it
                    }
                    total.getPawnSumReceived()?.let {
                        map["$type.PawnSumReceived"] = it
                    }
                }
            }

            mTaxRates?.forEach { taxRow ->
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
                map["$type.Tax.Sum:$json"] = taxRow.getSum()
                map["$type.Tax.Turnover:$json"] = taxRow.getTurnoverSum()
                taxRow.getSourceSum()?.let {
                    map["$type.Tax.SourceSum:$json"] = it
                }
            }

            mPayForms?.forEach { payRow ->
                val json = JSONObject()
                json.put("Code", payRow.getCode())
                json.put("Name", payRow.getName())

                payRow.getSum()?.let {
                    map["$type.PayForm.Sum:$json"] = it
                }
                payRow.getProvidedSum()?.let {
                    map["$type.PayForm.ProvidedSum:$json"] = it
                }
                payRow.getSumOfRemain()?.let {
                    map["$type.PayForm.RemainSum:$json"] = it
                }
            }
        }
        return map
    }
}
