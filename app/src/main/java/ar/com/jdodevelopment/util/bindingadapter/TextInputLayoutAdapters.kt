package ar.com.jdodevelopment.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutAdapters {

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun setErrorMessage(textInputLayout: TextInputLayout, value: CharSequence?) {
        textInputLayout.setError(value)
    }
}