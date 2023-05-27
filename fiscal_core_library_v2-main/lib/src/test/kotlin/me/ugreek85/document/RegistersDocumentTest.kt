package me.ugreek85.document

import me.ugreek85.document.report.impl.ReportDocumentImpl
import me.ugreek85.utils.ResourcesUtil
import me.ugreek85.view.xml.impl.report.XmlReportDocument
import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import org.json.JSONObject
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RegistersDocumentTest {
    @Test
    fun zReportRegisters() {
        val doc = ReportDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReportDocument::class.java, ResourcesUtil.getResource("zReportTest.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTotalsBySale(xml.getTotalsBySale())
        doc.setTotalsByReturn(xml.getTotalsByReturn())
        doc.setTotalsByCash(xml.getTotalsByCash())
        doc.setServiceInputSum(xml.getServiceInputSum())
        doc.setServiceOutputSum(xml.getServiceOutputSum())

        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(33363.66), doc.getRegisters()?.get("Sale.Sum"))
        assertEquals(BigDecimal.valueOf(6), doc.getRegisters()?.get("Sale.ReceiptsCount"))
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Sale.PawnSumIssued"))
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Sale.PawnSumReceived"))

        var json = JSONObject()
        json.put("Letter", "A")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 20)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(2495.42), doc.getRegisters()?.get("Sale.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(15702.16), doc.getRegisters()?.get("Sale.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(14972.56), doc.getRegisters()?.get("Sale.Tax.SourceSum:${json}"))

        json = JSONObject()
        json.put("Code", "0")
        json.put("Name", "Готівка")
        assertEquals(BigDecimal.valueOf(18348.20).setScale(2), doc.getRegisters()?.get("Sale.PayForm.Sum:${json}"))


        assertEquals(BigDecimal.valueOf(6584.43), doc.getRegisters()?.get("Return.Sum"))
        assertEquals(BigDecimal.valueOf(1), doc.getRegisters()?.get("Return.ReceiptsCount"))
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Return.PawnSumIssued"))
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Return.PawnSumReceived"))

        json = JSONObject()
        json.put("Letter", "A")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 20)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(89.73), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(575.05), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(538.39), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json = JSONObject()
        json.put("Code", "0")
        json.put("Name", "Готівка")
        assertEquals(BigDecimal.valueOf(6084.40).setScale(2), doc.getRegisters()?.get("Return.PayForm.Sum:${json}"))


        assertEquals(BigDecimal.valueOf(2244.96), doc.getRegisters()?.get("Cash.Sum"))
        assertEquals(BigDecimal.valueOf(10.00).setScale(2), doc.getRegisters()?.get("Cash.CommissionSum"))
        assertEquals(BigDecimal.valueOf(2), doc.getRegisters()?.get("Cash.ReceiptsCount"))


        assertEquals(BigDecimal.valueOf(8.00).setScale(2), doc.getRegisters()?.get("ServiceInput.Sum"))
        assertEquals(BigDecimal.valueOf(10.00).setScale(2), doc.getRegisters()?.get("ServiceOutput.Sum"))
    }
}