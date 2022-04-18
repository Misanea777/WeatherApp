package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository

import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.di.dispatchers.IoDispatcher
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.safeApiCall
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.ExcludeList
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.LatLong
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.exception.NoSuchParameterInResponseException
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import com.endava.internship.mobile.weatherapp.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultWeatherRepository @Inject constructor(
    private val api: WeatherApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    override suspend fun getDailyForecastFromLatLong(
        latLong: LatLong,
        days: Int
    ): Resource<List<Daily>> =
        safeApiCall(dispatcher = ioDispatcher) {
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
            ).daily?.take(days+1)?.drop(1) ?: throw  NoSuchParameterInResponseException()
        }

    override suspend fun getHourlyForecastFromLatLong(latLong: LatLong): Resource<ForecastResponse> =
        safeApiCall(dispatcher = ioDispatcher) {
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