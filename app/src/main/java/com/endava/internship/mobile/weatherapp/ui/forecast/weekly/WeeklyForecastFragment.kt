package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly.DayForecast
import com.endava.internship.mobile.weatherapp.databinding.FragmentWeeklyForecastBinding
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeeklyForecastBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: WeeklyForecastRecycleViewAdapter

    private val viewModel by viewModels<WeeklyForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycleView()

        viewModel.getDailyForecast()

        viewModel.forecast.observe(viewLifecycleOwner) { forecast ->
            binding.progressBar.isVisible = forecast is Resource.Loading
            when (forecast) {
                is Resource.Success ->

                    viewAdapter.updateDataSet(forecast.value.map {
                        DayForecast(it.dt!!, it.temp?.max!!, it.weather?.get(0)?.id!!)
                    }.take(Constants.MAX_DAILY_FORECAST_DAYS+1).drop(1).toTypedArray())
                is Resource.Failure -> Toast.makeText(
                    this.context,
                    forecast.errorBody.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun initRecycleView() {
        viewAdapter = WeeklyForecastRecycleViewAdapter(emptyArray())
        recyclerView = binding.forecastRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter
        }
    }
}