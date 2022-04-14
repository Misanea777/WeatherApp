package com.endava.internship.mobile.weatherapp.utils

import com.endava.internship.mobile.weatherapp.BuildConfig
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.LatLong


object Constants {
    const val WEATHER_API_QUERY_APP_ID: String = "appid"
    const val WEATHER_API_QUERY_UNITS: String = "units"
    const val WEATHER_API_QUERY_UNITS_METRIC: String = "metric"

    const val WEATHER_API_QUERY_LAT: String = "lat"
    const val WEATHER_API_QUERY_LONG: String = "lon"

    const val WEATHER_API_QUERY_EXCLUDE: String = "exclude"
    const val WEATHER_API_QUERY_FIELD_CURRENT = "current"
    const val WEATHER_API_QUERY_FIELD_MINUTELY = "minutely"
    const val WEATHER_API_QUERY_FIELD_HOURLY = "hourly"
    const val WEATHER_API_QUERY_FIELD_DAILY = "daily"
    const val WEATHER_API_QUERY_FIELD_ALERTS = "alerts"

    const val WEATHER_API_KEY: String = BuildConfig.WEATHER_API_KEY
    const val BASE_URL_RETROFIT_API: String = BuildConfig.WEATHER_API_URL

    const val WEATHER_API_PATH_ONE_CALL: String = "onecall"

    val LAT_LONG_CHISINAU = LatLong(47.0105, 28.8638)

    const val MAX_DAILY_FORECAST_DAYS = 7

    object WeatherConditionCodes {
        val Thunderstorm = 200..299
        val  Drizzle = 300..399
        val Rain = 500..599
        val Snow = 600..699
        val Atmosphere = 700..799
        const val Clear = 800
        val Clouds = 801..Int.MAX_VALUE
    }

    const val MINIMUM_FETCH_INTERVAL : Long = 3600
}
