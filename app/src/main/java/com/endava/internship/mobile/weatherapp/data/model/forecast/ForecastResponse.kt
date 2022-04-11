package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @field:SerializedName("current")
    val current: Current?,

    @field:SerializedName("daily")
    val daily: List<Daily>?,

    @field:SerializedName("hourly")
    val hourly: List<Hourly>?,

    @field:SerializedName("lat")
    val lat: Double?,

    @field:SerializedName("lon")
    val lon: Double?,

    @field:SerializedName("minutely")
    val minutely: List<Minutely>?,

    @field:SerializedName("timezone")
    val timezone: String?,

    @field:SerializedName("timezone_offset")
    val timezone_offset: Int?
)