package me.ugreek85.view.xml.impl.report

import me.ugreek85.view.xml.impl.types.Decimal2
import me.ugreek85.view.xml.impl.utils.DecimalUtil
import org.simpleframework.xml.Element
import org.simpleframework.xml.Order
import java.math.BigDecimal

@Order(elements = ["SERVICEINPUT", "SERVICEOUTPUT"/*, "VEHICLERN"*/])
class XmlReportBody {

    @field:Element(name="SERVICEINPUT", required = false)
    private var mServiceInputSum: Decimal2? = null

    @field:Element(name="SERVICEOUTPUT", required = false)
    private var mServiceOutputSum: Decimal2? = null

    fun setServiceInputSum(sum: BigDecimal?) {
        mServiceInputSum = DecimalUtil.create(sum)
    }

    fun getServiceInputSum(): BigDecimal? {
        return DecimalUtil.getValue(mServiceInputSum)
    }

    fun setServiceOutputSum(sum: BigDecimal?) {
        mServiceOutputSum = DecimalUtil.create(sum)
    }

     fun getServiceOutputSum(): BigDecimal? {
         return DecimalUtil.getValue(mServiceOutputSum)
    }
}
