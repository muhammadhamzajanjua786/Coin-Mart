package com.example.coinmart.features_news.data.remote

import com.example.coinmart.features_news.data.remote.response.CurrentPriceResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("currentprice.json")
    suspend fun getCurrentPrice(): Response<CurrentPriceResponse>
}