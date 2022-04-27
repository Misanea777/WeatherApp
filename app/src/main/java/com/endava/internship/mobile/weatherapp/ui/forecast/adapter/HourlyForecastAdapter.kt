package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.utils.Constants

class HourlyForecastAdapter(
    private val hourlyWeather: List<Hourly>?,
    private val context: Context
) : RecyclerView.Adapter<HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_hourly_forecast, parent, false
            ), context
        )
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) =
        holder.onBind(hourlyWeather?.get(position))

    override fun getItemCount(): Int = Constants.MAX_DAILY_FORECAST_DAYS
}