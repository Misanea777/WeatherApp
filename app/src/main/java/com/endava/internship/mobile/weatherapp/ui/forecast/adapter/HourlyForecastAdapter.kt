package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.utils.Constants
import kotlin.math.roundToInt

class HourlyForecastAdapter(
    private val hourlyWeather: List<Hourly>
) : RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastHolder>() {

    class HourlyForecastHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cloudyImage: ImageView = view.findViewById(R.id.cloudy_image)
        val tempValue: TextView = view.findViewById(R.id.temp_value_list)
        val hourText: TextView = view.findViewById(R.id.hour_text_list)

        fun bindHourlyDataView(hourlyWeather: Hourly) = hourlyWeather.weather?.map { weatherIDToResourceID(it.id!!) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_forecast, parent, false)

        return HourlyForecastHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HourlyForecastHolder, position: Int) {

        val item = hourlyWeather[position]
        holder.tempValue.text = item.temp?.roundToInt().toString() + "°"
        holder.hourText.text = "12:00"
        holder.cloudyImage.setImageResource(holder.bindHourlyDataView(item)?.get(0)!!)
    }

    override fun getItemCount(): Int = Constants.MAX_DAILY_FORECAST_DAYS
}