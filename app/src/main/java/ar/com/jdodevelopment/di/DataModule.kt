package ar.com.jdodevelopment.di

import ar.com.jdodevelopment.data.repository.CardIssuersRepository
import ar.com.jdodevelopment.data.repository.InstallmentsRepository
import ar.com.jdodevelopment.data.repository.PaymentMethodsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
class DataModule {

    @Provides
    fun providePaymentMethodsRepository(
        @Named("apiKey") apiKey: String
    ) = PaymentMethodsRepository(apiKey)

    @Provides
    fun provideCardIssuersRepository(
        @Named("apiKey") apiKey: String
    ) = CardIssuersRepository(apiKey)

    @Provides
    fun provideInstallmentsRepository(
        @Named("apiKey") apiKey: String
    ) = InstallmentsRepository(apiKey)

}