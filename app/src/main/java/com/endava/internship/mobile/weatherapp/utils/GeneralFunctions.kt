package com.endava.internship.mobile.weatherapp.utils

import android.text.TextUtils
import android.util.Patterns
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.utils.Constants
import java.util.*
import java.util.regex.Pattern

fun itemsString(items: List<String>) = items.joinToString(separator = ",")

fun weatherIDToResourceID(id: Int): Int {
    return when(id) {
        in Constants.WeatherConditionCodes.Thunderstorm -> R.drawable.ic_thunder
        in Constants.WeatherConditionCodes.Drizzle -> R.drawable.ic_shower
        in Constants.WeatherConditionCodes.Rain -> R.drawable.ic_rain
        Constants.WeatherConditionCodes.Clear -> R.drawable.ic_sunny
        in Constants.WeatherConditionCodes.Clouds -> R.drawable.ic_cloudy
        else -> R.drawable.ic_sunny
    }
}

fun String.capitalize() : String {
    return this
        .lowercase(Locale.getDefault())
        .replaceFirstChar { it.uppercase()}
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = Pattern.matches("^.{6,}$", this)