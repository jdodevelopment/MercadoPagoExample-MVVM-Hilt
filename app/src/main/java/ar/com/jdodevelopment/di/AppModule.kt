package ar.com.jdodevelopment.di

import android.app.Application
import ar.com.jdodevelopment.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(application: Application): String {
        return application.getString(R.string.api_key)
    }

}