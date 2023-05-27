package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.IDocumentTaxRate
import me.ugreek85.document.receipt.IReceiptPayForm
import me.ugreek85.document.receipt.IReceiptProduct
import me.ugreek85.document.receipt.IReceiptTotal
import me.ugreek85.utils.DocumentTypeUtil
import me.ugreek85.utils.ResourcesUtil
import me.ugreek85.view.xml.impl.document.XmlDocumentTaxRate
import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import java.io.*
import java.math.BigDecimal
import kotlin.test.*


internal class XmlReceiptDocumentTest {

    @Test
    fun loadBeginOfflineSession() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("beginOfflineSession.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.BeginOffline, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("8ae2fdfe-b51a-4119-962d-ccbcb4e719e6", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("210222", header.getTime())
        assertEquals(33512L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals(false, (header as XmlReceiptHeader).isRevokedLastOnlineDocument())
        assertEquals(true, header.isOffline())
        assertEquals("432414.1.1610", header.getFiscalNumber())
        assertEquals("1610", header.getCheckNumber())
        assertEquals("21:02:22 17.01.2022", header.getFormattedDateTime())
    }

    @Test
    fun loadCancel() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("cancel.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals("c9831340-55c0-43ea-99d8-eb43664df00c", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205523", header.getTime())
        assertEquals(33443L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals("184087284", (header as XmlReceiptHeader).getFiscalNumberForCancel())
        assertNull(header.getCheckNumber())
        assertEquals("20:55:23 17.01.2022", header.getFormattedDateTime())
    }

    @Test
    fun loadCashWithdrawal() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("cashWithdrawal.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.CashWithdrawal, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("c50d7d6c-6504-4272-aab9-d1e930150b34", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205524", header.getTime())
        assertEquals(33445L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals("ReqComment", (header as XmlReceiptHeader).getComment())
        assertNull(header.getCheckNumber())
        assertEquals("20:55:24 17.01.2022", header.getFormattedDateTime())

        val total = receiptDocument.getTotal()
        assertNotNull(total)
        assertEquals(BigDecimal.valueOf(1929.80).setScale(2), total.getSum())
        assertEquals(BigDecimal.valueOf(0.04), total.getRoundingSum())
        assertEquals(BigDecimal.valueOf(1929.84), total.getSumWithoutRounding())
        assertEquals(BigDecimal.valueOf(0.00).setScale(2), total.getCommissionSum())

        val payForms = receiptDocument.getPayForms()
        assertNotNull(payForms)
        payForms.forEach {
            assertEquals("0", it.getCode())
            assertEquals("Готівка", it.getName())
            assertEquals(BigDecimal.valueOf(1929.80).setScale(2), it.getSum())
            assertEquals(BigDecimal.valueOf(2000.00).setScale(2), it.getProvidedSum())
            assertEquals(BigDecimal.valueOf(70.20).setScale(2), it.getSumOfRemain())
        }

        val taxRates = receiptDocument.getTaxRates()
        assertNotNull(taxRates)
        taxRates.forEach {
            assertEquals(0, it.getType())
            assertEquals("ПДВ", it.getName())
            assertEquals("В", it.getLetter())
            assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getPercent())
            assertEquals(false, it.getSign())
            assertEquals(BigDecimal.valueOf(1929.84).setScale(2), it.getTurnoverSum())
            assertEquals(BigDecimal.valueOf(1929.84).setScale(2), it.getSourceSum())
            assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSum())
        }

        val bodies = receiptDocument.getProducts()
        var row = 1
        assertNotNull(bodies)
        bodies.forEach {
            assertEquals("1", it.getCode())
            assertEquals("1", it.getUnitCode())
            assertEquals("sht.", it.getUnitName())
            assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
            assertEquals("В", it.getTaxRateLetter())
            assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getDiscountSum())
            assertEquals("Comment", it.getComment())
            if (row == 1) {
                assertEquals("Return deposit body ", it.getName())
                assertEquals("dog. 251245 ", it.getDescription())
                assertEquals(BigDecimal.valueOf(1211.42).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(1211.42).setScale(2), it.getCost())
            }
            else {
                assertEquals("Pay a deposit procents", it.getName())
                assertEquals("dog 251245 ", it.getDescription())
                assertEquals(BigDecimal.valueOf(718.42).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(718.42).setScale(2), it.getCost())
            }
            row++
        }
    }

    @Test
    fun loadCloseShift() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("closeShift.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.CloseShift, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("55458629-0f34-4a3e-b3e8-00499b2c6315", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("210219", header.getTime())
        assertEquals(33509L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertNull(header.getCheckNumber())
        assertEquals("21:02:19 17.01.2022", header.getFormattedDateTime())
    }

    @Test
    fun loadFinishOfflineSession() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("finishOfflineSession.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.FinishOffline, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("6db96a46-d8d0-4188-8074-31a304b05c5d", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("210423", header.getTime())
        assertEquals(33514L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals("432414.3.572", header.getFiscalNumber())
        assertEquals(true, header.isOffline())
        assertEquals("53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c", header.getPreviousDocumentHash())
        assertEquals("572", header.getCheckNumber())
        assertEquals("21:04:23 17.01.2022", header.getFormattedDateTime())
    }

    @Test
    fun loadOpenShift() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("openShift.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.OpenShift, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("b55f1710-82a6-4d7c-8af3-568bf091ba89", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("210011", header.getTime())
        assertEquals(33503L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertNull(header.getCheckNumber())
        assertEquals("21:00:11 17.01.2022", header.getFormattedDateTime())
    }

    @Test
    fun loadReturn() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("return.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.Return, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("635aeb21-2d39-4859-9f36-ce6337f2ae3d", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205807", header.getTime())
        assertEquals(33497L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertEquals("2346589", (header as XmlReceiptHeader).getFiscalNumberForReturn())
        assertEquals("Спасибо за Покупку!", header.getComment())
        assertNull(header.getCheckNumber())
        assertEquals("20:58:07 17.01.2022", header.getFormattedDateTime())

        val total = receiptDocument.getTotal()
        assertNotNull(total)
        assertEquals(BigDecimal.valueOf(6584.43).setScale(2), total.getSum())
        assertEquals(BigDecimal.valueOf(0.02), total.getRoundingSum())
        assertEquals(BigDecimal.valueOf(6584.45), total.getSumWithoutRounding())
        assertEquals(BigDecimal.valueOf(25.00).setScale(2), total.getDiscountSum())

        val payForms = receiptDocument.getPayForms()
        assertNotNull(payForms)
        var row = 1
        payForms.forEach {
            if (row == 1) {
                assertEquals("0", it.getCode())
                assertEquals("Готівка", it.getName())
                assertEquals(BigDecimal.valueOf(6084.40).setScale(2), it.getSum())
                assertEquals(BigDecimal.valueOf(6100.00).setScale(2), it.getProvidedSum())
                assertEquals(BigDecimal.valueOf(15.60).setScale(2), it.getSumOfRemain())
            }
            else if (row == 2) {
                assertEquals("1", it.getCode())
                assertEquals("Банківська картка", it.getName())
                assertEquals(BigDecimal.valueOf(400.02).setScale(2), it.getSum())
            }
            else {
                assertEquals("2", it.getCode())
                assertEquals("Попередня оплата", it.getName())
                assertEquals(BigDecimal.valueOf(100.01).setScale(2), it.getSum())
            }
            row++
        }

        val taxRates = receiptDocument.getTaxRates()
        assertNotNull(taxRates)
        row = 1
        taxRates.forEach {
            if (row == 1) {
                assertEquals(1, it.getType())
                assertEquals("Акциз", it.getName())
                assertEquals("М", it.getLetter())
                assertEquals(BigDecimal.valueOf(5.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(259.90).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(244.90).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(11.66).setScale(2), it.getSum())
            }
            else if (row == 2) {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("A", it.getLetter())
                assertEquals(BigDecimal.valueOf(20.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(575.05).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(538.39).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(89.73).setScale(2), it.getSum())
            }
            else if (row == 3) {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("Б", it.getLetter())
                assertEquals(BigDecimal.valueOf(7.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(3405.00).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(3405.00).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(222.76).setScale(2), it.getSum())
            }
            else if (row == 4) {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("В", it.getLetter())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(111.80).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(111.80).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSum())
            }
            else {
                assertEquals(0, it.getType())
                assertEquals("ПДВ", it.getName())
                assertEquals("Д", it.getLetter())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getPercent())
                assertEquals(false, it.getSign())
                assertEquals(BigDecimal.valueOf(2517.60).setScale(2), it.getTurnoverSum())
                assertEquals(BigDecimal.valueOf(2517.60).setScale(2), it.getSourceSum())
                assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSum())
            }
            row++
        }

        val bodies = receiptDocument.getProducts()
        assertNotNull(bodies)
        row = 1
        bodies.forEach {
            if (row == 1) {
                assertEquals("42342", it.getCode())
                assertEquals("2009", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("A", it.getTaxRateLetter())
                assertEquals(BigDecimal.valueOf(10.00).setScale(2), it.getDiscountSum())
                assertEquals("Этот товар выбирают 98% наших покупателей!", it.getComment())
                assertEquals("Коньки", it.getName())
                assertEquals("Элит", it.getDescription())
                assertEquals(BigDecimal.valueOf(315.15).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(315.15).setScale(2), it.getCost())
            }
            else if (row == 2) {
                assertEquals("12332", it.getCode())
                assertEquals("2009", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(10.000).setScale(3), it.getAmount())
                assertEquals("Б", it.getTaxRateLetter())
                assertEquals("На складе осталось 5 шт этого товара!", it.getComment())
                assertEquals("Шлем", it.getName())
                assertEquals("Стандарт", it.getDescription())
                assertEquals(BigDecimal.valueOf(340.50).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(3405.00).setScale(2), it.getCost())
            }
            else if (row == 3) {
                assertEquals("42342", it.getCode())
                assertEquals("2009", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(2.000).setScale(3), it.getAmount())
                assertEquals("В", it.getTaxRateLetter())
                assertEquals("Вам начислен Бонус за покупку этого товара 20!", it.getComment())
                assertEquals("Шайба", it.getName())
                assertEquals("Канада", it.getDescription())
                assertEquals(BigDecimal.valueOf(55.90).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(111.80).setScale(2), it.getCost())
            }
            else if (row == 4) {
                assertEquals("8765", it.getCode())
                assertEquals("2009", it.getUnitCode())
                assertEquals("л", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("AМ", it.getTaxRateLetter())
                assertEquals(BigDecimal.valueOf(15.00).setScale(2), it.getDiscountSum())
                assertEquals("Этот товар Вам достался со скидкой 15.00!", it.getComment())
                assertEquals("Спирт", it.getName())
                assertEquals("Экстра", it.getDescription())
                assertEquals(BigDecimal.valueOf(259.90).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(259.90).setScale(2), it.getCost())
            }
            else if (row == 5) {
                assertEquals("987", it.getCode())
                assertEquals("2009", it.getUnitCode())
                assertEquals("кг", it.getUnitName())
                assertEquals(BigDecimal.valueOf(8.000).setScale(3), it.getAmount())
                assertEquals("Д", it.getTaxRateLetter())
                assertEquals("Лёд", it.getName())
                assertEquals("ХимПром", it.getDescription())
                assertEquals(BigDecimal.valueOf(314.70).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(2517.60).setScale(2), it.getCost())
            }
            row++
        }
    }

    @Test
    fun loadSale() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("sale.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.Sale, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("8b826687-4521-496b-a6cd-a2e5779bd2bf", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205522", header.getTime())
        assertEquals(33440L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertNull(header.getCheckNumber())
        assertEquals("20:55:22 17.01.2022", header.getFormattedDateTime())

        val total = receiptDocument.getTotal()
        assertNotNull(total)
        assertEquals(BigDecimal.valueOf(203.12).setScale(2), total.getSum())
        assertEquals(BigDecimal.valueOf(2.00).setScale(2), total.getDiscountSum())

        val payForms = receiptDocument.getPayForms()
        assertNotNull(payForms)
        payForms.forEach {
            assertEquals("2", it.getCode())
            assertEquals("КАРТКА", it.getName())
            assertEquals(BigDecimal.valueOf(203.12).setScale(2), it.getSum())
            assertEquals(BigDecimal.valueOf(203.12).setScale(2), it.getProvidedSum())
            assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSumOfRemain())
        }

        val taxRates = receiptDocument.getTaxRates()
        assertNotNull(taxRates)
        taxRates.forEach {
            assertEquals(0, it.getType())
            assertEquals("ПДВ", it.getName())
            assertEquals("A", it.getLetter())
            assertEquals(BigDecimal.valueOf(20.00).setScale(2), it.getPercent())
            assertEquals(false, it.getSign())
            assertEquals(BigDecimal.valueOf(205.12).setScale(2), it.getTurnoverSum())
            assertEquals(BigDecimal.valueOf(203.12).setScale(2), it.getSourceSum())
            assertEquals(BigDecimal.valueOf(33.85).setScale(2), it.getSum())
        }

        val bodies = receiptDocument.getProducts()
        assertNotNull(bodies)
        var row = 1
        bodies.forEach {
            if (row == 1) {
                assertEquals("501000334", it.getCode())
                assertEquals("4", it.getUnitCode())
                assertEquals("м/п", it.getUnitName())
                assertEquals(BigDecimal.valueOf(2.500).setScale(3), it.getAmount())
                assertEquals("A", it.getTaxRateLetter())
                assertEquals("Дріт ПВС 2 * 2.5", it.getName())
                assertEquals(BigDecimal.valueOf(22.49).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(56.23).setScale(2), it.getCost())
            }
            else if (row == 2) {
                assertEquals("501005869", it.getCode())
                assertEquals("1", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("A", it.getTaxRateLetter())
                assertEquals("Касета до подовжувача MAKEL б/заз. 3яч. MGP121", it.getName())
                assertEquals(BigDecimal.valueOf(81.90).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(81.90).setScale(2), it.getCost())
            }
            else if (row == 3) {
                assertEquals("501001407", it.getCode())
                assertEquals("1", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("A", it.getTaxRateLetter())
                assertEquals("Розетка 2-а б/заз. VIKO Carmen крем", it.getName())
                assertEquals(BigDecimal.valueOf(47.90).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(47.90).setScale(2), it.getCost())
            }
            else if (row == 4) {
                assertEquals("534014477", it.getCode())
                assertEquals("1", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("A", it.getTaxRateLetter())
                assertEquals("Вилка штепсельна з заз. LIDER LVL16-074 біл.", it.getName())
                assertEquals(BigDecimal.valueOf(19.09).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(19.09).setScale(2), it.getCost())
            }
            row++
        }
    }

    @Test
    fun loadServiceInput() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("serviceInput.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.ServiceInput, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("0a21dff8-11f0-434a-85c9-4468280b95fa", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205525", header.getTime())
        assertEquals(33447L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertNull(header.getCheckNumber())
        assertEquals("20:55:25 17.01.2022", header.getFormattedDateTime())

        val total = receiptDocument.getTotal()
        assertEquals(BigDecimal.valueOf(8.00).setScale(2), total?.getSum())
    }

    @Test
    fun loadServiceOutput() {
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, ResourcesUtil.getResource("serviceOutput.xml"))
        assertNotNull(receiptDocument)

        val header = receiptDocument.getDocumentHeader()
        assertNotNull(header)
        assertEquals(DocumentType.ServiceOutput, DocumentTypeUtil.detectDocumentTypeByHeader(header))
        assertEquals("2e136c51-81a8-4d92-ae94-cc0e0d0ba89a", header.getUid())
        assertEquals("34554363", header.getSellerId())
        assertEquals("123456789020", header.getVatNumber())
        assertEquals("Тестовий платник 4", header.getCompanyName())
        assertEquals("Магазин Іван-Тест", header.getPointName())
        assertEquals("УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1", header.getPointAddress())
        assertEquals("17012022", header.getDate())
        assertEquals("205525", header.getTime())
        assertEquals(33446L, header.getDocumentNumber())
        assertEquals(24L, header.getRegistrarLocalNumber())
        assertEquals(4000066879, header.getRegistrarId())
        assertEquals("Для РРО № 4000066879", header.getCashierName())
        assertEquals(1, header.getVersion())
        assertNull(header.getCheckNumber())
        assertEquals("20:55:25 17.01.2022", header.getFormattedDateTime())

        val total = receiptDocument.getTotal()
        assertEquals(BigDecimal.valueOf(5.00).setScale(2), total?.getSum())
    }


    @Test
    @Suppress("NAME_SHADOWING")
    fun createReceiptDocument() {

        val doc = XmlReceiptDocument()
        val header = XmlReceiptHeader()
        val total: IReceiptTotal = XmlReceiptTotal()
        val payForms = mutableListOf<XmlReceiptPayForm>()
        val taxRates = mutableListOf<XmlDocumentTaxRate>()
        val bodies = mutableListOf<XmlReceiptProduct>()

        DocumentTypeUtil.fillDocumentHeaderByDocumentType(header, DocumentType.Sale)
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
        header.setRevokedLastOnlineDocument(false)
        header.setOffline(true)
        header.setFiscalNumber("434343.1.1616")
        header.setFiscalNumberForCancel("1818181818")
        header.setFiscalNumberForReturn("23232323")
        header.setPreviousDocumentHash("53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c")
        header.setComment("Comment")

        total.setSum(BigDecimal.valueOf(1999.91).setScale(2))
        total.setRoundingSum(BigDecimal.valueOf(0.21).setScale(2))
        total.setSumWithoutRounding(BigDecimal.valueOf(1999.70).setScale(2))
        total.setCommissionSum(BigDecimal.valueOf(0.00).setScale(2))
        total.setDiscountSum(BigDecimal.valueOf(2.00).setScale(2))

        val payRow1 = XmlReceiptPayForm()
        payRow1.setRow(1)
        payRow1.setCode("2")
        payRow1.setName("КАРТКА")
        payRow1.setSum(BigDecimal.valueOf(250.12).setScale(2))
        payRow1.setProvidedSum(BigDecimal.valueOf(250.12).setScale(2))
        payRow1.setSumOfRemain(BigDecimal.valueOf(0.00).setScale(2))

        val payRow2 = XmlReceiptPayForm()
        payRow2.setRow(2)
        payRow2.setCode("0")
        payRow2.setName("Готівка")
        payRow2.setSum(BigDecimal.valueOf(6084.40).setScale(2))
        payRow2.setProvidedSum(BigDecimal.valueOf(6100.00).setScale(2))
        payRow2.setSumOfRemain(BigDecimal.valueOf(15.60).setScale(2))

        payForms.add(payRow1)
        payForms.add(payRow2)

        val taxRow1 = XmlDocumentTaxRate()
        taxRow1.setRow(1)
        taxRow1.setType(1)
        taxRow1.setName("Акциз")
        taxRow1.setLetter("М")
        taxRow1.setPercent(BigDecimal.valueOf(5.00).setScale(2))
        taxRow1.setSign(false)
        taxRow1.setTurnoverSum(BigDecimal.valueOf(259.90).setScale(2))
        taxRow1.setSourceSum(BigDecimal.valueOf(244.90).setScale(2))
        taxRow1.setSum(BigDecimal.valueOf(11.66).setScale(2))

        val taxRow2 = XmlDocumentTaxRate()
        taxRow2.setRow(2)
        taxRow2.setType(0)
        taxRow2.setName("ПДВ")
        taxRow2.setLetter("В")
        taxRow2.setPercent(BigDecimal.valueOf(0.00).setScale(2))
        taxRow2.setSign(false)
        taxRow2.setTurnoverSum(BigDecimal.valueOf(1260.00).setScale(2))
        taxRow2.setSourceSum(BigDecimal.valueOf(1260.00).setScale(2))
        taxRow2.setSum(BigDecimal.valueOf(0.00).setScale(2))

        taxRates.add(taxRow1)
        taxRates.add(taxRow2)

        val rowProduct1 = XmlReceiptProduct()
        rowProduct1.setRow(1)
        rowProduct1.setCode("111222")
        rowProduct1.setUnitCode("1")
        rowProduct1.setUnitName("шт")
        rowProduct1.setAmount(BigDecimal.valueOf(1.000).setScale(3))
        rowProduct1.setTaxRateLetter("А")
        rowProduct1.setName("Машина")
        rowProduct1.setDescription("Ford Focus")
        rowProduct1.setDiscountSum(BigDecimal.valueOf(10.00).setScale(2))
        rowProduct1.setPrice(BigDecimal.valueOf(1909.09).setScale(2))
        rowProduct1.setCost(BigDecimal.valueOf(1909.09).setScale(2))
        rowProduct1.setComment("Осталось - 23 шт.")

        bodies.add(rowProduct1)

        doc.setDocumentHeader(header)
        doc.setTotal(total)
        doc.setPayForms(payForms as List<IReceiptPayForm>)
        doc.setTaxRates(taxRates as List<IDocumentTaxRate>)
        doc.setProducts(bodies as List<IReceiptProduct>)

        val outputStream = ByteArrayOutputStream()
        XmlMapperUtil.toXML(doc, outputStream)

        /////////////////////////////////////////////////////////

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val receiptDocument = XmlMapperUtil.fromXML(XmlReceiptDocument::class.java, inputStream)
        assertNotNull(receiptDocument)

        receiptDocument.getDocumentHeader().let { header ->
            assertNotNull(header)
            assertEquals(DocumentType.Sale, DocumentTypeUtil.detectDocumentTypeByHeader(header))
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
            assertEquals(true, header.isOffline())
            assertEquals("434343.1.1616", header.getFiscalNumber())
            assertEquals("1616", header.getCheckNumber())
            assertEquals("03:02:22 17.01.2022", header.getFormattedDateTime())
            assertEquals(
                "53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c",
                header.getPreviousDocumentHash()
            )
            assertEquals("23232323", (header as XmlReceiptHeader).getFiscalNumberForReturn())
            assertEquals(false, header.isRevokedLastOnlineDocument())
            assertEquals("1818181818", header.getFiscalNumberForCancel())
            assertEquals("Comment", header.getComment())

        }

        receiptDocument.getTotal().let { total ->
            assertNotNull(total)
            assertEquals(BigDecimal.valueOf(1999.91).setScale(2), total.getSum())
            assertEquals(BigDecimal.valueOf(0.21).setScale(2), total.getRoundingSum())
            assertEquals(BigDecimal.valueOf(1999.70).setScale(2), total.getSumWithoutRounding())
            assertEquals(BigDecimal.valueOf(0.00).setScale(2), total.getCommissionSum())
            assertEquals(BigDecimal.valueOf(2.00).setScale(2), total.getDiscountSum())
        }

        receiptDocument.getPayForms().let { payForms ->
            assertNotNull(payForms)
            payForms.forEach {
                if (it.getRow() == 1) {
                    assertEquals("2", it.getCode())
                    assertEquals("КАРТКА", it.getName())
                    assertEquals(BigDecimal.valueOf(250.12).setScale(2), it.getSum())
                    assertEquals(BigDecimal.valueOf(250.12).setScale(2), it.getProvidedSum())
                    assertEquals(BigDecimal.valueOf(0.00).setScale(2), it.getSumOfRemain())
                } else {
                    assertEquals("0", it.getCode())
                    assertEquals("Готівка", it.getName())
                    assertEquals(BigDecimal.valueOf(6084.40).setScale(2), it.getSum())
                    assertEquals(BigDecimal.valueOf(6100.00).setScale(2), it.getProvidedSum())
                    assertEquals(BigDecimal.valueOf(15.60).setScale(2), it.getSumOfRemain())
                }
            }
        }

        receiptDocument.getTaxRates().let { taxRates ->

            assertNotNull(taxRates)
            taxRates.forEach {
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
        }

        receiptDocument.getProducts().let { bodies ->
            assertNotNull(bodies)
            bodies.forEach {
                assertEquals("111222", it.getCode())
                assertEquals("1", it.getUnitCode())
                assertEquals("шт", it.getUnitName())
                assertEquals(BigDecimal.valueOf(1.000).setScale(3), it.getAmount())
                assertEquals("А", it.getTaxRateLetter())
                assertEquals(BigDecimal.valueOf(10.00).setScale(2), it.getDiscountSum())
                assertEquals("Осталось - 23 шт.", it.getComment())
                assertEquals("Машина", it.getName())
                assertEquals("Ford Focus", it.getDescription())
                assertEquals(BigDecimal.valueOf(1909.09).setScale(2), it.getPrice())
                assertEquals(BigDecimal.valueOf(1909.09).setScale(2), it.getCost())
            }
        }
    }
}
