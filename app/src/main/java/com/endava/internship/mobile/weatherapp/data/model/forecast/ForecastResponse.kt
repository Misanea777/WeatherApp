package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @field:SerializedName("city")
    val city: City?,

    @field:SerializedName("cnt")
    val cnt: Int?,

    @field:SerializedName("cod")
    val cod: String?,

    @field:SerializedName("message")
    val message: Int?,

    @field:SerializedName("list")
    val list: List<ListItem>?
)