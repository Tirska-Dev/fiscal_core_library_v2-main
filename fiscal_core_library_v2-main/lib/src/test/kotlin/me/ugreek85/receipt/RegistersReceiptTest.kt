package me.ugreek85.receipt

import me.ugreek85.document.receipt.impl.ReceiptDocumentImpl
import me.ugreek85.utils.ResourcesUtil
import me.ugreek85.view.xml.impl.receipt.XmlReceiptDocument
import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import org.json.JSONObject
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RegistersReceiptTest {
    @Test
    fun saleRegisters() {
        val doc = ReceiptDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("sale.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTaxRates(xml.getTaxRates())
        doc.setTotal(xml.getTotal())
        doc.setPayForms(xml.getPayForms())
        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(203.12), doc.getRegisters()?.get("Sale.Sum"))
        assertEquals(BigDecimal.valueOf(2.00).setScale(2), doc.getRegisters()?.get("Sale.DiscountSum"))

        var json = JSONObject()
        json.put("Letter", "A")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 20)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(33.85), doc.getRegisters()?.get("Sale.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(205.12), doc.getRegisters()?.get("Sale.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(203.12), doc.getRegisters()?.get("Sale.Tax.SourceSum:${json}"))

        json = JSONObject()
        json.put("Code", "2")
        json.put("Name", "КАРТКА")
        assertEquals(BigDecimal.valueOf(203.12), doc.getRegisters()?.get("Sale.PayForm.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(203.12), doc.getRegisters()?.get("Sale.PayForm.ProvidedSum:${json}"))
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Sale.PayForm.RemainSum:${json}"))
    }

    @Test
    fun cashRegisters() {
        val doc = ReceiptDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("cashWithdrawal.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTaxRates(xml.getTaxRates())
        doc.setTotal(xml.getTotal())
        doc.setPayForms(xml.getPayForms())
        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(1929.80).setScale(2), doc.getRegisters()?.get("Cash.Sum"))

        var json = JSONObject()
        json.put("Letter", "В")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 0)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Cash.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(1929.84), doc.getRegisters()?.get("Cash.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(1929.84), doc.getRegisters()?.get("Cash.Tax.SourceSum:${json}"))

        json = JSONObject()
        json.put("Code", "0")
        json.put("Name", "Готівка")
        assertEquals(BigDecimal.valueOf(1929.80).setScale(2), doc.getRegisters()?.get("Cash.PayForm.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(2000.00).setScale(2), doc.getRegisters()?.get("Cash.PayForm.ProvidedSum:${json}"))
        assertEquals(BigDecimal.valueOf(70.20).setScale(2), doc.getRegisters()?.get("Cash.PayForm.RemainSum:${json}"))
    }

    @Test
    fun returnRegisters() {
        val doc = ReceiptDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("return.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTaxRates(xml.getTaxRates())
        doc.setTotal(xml.getTotal())
        doc.setPayForms(xml.getPayForms())
        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(6584.43), doc.getRegisters()?.get("Return.Sum"))
        assertEquals(BigDecimal.valueOf(25.00).setScale(2), doc.getRegisters()?.get("Return.DiscountSum"))

        var json = JSONObject()
        json.put("Letter", "М")
        json.put("Name", "Акциз")
        json.put("Type", 1)
        json.put("Prc", 5)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(11.66), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(259.90).setScale(2), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(244.90).setScale(2), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json.put("Letter", "A")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 20)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(89.73), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(575.05), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(538.39), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json.put("Letter", "Б")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 7)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(222.76), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(3405.00).setScale(2), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(3405.00).setScale(2), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json.put("Letter", "В")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 0)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(111.80).setScale(2), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(111.80).setScale(2), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json.put("Letter", "Д")
        json.put("Name", "ПДВ")
        json.put("Type", 0)
        json.put("Prc", 0)
        json.put("Sign", false)
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), doc.getRegisters()?.get("Return.Tax.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(2517.60).setScale(2), doc.getRegisters()?.get("Return.Tax.Turnover:${json}"))
        assertEquals(BigDecimal.valueOf(2517.60).setScale(2), doc.getRegisters()?.get("Return.Tax.SourceSum:${json}"))

        json = JSONObject()
        json.put("Code", "0")
        json.put("Name", "Готівка")
        assertEquals(BigDecimal.valueOf(6084.40).setScale(2), doc.getRegisters()?.get("Return.PayForm.Sum:${json}"))
        assertEquals(BigDecimal.valueOf(6100.00).setScale(2), doc.getRegisters()?.get("Return.PayForm.ProvidedSum:${json}"))
        assertEquals(BigDecimal.valueOf(15.60).setScale(2), doc.getRegisters()?.get("Return.PayForm.RemainSum:${json}"))

        json.put("Code", "1")
        json.put("Name", "Банківська картка")
        assertEquals(BigDecimal.valueOf(400.02), doc.getRegisters()?.get("Return.PayForm.Sum:${json}"))

        json.put("Code", "2")
        json.put("Name", "Попередня оплата")
        assertEquals(BigDecimal.valueOf(100.01), doc.getRegisters()?.get("Return.PayForm.Sum:${json}"))
    }

    @Test
    fun serviceInputRegisters() {
        val doc = ReceiptDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("serviceInput.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTaxRates(xml.getTaxRates())
        doc.setTotal(xml.getTotal())
        doc.setPayForms(xml.getPayForms())
        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(8.00).setScale(2), doc.getRegisters()?.get("ServiceInput.Sum"))
    }

    @Test
    fun serviceOutputRegisters() {
        val doc = ReceiptDocumentImpl()
        val xml = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("serviceOutput.xml"))
        doc.setDocumentHeader(xml.getDocumentHeader())
        doc.setTaxRates(xml.getTaxRates())
        doc.setTotal(xml.getTotal())
        doc.setPayForms(xml.getPayForms())
        assertNotNull(doc.getRegisters())
        assertEquals(BigDecimal.valueOf(5.00).setScale(2), doc.getRegisters()?.get("ServiceOutput.Sum"))
    }
}