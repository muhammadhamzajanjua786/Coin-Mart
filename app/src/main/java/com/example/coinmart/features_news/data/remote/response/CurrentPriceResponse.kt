package com.example.coinmart.features_news.data.remote.response

data class CurrentPriceResponse(
    val bpi: Bpi,
    val chartName: String,
    val disclaimer: String,
    val time: Time
)