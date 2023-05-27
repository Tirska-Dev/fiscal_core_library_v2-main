package me.ugreek85.view.xml.impl.receipt

import me.ugreek85.document.common.InternalDocumentSubType
import me.ugreek85.document.common.InternalDocumentType
import me.ugreek85.view.xml.impl.document.XmlDocumentHeader
import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import kotlin.test.*

internal class XmlReceiptHeaderTest {

    private val xmlReceiptHeader = XmlReceiptHeader()

    @Test
    fun minFilling() {

        val header = XmlReceiptHeader()
        val xmlHeader = XmlMapperUtil.toXMLString(header)
        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
            "<xmlReceiptHeader>\n" +
            "   <UID></UID>\n" +
            "   <TIN></TIN>\n" +
            "   <ORGNM></ORGNM>\n" +
            "   <POINTNM></POINTNM>\n" +
            "   <ORDERDATE></ORDERDATE>\n" +
            "   <ORDERTIME></ORDERTIME>\n" +
            "   <ORDERNUM>0</ORDERNUM>\n" +
            "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
            "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
            "   <VER>0</VER>\n" +
            "</xmlReceiptHeader>", xmlHeader)
    }

    @Test
    fun maxFilling() {

        val header = XmlReceiptHeader()
        header.setUid("2bfa4f05-012c-4b56-bbad-4b954a48a906")
        header.setSellerId("test seller id")
        header.setVatNumber("11223344")
        header.setCompanyName("Test Company Name")
        header.setPointName("Test Point Name")
        header.setPointAddress("Test Point Address")
        header.setDate("112233")
        header.setTime("223344")
        header.setDocumentNumber(1)
        header.setRegistrarLocalNumber(1)
        header.setRegistrarId(100000001)
        header.setFiscalNumber("123131231313")
        header.setCashierName("Test Cashier Name")
        header.setOffline(true)
        header.setPreviousDocumentHash("Test Prev Doc Hash")
        header.setRevoked(true)
        header.setTest(true)
        header.setVersion(1)

        header.setInternalDocumentType(InternalDocumentType.SALE)
        header.setInternalDocumentSubType(InternalDocumentSubType.SALE)
        header.setComment("Test Comment")
        header.setOperationTypeName("Test Operation Type Name")
        header.setVehicleRegistrationNumber("Test Vehicle Registration Number")
        header.setLogoUrl("Test Logo Url")
        header.setCanceled(true)
        header.setFiscalNumberForCancel("Test Fiscal Number For Cancel")
        header.setRevokedLastOnlineDocument(true)
        header.setFiscalNumberForReturn("Test Fiscal Number For Return")


        val xmlHeader = XmlMapperUtil.toXMLString(header)

        println(xmlHeader)
        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
            "<xmlReceiptHeader>\n" +
            "   <DOCTYPE>0</DOCTYPE>\n" +
            "   <DOCSUBTYPE>0</DOCSUBTYPE>\n" +
            "   <UID>2bfa4f05-012c-4b56-bbad-4b954a48a906</UID>\n" +
            "   <TIN>test seller id</TIN>\n" +
            "   <IPN>11223344</IPN>\n" +
            "   <ORGNM>Test Company Name</ORGNM>\n" +
            "   <POINTNM>Test Point Name</POINTNM>\n" +
            "   <POINTADDR>Test Point Address</POINTADDR>\n" +
            "   <ORDERDATE>112233</ORDERDATE>\n" +
            "   <ORDERTIME>223344</ORDERTIME>\n" +
            "   <ORDERNUM>1</ORDERNUM>\n" +
            "   <CASHDESKNUM>1</CASHDESKNUM>\n" +
            "   <CASHREGISTERNUM>100000001</CASHREGISTERNUM>\n" +
            "   <ORDERRETNUM>Test Fiscal Number For Return</ORDERRETNUM>\n" +
            "   <ORDERSTORNUM>Test Fiscal Number For Cancel</ORDERSTORNUM>\n" +
            "   <OPERTYPENM>Test Operation Type Name</OPERTYPENM>\n" +
            "   <VEHICLERN>Test Vehicle Registration Number</VEHICLERN>\n" +
            "   <REVOKELASTONLINEDOC>true</REVOKELASTONLINEDOC>\n" +
            "   <CASHIER>Test Cashier Name</CASHIER>\n" +
            "   <LOGOURL>Test Logo Url</LOGOURL>\n" +
            "   <COMMENT>Test Comment</COMMENT>\n" +
            "   <VER>1</VER>\n" +
            "   <ORDERTAXNUM>123131231313</ORDERTAXNUM>\n" +
            "   <OFFLINE>true</OFFLINE>\n" +
            "   <PREVDOCHASH>Test Prev Doc Hash</PREVDOCHASH>\n" +
            "   <REVOKED>true</REVOKED>\n" +
            "   <STORNED>true</STORNED>\n" +
            "   <TESTING>true</TESTING>\n" +
            "</xmlReceiptHeader>", xmlHeader)
    }

    @Test
    fun setInternalDocumentType() {

        xmlReceiptHeader.setInternalDocumentType(InternalDocumentType.OPEN_SHIFT)
        assertEquals(InternalDocumentType.OPEN_SHIFT, xmlReceiptHeader.getInternalDocumentType())
    }

    @Test
    fun setInternalDocumentSubType() {

        xmlReceiptHeader.setInternalDocumentSubType(InternalDocumentSubType.CANCEL)
        assertEquals(InternalDocumentSubType.CANCEL, xmlReceiptHeader.getInternalDocumentSubType())
    }

    @Test
    fun setFiscalNumberForReturn() {
    }

    @Test
    fun setFiscalNumberForCancel() {
    }

    @Test
    fun setRevokedLastOnlineDocument() {
    }

    @Test
    fun setLogoUrl() {
    }

    @Test
    fun setCanceled() {
    }

    @Test
    fun setOperationTypeName() {
    }

    @Test
    fun setVehicleRegistrationNumber() {
    }
}
