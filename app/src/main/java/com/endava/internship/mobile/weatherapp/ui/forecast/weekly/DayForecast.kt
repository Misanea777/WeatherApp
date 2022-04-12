package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import com.endava.internship.mobile.weatherapp.utils.Constants.DEGREE_CHAR
import com.endava.internship.mobile.weatherapp.utils.capitalize
import com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

class DayForecast(dt: Long, private val temp: Double, private val weatherID: Int) {
    private val time = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC)

    fun getDayOfWeekPretty() = time.dayOfWeek.name.capitalize()
    fun getDatePretty() = "${Month.from(time).name} ${time.dayOfMonth}".capitalize()
    fun getTempPretty() = "${temp.toInt()}${DEGREE_CHAR}"
    fun getImageResource() = weatherIDToResourceID(weatherID)
}