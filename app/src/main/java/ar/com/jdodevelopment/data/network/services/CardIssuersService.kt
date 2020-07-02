package ar.com.jdodevelopment.data.network.services

import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.model.PaymentMethod
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardIssuersService {

    @GET("payment_methods/card_issuers")
    suspend fun getCardIssuers(
        @Query("public_key") publicKey: String,
        @Query("payment_method_id") paymentMethodId: String
    ): Response<List<CardIssuer>>


}