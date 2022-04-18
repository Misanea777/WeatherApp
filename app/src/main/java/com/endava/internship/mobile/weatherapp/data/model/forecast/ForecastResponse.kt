package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val current: Current?,
    val daily: List<Daily>?,
    val hourly: List<Hourly>?,
    val lat: Double?,
    val lon: Double?,
    val minutely: List<Minutely>?,
    val timezone: String?,

    @field:SerializedName("timezone_offset")
    val timezoneOffset: Int?
)