package com.example.newmoneyapp.ui.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmoneyapp.domain.GetCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getCurrencyRatesUseCase : GetCurrencyRatesUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(CurrencyListUiState())
    val uiState: StateFlow<CurrencyListUiState> = _uiState

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
}


