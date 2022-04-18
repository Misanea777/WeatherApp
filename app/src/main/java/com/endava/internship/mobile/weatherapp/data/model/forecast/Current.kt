package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Current(
    val clouds: Int?,

    @field:SerializedName("dew_point")
    val dewPoint: Double?,

    val dt: Long?,

    @field:SerializedName("feels_like")
    val feelsLike: Double?,

    val humidity: Int?,

    val pressure: Int?,

    val sunrise: Int?,

    val sunset: Int?,

    val temp: Double?,

    val uvi: Double?,

    val visibility: Int?,

    val weather: List<Weather>?,

    @field:SerializedName("wind_deg")
    val windDeg: Int?,

    @field:SerializedName("wind_gust")
    val windGust: Int?,

    @field:SerializedName("wind_speed")
    val windSpeed: Double?
)