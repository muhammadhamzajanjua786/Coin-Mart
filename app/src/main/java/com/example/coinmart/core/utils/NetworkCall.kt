package com.example.coinmart.core.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> NetworkCall(call: suspend () -> Response<T>): Flow<ApiResponse<T?>> = flow {

    try {
        emit(ApiResponse.Loading)

        val c = call()

        c?.let { response ->

            if (response.isSuccessful) {
                emit(ApiResponse.Success(response.body(),"Success"))
            } else if(!response.isSuccessful) {
                Log.d("NetworkCall",response.message())
                emit(ApiResponse.Failure(status = "00516881", message = response.message()))
            } else {
                c.errorBody()?.let { error ->
                    Log.d("NetworkCall",error.toString())
                    error.close()
                    emit(ApiResponse.Failure(error.toString()))
                }
            }

        }
    } catch (e:HttpException) {
        Log.d("NetworkCall",e.message.toString())
        emit(ApiResponse.Failure(status = "",message = "Couldn't reach server, Something went wrong!"))
    }
    catch (e:IOException) {
        Log.d("NetworkCall",e.message.toString())
        emit(ApiResponse.Failure(status = "",message = "Couldn't reach server, Something went wrong!"))
    }
}