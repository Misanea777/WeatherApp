package com.endava.internship.mobile.weatherapp.data.remote

import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.WEATHER_API_PATH_FORECAST)
     suspend fun getWeatherDataFromCity(
        @Query(Constants.WEATHER_API_QUERY_CITY) cityName: String,
        @Query(Constants.WEATHER_API_QUERY_UNITS) units: String = Constants.WEATHER_API_QUERY_UNITS_METRIC
    ): Response<ForecastResponse>
}