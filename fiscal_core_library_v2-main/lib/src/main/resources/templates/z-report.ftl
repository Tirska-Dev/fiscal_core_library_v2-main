<#assign CompanyName="${document.getDocumentHeader().getCompanyName()}">

<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        table {
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #ccc;
            /*font-family: monospace, monospace;*/
            font-family: sans-serif;
            font-size: 9pt;
            padding: 4mm;
            width: 72mm;
        }

        .vertical-indent {
            height: 2mm;
        }

        td.left {
            text-align: left;
        }

        td.right {
            text-align: right;
        }

        td.center {
            text-align: center;
        }

    </style>
</head>
<body>
<div>
    <table>

        <#include 'header.ftl'>

        <#setting number_format="0.00">
        <tr class="vertical-indent"></tr>

        <!-- Підсумки реалізації -->
        <tr>
            <td colspan="2" class="center">
                <hr/>
                Підсумки реалізації
                <hr/>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="center">ОДЕРЖАНО ЗА ФОРМАМИ ОПЛАТИ</td>
        </tr>


        <#if document.getTotalsBySale()?has_content>

            <#if document.getTotalsBySale().getPayForms()?has_content>
                <#list document.getTotalsBySale().getPayForms() as payForm>
                <tr>
                    <td class="left">${payForm.getName()}</td>
                    <td class="right">${payForm.getSum()}</td>
                </tr>
                </#list>
            </#if>

            <tr>
                <td class="left">ЗАГАЛЬНИЙ ОБОРОТ</td>
                <td class="right">${document.getTotalsBySale().getSum()}</td>
            </tr>
            <tr>
                <td class="left">Кiлькicть чеків</td>
                <td class="right">${document.getTotalsBySale().getNumberOfReceipts()?string("0")}</td>
            </tr>

            <tr>
                <td colspan="2" class="center"><hr/></td>
            </tr>

            <#if document.getTotalsBySale().getTaxRates()?has_content>
                <#list document.getTotalsBySale().getTaxRates() as taxRate>
                    <tr>
                        <td class="left">${taxRate.getName()} ${taxRate.getLetter()}</td>
                        <td class="right">${taxRate.getPercent()}</td>
                    </tr>
                    <tr>
                        <td class="left">Оборот по податку</td>
                        <td class="right">${taxRate.getSourceSum()}</td>
                    </tr>
                    <tr>
                        <td class="left">Сума податку вiд обороту</td>
                        <td class="right">${taxRate.getSum()}</td>
                    </tr>
                    <#sep>
                        <tr>
                            <td colspan="2" class="left">---</td>
                        </tr>
                    </#sep>
                </#list>
            </#if>

            <tr class="vertical-indent"></tr>

            <!-- Підсумки повернення -->
            <tr>
                <td colspan="2" class="center">
                    <hr/>
                    Підсумки повернення
                    <hr/>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="center">ВИДАНО ЗА ФОРМАМИ ОПЛАТИ</td>
            </tr>

            <#if document.getTotalsByReturn()?has_content>
                <#if document.getTotalsByReturn().getPayForms()?has_content>
                    <#list document.getTotalsByReturn().getPayForms() as payForm>
                        <tr>
                            <td class="left">${payForm.getName()}</td>
                            <td class="right">${payForm.getSum()}</td>
                        </tr>
                    </#list>
                </#if>

                <tr>
                    <td class="left">ЗАГАЛЬНИЙ ОБОРОТ</td>
                    <td class="right">${document.getTotalsByReturn().getSum()}</td>
                </tr>
                <tr>
                    <td class="left">Кiлькicть чеків</td>
                    <td class="right">${document.getTotalsByReturn().getNumberOfReceipts()?string("0")}</td>
                </tr>

                <tr>
                    <td colspan="2" class="center"><hr/></td>
                </tr>

                <#if document.getTotalsByReturn().getTaxRates()?has_content>
                    <#list document.getTotalsByReturn().getTaxRates() as taxRate>
                        <tr>
                            <td class="left">${taxRate.getName()} ${taxRate.getLetter()}</td>
                            <td class="right">${taxRate.getPercent()}</td>
                        </tr>
                        <tr>
                            <td class="left">Оборот по податку</td>
                            <td class="right">${taxRate.getSourceSum()}</td>
                        </tr>
                        <tr>
                            <td class="left">Сума податку вiд обороту</td>
                            <td class="right">${taxRate.getSum()}</td>
                        </tr>
                        <#sep>
                            <tr>
                                <td colspan="2" class="left">---</td>
                            </tr>
                        </#sep>
                    </#list>
                </#if>
            </#if>

            <tr class="vertical-indent"></tr>
        </#if>


        <!-- Підсумки за операціями ломбардів -->

        <#if document.getTotalsBySale()?has_content>
            <tr>
                <td colspan="2" class="center">
                    <hr/>
                    Підсумки за операціями ломбардів
                    <hr/>
                </td>
            </tr>

            <tr>
                <td class="left">Видано клієнтам</td>
                <#if document.getTotalsBySale().getPawnSumIssued()?has_content>
                    <td class="right">${document.getTotalsBySale().getPawnSumIssued()}</td>
                <#else>
                    <td class="right">${0}</td>
                </#if>
            </tr>
            <tr>
                <td class="left">Отримано від клієнтів</td>
                <#if document.getTotalsBySale().getPawnSumReceived()?has_content>
                    <td class="right">${document.getTotalsBySale().getPawnSumReceived()}</td>
                <#else>
                    <td class="right">${0}</td>
                </#if>
            </tr>
        </#if>


        <!-- Підсумки видачі готівки -->
        <tr>
            <td colspan="2" class="center">
                <hr/>
                Підсумки видачі готівки
                <hr/>
            </td>
        </tr>

        <#if document.getTotalsByCash()?has_content>
            <tr>
                <td class="left">ГРОШI</td>
                <td class="right">${document.getTotalsByCash().getSum()}</td>
            </tr>
            <tr>
                <td class="left">Кiлькiсть чеків</td>
                <td class="right">${document.getTotalsByCash().getNumberOfReceipts()}</td>
            </tr>
        <#else>
            <tr>
                <td class="left">ГРОШI</td>
                <td class="right">0.00</td>
            </tr>
            <tr>
                <td class="left">Кiлькiсть чеків</td>
                <td class="right">0</td>
            </tr>
        </#if>


        <!-- Готівкові кошти в касе -->
        <tr>
            <td colspan="2" class="center">
                <hr/>
                Готівкові кошти в касе
                <hr/>
            </td>
        </tr>
        <tr>
            <td class="left">Службове внесення</td>
            <td class="right">${document.getServiceInputSum()}</td>
        </tr>
        <tr>
            <td class="left">Служебне вилучення</td>
            <td class="right">${document.getServiceOutputSum()}</td>
        </tr>



        <tr>
            <td colspan="2" class="center"><hr/></td>
        </tr>
        <tr>
            <td colspan="2" class="left">1. Підсумкові регістри даних обнулені</td>
        </tr>
        <tr>
            <td colspan="2" class="left">2. ФІСКАЛЬНИЙ ЗВІТ ДІЙСНИЙ</td>
        </tr>

<#include 'footer.ftl'>

    </table>
</div>
</body>
</html>
