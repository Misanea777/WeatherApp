package com.endava.internship.mobile.weatherapp.utils

import com.endava.internship.mobile.weatherapp.R
import java.util.*

fun itemsString(items: List<String>) = items.joinToString(separator = ",")

fun weatherIDToResourceID(id: Int): Int {
    return when(id) {
        in 200..299 -> R.drawable.ic_thunder
        in 300..399 -> R.drawable.ic_shower
        in 500..699 -> R.drawable.ic_rain
        800 -> R.drawable.ic_sunny
        in 801..Int.MAX_VALUE -> R.drawable.ic_cloudy
        else -> R.drawable.ic_sunny
    }
}

fun String.capitalize() : String {
    return this
        .lowercase(Locale.getDefault())
        .replaceFirstChar { it.uppercase()}
}