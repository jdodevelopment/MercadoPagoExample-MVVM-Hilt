package ar.com.jdodevelopment.util

import java.text.NumberFormat
import java.util.*

object FormatingUtil {


    @JvmStatic
    fun formatCurrency(value: Double): String {
        val decimalFormat =
            NumberFormat.getCurrencyInstance(Locale.getDefault())
        decimalFormat.minimumFractionDigits = 2
        decimalFormat.maximumFractionDigits = 2
        return decimalFormat.format(value)
    }
}