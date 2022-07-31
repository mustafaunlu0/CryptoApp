package com.mustafaunlu.cryptocrazyagain.dependencyinjection

import com.mustafaunlu.cryptocrazyagain.repo.CryptoRepository
import com.mustafaunlu.cryptocrazyagain.service.CryptoAPI
import com.mustafaunlu.cryptocrazyagain.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesRepository(api : CryptoAPI)=CryptoRepository(api)

    @Singleton
    @Provides
    fun providesCryptoAPI() : CryptoAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CryptoAPI::class.java)
    }
}