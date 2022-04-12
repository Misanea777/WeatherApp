package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository

import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.di.dispatchers.IoDispatcher
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.safeApiCall
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.LatLong
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
)  {
    suspend fun getDailyForecastFromLatLong(latLong: LatLong): Resource<ForecastResponse> =
        safeApiCall(apiCall = {
            api.getDailyForecastFromLatLong(
                lat = latLong.lat,
                long = latLong.long
            )
        }, dispatcher = ioDispatcher)

    suspend fun getHourlyForecastFromLatLong(latLong: LatLong): Resource<ForecastResponse> =
        safeApiCall(apiCall = {
            api.getHourlyForecastFromLatLong(lat = latLong.lat, long = latLong.long)
        }, dispatcher = ioDispatcher)
}