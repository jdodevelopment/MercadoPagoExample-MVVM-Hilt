package ar.com.jdodevelopment.data.network.services

import ar.com.jdodevelopment.data.model.Installments
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InstallmentsService {


    @GET("payment_methods/installments")
    suspend fun getInstallments(
        @Query("public_key") publicKey: String,
        @Query("payment_method_id") paymentMethodId: String,
        @Query("issuer.id") issuerId: String,
        @Query("amount") amount: Double
    ): Response<List<Installments>>

}