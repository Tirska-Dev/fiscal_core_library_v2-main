package me.ugreek85.utils

import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory


object XmlSchemaValidationUtil {

    private val mDocumentBuilder = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()

    private var mReceiptDocumentSchema: Schema
    private var mReportDocumentSchema: Schema


    init {

//        var schema = XMLConstants.W3C_XML_SCHEMA_NS_URI
        val factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"/*XMLConstants.W3C_XML_SCHEMA_NS_URI*/)
        mReceiptDocumentSchema = factory.newSchema(XmlSchemaValidationUtil::class.java.classLoader.getResource("check01.xsd"))
        mReportDocumentSchema = factory.newSchema(XmlSchemaValidationUtil::class.java.classLoader.getResource("zrep01.xsd"))

    }

    private fun loadDocument(byteArray: ByteArray): DOMSource
    {
        byteArray.inputStream().use {
            return DOMSource(mDocumentBuilder.parse(it))
        }
    }

    fun validateReceiptDocument(byteArray: ByteArray)
    {
        mReceiptDocumentSchema.newValidator().validate(loadDocument(byteArray))
    }

    fun validateReportDocument(byteArray: ByteArray)
    {
        mReportDocumentSchema.newValidator().validate(loadDocument(byteArray))
    }
}
