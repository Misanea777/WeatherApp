package com.endava.internship.mobile.weatherapp.data.remote

import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi {
    @GET(Constants.WEATHER_API_PATH_FORECAST)
     suspend fun getWeatherData(): Response<ForecastResponse>
}