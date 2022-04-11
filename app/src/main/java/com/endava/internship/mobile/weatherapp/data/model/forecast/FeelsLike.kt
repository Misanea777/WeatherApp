package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @field:SerializedName("day")
    val day: Double?,

    @field:SerializedName("eve")
    val eve: Double?,

    @field:SerializedName("morn")
    val morn: Double?,

    @field:SerializedName("night")
    val night: Double?
)