package ar.com.jdodevelopment.data.network.services

import ar.com.jdodevelopment.data.model.PaymentMethod
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentMethodsService {

    @GET("payment_methods")
    suspend fun getPaymentMethods(
        @Query("public_key") publicKey: String
    ): Response<List<PaymentMethod>>


}