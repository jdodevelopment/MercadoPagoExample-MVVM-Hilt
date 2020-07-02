package ar.com.jdodevelopment.data.repository

import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit
import ar.com.jdodevelopment.data.network.services.CardIssuersService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class CardIssuersRepository @Inject constructor(
    @Named("apiKey") private val apiKey: String
) {


    suspend fun getCardIssuers(paymentMethodId: String): Response<List<CardIssuer>> =
        MercadoPagoRetrofit.create(CardIssuersService::class.java).getCardIssuers(apiKey, paymentMethodId)


}