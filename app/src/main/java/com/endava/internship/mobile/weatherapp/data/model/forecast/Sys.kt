package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Sys(
    @field:SerializedName("pod")
    val pod: String?
)