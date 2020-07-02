package ar.com.jdodevelopment.ui.main.banks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.repository.CardIssuersRepository
import ar.com.jdodevelopment.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BanksViewModel  @ViewModelInject constructor(
    private val repository: CardIssuersRepository
) : ViewModel() {



    private val _data = MutableLiveData<Resource<List<CardIssuer>>>()
    val data: LiveData<Resource<List<CardIssuer>>> get() = _data


    fun getCardIssuers(paymentMethodId: String) = viewModelScope.launch {
        _data.value = (Resource.Loading())
        val response = repository.getCardIssuers(paymentMethodId)
        _data.value = (handleResponse(response))
    }

    private fun handleResponse(response: Response<List<CardIssuer>>): Resource<List<CardIssuer>> {
        if (response.isSuccessful) {
            response.body()?.let { resource ->
                return Resource.Success(resource)
            }
        }
        return Resource.Error(response.message())
    }


}