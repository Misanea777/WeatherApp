package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network

import com.endava.internship.mobile.weatherapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class InterceptorWeatherAppId : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .url(
                    chain.request().url.newBuilder()
                        .addQueryParameter(
                            name = Constants.WEATHER_API_QUERY_APP_ID,
                            value = Constants.WEATHER_API_KEY
                        )
                        .build()
                )
                .build()
        )
    }
}