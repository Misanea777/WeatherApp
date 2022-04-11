package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.databinding.ForecastItemBinding
import com.endava.internship.mobile.weatherapp.utils.capitalize
import com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset


class DayForecastViewHolder(private val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Daily) {
        val time = LocalDateTime.ofEpochSecond(model.dt!!, 0, ZoneOffset.UTC)
        binding.dayOfWeek.text = time.dayOfWeek.name.capitalize()
        binding.date.text = "${Month.from(time).name} ${time.dayOfMonth}".capitalize()
        binding.temp.text = "${model.temp?.max?.toInt().toString()}${0x00B0.toChar()}"
        binding.imageView.setImageResource(weatherIDToResourceID(model.weather?.get(0)?.id!!))
    }
}