package com.example.coinmart.features_news.data.repository

import com.example.coinmart.core.utils.NetworkCall
import com.example.coinmart.features_news.data.remote.ApiService
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) {

    operator fun invoke() = NetworkCall {
        apiService.getCurrentPrice()
    }
}