package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Daily(
    @field:SerializedName("clouds")
    val clouds: Int?,

    @field:SerializedName("dew_point")
    val dew_point: Double?,

    @field:SerializedName("dt")
    val dt: Long?,

    @field:SerializedName("feels_like")
    val feels_like: FeelsLike?,

    @field:SerializedName("humidity")
    val humidity: Int?,

    @field:SerializedName("moon_phase")
    val moon_phase: Double?,

    @field:SerializedName("moonrise")
    val moonrise: Int?,

    @field:SerializedName("moonset")
    val moonset: Int?,

    @field:SerializedName("pop")
    val pop: Double?,

    @field:SerializedName("pressure")
    val pressure: Int?,

    @field:SerializedName("rain")
    val rain: Double?,

    @field:SerializedName("snow")
    val snow: Double?,

    @field:SerializedName("sunrise")
    val sunrise: Int?,

    @field:SerializedName("sunset")
    val sunset: Int?,

    @field:SerializedName("temp")
    val temp: Temp?,

    @field:SerializedName("uvi")
    val uvi: Double?,

    @field:SerializedName("weather")
    val weather: List<Weather>?,

    @field:SerializedName("wind_deg")
    val wind_deg: Int?,

    @field:SerializedName("wind_gust")
    val wind_gust: Double?,

    @field:SerializedName("wind_speed")
    val wind_speed: Double?
)