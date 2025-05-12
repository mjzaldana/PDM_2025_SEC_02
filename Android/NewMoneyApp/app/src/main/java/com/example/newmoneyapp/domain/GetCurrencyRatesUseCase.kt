package com.example.newmoneyapp.domain

import com.example.newmoneyapp.data.model.CurrencyRate
import com.example.newmoneyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyRatesUseCase @Inject constructor(private val repository: CurrencyRepository){
    suspend operator fun invoke(): CurrencyRate{
        return repository.getCurrencyRates()
    }
}
