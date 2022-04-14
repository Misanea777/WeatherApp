package com.endava.internship.mobile.weatherapp.utils

import androidx.annotation.StringRes
import com.endava.internship.mobile.weatherapp.BuildConfig
import com.endava.internship.mobile.weatherapp.R


object Constants {
    const val WEATHER_API_QUERY_APP_ID: String = "appid"
    const val WEATHER_API_QUERY_CITY: String = "q"
    const val WEATHER_API_QUERY_UNITS: String = "units"
    const val WEATHER_API_QUERY_CITY_CHISINAU: String = "chisinau"
    const val WEATHER_API_QUERY_UNITS_METRIC: String = "metric"
    const val WEATHER_API_KEY: String = BuildConfig.WEATHER_API_KEY
    const val BASE_URL_RETROFIT_API: String = BuildConfig.WEATHER_API_URL
    const val WEATHER_API_PATH_FORECAST: String = "forecast"
    const val MINIMUM_FETCH_INTERVAL : Long = 3600
}
