package com.example.newmoneyapp.data.remote

import com.example.newmoneyapp.data.model.CurrencyRate
import retrofit2.http.GET

interface CurrencyApiService {
    @GET("latest.json")
    suspend fun getLatestRates(): CurrencyRate
}


