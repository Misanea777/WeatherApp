package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Weather(
    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("icon")
    val icon: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("main")
    val main: String?
)