package ar.com.jdodevelopment.util

object ParsingUtil {
    @JvmStatic
    fun parseDouble(amount: String): Double {
        return try {
            amount.toDouble()
        } catch (e: Exception) {
            0.0
        }
    }
}