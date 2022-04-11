package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Hourly(
    @field:SerializedName("clouds")
    val clouds: Int?,

    @field:SerializedName("dew_point")
    val dew_point: Double?,

    @field:SerializedName("dt")
    val dt: Long?,

    @field:SerializedName("feels_like")
    val feels_like: Double?,

    @field:SerializedName("humidity")
    val humidity: Int?,

    @field:SerializedName("pop")
    val pop: Double?,

    @field:SerializedName("pressure")
    val pressure: Int?,

    @field:SerializedName("rain")
    val rain: Rain?,

    @field:SerializedName("snow")
    val snow: Snow?,

    @field:SerializedName("temp")
    val temp: Double?,

    @field:SerializedName("uvi")
    val uvi: Double?,

    @field:SerializedName("visibility")
    val visibility: Int?,

    @field:SerializedName("weather")
    val weather: List<Weather>?,

    @field:SerializedName("wind_deg")
    val wind_deg: Int?,

    @field:SerializedName("wind_gust")
    val wind_gust: Double?,

    @field:SerializedName("wind_speed")
    val wind_speed: Double?
)