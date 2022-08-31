package com.example.coinmart.features_news.data.remote.response

data class USD(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
)