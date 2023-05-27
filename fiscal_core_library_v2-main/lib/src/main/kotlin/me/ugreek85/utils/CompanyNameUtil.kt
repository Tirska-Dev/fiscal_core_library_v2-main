package me.ugreek85.utils


object CompanyNameUtil {

    //Physical person entrepreneur
    private val PPE_REGEXP = Regex("([0-9]{9,}|^\\p{IsAlphabetic}+\$)");
    private val PREFIX_REGEXP = Regex("^(ФОП .+|\\p{IsAlphabetic}{2,} \".+\".*)\$");

    fun isPPE(tin: String) : Boolean {
        return tin.matches(PPE_REGEXP)
    }

    fun hasPrefix(name: String): Boolean {
        return name.matches(PREFIX_REGEXP);
    }

    fun getCorrectName(name: String, sellerId: String): String {

        if(isPPE(sellerId) && !hasPrefix(name)) return "ФОП \"$name\""
        return name
    }
}