package com.endava.internship.mobile.weatherapp.data.repository

import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.remote.RemoteDataSource
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import com.endava.internship.mobile.weatherapp.utils.*

object WeatherRepository : SafeApiCall {
    private val api = RemoteDataSource.buildApi(WeatherApi::class.java)

    suspend fun getDailyForecast(latLong: LatLong, days: Int = Constants.MAX_DAILY_FORECAST_DAYS): Resource<List<Daily>> = safeApiCall {
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
        ).daily!!.take(days+1).drop(1)
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
}