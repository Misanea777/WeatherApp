package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Minutely(
    @field:SerializedName("dt")
    val dt: Long?,

    @field:SerializedName("precipitation")
    val precipitation: Int?
)