package me.ugreek85.view.xml.impl.document

import me.ugreek85.view.xml.impl.utils.XmlMapperUtil
import kotlin.test.*

internal class XmlDocumentHeaderTest {

    @Test
    fun minFilling() {

        val header = XmlDocumentHeader()
        val xmlHeader = XmlMapperUtil.toXMLString(header)
        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
            "<xmlDocumentHeader>\n" +
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
            "</xmlDocumentHeader>", xmlHeader)

    }

    @Test
    fun maxFilling() {

        val header = XmlDocumentHeader()
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

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
            "<xmlDocumentHeader>\n" +
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
            "   <CASHIER>Test Cashier Name</CASHIER>\n" +
            "   <VER>1</VER>\n" +
            "   <ORDERTAXNUM>123131231313</ORDERTAXNUM>\n" +
            "   <OFFLINE>true</OFFLINE>\n" +
            "   <PREVDOCHASH>Test Prev Doc Hash</PREVDOCHASH>\n" +
            "   <REVOKED>true</REVOKED>\n" +
            "   <TESTING>true</TESTING>\n" +
            "</xmlDocumentHeader>", xmlHeader)

    }

    @Test
    fun setUid() {
        val header = XmlDocumentHeader()
        header.setUid("2bfa4f05-012c-4b56-bbad-4b954a48a906")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID>2bfa4f05-012c-4b56-bbad-4b954a48a906</UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun getSellerId() {
        val header = XmlDocumentHeader()
        header.setSellerId("test seller id")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN>test seller id</TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setCompanyName() {
        val header = XmlDocumentHeader()
        header.setCompanyName("test name")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM>test name</ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setPointName() {
        val header = XmlDocumentHeader()
        header.setPointName("test pt name")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM>test pt name</POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setPointAddress() {
        val header = XmlDocumentHeader()
        header.setPointAddress("test pt address")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <POINTADDR>test pt address</POINTADDR>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setDate() {
        val header = XmlDocumentHeader()
        header.setDate("test date")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE>test date</ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setTime() {
        val header = XmlDocumentHeader()
        header.setTime("test time")

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME>test time</ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setDocumentNumber() {
        val header = XmlDocumentHeader()
        header.setDocumentNumber(1234567)

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>1234567</ORDERNUM>\n" +
                "   <CASHDESKNUM>0</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setRegistrarLocalNumber() {
        val header = XmlDocumentHeader()
        header.setRegistrarLocalNumber(1234567)

        val xmlHeader = XmlMapperUtil.toXMLString(header)

        assertEquals("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<xmlDocumentHeader>\n" +
                "   <UID></UID>\n" +
                "   <TIN></TIN>\n" +
                "   <ORGNM></ORGNM>\n" +
                "   <POINTNM></POINTNM>\n" +
                "   <ORDERDATE></ORDERDATE>\n" +
                "   <ORDERTIME></ORDERTIME>\n" +
                "   <ORDERNUM>0</ORDERNUM>\n" +
                "   <CASHDESKNUM>1234567</CASHDESKNUM>\n" +
                "   <CASHREGISTERNUM>0</CASHREGISTERNUM>\n" +
                "   <VER>0</VER>\n" +
                "</xmlDocumentHeader>", xmlHeader)
    }

    @Test
    fun setRegistrarId() {
    }

    @Test
    fun setCashierName() {
    }

    @Test
    fun setVersion() {
    }

    @Test
    fun setFiscalNumber() {
    }

    @Test
    fun setRevoked() {
    }

    @Test
    fun setTest() {
    }

    @Test
    fun setOffline() {
    }

    @Test
    fun setPreviousDocumentHash() {
    }
}
