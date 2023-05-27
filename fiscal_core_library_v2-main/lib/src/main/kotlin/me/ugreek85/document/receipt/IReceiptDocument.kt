package me.ugreek85.document.receipt


import me.ugreek85.document.common.IDocument
import me.ugreek85.document.common.IDocumentTaxRate

interface IReceiptDocument : IDocument {

    fun setTotal(total: IReceiptTotal?)
    fun getTotal(): IReceiptTotal?

    fun setPayForms(payForms: List<IReceiptPayForm>?)
    fun getPayForms(): List<IReceiptPayForm>?

    fun setTaxRates(taxRates: List<IDocumentTaxRate>?)
    fun getTaxRates(): List<IDocumentTaxRate>?

    fun setProducts(products: List<IReceiptProduct>?)
    fun getProducts(): List<IReceiptProduct>?
}