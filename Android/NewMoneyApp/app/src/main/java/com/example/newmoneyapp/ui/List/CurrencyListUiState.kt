package com.example.newmoneyapp.ui.List

data class CurrencyListUiState(
    val loading: Boolean = false,
    val rates: Map<String, Double> = emptyMap(),
    val error: String? = null
)