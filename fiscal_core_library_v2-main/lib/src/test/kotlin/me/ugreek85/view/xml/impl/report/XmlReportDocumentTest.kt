package me.ugreek85.view.xml.impl.report

import me.ugreek85.utils.ResourcesUtil
import me.ugreek85.view.html.impl.HtmlSerializer
import me.ugreek85.view.xml.impl.document.XmlDocumentHeader
import me.ugreek85.view.xml.impl.document.XmlDocumentTaxRate
import me.ugreek85.view.xml.impl.receipt.*
import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import kotlin.test.*

import org.junit.jupiter.api.Assertions.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.math.BigDecimal

internal class XmlReportDocumentTest {

    @Test
    fun loadFromXml() {

        val reportDocument = XmlMapperUtil.fromXML(XmlReportDocument::class.java, ResourcesUtil.getResource("zReport.xml"))
        assertNotNull(reportDocument)

        val header = reportDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals("7e97efcf-2cbb-4e75-a502-7f115002db85", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205525", header.getTime())
        assertEquals(33448L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
    }

    @Test
    fun saveToXml() {

        val doc = XmlReportDocument()
        val header = XmlDocumentHeader()
        val realiz = XmlReportTotal()
        val retrn = XmlReportTotal()
        val payFormsRealiz = mutableListOf<XmlReportPayForm>()
        val payFormsReturn = mutableListOf<XmlReportPayForm>()
        val taxRatesRealiz = mutableListOf<XmlDocumentTaxRate>()
        val taxRatesReturn = mutableListOf<XmlDocumentTaxRate>()
        val cash = XmlReportCashTotal()

        header.setUid("5555555-0f0f-4a4a-b3b3-000000000000")
        header.setSellerId("34554363")
        header.setVatNumber("123456789020")
        header.setCompanyName("Тестовий платник 4")
        header.setPointName("Магазин Іван-Тест")
        header.setPointAddress("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1")
        header.setDate("17012022")
        header.setTime("030222")
        header.setDocumentNumber(33555L)
        header.setRegistrarLocalNumber(24L)
        header.setRegistrarId(4000066879)
        header.setCashierName("Для РРО № 4000066879")
        header.setVersion(1)
        header.setFiscalNumber("434343.1.1616")
        header.setOffline(true)
        header.setRevoked(false)
        header.setPreviousDocumentHash("53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c")
        header.setTest(true)

        realiz.setSum(BigDecimal.valueOf(3333.66).setScale(2))
        realiz.setPawnSumIssued(BigDecimal.valueOf(0.00).setScale(2))
        realiz.setPawnSumReceived(BigDecimal.valueOf(0.55).setScale(2))
        realiz.setNumberOfReceipts(3)

        retrn.setSum(BigDecimal.valueOf(4444.77).setScale(2))
        retrn.setPawnSumIssued(BigDecimal.valueOf(0.66).setScale(2))
        retrn.setPawnSumReceived(BigDecimal.valueOf(0.00).setScale(2))
        retrn.setNumberOfReceipts(4)

        var payRow1 = XmlReportPayForm()
        payRow1.setRow(1)
        payRow1.setCode("2")
        payRow1.setName("КАРТКА")
        payRow1.setSum(BigDecimal.valueOf(250.12).setScale(2))

        var payRow2 = XmlReportPayForm()
        payRow2.setRow(2)
        payRow2.setCode("0")
        payRow2.setName("Готівка")
        payRow2.setSum(BigDecimal.valueOf(6084.40).setScale(2))

        payFormsRealiz.add(payRow1)
        payFormsRealiz.add(payRow2)

        realiz.setPayForms(payFormsRealiz)

        payRow1 = XmlReportPayForm()
        payRow1.setRow(1)
        payRow1.setCode("2")
        payRow1.setName("КАРТКА")
        payRow1.setSum(BigDecimal.valueOf(249.11).setScale(2))

        payRow2 = XmlReportPayForm()
        payRow2.setRow(2)
        payRow2.setCode("1")
        payRow2.setName("Банківська картка")
        payRow2.setSum(BigDecimal.valueOf(1260.28).setScale(2))

        payFormsReturn.add(payRow1)
        payFormsReturn.add(payRow2)

        retrn.setPayForms(payFormsReturn)

        var taxRow1 = XmlDocumentTaxRate()
        taxRow1.setRow(1)
        taxRow1.setType(1)
        taxRow1.setName("Акциз")
        taxRow1.setLetter("М")
        taxRow1.setPercent(BigDecimal.valueOf(5.00).setScale(2))
        taxRow1.setSign(false)
        taxRow1.setTurnoverSum(BigDecimal.valueOf(259.90).setScale(2))
        taxRow1.setSourceSum(BigDecimal.valueOf(244.90).setScale(2))
        taxRow1.setSum(BigDecimal.valueOf(11.66).setScale(2))

        var taxRow2 = XmlDocumentTaxRate()
        taxRow2.setRow(2)
        taxRow2.setType(0)
        taxRow2.setName("ПДВ")
        taxRow2.setLetter("В")
        taxRow2.setPercent(BigDecimal.valueOf(0.00).setScale(2))
        taxRow2.setSign(false)
        taxRow2.setTurnoverSum(BigDecimal.valueOf(1260.00).setScale(2))
        taxRow2.setSourceSum(BigDecimal.valueOf(1260.00).setScale(2))
        taxRow2.setSum(BigDecimal.valueOf(0.00).setScale(2))

        taxRatesRealiz.add(taxRow1)
        taxRatesRealiz.add(taxRow2)

        realiz.setTaxRates(taxRatesRealiz)

        taxRow1 = XmlDocumentTaxRate()
        taxRow1.setRow(1)
        taxRow1.setType(1)
        taxRow1.setName("Акциз")
        taxRow1.setLetter("М")
        taxRow1.setPercent(BigDecimal.valueOf(5.00).setScale(2))
        taxRow1.setSign(false)
        taxRow1.setTurnoverSum(BigDecimal.valueOf(824.70).setScale(2))
        taxRow1.setSourceSum(BigDecimal.valueOf(779.70).setScale(2))
        taxRow1.setSum(BigDecimal.valueOf(37.12).setScale(2))

        taxRow2 = XmlDocumentTaxRate()
        taxRow2.setRow(2)
        taxRow2.setType(0)
        taxRow2.setName("ПДВ")
        taxRow2.setLetter("Б")
        taxRow2.setPercent(BigDecimal.valueOf(7.00).setScale(2))
        taxRow2.setSign(false)
        taxRow2.setTurnoverSum(BigDecimal.valueOf(11260.00).setScale(2))
        taxRow2.setSourceSum(BigDecimal.valueOf(11260.00).setScale(2))
        taxRow2.setSum(BigDecimal.valueOf(0.00).setScale(2))

        taxRatesReturn.add(taxRow1)
        taxRatesReturn.add(taxRow2)

        retrn.setTaxRates(taxRatesReturn)

        cash.setSum(BigDecimal.valueOf(2244.96).setScale(2))
        cash.setSumOfCommission(BigDecimal.valueOf(10.00).setScale(2))
        cash.setNumberOfReceipts(4)

        doc.setDocumentHeader(header)
        doc.setTotalsBySale(realiz)
        doc.setTotalsByReturn(retrn)
        doc.setTotalsByCash(cash)
        doc.setServiceInputSum(BigDecimal.valueOf(8.00).setScale(2))
        doc.setServiceOutputSum(BigDecimal.valueOf(10.00).setScale(2))

        val outputStream = FileOutputStream("newReport.xml")

        XmlMapperUtil.toXML(doc, outputStream)
    }

    @Test
    fun loadNewReport() {

        val reportDocument = XmlMapperUtil.fromXML(XmlReportDocument::class.java, FileInputStream("newReport.xml"))
        assertNotNull(reportDocument)

        val header = reportDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals("5555555-0f0f-4a4a-b3b3-000000000000", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("030222", header.getTime())
        assertEquals(33555L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals("434343.1.1616", header.getFiscalNumber())
        assertEquals("53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c", header.getPreviousDocumentHash())
        assertEquals(false, header.isRevoked())
        assertEquals(true, header.isOffline())
        assertEquals(true, header.isTest())

        val realiz = reportDocument.getTotalsBySale()
        assertNotNull(realiz)
        assertEquals(BigDecimal.valueOf(3333.66).setScale(2), realiz?.getSum())
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), realiz?.getPawnSumIssued())
        assertEquals(BigDecimal.valueOf(0.55).setScale(2), realiz?.getPawnSumReceived())
        assertEquals(3, realiz?.getNumberOfReceipts())

        val retrn = reportDocument.getTotalsByReturn()
        assertNotNull(retrn)
        assertEquals(BigDecimal.valueOf(4444.77).setScale(2), retrn?.getSum())
        assertEquals(BigDecimal.valueOf(0.66).setScale(2), retrn?.getPawnSumIssued())
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), retrn?.getPawnSumReceived())
        assertEquals(4, retrn?.getNumberOfReceipts())

        var payForms = realiz?.getPayForms()
        assertNotNull(payForms)
        payForms?.forEach {
            if (it.getRow() == 1) {
                assertEquals(BigDecimal.valueOf(250.12).setScale(2), it.getSum())
                assertEquals("2", it.getCode())
                assertEquals("КАРТКА", it.getName())
            } else {
                assertEquals(BigDecimal.valueOf(6084.40).setScale(2), it.getSum())
                assertEquals("0", it.getCode())
                assertEquals("Готівка", it.getName())
            }
        }

        var taxRates = realiz?.getTaxRates()
        assertNotNull(taxRates)
        taxRates?.forEach {
            if (it.getRow() == 1) {
                assertEquals(1, it.getType())
                assertEquals("Акциз", it.getName())
                assertEquals("М", it.getLetter())
                assertEquals(BigDecimal.valueOf(5.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(259.90).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(244.90).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(11.66).setScale(2), it.getSum())
            } else {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("В", it.getLetter())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(1260.00).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(1260.00).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSum())
            }
        }

        payForms = retrn?.getPayForms()
        assertNotNull(payForms)
        payForms?.forEach {
            if (it.getRow() == 1) {
                assertEquals(BigDecimal.valueOf(249.11).setScale(2), it.getSum())
                assertEquals("2", it.getCode())
                assertEquals("КАРТКА", it.getName())
            } else {
                assertEquals(BigDecimal.valueOf(1260.28).setScale(2), it.getSum())
                assertEquals("1", it.getCode())
                assertEquals("Банківська картка", it.getName())
            }
        }

        taxRates = retrn?.getTaxRates()
        assertNotNull(taxRates)
        taxRates?.forEach {
            if (it.getRow() == 1) {
                assertEquals(1, it.getType())
                assertEquals("Акциз", it.getName())
                assertEquals("М", it.getLetter())
                assertEquals(BigDecimal.valueOf(5.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(824.70).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(779.70).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(37.12).setScale(2), it.getSum())
            } else {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("Б", it.getLetter())
                assertEquals(BigDecimal.valueOf(7.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(11260.00).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(11260.00).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSum())
            }
        }

        val cash = reportDocument.getTotalsByCash()
        assertNotNull(cash)
        assertEquals(BigDecimal.valueOf(2244.96).setScale(2), cash?.getSum())
        assertEquals(BigDecimal.valueOf(10.00).setScale(2), cash?.getSumOfCommission())
        assertEquals(4, cash?.getNumberOfReceipts())

        assertEquals(BigDecimal.valueOf(8.00).setScale(2), reportDocument.getServiceInputSum())
        assertEquals(BigDecimal.valueOf(10.00).setScale(2), reportDocument.getServiceOutputSum())
    }
}
