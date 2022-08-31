package com.example.coinmart.features_news.presentation.fragments.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmart.core.utils.ApiResponse
import com.example.coinmart.features_news.data.remote.response.CurrentPriceResponse
import com.example.coinmart.features_news.data.repository.DashboardRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: DashboardRepositoryImpl
) : ViewModel() {

    private val channel = Channel<STATE>()
    var flow = channel.receiveAsFlow()

    init {
        getCurrentPrice()
    }

    //ADDITIONAL

    private fun getCurrentPrice() {
        viewModelScope.launch {
            repo().collectLatest { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let { channel.send(STATE.SUCCESS(it)) }
                    }
                    is ApiResponse.Failure -> {
                        channel.send(STATE.FAILURE(response.message.toString()))
                    }
                }
            }
        }
    }

    sealed class STATE {
        data class SUCCESS(val data: CurrentPriceResponse) : STATE()
        data class FAILURE(val message: String) : STATE()
    }
}