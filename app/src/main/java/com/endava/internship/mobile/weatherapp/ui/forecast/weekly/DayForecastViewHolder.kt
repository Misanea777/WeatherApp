package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly.DayForecast
import com.endava.internship.mobile.weatherapp.databinding.ItemDailyForecastBinding

class DayForecastViewHolder(private val binding: ItemDailyForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: DayForecast) {
        binding.dayOfWeek.text = model.getDayOfWeekPretty()
        binding.date.text = model.getDatePretty()
        binding.temp.text = model.getTempPretty()
        binding.imageView.setImageResource(model.getImageResource())
    }
}