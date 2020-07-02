package ar.com.jdodevelopment.data.repository

import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit
import ar.com.jdodevelopment.data.network.services.PaymentMethodsService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class PaymentMethodsRepository @Inject constructor(
    @Named("apiKey") private val apiKey: String
) {


    suspend fun getPaymentMethods(): Response<List<PaymentMethod>> =
        MercadoPagoRetrofit.create(PaymentMethodsService::class.java).getPaymentMethods(apiKey)


}