package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository

import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource

import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.endava.internship.mobile.weatherapp.utils.LatLong

interface WeatherRepository {
    suspend fun getDailyForecastFromLatLong(
        latLong: LatLong,
        days: Int = Constants.MAX_DAILY_FORECAST_DAYS
    ): Resource<List<Daily>>

    suspend fun getHourlyForecastFromLatLong(latLong: LatLong): Resource<ForecastResponse>

    suspend fun getCurrentForecastFromLatLong(latLong: LatLong): Resource<ForecastResponse>
}