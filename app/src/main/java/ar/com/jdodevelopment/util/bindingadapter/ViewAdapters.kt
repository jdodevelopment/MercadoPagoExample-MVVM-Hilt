package ar.com.jdodevelopment.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter

object ViewAdapters {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }
}