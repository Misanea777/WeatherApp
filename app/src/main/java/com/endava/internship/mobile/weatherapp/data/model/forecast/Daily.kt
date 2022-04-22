package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Daily(
    val clouds: Int,

    @field:SerializedName("dew_point")
    val dewPoint: Double,

    @field:SerializedName("dt")
    val dateTime: Long,

    @field:SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val humidity: Int,

    @field:SerializedName("moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val snow: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,

    @field:SerializedName("wind_deg")
    val windDeg: Int,

    @field:SerializedName("wind_gust")
    val windGust: Double,

    @field:SerializedName("wind_speed")
    val windSpeed: Double
)