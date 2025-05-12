package com.example.newmoneyapp.ui.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmoneyapp.domain.GetCurrencyRatesUseCase
import com.example.newmoneyapp.ui.List.CurrencyListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(CurrencyConverterUiState())
    val uiState: StateFlow<CurrencyConverterUiState> = _uiState

    init {
        fetchCurrencyRates()
    }

    private fun fetchCurrencyRates(){
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            try {
                val currencyRate = getCurrencyRatesUseCase()
                _uiState.update { it.copy(loading = false, rates = currencyRate.rates) }
            }catch (e: Exception){
                _uiState.update { it.copy(loading = false, error = e.localizedMessage) }
            }
        }
    }

    fun onFromCurrencyChanged(currency: String){
        _uiState.update { it.copy(fromCurrency = currency, conversionResult = "") }
    }

    fun onToCurrencyChanged(currency: String){
        _uiState.update { it.copy(toCurrency = currency, conversionResult = "") }
    }

    fun onAmountChanged(amount: String){
        _uiState.update { it.copy(amount = amount, conversionResult = "") }
    }

    fun convertToCurrency(){
        val amount = _uiState.value.amount.toDoubleOrNull()
        val fromRate = _uiState.value.rates[_uiState.value.fromCurrency]
        val toRate = _uiState.value.rates[_uiState.value.toCurrency]

        if(amount != null && fromRate != null && toRate != null){
            val result =  (amount / fromRate) * toRate
            _uiState.update { it.copy(conversionResult = String.format("%.2f", result)) }
        }else{
            _uiState.update { it.copy(error = "Alguno de los valores podria estar vacio", conversionResult = "")}
        }
    }
}


