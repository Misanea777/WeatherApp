package com.endava.internship.mobile.weatherapp.di

import com.endava.internship.mobile.weatherapp.BuildConfig
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.InterceptorWeatherAppId
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL_RETROFIT_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptorWeatherAppId: InterceptorWeatherAppId,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(interceptorWeatherAppId)
        .apply { if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor) }
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun provideInterceptorWeatherAppId(): InterceptorWeatherAppId = InterceptorWeatherAppId()

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(level = HttpLoggingInterceptor.Level.BASIC)
    }

}