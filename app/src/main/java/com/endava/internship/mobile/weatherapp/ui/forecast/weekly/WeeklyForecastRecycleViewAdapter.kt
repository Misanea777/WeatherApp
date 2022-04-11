package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.databinding.ForecastItemBinding

class WeeklyForecastRecycleViewAdapter(var dataSet: Array<Daily>) : RecyclerView.Adapter<DayForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val binding = ForecastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(dataSet: Array<Daily>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}