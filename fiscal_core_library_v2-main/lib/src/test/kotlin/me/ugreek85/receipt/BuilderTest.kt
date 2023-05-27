package me.ugreek85.receipt


import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.receipt.IReceiptHeader
import me.ugreek85.document.receipt.builders.SaleReturnBuilder
import me.ugreek85.utils.DocumentTypeUtil
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BuilderTest {

    @Test
    fun returnDocTest() {
        val docBuilder = SaleReturnBuilder(
            InternalDocumentSubType.RETURN,
            "2e136c51-81a8-4d92-ae94-cc0e0d0ba89a",
            "34554363",
            "123456789020",
            "Тестовий платник 4",
            "Магазин Іван-Тест",
            "УКРАЇНА, СУМСЬКА ОБЛ., М. СУМИ, вул. Соборна 1",
            "17012022",
            "205525",
            33446L,
            24L,
            4000066879,
            "Для РРО № 4000066879",
            1,
            _fiscalNumForReturn = "2346589",
            _comment = "Спасибо за Покупку!"
        )
        docBuilder.addTotals(
            BigDecimal.valueOf(2055.40).setScale(2),
            BigDecimal.valueOf(55.40).setScale(2),
        )
        docBuilder.addPayRow(
            1,
            "CARD",
            BigDecimal.valueOf(1923),
            BigDecimal.valueOf(1923),
            BigDecimal.valueOf(0),
            "2"
        )
        docBuilder.addTaxRow(
            1,
            0,
            "ПДВ",
            "А",
            BigDecimal.valueOf(20.00).setScale(2),
            false,
            BigDecimal.valueOf(205.12),
            BigDecimal.valueOf(203.12),
            BigDecimal.valueOf(33.85)
        )

        docBuilder.addProdRow(
            0,
            "Футболка",
            "Красная, XL",
            "1",
            "шт",
            BigDecimal.valueOf(160),
            BigDecimal.valueOf(250)
        )

        docBuilder.setOffline(
            "432414",
            4,
            "269847884429054",
            2055.40,
            "53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c"
        )

        val doc = docBuilder.getDocument()

        val header = doc.getDocumentHeader()

        assertEquals(DocumentType.Return, DocumentTypeUtil.detectDocumentTypeByHeader(header))
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
        assertEquals("2346589", (header as IReceiptHeader).getFiscalNumberForReturn())
        assertEquals("Спасибо за Покупку!", header.getComment())
        assertEquals(true, header.isOffline())
        assertEquals("53b0ff715c05d78eb94eb3f07807b80ec7c39aee38316532019eb6eb83799e3c", header.getPreviousDocumentHash())
        assertEquals("8860", header.getCheckNumber())
        assertEquals("432414.4.8860", header.getFiscalNumber())


        val totals = doc.getTotal()

        assertNotNull(totals)
        assertEquals(BigDecimal.valueOf(2055.40).setScale(2), totals.getSum())
        assertEquals(BigDecimal.valueOf(55.40).setScale(2), totals.getDiscountSum())

        val payForms = doc.getPayForms()

        assertNotNull(payForms)
        assertEquals("CARD", payForms[0].getName())
        assertEquals(BigDecimal.valueOf(1923), payForms[0].getSum())
        assertEquals(BigDecimal.valueOf(1923), payForms[0].getProvidedSum())
        assertEquals(BigDecimal.valueOf(0), payForms[0].getSumOfRemain())
        assertEquals("2", payForms[0].getCode())

        val taxRates = doc.getTaxRates()

        assertNotNull(taxRates)
        assertEquals(0, taxRates[0].getType())
        assertEquals("ПДВ", taxRates[0].getName())
        assertEquals("А", taxRates[0].getLetter())
        assertEquals(BigDecimal.valueOf(20.00).setScale(2), taxRates[0].getPercent())
        assertEquals(false, taxRates[0].getSign())
        assertEquals(BigDecimal.valueOf(205.12), taxRates[0].getTurnoverSum())
        assertEquals(BigDecimal.valueOf(203.12), taxRates[0].getSourceSum())
        assertEquals(BigDecimal.valueOf(33.85), taxRates[0].getSum())

        docBuilder.addProdRow(
            0,
            "Футболка",
            "Красная, XL",
            "1",
            "шт",
            BigDecimal.valueOf(160),
            BigDecimal.valueOf(250)
        )

        val prodRow = doc.getProducts()

        assertNotNull(prodRow)
        assertEquals("Футболка", prodRow[0].getName())
        assertEquals("Красная, XL", prodRow[0].getDescription())
        assertEquals("1", prodRow[0].getUnitCode())
        assertEquals("шт", prodRow[0].getUnitName())
        assertEquals(BigDecimal.valueOf(160), prodRow[0].getAmount())
        assertEquals(BigDecimal.valueOf(250), prodRow[0].getPrice())

    }

}