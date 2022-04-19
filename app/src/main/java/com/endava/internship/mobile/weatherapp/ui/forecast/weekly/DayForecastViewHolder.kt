package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly.DayForecast
import com.endava.internship.mobile.weatherapp.databinding.ItemDailyForecastBinding

class DayForecastViewHolder(private val binding: ItemDailyForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(model: DayForecast) {
        with(binding) {
            dayOfWeek.text = model.getDayOfWeekPretty()
            date.text = model.getDatePretty()
            temp.text = model.getTempPretty()
            imageView.setImageResource(model.getImageResource())
        }
    }
}