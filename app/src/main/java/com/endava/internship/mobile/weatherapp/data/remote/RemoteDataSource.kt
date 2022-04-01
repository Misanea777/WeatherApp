package com.endava.internship.mobile.weatherapp.data.remote

import com.endava.internship.mobile.weatherapp.BuildConfig
import com.endava.internship.mobile.weatherapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RemoteDataSource {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {chain ->
            chain.proceed(chain.request().newBuilder()
                .url(chain.request().url.newBuilder()
                    .addQueryParameter(Constants.WEATHER_API_QUERY_APP_ID, Constants.WEATHER_API_KEY)
                    .build()
                )
                .build())
        }.also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                client.addInterceptor(logging)
            }
        }.build()

    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_RETROFIT_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}