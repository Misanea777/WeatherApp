package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Hourly(
    val clouds: Int,

    @field:SerializedName("dew_point")
    val dewPoint: Double,

    @field:SerializedName("dt")
    val dateTime: Long,

    @field:SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Rain,
    val snow: Snow,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,

    @field:SerializedName("wind_deg")
    val windDeg: Int,

    @field:SerializedName("wind_gust")
    val windGust: Double,

    @field:SerializedName("wind_speed")
    val windSpeed: Double
)