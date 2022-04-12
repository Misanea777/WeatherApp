package com.endava.internship.mobile.weatherapp.utils

import com.endava.internship.mobile.weatherapp.BuildConfig

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

    const val MAX_DAILY_FORECAST_DAYS = 5

    val DAY_HOURS_RANGE = 7..18

    const val DEGREE_CHAR = 0x00B0.toChar()
}