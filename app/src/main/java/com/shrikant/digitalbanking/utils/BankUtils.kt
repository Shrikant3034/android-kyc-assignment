package com.shrikant.digitalbanking.utils

object BankUtils {

    fun getBank(ifsc: String): String {
        return when (ifsc) {
            "HDFC0CAGSBK" -> "HDFC Bank"
            "SBIN0000001" -> "State Bank of India"
            "ICIC0000001" -> "ICICI Bank"
            else -> "Unknown Bank"
        }
    }

    fun getBranch(ifsc: String): String {
        return when (ifsc) {
            "HDFC0CAGSBK" -> "Corporate Branch"
            "SBIN0000001" -> "Main Branch"
            "ICIC0000001" -> "Pune Branch"
            else -> "Unknown Branch"
        }
    }
}