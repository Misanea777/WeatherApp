package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.os.Build
import androidx.annotation.RequiresApi
import com.endava.internship.mobile.weatherapp.utils.Constants.DEGREE_CHAR
import com.endava.internship.mobile.weatherapp.utils.capitalize
import com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

class DayForecast(dateTime: Long, private val temp: Double, private val weatherID: Int) {
    @RequiresApi(Build.VERSION_CODES.O)
    private val time = LocalDateTime.ofEpochSecond(dateTime, 0, ZoneOffset.UTC)

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayOfWeekPretty() = time.dayOfWeek.name.capitalize()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatePretty() = "${Month.from(time).name} ${time.dayOfMonth}".capitalize()
    fun getTempPretty() = "${temp.toInt()}${DEGREE_CHAR}"
    fun getImageResource() = weatherIDToResourceID(weatherID)
}