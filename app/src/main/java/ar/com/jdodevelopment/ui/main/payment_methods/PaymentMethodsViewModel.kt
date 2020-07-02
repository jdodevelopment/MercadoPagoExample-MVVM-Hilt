package ar.com.jdodevelopment.ui.main.payment_methods

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.data.repository.PaymentMethodsRepository
import ar.com.jdodevelopment.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PaymentMethodsViewModel @ViewModelInject constructor(
    private val repository: PaymentMethodsRepository
) : ViewModel() {


    private val _data = MutableLiveData<Resource<List<PaymentMethod>>>()
    val data: LiveData<Resource<List<PaymentMethod>>> get() = _data


    fun getPaymentMethods() = viewModelScope.launch {
        _data.value = (Resource.Loading())
        val response = repository.getPaymentMethods()
        _data.value = (handleResponse(response))
    }

    private fun handleResponse(response: Response<List<PaymentMethod>>): Resource<List<PaymentMethod>> {
        if (response.isSuccessful) {
            response.body()?.let { resource ->
                return Resource.Success(resource)
            }
        }
        return Resource.Error(response.message())
    }

}