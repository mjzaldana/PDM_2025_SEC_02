package com.example.newmoneyapp.data.repository

import com.example.newmoneyapp.data.model.CurrencyRate
import com.example.newmoneyapp.data.remote.CurrencyApiService
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val apiService: CurrencyApiService){
    suspend fun getCurrencyRates(): CurrencyRate {
        return apiService.getLatestRates()
    }
}
