package me.ugreek85.view.html.impl


import freemarker.template.Configuration
import freemarker.template.Template
import me.ugreek85.document.common.DocumentType
import me.ugreek85.document.common.IDocument
import me.ugreek85.document.common.IDocumentHeader
import me.ugreek85.document.receipt.IReceiptDocument
import me.ugreek85.document.report.IReportDocument
import me.ugreek85.utils.DocumentTypeUtil
import me.ugreek85.view.IDocumentSerializer
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HtmlSerializer : IDocumentSerializer<ByteArray> {

    private fun getTemplateConfiguration(): Configuration {
        // Конфигурация
        val cfg = Configuration(Configuration.VERSION_2_3_31)
        cfg.setClassForTemplateLoading(HtmlSerializer::class.java, "/templates")
        cfg.defaultEncoding = Charsets.UTF_8.name()
        return cfg
    }

    override fun serialize(document: IDocument, args: Map<String, Any>?): ByteArray {
        return serializeDocument(document, args ?: emptyMap())
    }

    private fun serializeDocument(document: IDocument, args: Map<String, Any>): ByteArray
    {
        val cfg = getTemplateConfiguration()

        val root: MutableMap<String, Any> = HashMap()

        when (document) {
            is IReportDocument -> root["document"] = document
            is IReceiptDocument -> root["document"] = document
            else -> throw IllegalStateException("Unknown document implementation")
        }

        root["documentName"] = args.getOrDefault("documentName", getDocumentName(document.getDocumentHeader()))  as String
        args["shiftId"]?.let {
            root["shiftId"] = it as String
        }

        // шаблон
        val temp: Template = cfg.getTemplate(getReceiptTemplateName(document.getDocumentHeader()))
        return processTemplate(temp, root)
    }

    private fun getDocumentName(documentHeader: IDocumentHeader): String {

        return when (DocumentTypeUtil.detectDocumentTypeByHeader(documentHeader)){
            DocumentType.OpenShift -> "ПОВІДОМЛЕННЯ ПРО ВІДКРИТТЯ ЗМІНИ"
            DocumentType.CloseShift -> "ПОВІДОМЛЕННЯ ПРО ЗАКРИТТЯ ЗМІНИ"
            DocumentType.BeginOffline -> "ПОВІДОМЛЕННЯ ПРО ПЕРЕХІД КАСИ В ОФФЛАЙН РЕЖИМ"
            DocumentType.FinishOffline -> "ПОВІДОМЛЕННЯ ПРО ПЕРЕХІД КАСИ В ОНЛАЙН РЕЖИМ"
            DocumentType.ZReport -> "Z-ЗВІТ"
            DocumentType.Sale -> "ПРОДАЖ"
            DocumentType.Return -> "ПОВЕРНЕННЯ"
            DocumentType.Cancel -> "СКАСУВАННЯ"
            DocumentType.CashWithdrawal-> "ВИДАЧА ГОТІВКИ"
            DocumentType.Pawn-> "ЛОМБАРД"
            else -> TODO()
        }
    }


    private fun getReceiptTemplateName(documentHeader: IDocumentHeader): String {

        return when (DocumentTypeUtil.detectDocumentTypeByHeader(documentHeader)){
            DocumentType.OpenShift -> "service-receipt.ftl"
            DocumentType.CloseShift -> "service-receipt.ftl"
            DocumentType.BeginOffline -> "service-receipt.ftl"
            DocumentType.FinishOffline -> "service-receipt.ftl"
            DocumentType.ZReport -> "z-report.ftl"
            DocumentType.Sale -> "sale.ftl"
            DocumentType.Return -> "return.ftl"
            DocumentType.Cancel -> "cancel.ftl"
            DocumentType.CashWithdrawal-> "cash-withdrawal.ftl"
            DocumentType.Pawn-> "pawn.ftl"
            else -> TODO()
        }
    }

    private fun processTemplate(temp: Template, data: Map<String, Any>): ByteArray
    {
        ByteArrayOutputStream().use {
            val out: Writer = OutputStreamWriter(it, Charsets.UTF_8)
            temp.process(data, out)
            return it.toByteArray()
        }
    }
}
