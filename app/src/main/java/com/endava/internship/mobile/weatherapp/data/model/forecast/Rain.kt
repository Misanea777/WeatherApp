package com.endava.internship.mobile.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Rain(
    @field:SerializedName("3h")
    val jsonMember3h: Double?
)