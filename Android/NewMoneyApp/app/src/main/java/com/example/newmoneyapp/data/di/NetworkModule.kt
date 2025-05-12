package com.example.newmoneyapp.data.di

import com.example.newmoneyapp.data.remote.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @dagger.Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cdn.dinero.today/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService{
        return retrofit.create(CurrencyApiService::class.java)
    }
}
