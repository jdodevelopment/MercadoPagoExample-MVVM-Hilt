package ar.com.jdodevelopment.ui.main.amount

import android.text.Editable
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AmountViewModel @ViewModelInject constructor(

) : ViewModel() {


    lateinit var callback: SubmitAmountCallback

    val inputAmount = ObservableField<String>()

    private val _amount = MutableLiveData<Double>()
    val amount: LiveData<Double> get() = _amount

    private val _errorAmount = MutableLiveData<String>()
    val errorAmount: LiveData<String> get() = _errorAmount


    fun sumbmitAmount() {
        _errorAmount.value = null
        _amount.value?.let { value ->
            if (value > 0.0) {
                callback.submitAmount(value)
            } else {
                _errorAmount.value = "Por favor, ingrese un numero que sea mayor a cero.";
            }
        }
    }

    fun inputAmountChanged(text: Editable) {
        text.toString().toDoubleOrNull()?.let {
            _amount.value = it
        } ?: let {
            _amount.value = 0.0
        }
    }


}