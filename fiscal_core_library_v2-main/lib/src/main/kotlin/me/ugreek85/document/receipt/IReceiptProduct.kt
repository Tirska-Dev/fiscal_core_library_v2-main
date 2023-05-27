package me.ugreek85.document.receipt

import me.ugreek85.document.common.IRow
import java.math.BigDecimal

interface IReceiptProduct : IRow {

    fun setCode(code: String?)
    fun getCode(): String?

    fun setBarCode(barCode: String?)
    fun getBarCode(): String?

    fun setNationalCode(nationalCode: String?)
    fun getNationalCode(): String?

    fun setServiceCode(serviceCode: String?)
    fun getServiceCode(): String?

    fun setName(name: String?)
    fun getName(): String?

    fun setDescription(description: String?)
    fun getDescription(): String?

    fun setUnitCode(unitCode: String?)
    fun getUnitCode(): String?

    fun setUnitName(unitName: String?)
    fun getUnitName(): String?

    fun setAmount(amount: BigDecimal?)
    fun getAmount(): BigDecimal?

    fun setPrice(price: BigDecimal?)
    fun getPrice(): BigDecimal?

    fun setTaxRateLetter(taxRateLetter: String?)
    fun getTaxRateLetter(): String?

    fun setCost(cost: BigDecimal?)
    fun getCost(): BigDecimal?

    fun setComment(comment: String?)
    fun getComment(): String?

    fun getPawnMoneyDirection(): PawnMoneyDirection?
    fun setPawnMoneyDirection(pawnMoneyDirection: PawnMoneyDirection?)

    fun setDiscountSum(discountSum: BigDecimal?)
    fun getDiscountSum(): BigDecimal?

    fun setExciseLabels(exciseLabels: List<IExciseLabel>?)
    fun getExciseLabels(): List<IExciseLabel>?
}
