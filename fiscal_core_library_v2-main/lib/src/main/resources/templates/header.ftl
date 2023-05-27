        <#setting number_format="0">
<!-- HEADER BEGIN -->
        <tr>
            <td colspan="2" class="center">${CompanyName}</td>
        </tr>
        <tr>
            <td colspan="2" class="center">${document.getDocumentHeader().getPointName()}</td>
        </tr>

        <#if document.getDocumentHeader().getVatNumber()?has_content>
        <tr>
            <td colspan="2" class="center">ИПН ${document.getDocumentHeader().getVatNumber()}</td>
        </tr>
        </#if>

        <tr>
            <td colspan="2" class="center">IД ${document.getDocumentHeader().getSellerId()}</td>
        </tr>
        <tr>
            <td colspan="2" class="center">${documentName}</td>
        </tr>
        <tr>
            <td colspan="2" class="center">Дата ${document.getDocumentHeader().getFormattedDateTime()}</td>
        </tr>
        <#if document.getDocumentHeader().getCashierName()?has_content>
            <tr>
                <td colspan="2" class="center">КАСИР ${document.getDocumentHeader().getCashierName()}</td>
            </tr>
        </#if>
        <tr>
            <td colspan="2" class="center">ФН ПРРО ${document.getDocumentHeader().getRegistrarId()}</td>
        </tr>

        <#if shiftId?has_content>
            <tr>
                <td colspan="2" class="center">Зміна № ${shiftId}</td>
            </tr>
        </#if>

        <tr>
            <td colspan="2" class="center">Чек № ${document.getDocumentHeader().getDocumentNumber()}</td>
        </tr>

        <#if document.getDocumentHeader().getFiscalNumber()?has_content>
        <tr>
            <td colspan="2" class="center">Звiт № ${document.getDocumentHeader().getFiscalNumber()}</td>
        </tr>
        </#if>

        <tr>
            <td colspan="2" class="center">Валюта Звиту ГРН</td>
        </tr>
        <!-- HEADER END -->
