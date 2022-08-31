package com.example.coinmart.core.utils

sealed class ApiResponse<out T> {
    object Loading: ApiResponse<Nothing>()
    data class Success<out T>(val data: T? = null, val status: String? = null): ApiResponse<T>()
    data class Failure(val status: String? = null, val message: String? = null): ApiResponse<Nothing>()
}