package me.ugreek85.document.common

enum class DocumentType(val value: String) {

    BeginOffline("BeginOffline"),

    OpenShift("OpenShift"),

    Sale("Sale"),
    Return("Return"),

    Cancel("Cancel"),

    ServiceInput("ServiceInput"),
    ServiceOutput("ServiceOutput"),

    CashWithdrawal("CashWithdrawal"),
    Pawn("Pawn"),

    ZReport("ZReport"),
    CloseShift("CloseShift"),


    CurrencyExchange("CurrencyExchange"),
    CurrencyInput("CurrencyInput"),
    CurrencyOutput("CurrencyOutput"),
    CurrencyAdditionalInput("CurrencyAdditionalInput"),

    FinishOffline("FinishOffline");
}
