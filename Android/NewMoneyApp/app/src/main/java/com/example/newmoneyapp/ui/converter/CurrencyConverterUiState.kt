package com.example.newmoneyapp.ui.converter

data class CurrencyConverterUiState(
    val loading: Boolean = false,
    val rates: Map<String, Double> = emptyMap(),
    val fromCurrency: String = "USD",
    val toCurrency: String = "EUR",
    val amount: String = "1.00",
    val conversionResult: String = "",
    val error: String? = null
)