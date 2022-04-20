package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository

import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.ExcludeList
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.LatLong
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.SafeApiCall
import com.endava.internship.mobile.weatherapp.data.model.forecast.Current
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import com.endava.internship.mobile.weatherapp.utils.Constants
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) : SafeApiCall {

    suspend fun getDailyForecast(
        latLong: LatLong,
        days: Int = Constants.MAX_DAILY_FORECAST_DAYS
    ): Resource<List<Daily>> = safeApiCall {
        api.getWeatherDataFromLatLong(
            lat = latLong.lat, long = latLong.long,
            excludeFields = ExcludeList(
                listOf(
                    Constants.WEATHER_API_QUERY_FIELD_MINUTELY,
                    Constants.WEATHER_API_QUERY_FIELD_ALERTS,
                    Constants.WEATHER_API_QUERY_FIELD_CURRENT,
                    Constants.WEATHER_API_QUERY_FIELD_HOURLY
                )
            ),
        ).daily!!.take(days + 1).drop(1)
    }

    suspend fun getHourlyForecast(latLong: LatLong): Resource<ForecastResponse> = safeApiCall {
        api.getWeatherDataFromLatLong(
            lat = latLong.lat, long = latLong.long,
            excludeFields = ExcludeList(
                listOf(
                    Constants.WEATHER_API_QUERY_FIELD_MINUTELY,
                    Constants.WEATHER_API_QUERY_FIELD_ALERTS,
                    Constants.WEATHER_API_QUERY_FIELD_DAILY
                )
            ),
        )
    }

    suspend fun getCurrentForecast(latLong: LatLong): Resource<ForecastResponse> = safeApiCall {
        api.getWeatherDataFromLatLong(
            lat = latLong.lat, long = latLong.long,
            excludeFields = ExcludeList(
                listOf(
                    Constants.WEATHER_API_QUERY_FIELD_MINUTELY,
                    Constants.WEATHER_API_QUERY_FIELD_ALERTS,
                    Constants.WEATHER_API_QUERY_FIELD_DAILY
                )
            ),
        )
    }
}