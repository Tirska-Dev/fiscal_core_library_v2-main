//package me.ugreek85.view.html.impl
//
//import me.ugreek85.utils.ResourcesUtil
//import me.ugreek85.view.xml.impl.receipt.XmlReceiptDocument
//import me.ugreek85.view.xml.impl.report.XmlReportDocument
//import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
//import kotlin.test.*
//
//internal class HtmlSerializerTest {
//
//    var separator = System.getProperty("file.separator")
//    private val htmlSerializer = HtmlSerializer()
//
//    @Test
//    fun serializeReportDocument() {
//
//        val reportDocument = XmlMapperUtil.fromXML(XmlReportDocument::class.java, ResourcesUtil.getResource("zReport.xml"))
//        val bytes = htmlSerializer.serialize(reportDocument)
//
//        val expectedBytes = ResourcesUtil.readBytes("html${separator}zReport.html")
//        assertContentEquals(expectedBytes, bytes)
//    }

//    @Test
//    fun serializeOpenShift() {
//
//        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("openShift.xml"))
//        val bytes = htmlSerializer.serialize(receiptDocument)
//
////        val expectedBytes = ResourcesUtil.readBytes("html${separator}openShift.html")
////        assertContentEquals(expectedBytes, bytes)
//    }
//
//    @Test
//    fun serializeCloseShift() {
//
//        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("closeShift.xml"))
//        val bytes = htmlSerializer.serialize(receiptDocument)
//
////        val expectedBytes = ResourcesUtil.readBytes("html${separator}openShift.html")
////        assertContentEquals(expectedBytes, bytes)
//    }

//}
