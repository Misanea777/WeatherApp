package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T,
    dispatcher: CoroutineDispatcher
): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                }
                else -> {
                    Resource.Failure(true, null, null)
                }
            }
        }
    }
}