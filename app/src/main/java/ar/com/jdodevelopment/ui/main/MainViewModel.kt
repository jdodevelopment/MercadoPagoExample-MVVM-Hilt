package ar.com.jdodevelopment.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.model.PayerCost
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.util.Resource

class MainViewModel @ViewModelInject constructor(

) : ViewModel () {


    private val _amount = MutableLiveData<Double>()
    val amount: LiveData<Double> get() = _amount

    private val _paymentMethod = MutableLiveData<PaymentMethod>()
    val paymentMethod: LiveData<PaymentMethod> get() = _paymentMethod

    private val _cardIssuer = MutableLiveData<CardIssuer>()
    val cardIssuer: LiveData<CardIssuer> get() = _cardIssuer

    private val _payerCost = MutableLiveData<PayerCost>()
    val payerCost: LiveData<PayerCost> get() = _payerCost


    fun sumbitAmount(amount: Double?){
        _amount.value = amount
    }

    fun sumbitPaymentMethod(paymentMethod: PaymentMethod?){
        _paymentMethod.value = paymentMethod
    }

    fun sumbitCardIssuer(cardIssuer: CardIssuer?){
        _cardIssuer.value = cardIssuer
    }

    fun sumbitPayerCost(payerCost: PayerCost?){
        _payerCost.value = payerCost
    }

    fun reset(){
        _amount.postValue(null)
        _paymentMethod.postValue(null)
        _cardIssuer.postValue(null)
        _payerCost.postValue(null)
    }

}