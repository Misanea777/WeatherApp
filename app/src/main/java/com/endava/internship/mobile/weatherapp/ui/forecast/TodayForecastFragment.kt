package com.endava.internship.mobile.weatherapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter.HourlyForecastAdapter
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels.TodayForecastViewModel
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.databinding.FragmentTodayForecastBinding

class TodayForecastFragment : Fragment() {

    private lateinit var binding: FragmentTodayForecastBinding

    private val sharedTodayViewModel: TodayForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedTodayViewModel

            todayFragment = this@TodayForecastFragment
        }

        sharedTodayViewModel.apply {
            getCurrentWeather()
            getHourlyWeather()
            hourlyData.observe(viewLifecycleOwner) {
                initHourlyAdapter(hourlyData.value)
            }
        }
    }

    private fun initHourlyAdapter(hourlyData: List<Hourly>?) {
        binding.weatherForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyForecastAdapter(hourlyData, context)
        }
    }
}