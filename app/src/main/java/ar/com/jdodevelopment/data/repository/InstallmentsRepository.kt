package ar.com.jdodevelopment.data.repository

import ar.com.jdodevelopment.data.model.Installments
import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit
import ar.com.jdodevelopment.data.network.services.InstallmentsService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class InstallmentsRepository @Inject constructor(
    @Named("apiKey") private val apiKey: String
) {


    suspend fun getInstallments(paymentMethodId: String, issuerId: String, amount: Double):
            Response<List<Installments>> =
        MercadoPagoRetrofit.create(InstallmentsService::class.java)
            .getInstallments(apiKey, paymentMethodId, issuerId, amount)


}