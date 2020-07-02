package ar.com.jdodevelopment.ui.main.installments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.jdodevelopment.data.model.Installments
import ar.com.jdodevelopment.data.repository.InstallmentsRepository
import ar.com.jdodevelopment.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class InstallmentsViewModel @ViewModelInject constructor(
    private val repository: InstallmentsRepository
) : ViewModel() {


    private val _data = MutableLiveData<Resource<List<Installments>>>()
    val data: LiveData<Resource<List<Installments>>> get() = _data


    fun getInstallments(paymentMethodId: String, issuerId: String, amount: Double) = viewModelScope.launch {
            _data.value = (Resource.Loading())
            val response = repository.getInstallments(paymentMethodId, issuerId, amount)
            _data.value = (handleResponse(response))
        }

    private fun handleResponse(response: Response<List<Installments>>): Resource<List<Installments>> {
        if (response.isSuccessful) {
            response.body()?.let { resource ->
                return Resource.Success(resource)
            }
        }
        return Resource.Error(response.message())
    }

}