package ar.com.jdodevelopment.util.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import ar.com.jdodevelopment.util.FormatingUtil.formatCurrency

object TextViewAdapters {

    @JvmStatic
    @BindingAdapter("currency")
    fun setCurrencyAndAmount(textView: TextView, amount: Double?) {
        var amount = amount
        if (amount == null) amount = 0.0
        val formattedValue = formatCurrency(amount)
        textView.text = formattedValue
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "android:text")
    fun getDouble(view: TextView): Double {
        val num = view.text.toString()
        return if (num.isEmpty()) 0.0 else try {
            num.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }
}