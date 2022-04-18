package com.endava.internship.mobile.weatherapp.data.remote

import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.ExcludeList
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.WEATHER_API_PATH_ONE_CALL)
    suspend fun getWeatherDataFromLatLong(
        @Query(Constants.WEATHER_API_QUERY_LAT) lat: Double,
        @Query(Constants.WEATHER_API_QUERY_LONG) long: Double,
        @Query(Constants.WEATHER_API_QUERY_EXCLUDE, encoded = true) excludeFields: ExcludeList =
            ExcludeList(emptyList()),
        @Query(Constants.WEATHER_API_QUERY_UNITS) units: String = Constants.WEATHER_API_QUERY_UNITS_METRIC
    ): ForecastResponse
}