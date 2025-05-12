package com.example.newmoneyapp.data.model

data class CurrencyRate(
    val base: String,
    val rates: Map<String, Double>
)