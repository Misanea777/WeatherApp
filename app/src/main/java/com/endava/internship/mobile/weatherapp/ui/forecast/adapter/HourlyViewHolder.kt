package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.databinding.ItemHourlyForecastBinding
import com.endava.internship.mobile.weatherapp.utils.Constants
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import kotlin.math.roundToInt

class HourlyViewHolder(
    private val binding: ItemHourlyForecastBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(hourlyWeather: Hourly?) {

        val id = hourlyWeather?.weather?.map { it.id }?.get(0)
        val dt = DateTimeZone.getDefault()
        val newTime = DateTime(hourlyWeather?.dt?.times(1000), dt).toString(Constants.TIME_PATTERN)

        binding.cloudyImage.setImageResource(weatherIDToResourceID(id))
        binding.tempValueList.text =
            context.getString(R.string.temp_value, hourlyWeather?.temp?.roundToInt().toString())
        binding.hourTextList.text = newTime
    }
}